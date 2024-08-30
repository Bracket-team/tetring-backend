package com.bracket.tetring.domain.block.repository;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.domain.BlockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, BlockId> {
}
