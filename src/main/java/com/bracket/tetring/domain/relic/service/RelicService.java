package com.bracket.tetring.domain.relic.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.service.GameService;
import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.domain.PlayerFoundRelics;
import com.bracket.tetring.domain.player.repository.PlayerFoundRelicsRepository;
import com.bracket.tetring.domain.player.service.PlayerService;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.response.GetGameRelicsResponseDto;
import com.bracket.tetring.domain.relic.dto.response.GetRelicExistResponseDto;
import com.bracket.tetring.domain.relic.repository.GameRelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.dto.response.GetRerollRelicResponseDto;
import com.bracket.tetring.domain.store.dto.response.PurchaseStoreRelicResponseDto;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import com.bracket.tetring.domain.store.service.StoreService;
import com.bracket.tetring.global.error.ErrorCode;
import com.bracket.tetring.global.handler.CustomException;
import com.bracket.tetring.global.util.RelicSelector;
import com.bracket.tetring.global.util.RerollPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.bracket.tetring.global.error.ErrorCode.ARE_SLOTS_FULL;
import static com.bracket.tetring.global.error.ErrorCode.STORE_RELIC_NOT_FOUND;
import static com.bracket.tetring.global.util.GameSettings.BLOCKS;

@Service
@RequiredArgsConstructor
public class RelicService {
    private final PlayerService playerService;
    private final GameService gameService;
    private final StoreService storeService;

    private final GameRelicRepository gameRelicRepository;
    private final StoreRelicRepository storeRelicRepository;
    private final BlockRepository blockRepository;
    private final PlayerFoundRelicsRepository playerFoundRelicsRepository;

    private final RelicSelector relicSelector;
    private final RerollPriceCalculator rerollPriceCalculator;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getGameRelics() {
        Game game = gameService.findPlayingGame();
        List<GameRelic> relics = gameRelicRepository.findByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(new GetGameRelicsResponseDto(relics));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> hasRelic(int relicNumber) {
        Game game = gameService.findPlayingGame();
        boolean present = gameRelicRepository.findByGameAndRelicNumber(game, relicNumber).isPresent();
        return ResponseEntity.status(HttpStatus.OK).body(new GetRelicExistResponseDto(present));
    }

    @Transactional
    public ResponseEntity<?> throwRelic(int slotNumber) {
        Game game = gameService.findPlayingGame();
        GameRelic relic = gameRelicRepository.findByGameAndSlotNumber(game, slotNumber).orElseThrow(() -> new CustomException(ErrorCode.RELIC_NOT_FOUND));
        gameRelicRepository.delete(relic);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    public ResponseEntity<?> purchaseStoreRelic(int slotNumber) {
        Game game = gameService.findPlayingGame();
        Store store = storeService.findPlayingStore();
        StoreRelic storeRelic = storeRelicRepository.findByStoreAndSlotNumber(store, slotNumber).orElseThrow(() -> new CustomException(STORE_RELIC_NOT_FOUND));
        int money = store.getMoney();
        if(money >= storeRelic.getPrice()) {
            // 살 수 있는 경우
            boolean canBuy = true;

            money -= storeRelic.getPrice();
            store.setMoney(money);

            Integer number = findLowestUnusedSlotNumber(game).orElseThrow(() -> new CustomException(ARE_SLOTS_FULL));

            GameRelic relic = new GameRelic(game, storeRelic.getRelic(), storeRelic.getRelic().getRate(), number);
            gameRelicRepository.save(relic);
            storeRelicRepository.delete(storeRelic);

            //플레이어가 발견한 유물 저장
            savePlayerFoundRelic(relic);

            // 특정 유물 관련 세팅
            if(relic.getRelic().getRelicNumber() == 10) { //쿠폰 블록
                store.setUseCoupon(false);
            }
            List<Block> blocks = new ArrayList<>();
            if(relic.getRelic().getRelicNumber() == 5) { // 재활용 블록
                Random random = new Random();
                for(int i = 0; i < 3; i++) {
                    int randomIndex = random.nextInt(BLOCKS.length);
                    Block block = new Block(BLOCKS[randomIndex].color(), BLOCKS[randomIndex].shape(), game);
                    blockRepository.save(block);
                    blocks.add(block);
                }
            }
            if(relic.getRelic().getRelicNumber() == 18) { //수표 블록
                money *= 2;
                store.setMoney(money);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new PurchaseStoreRelicResponseDto(canBuy, money, relic, blocks));
        }
        else {
            // 살 수 없는 경우
            boolean canBuy = false;
            List<Block> blocks = new ArrayList<>();
            return ResponseEntity.status(HttpStatus.OK).body(new PurchaseStoreRelicResponseDto(canBuy, money, null, blocks));
        }
    }

    private void savePlayerFoundRelic(GameRelic relic) {
        Player player = playerService.findPlayer();
        playerFoundRelicsRepository.save(new PlayerFoundRelics(player, relic.getRelic()));
    }

    @Transactional
    public ResponseEntity<?> rerollRelic() {
        Game game = gameService.findPlayingGame();
        Store store = storeService.findPlayingStore();
        int money = store.getMoney();
        int rerollPrice = store.getRerollPrice();
        if (money >= rerollPrice) {
            // 리롤 가능한 경우
            boolean canReroll = true;
            //돈 삭감
            money -= rerollPrice;
            store.setMoney(money);
            //리롤 가격 조정
            rerollPrice = rerollPriceCalculator.getRerollPrice(game, store);
            store.setRerollPrice(rerollPrice);

            List<GameRelic> relics = gameRelicRepository.findByGame(store.getGame());
            List<StoreRelic> randomRelics = relicSelector.getRandomRelics(store, relics);

            return ResponseEntity.status(HttpStatus.OK).body(new GetRerollRelicResponseDto(canReroll, rerollPrice, money, randomRelics));
        }
        else {
            // 리롤 불가능한 경우
            boolean canReroll = false;
            return ResponseEntity.status(HttpStatus.OK).body(new GetRerollRelicResponseDto(canReroll, rerollPrice, money, null));
        }
    }

    private Optional<Integer> findLowestUnusedSlotNumber(Game game) {
        List<Integer> usedSlots = gameRelicRepository.findUsedSlotNumbersByGame(game);
        for (int i = 1; i <= 5; i++) {
            if (!usedSlots.contains(i)) {
                return Optional.of(i); // 사용되지 않은 가장 낮은 슬롯 번호 반환
            }
        }
        return Optional.empty(); // 모든 슬롯이 사용 중인 경우
    }
}
