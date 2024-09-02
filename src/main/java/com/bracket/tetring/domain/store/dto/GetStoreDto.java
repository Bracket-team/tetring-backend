package com.bracket.tetring.domain.store.dto;

import com.bracket.tetring.domain.store.domain.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetStoreDto {
    @JsonProperty("reroll_price")
    private int rerollPrice;

    private int money;

    @JsonProperty("money_level")
    private int moneyLevel;

    @JsonProperty("money_level_up_price")
    private int moneyLevelUpPrice;

    public GetStoreDto(Store store, int moneyLevelUpPrice) {
        this.rerollPrice = store.getRerollPrice();
        this.money = store.getMoney();
        this.moneyLevel = store.getMoneyLevel();
        this.moneyLevelUpPrice = moneyLevelUpPrice;
    }
}
