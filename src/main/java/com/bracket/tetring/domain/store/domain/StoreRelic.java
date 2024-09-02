package com.bracket.tetring.domain.store.domain;

import com.bracket.tetring.domain.relic.domain.Relic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(StoreRelicId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRelic {
    @Id
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Id
    @ManyToOne
    @JoinColumn(name = "relic_number", nullable = false)
    private Relic relic;

    @Column(nullable = false)
    private Integer slotNumber;

    private Integer price;
}
