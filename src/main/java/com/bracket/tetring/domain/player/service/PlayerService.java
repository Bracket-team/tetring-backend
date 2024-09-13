package com.bracket.tetring.domain.player.service;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.dto.response.GetPlayerFoundRelicsResponseDto;
import com.bracket.tetring.domain.player.repository.PlayerFoundRelicsRepository;
import com.bracket.tetring.domain.player.repository.PlayerRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bracket.tetring.global.error.ErrorCode.PLAYER_NOT_FOUND;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerFoundRelicsRepository playerFoundRelicsRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllFoundRelics(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new CustomValidationException(PLAYER_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(new GetPlayerFoundRelicsResponseDto(playerFoundRelicsRepository.findRelicsByPlayer(player)));
    }
}
