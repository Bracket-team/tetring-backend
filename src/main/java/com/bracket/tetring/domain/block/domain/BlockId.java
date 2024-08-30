package com.bracket.tetring.domain.block.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BlockId implements Serializable {
    private Long game;
    private Integer blockId;
}
