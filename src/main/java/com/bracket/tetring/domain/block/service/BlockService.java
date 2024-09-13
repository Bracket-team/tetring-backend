package com.bracket.tetring.domain.block.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.dto.response.GetGameBlocksResponseDto;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.global.handler.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bracket.tetring.global.error.ErrorCode.BLOCK_NOT_FOUND;
import static com.bracket.tetring.global.error.ErrorCode.INVALID_BLOCK_ID;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;

    @Transactional
    public ResponseEntity<?> getGameBlocks(Game game) {
        List<Block> gameBlocks = blockRepository.findByGame(game);
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
}
