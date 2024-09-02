package com.bracket.tetring.domain.block.domain;

import com.bracket.tetring.domain.game.domain.Game;
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
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockId;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String shape;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private Boolean isStore;

    private Integer slotNumber;

    private Integer price;
}
