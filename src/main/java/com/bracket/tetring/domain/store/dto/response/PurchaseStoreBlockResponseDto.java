package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.dto.GetGameBlockDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PurchaseStoreBlockResponseDto {
    private String status;
    private Data data;

    public PurchaseStoreBlockResponseDto(boolean canBuy, int remainedMoney, Block block) {
        this.status = "success";
        this.data = new Data(canBuy, remainedMoney, new GetGameBlockDto(block));
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
