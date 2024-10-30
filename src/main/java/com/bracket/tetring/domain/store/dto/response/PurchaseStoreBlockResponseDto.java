package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.dto.GetGameBlockDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseStoreBlockResponseDto {
    private String status;
    private Data data;

    public PurchaseStoreBlockResponseDto(boolean canBuy, int remainedMoney, Block block) {
        this.status = "success";
        this.data = new Data(canBuy, remainedMoney, block != null ? new GetGameBlockDto(block) : null);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("can_buy")
        private boolean canBuy;

        @JsonProperty("remained_money")
        private int remainedMoney;

        private GetGameBlockDto block;
    }
}
