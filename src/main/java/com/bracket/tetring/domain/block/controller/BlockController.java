package com.bracket.tetring.domain.block.controller;

import com.bracket.tetring.domain.block.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/games")
public class BlockController {
    private final BlockService blockService;

    @GetMapping("/blocks")
    public ResponseEntity<?> getPlayerBlocks() {
        return blockService.getGameBlocks();
    }
}
