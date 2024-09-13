package com.bracket.tetring.domain.block.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.block.dto.response.GetGameBlocksResponseDto;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.block.repository.StoreBlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.repository.GameRelicRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.dto.response.PurchaseStoreBlockResponseDto;
import com.bracket.tetring.global.handler.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bracket.tetring.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;
    private final StoreBlockRepository storeBlockRepository;
    private final GameRelicRepository gameRelicRepository;

    @Transactional
    public ResponseEntity<?> getGameBlocks(Game game) {
        List<Block> gameBlocks = blockRepository.findBlocksInGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(new GetGameBlocksResponseDto(gameBlocks));
    }

    @Transactional
    public ResponseEntity<?> changeBlockShape(Game game, Long blockId, String shape) {
        Block block = blockRepository.findById(blockId).orElseThrow(() -> new CustomValidationException(BLOCK_NOT_FOUND));
        if(block.getGame() != game)
            throw new CustomValidationException(INVALID_BLOCK_ID);

        block.setShape(shape);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<?> purchaseBlock(Game game, int slotNumber, Store store) {
        //블록 구매하면, 상점에 있는 블록을 없애고, 유저 블록에 추가
        StoreBlock storeBlock = storeBlockRepository.findStoreBlockByGameAndSlotNumber(game, slotNumber).orElseThrow(() -> new CustomValidationException(STORE_BLOCK_NOT_FOUND));
        int money = store.getMoney();
        if(money >= storeBlock.getPrice()) {
            //블록을 살 수 있는 경우 -> 돈 줄이고, 상점 블록 없애고, 게임 블록 추가
            boolean can_buy = true;

            money -= storeBlock.getPrice();
            store.setMoney(money);

            Block block = new Block(storeBlock.getColor(), storeBlock.getShape(), game);
            blockRepository.save(block);
            storeBlockRepository.delete(storeBlock);

            gameRelicRepository.findByGameAndRelicNumber(game, 24).ifPresent(eaterBlock -> eaterBlock.setRate(eaterBlock.getRate() + 0.1));

            return ResponseEntity.status(HttpStatus.OK).body(new PurchaseStoreBlockResponseDto(can_buy, money, block));
        }
        else {
            //블록을 살 수 없는 경우 -> 변화 X
            boolean can_buy = false;
            return ResponseEntity.status(HttpStatus.OK).body(new PurchaseStoreBlockResponseDto(can_buy, money, null));
        }
    }
}
