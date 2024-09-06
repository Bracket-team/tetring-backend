package com.bracket.tetring.domain.game.service;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.dto.GetGameDto;
import com.bracket.tetring.domain.game.dto.response.GetCheckPlayingResponseDto;
import com.bracket.tetring.domain.game.dto.response.GetPlayGameResponseDto;
import com.bracket.tetring.domain.game.repository.GameRepository;
import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.repository.PlayerRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
import com.bracket.tetring.global.util.GameSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@AllArgsConstructor
public class GameService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GetCheckPlayingResponseDto checkPlayingGame(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new CustomValidationException(Collections.singletonList("플레이어를 찾을 수 없습니다.")));
        return new GetCheckPlayingResponseDto(gameRepository.existsByPlayerAndIsPlayingTrue(player));
    }

//    @Transactional
//    public GetPlayGameResponseDto playGame(Long playerId) {
//        Player player = playerRepository.findById(playerId).orElseThrow(() -> new CustomValidationException(Collections.singletonList("플레이어를 찾을 수 없습니다.")));
//        /*게임, 스코어 점수, 게임 블록, 게임 유물, 상점, 라운드 목표, 상점 블록, 상점 유물*/
//        if(gameRepository.existsByPlayerAndIsPlayingTrue(player)) {
//            /*플레이할 게임이 존재할 경우 -> 기존에 게임에 대한 데이터 수집*/
//            Game game = gameRepository.findByPlayerAndIsPlayingTrue(player).orElseThrow(() -> new CustomValidationException(Collections.singletonList("게임을 찾지 못했습니다.")));
//            int roundGoal = GameSettings.getRoundGoal(game.getRoundNumber());
//
//
//        }
//    }
}
