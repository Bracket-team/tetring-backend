package com.bracket.tetring.domain.store.repository;

import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.domain.StoreRelicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRelicRepository extends JpaRepository<StoreRelic, StoreRelicId> {
    List<StoreRelic> findByStore(Store store);

    @Query("SELECT sr FROM StoreRelic sr WHERE sr.store = :store AND sr.slotNumber = :slotNumber")
    Optional<StoreRelic> findByStoreAndSlotNumber(@Param("store") Store store, @Param("slotNumber") int slotNumber);

    void deleteByStore(Store store);
}
