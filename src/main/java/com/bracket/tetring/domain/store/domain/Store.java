package com.bracket.tetring.domain.store.domain;

import com.bracket.tetring.domain.game.domain.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Boolean useCoupon;

    public Store(Game game, Integer rerollPrice, Integer money, Integer moneyLevel) {
        this.game = game;
        this.rerollPrice = rerollPrice;
        this.money = money;
        this.moneyLevel = moneyLevel;
    }
}
