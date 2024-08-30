package com.bracket.tetring.domain.player.domain;

import com.bracket.tetring.domain.relic.domain.Relic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(PlayerFoundRelicsId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerFoundRelics {

    @Id
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "relic_number", nullable = false)
    private Relic relic;
}
