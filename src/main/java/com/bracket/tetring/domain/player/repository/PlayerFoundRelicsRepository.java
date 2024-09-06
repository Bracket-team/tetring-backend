package com.bracket.tetring.domain.player.repository;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.domain.PlayerFoundRelics;
import com.bracket.tetring.domain.player.domain.PlayerFoundRelicsId;
import com.bracket.tetring.domain.relic.domain.Relic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerFoundRelicsRepository extends JpaRepository<PlayerFoundRelics, PlayerFoundRelicsId> {
    @Query("SELECT pfr.relic FROM PlayerFoundRelics pfr WHERE pfr.player = :player")
    List<Relic> findRelicsByPlayer(@Param("player") Player player);
}
