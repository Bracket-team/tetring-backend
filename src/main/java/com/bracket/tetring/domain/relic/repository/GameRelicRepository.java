package com.bracket.tetring.domain.relic.repository;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.domain.GameRelicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRelicRepository extends JpaRepository<GameRelic, GameRelicId> {

    List<GameRelic> findByGame(Game game);
}
