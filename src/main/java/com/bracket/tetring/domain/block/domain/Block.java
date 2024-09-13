package com.bracket.tetring.domain.block.domain;

import com.bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="block")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) // Joined 전략을 사용하여 상속 구조
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

    public Block(String color, String shape, Game game) {
        this.color = color;
        this.shape = shape;
        this.game = game;
    }
}
