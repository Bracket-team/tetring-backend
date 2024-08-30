package com.bracket.tetring.domain.game.repository;

import com.bracket.tetring.domain.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
