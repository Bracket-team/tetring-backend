package com.bracket.tetring.domain.block.domain;

import com.bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(BlockId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    @Id
    private Integer blockId;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String shape;

    private Boolean isStore;

    private Integer slotNumber;
}
