package com.bracket.tetring.domain.player.controller;

import com.bracket.tetring.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/relics")
    public ResponseEntity<?> getPlayerPlayerFoundRelics() {
        return playerService.findAllFoundRelics();
    }
}
