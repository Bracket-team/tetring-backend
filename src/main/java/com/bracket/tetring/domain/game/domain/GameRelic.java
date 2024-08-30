package com.bracket.tetring.domain.game.domain;

import com.bracket.tetring.domain.relic.domain.Relic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(GameRelicId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameRelic {
    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Relic relic;

    private Double rate;

    @Column(nullable = false)
    private Integer slotNumber;
}
