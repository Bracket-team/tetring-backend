package com.bracket.tetring.domain.relic.repository;

import com.bracket.tetring.domain.relic.domain.Relic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelicRepository extends JpaRepository<Relic, Integer> {
}
