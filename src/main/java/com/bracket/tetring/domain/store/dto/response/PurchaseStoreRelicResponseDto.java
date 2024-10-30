package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.dto.GetGameBlockDto;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.bracket.tetring.domain.block.dto.GetGameBlockDto.convertToGameBlockDtoList;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseStoreRelicResponseDto {
    private String status;
    private Data data;

    public PurchaseStoreRelicResponseDto(boolean canBuy, int remainedMoney, GameRelic relic, List<Block> blocks) {
        this.status = "success";
        this.data = new Data(canBuy, remainedMoney, relic != null ? new GetGameRelicDto(relic) : null, convertToGameBlockDtoList(blocks));
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

        @JsonInclude(JsonInclude.Include.NON_EMPTY)  // 블록 목록이 없으면 JSON 응답에서 제외
        private List<GetGameBlockDto> blocks;
    }
}
