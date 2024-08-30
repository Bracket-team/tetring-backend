package com.bracket.tetring.domain.game.repository;

import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.game.domain.GameRelicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRelicRepository extends JpaRepository<GameRelic, GameRelicId> {
}
