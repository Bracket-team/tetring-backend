package com.bracket.tetring.domain.game.controller;

import com.bracket.tetring.domain.game.dto.request.UpdateEndRoundRequestDto;
import com.bracket.tetring.domain.game.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    @GetMapping("/check")
    public ResponseEntity<?> getPlayingGame() {
        return gameService.checkPlayingGame();
    }

    @GetMapping("/start")
    public ResponseEntity<?> getStartGame() {
        return gameService.playGame();
    }

    @GetMapping("/round/start")
    public ResponseEntity<?> getRoundStart() {
        return gameService.getGameDetailsForNewRound();
    }

    @PatchMapping("/round/end")
    public ResponseEntity<?> getRoundEnd(@Valid @RequestBody UpdateEndRoundRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // 에러 메시지 추출
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            // 에러 응답을 생성하여 반환
            return ResponseEntity.badRequest().body(errorMessages);
        }
        else {
            return gameService.getGameDetailsForEndRound(requestDto.getScore());
        }
    }
}
