package com.bracket.tetring.domain.game.controller;

import com.bracket.tetring.domain.game.service.GameService;
import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/games")
public class GameController {
    private final PlayerService playerService;
    private final GameService gameService;

    @GetMapping("/check")
    public ResponseEntity<?> getPlayingGame() {
        return gameService.checkPlayingGame();
    }
}
