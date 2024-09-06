package com.bracket.tetring.domain.block.repository;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
    List<Block> findByGame(Game game);
}
