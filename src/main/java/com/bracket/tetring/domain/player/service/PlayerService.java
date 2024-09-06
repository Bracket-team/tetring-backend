package com.bracket.tetring.domain.player.service;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.repository.PlayerFoundRelicsRepository;
import com.bracket.tetring.domain.player.repository.PlayerRepository;
import com.bracket.tetring.domain.relic.domain.Relic;
import com.bracket.tetring.global.handler.CustomValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerFoundRelicsRepository playerFoundRelicsRepository;

    @Transactional
    public List<Relic> findAllFoundRelics(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new CustomValidationException(Collections.singletonList("플레이어를 찾을 수 없습니다.")));
        return playerFoundRelicsRepository.findRelicsByPlayer(player);
    }
}
