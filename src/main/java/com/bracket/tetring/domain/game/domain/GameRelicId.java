package com.bracket.tetring.domain.game.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GameRelicId implements Serializable {
    private Long game;
    private Integer relic;
}
