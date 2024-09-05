package com.bracket.tetring.domain.store.repository;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
