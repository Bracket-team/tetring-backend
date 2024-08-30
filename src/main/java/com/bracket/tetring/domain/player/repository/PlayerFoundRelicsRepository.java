package com.bracket.tetring.domain.player.repository;

import com.bracket.tetring.domain.player.domain.PlayerFoundRelics;
import com.bracket.tetring.domain.player.domain.PlayerFoundRelicsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerFoundRelicsRepository extends JpaRepository<PlayerFoundRelics, PlayerFoundRelicsId> {
}
