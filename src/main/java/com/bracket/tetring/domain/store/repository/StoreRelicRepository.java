package com.bracket.tetring.domain.store.repository;

import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.domain.StoreRelicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRelicRepository extends JpaRepository<StoreRelic, StoreRelicId> {
}
