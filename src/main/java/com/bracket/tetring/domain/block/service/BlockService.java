package com.bracket.tetring.domain.block.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.repository.GameRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final GameRepository gameRepository;
    private final BlockRepository blockRepository;

    @Transactional
    public ResponseEntity<?> getGameBlocks(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new CustomValidationException(Collections.singletonList("유효하지 않은 게임아이디입니다.")));
        List<Block> gameBlocks = blockRepository.findByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(gameBlocks);
    }


}
