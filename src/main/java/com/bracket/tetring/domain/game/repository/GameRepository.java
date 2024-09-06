package com.bracket.tetring.domain.game.repository;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByPlayerAndIsPlayingTrue(Player player);

    Optional<Game> findByPlayerAndIsPlayingTrue(Player player);
}
