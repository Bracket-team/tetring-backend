package com.bracket.tetring.domain.store.repository;

import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.domain.StoreRelicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRelicRepository extends JpaRepository<StoreRelic, StoreRelicId> {
    List<StoreRelic> findByStore(Store store);
}
