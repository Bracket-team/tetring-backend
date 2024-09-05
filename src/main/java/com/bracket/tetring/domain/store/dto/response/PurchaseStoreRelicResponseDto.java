package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PurchaseStoreRelicResponseDto {
    private String status;
    private Data data;

    public PurchaseStoreRelicResponseDto(boolean canBuy, int remainedMoney, GameRelic relic) {
        this.status = "success";
        this.data = new Data(canBuy, remainedMoney, new GetGameRelicDto(relic));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Data {
        @JsonProperty("can_buy")
        private boolean canBuy;

        @JsonProperty("remained_money")
        private int remainedMoney;

        private GetGameRelicDto relic;
    }
}
