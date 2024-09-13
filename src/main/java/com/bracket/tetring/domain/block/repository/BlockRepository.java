package com.bracket.tetring.domain.block.repository;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {

    @Query("SELECT b FROM Block b WHERE b.game = :game AND TYPE(b) <> StoreBlock")
    List<Block> findBlocksNotInStore(@Param("game") Game game);
}
