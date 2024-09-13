package com.bracket.tetring.domain.store.service;

import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.block.repository.StoreBlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.service.GameService;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.dto.response.GetMoneyResponseDto;
import com.bracket.tetring.domain.store.dto.response.GetStoreInfoResponseDto;
import com.bracket.tetring.domain.store.dto.response.UpdateMoneyLevelUpResponseDto;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import com.bracket.tetring.domain.store.repository.StoreRepository;
import com.bracket.tetring.global.handler.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bracket.tetring.global.error.ErrorCode.STORE_NOT_FOUND;
import static com.bracket.tetring.global.util.GameSettings.getMoneyLevelUpPrice;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreBlockRepository storeBlockRepository;
    private final StoreRelicRepository storeRelicRepository;

    private final GameService gameService;

    @Transactional
    public Store findPlayingStore() {
        Game game = gameService.findPlayingGame();
        return storeRepository.findByGame(game).orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getStoreDetails() {
        Game game = gameService.findPlayingGame();
        Store store = findPlayingStore();
        int moneyLevelUpPrice = getMoneyLevelUpPrice(store.getMoneyLevel());
        List<StoreBlock> blocks = storeBlockRepository.findStoreBlocksByGame(game);
        List<StoreRelic> relics = storeRelicRepository.findByStore(store);
        return ResponseEntity.status(HttpStatus.OK).body(new GetStoreInfoResponseDto(store, moneyLevelUpPrice, blocks, relics));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getGameMoney() {
        Store store = findPlayingStore();
        return ResponseEntity.status(HttpStatus.OK).body(new GetMoneyResponseDto(store));
    }

    @Transactional
    public ResponseEntity<?> patchMoneyLevelUp() {
        Store store = findPlayingStore();
        int money = store.getMoney();
        int moneyLevel = store.getMoneyLevel();
        int levelUpPrice = getMoneyLevelUpPrice(moneyLevel);
        if(money >= levelUpPrice && moneyLevel < 5) {
            // 레벨 업을 할 수 있는 경우
            boolean can_buy = true;
            //보유 돈 감소
            money -= levelUpPrice;
            store.setMoney(money);
            //머니 레벨 업
            moneyLevel += 1;
            store.setMoneyLevel(moneyLevel);
            levelUpPrice = getMoneyLevelUpPrice(moneyLevel);
            return ResponseEntity.status(HttpStatus.OK).body(new UpdateMoneyLevelUpResponseDto(can_buy, levelUpPrice, money));
        }
        else {
            // 레벨 업을 할 수 없는 경우
            boolean can_buy = false;

            return ResponseEntity.status(HttpStatus.OK).body(new UpdateMoneyLevelUpResponseDto(can_buy, levelUpPrice, money));
        }
    }
}
