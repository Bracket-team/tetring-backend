package com.bracket.tetring.domain.block.domain;

import com.bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store_block")
@Getter
@Setter
@NoArgsConstructor
public class StoreBlock extends Block{
    private Integer slotNumber;

    private Integer price;

    public StoreBlock(String color, String shape, Game game, Integer slotNumber, Integer price) {
        super(color, shape, game);
        this.slotNumber = slotNumber;
        this.price = price;
    }
}
