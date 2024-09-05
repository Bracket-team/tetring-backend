package com.bracket.tetring.domain.store.domain;

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
public class Store {
    @Id
    private Long storeId;

    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(nullable = false)
    private Integer rerollPrice;

    @Column(nullable = false)
    private Integer money;

    @Column(nullable = false)
    private Integer moneyLevel;
}
