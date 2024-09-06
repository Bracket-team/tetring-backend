package com.bracket.tetring.domain.game.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.block.repository.StoreBlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.game.dto.GetGameDto;
import com.bracket.tetring.domain.game.dto.response.GetCheckPlayingResponseDto;
import com.bracket.tetring.domain.game.dto.response.GetPlayGameResponseDto;
import com.bracket.tetring.domain.game.repository.GameRelicRepository;
import com.bracket.tetring.domain.game.repository.GameRepository;
import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.repository.PlayerRepository;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import com.bracket.tetring.domain.store.repository.StoreRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
import com.bracket.tetring.global.util.GameSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final BlockRepository blockRepository;
    private final GameRelicRepository gameRelicRepository;
    private final StoreRepository storeRepository;
    private final StoreBlockRepository storeBlockRepository;
    private final StoreRelicRepository storeRelicRepository;

    @Transactional(readOnly = true)
    public GetCheckPlayingResponseDto checkPlayingGame(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new CustomValidationException(Collections.singletonList("플레이어를 찾을 수 없습니다.")));
        return new GetCheckPlayingResponseDto(gameRepository.existsByPlayerAndIsPlayingTrue(player));
    }

//    @Transactional
//    public GetPlayGameResponseDto playGame(Long playerId) {
//        Player player = playerRepository.findById(playerId).orElseThrow(() -> new CustomValidationException(Collections.singletonList("플레이어를 찾을 수 없습니다.")));
//        /*게임, 스코어 점수, 게임 블록, 게임 유물, 상점, 머니 가격, 상점 블록, 상점 유물*/
//        if(gameRepository.existsByPlayerAndIsPlayingTrue(player)) {
//            /*플레이할 게임이 존재할 경우 -> 기존에 게임에 대한 데이터 수집*/
//            Game game = gameRepository.findByPlayerAndIsPlayingTrue(player).orElseThrow(() -> new CustomValidationException(Collections.singletonList("게임을 찾지 못했습니다.")));
//            int roundGoal = GameSettings.getRoundGoal(game.getRoundNumber());
//            List<Block> gameBlocks = blockRepository.findByGame(game);
//            List<GameRelic> gameRelics = gameRelicRepository.findByGame(game);
//            Store store = storeRepository.findByGame(game).orElseThrow(() -> new CustomValidationException(Collections.singletonList("상점을 찾을 수 없습니다.")));
//            int moneyLevelUpPrice = GameSettings.getMoneyLevelUpPrice(store.getMoneyLevel());
//
//        } else {
//            /*게임이 존재하지 않을 경우 게임 초기화*/
//        }
//    }
}
