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
    @MapsId
    @OneToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @Id
    @Column(name = "store_block_id")
    private Long storeBlockId;

    private Integer slotNumber;

    private Integer price;
}
