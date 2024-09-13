package com.bracket.tetring.domain.game.service;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.block.repository.BlockRepository;
import com.bracket.tetring.domain.block.repository.StoreBlockRepository;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.dto.response.*;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.repository.GameRelicRepository;
import com.bracket.tetring.domain.game.repository.GameRepository;
import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.repository.StoreRelicRepository;
import com.bracket.tetring.domain.store.repository.StoreRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
import com.bracket.tetring.global.util.GameSettings;
import com.bracket.tetring.global.util.RelicSelector;
import com.bracket.tetring.global.util.RerollPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.bracket.tetring.global.error.ErrorCode.*;
import static com.bracket.tetring.global.util.GameSettings.*;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final BlockRepository blockRepository;
    private final GameRelicRepository gameRelicRepository;
    private final StoreRepository storeRepository;
    private final StoreBlockRepository storeBlockRepository;
    private final StoreRelicRepository storeRelicRepository;

    private final RelicSelector relicSelector;
    private final RerollPriceCalculator rerollPriceCalculator;

    @Transactional(readOnly = true)
    public ResponseEntity<?> checkPlayingGame(Player player) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetCheckPlayingResponseDto(gameRepository.existsByPlayerAndIsPlayingTrue(player)));
    }

    @Transactional
    public ResponseEntity<?> playGame(Player player) {
        /*게임, 스코어 점수, 게임 블록, 게임 유물, 상점, 머니 가격, 상점 블록, 상점 유물*/
        if(gameRepository.existsByPlayerAndIsPlayingTrue(player)) {
            /*플레이할 게임이 존재할 경우 -> 기존에 게임에 대한 데이터 수집*/
            Game game = gameRepository.findByPlayerAndIsPlayingTrue(player).orElseThrow(() -> new CustomValidationException(GAME_NOT_FOUND));
            int roundGoal = getRoundGoal(game.getRoundNumber());
            List<Block> gameBlocks = blockRepository.findBlocksInGame(game);
            List<GameRelic> gameRelics = gameRelicRepository.findByGame(game);
            Store store = storeRepository.findByGame(game).orElseThrow(() -> new CustomValidationException(STORE_NOT_FOUND));
            int moneyLevelUpPrice = GameSettings.getMoneyLevelUpPrice(store.getMoneyLevel());
            List<StoreBlock> storeBlocks = storeBlockRepository.findStoreBlocksByGame(game);
            List<StoreRelic> storeRelics = storeRelicRepository.findByStore(store);
            return ResponseEntity.status(HttpStatus.OK).body(new GetPlayGameResponseDto(game, roundGoal, gameBlocks, gameRelics, store, moneyLevelUpPrice, storeBlocks, storeRelics));
        }
        /*게임이 존재하지 않을 경우 초기화*/
        Game game = new Game(1, false, player, LocalDateTime.now(), 0L, true);
        gameRepository.save(game);
        int roundGoal = getRoundGoal(game.getRoundNumber());
        List<Block> gameBlocks = initialBlocks(game);
        List<GameRelic> gameRelics = new ArrayList<>();
        Store store = new Store(game, rerollPriceCalculator.getInitPrice(game), INITIAL_MONEY, 1);
        storeRepository.save(store);
        int moneyLevelUpPrice = getMoneyLevelUpPrice(store.getMoneyLevel());
        List<StoreBlock> storeBlocks = initialStoreBlocks(game);
        List<StoreRelic> storeRelics = relicSelector.getRandomRelics(store, gameRelics);
        return ResponseEntity.status(HttpStatus.OK).body(new GetPlayGameResponseDto(game, roundGoal, gameBlocks, gameRelics, store, moneyLevelUpPrice, storeBlocks, storeRelics));
    }

    @Transactional
    public ResponseEntity<?> getGameDetailsForNewRound(Game game) {
        if(!game.getIsStore()) {
            throw new CustomValidationException(ALREADY_IN_ROUND);
        }
        game.setIsStore(false);

        int roundGoal = getRoundGoal(game.getRoundNumber());
        List<Block> blocks = blockRepository.findBlocksInGame(game);
        List<GameRelic> relics = gameRelicRepository.findByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(new GetStartRoundResponseDto(game, roundGoal, blocks, relics));
    }

    @Transactional
    public ResponseEntity<?> getGameDetailsForEndRound(Game game, Store store, Long score) {
        if(game.getIsStore()) {
            throw new CustomValidationException(ALREADY_IN_STORE);
        }
        game.setIsStore(true);

        //최고 기록 확인
        if(game.getBestScore() < score)
            game.setBestScore(score);

        //리롤 가격 초기화
        store.setRerollPrice(rerollPriceCalculator.getInitPrice(game));

        int roundGoal = getRoundGoal(game.getRoundNumber());
        boolean whiteSkull = gameRelicRepository.findByGameAndRelicNumber(game, 19).isPresent();//흰색 해골 블록
        if(score >= roundGoal || (whiteSkull && score >= roundGoal * 0.25)) {
            if(whiteSkull && score < roundGoal) {
                gameRelicRepository.deleteByGameAndRelic_RelicNumber(game, 19);
            }
            // 이겼을 경우
            boolean isWin = true;
            int nextRoundNumber = game.getRoundNumber() + 1;
            game.setRoundNumber(nextRoundNumber);
            int nextRoundGoal = getRoundGoal(nextRoundNumber);
            int nextMoney = store.getMoney() + getMoney(store.getMoneyLevel());

            boolean overworkBlock = gameRelicRepository.findByGameAndRelicNumber(game, 7).isPresent();//초과 근무 블록
            if(overworkBlock) {
                nextMoney += (int) ((score - roundGoal) / 1000);
            }
            boolean investmentBlock = gameRelicRepository.findByGameAndRelicNumber(game, 20).isPresent();//투자 블록
            if(investmentBlock) {
                nextMoney += Math.max(10, (int)(nextMoney / 4));
            }

            store.setMoney(nextMoney);
            return ResponseEntity.status(HttpStatus.OK).body(new UpdateEndRoundResponseDto(isWin, nextRoundNumber, nextRoundGoal, nextMoney));
        }
        else {
            // 졌을 경우
            boolean isWin = false;
            int nextRoundNumber = game.getRoundNumber();
            int nextRoundGoal = getRoundGoal(nextRoundNumber);
            int nextMoney = store.getMoney();
            return ResponseEntity.status(HttpStatus.OK).body(new UpdateEndRoundResponseDto(isWin, nextRoundNumber, nextRoundGoal, nextMoney));
        }
    }

    @Transactional
    public ResponseEntity<?> getGameResult(Game game, Store store) {
        if(!game.getIsPlaying()) {
            throw new CustomValidationException(ALREADY_END_GAME);
        }
        game.setIsPlaying(false);

        int roundNumber = game.getRoundNumber();
        Long bestScore = game.getBestScore();
        int count = blockRepository.findBlocksInGame(game).size();
        List<GameRelic> relics = gameRelicRepository.findByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(new GetGameResultResponseDto(roundNumber, bestScore, count, store, relics));
    }

    private List<Block> initialBlocks(Game game) {
        List<Block> gameBlocks = new ArrayList<>();
        for (GameSettings.Block value : BLOCKS) {
            for (int j = 0; j < BLOCK_COUNT; j++) {
                Block block = new Block(value.color(), value.shape(), game);
                blockRepository.save(block);
                gameBlocks.add(block);
            }
        }
        return gameBlocks;
    }

    private List<StoreBlock> initialStoreBlocks(Game game) {
        List<StoreBlock> storeBlocks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int number = random.nextInt(7);
            StoreBlock storeBlock = new StoreBlock(BLOCKS[number].color(), BLOCKS[number].shape(), game, i + 1, BLOCK_PRICE);
            storeBlockRepository.save(storeBlock);
            storeBlocks.add(storeBlock);
        }
        return storeBlocks;
    }
}
