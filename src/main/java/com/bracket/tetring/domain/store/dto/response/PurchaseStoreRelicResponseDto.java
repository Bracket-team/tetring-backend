package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseStoreRelicResponseDto {
    private String status;
    private Data data;

    public PurchaseStoreRelicResponseDto(boolean canBuy, int remainedMoney, GameRelic relic) {
        this.status = "success";
        this.data = new Data(canBuy, remainedMoney, relic != null ? new GetGameRelicDto(relic) : null);
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
