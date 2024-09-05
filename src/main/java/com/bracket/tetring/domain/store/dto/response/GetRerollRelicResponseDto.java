package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.relic.dto.GetStoreRelicDto;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.bracket.tetring.domain.relic.dto.GetStoreRelicDto.convertToStoreRelicDtoList;

@Getter
@AllArgsConstructor
public class GetRerollRelicResponseDto {
    private String status;
    private Data data;

    public GetRerollRelicResponseDto(boolean canReroll, int nextRerollPrice, int remainedMoney, List<StoreRelic> relics) {
        this.status = "succcess";
        this.data = new Data(canReroll, nextRerollPrice, remainedMoney, convertToStoreRelicDtoList(relics));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("can_reroll")
        private boolean canReroll;

        @JsonProperty("next_reroll_price")
        private int nextRerollPrice;

        @JsonProperty("remained_money")
        private int remainedMoney;

        private List<GetStoreRelicDto> relics;
    }
}
