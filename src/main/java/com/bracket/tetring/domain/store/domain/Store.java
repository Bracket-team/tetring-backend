package com.bracket.tetring.domain.store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    private Long storeId;

    @Column(nullable = false)
    private Integer rerollPrice;

    @Column(nullable = false)
    private Integer money;

    @Column(nullable = false)
    private Integer moneyLevel;
}
