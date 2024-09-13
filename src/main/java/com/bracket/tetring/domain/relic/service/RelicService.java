package com.bracket.tetring.domain.relic.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.response.GetGameRelicsResponseDto;
import com.bracket.tetring.domain.relic.dto.response.GetRelicExistResponseDto;
import com.bracket.tetring.domain.relic.repository.GameRelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.dto.response.GetRerollRelicResponseDto;
import com.bracket.tetring.domain.store.dto.response.PurchaseStoreRelicResponseDto;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
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
    private final GameRelicRepository gameRelicRepository;
    private final StoreRelicRepository storeRelicRepository;
    private final BlockRepository blockRepository;

    private final RelicSelector relicSelector;
    private final RerollPriceCalculator rerollPriceCalculator;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getGameRelics(Game game) {
        List<GameRelic> relics = gameRelicRepository.findByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(new GetGameRelicsResponseDto(relics));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> hasRelic(Game game, int relicNumber) {
        boolean present = gameRelicRepository.findByGameAndRelicNumber(game, relicNumber).isPresent();
        return ResponseEntity.status(HttpStatus.OK).body(new GetRelicExistResponseDto(present));
    }

    @Transactional
    public ResponseEntity<?> throwRelic(Game game, int slotNumber) {
        GameRelic relic = gameRelicRepository.findByGameAndSlotNumber(game, slotNumber).orElseThrow(() -> new CustomException(ErrorCode.RELIC_NOT_FOUND));
        gameRelicRepository.delete(relic);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    public ResponseEntity<?> purchaseStoreRelic(Game game, int slotNumber, Store store) {
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

    @Transactional
    public ResponseEntity<?> rerollRelic(Game game, Store store) {
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

            //상점에 있는 유물들 다 버리고 새로 뽑아야 함
            storeRelicRepository.deleteByStore(store);
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
