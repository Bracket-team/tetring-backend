package com.bracket.tetring.domain.relic.service;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.response.GetGameRelicsResponseDto;
import com.bracket.tetring.domain.relic.dto.response.GetRelicExistResponseDto;
import com.bracket.tetring.domain.relic.repository.GameRelicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelicService {
    private final GameRelicRepository gameRelicRepository;

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
}
