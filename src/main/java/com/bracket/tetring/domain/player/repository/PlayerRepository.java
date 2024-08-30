package com.bracket.tetring.domain.player.repository;

import com.bracket.tetring.domain.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
