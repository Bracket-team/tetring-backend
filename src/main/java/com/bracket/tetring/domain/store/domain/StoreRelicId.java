package com.bracket.tetring.domain.store.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StoreRelicId implements Serializable {
    private Long store;
    private Integer relic;
}
