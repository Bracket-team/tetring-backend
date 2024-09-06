package com.bracket.tetring.domain.block.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreBlock {
    @Id
    @Column(name = "block_id")
    private Long blockID;

    private Integer slotNumber;

    private Integer price;

    @OneToOne
    @MapsId
    @JoinColumn(name = "blcok_id")
    private Block block;
}
