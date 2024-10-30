package com.bracket.tetring.domain.game.repository;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.player.domain.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByPlayerAndIsPlayingTrue(Player player);

    Optional<Game> findByPlayerAndIsPlayingTrue(Player player);

    @Query("SELECT g FROM Game g GROUP BY g.player ORDER BY MAX(g.bestScore) DESC")
    List<Game> findTopPlayersByBestScore(Pageable pageable);
}
