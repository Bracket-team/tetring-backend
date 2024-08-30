package com.bracket.tetring.domain.player.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerFoundRelicsId implements Serializable {
    private Long player;
    private Integer relic;
}
