package com.bracket.tetring.domain.store.service;

import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.block.repository.StoreBlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.dto.response.GetMoneyResponseDto;
import com.bracket.tetring.domain.store.dto.response.GetStoreInfoResponseDto;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import com.bracket.tetring.domain.store.repository.StoreRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
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

    @Transactional(readOnly = true)
    public ResponseEntity<?> getStoreDetails(Game game) {
        Store store = storeRepository.findByGame(game).orElseThrow(() -> new CustomValidationException(STORE_NOT_FOUND));
        int moneyLevelUpPrice = getMoneyLevelUpPrice(store.getMoneyLevel());
        List<StoreBlock> blocks = storeBlockRepository.findStoreBlocksByGame(game);
        List<StoreRelic> relics = storeRelicRepository.findByStore(store);
        return ResponseEntity.status(HttpStatus.OK).body(new GetStoreInfoResponseDto(store, moneyLevelUpPrice, blocks, relics));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getGameMoney(Store store) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetMoneyResponseDto(store));
    }
}
