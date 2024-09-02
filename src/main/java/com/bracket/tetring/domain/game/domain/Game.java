package com.bracket.tetring.domain.game.domain;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.store.domain.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(nullable = false)
    private Integer roundNumber;

    @Column(nullable = false)
    private Boolean isStore;

    @ManyToOne
    private Player player;

    private LocalDateTime playDate;

    private Long bestScore;

    @Column(nullable = false)
    private Boolean is_playing;
}
