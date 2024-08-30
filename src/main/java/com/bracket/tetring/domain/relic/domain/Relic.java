package com.bracket.tetring.domain.relic.domain;

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
public class Relic {
    @Id
    private Integer relicNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String effect;

    @Column(nullable = false)
    private String rarity;
    //normal, rare, ultra
}
