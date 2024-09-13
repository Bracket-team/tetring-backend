package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.relic.dto.GetStoreRelicDto;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.bracket.tetring.domain.relic.dto.GetStoreRelicDto.convertToStoreRelicDtoList;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRerollRelicResponseDto {
    private String status;
    private Data data;

    public GetRerollRelicResponseDto(boolean canReroll, int nextRerollPrice, int remainedMoney, List<StoreRelic> relics) {
        this.status = "success";
        this.data = new Data(canReroll, nextRerollPrice, remainedMoney, relics != null ? convertToStoreRelicDtoList(relics) : null);
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
