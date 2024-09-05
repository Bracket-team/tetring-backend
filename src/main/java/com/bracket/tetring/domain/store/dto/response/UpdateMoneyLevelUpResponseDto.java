package com.bracket.tetring.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UpdateMoneyLevelUpResponseDto {
    private String status;
    private Data data;

    public UpdateMoneyLevelUpResponseDto(boolean canBuy, int levelUpPrice, int remainedMoney) {
        this.status = "success";
        this.data = new Data(canBuy, levelUpPrice, remainedMoney);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("can_buy")
        private boolean canBuy;

        @JsonProperty("level_up_price")
        private int levelUpPrice;

        @JsonProperty("remained_money")
        private int remainedMoney;
    }
}
