package com.bracket.tetring.domain.block.repository;

import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreBlockRepository extends JpaRepository<StoreBlock, Long> {
    @Query("SELECT sb FROM StoreBlock sb WHERE sb.game = :game")
    List<StoreBlock> findStoreBlocksByGame(@Param("game") Game game);
}
