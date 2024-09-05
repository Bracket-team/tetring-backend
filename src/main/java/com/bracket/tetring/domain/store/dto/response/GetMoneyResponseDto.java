package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.store.domain.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class GetMoneyResponseDto {
    private String status;
    private Data data;

    public GetMoneyResponseDto(Store store) {
        this.status = "success";
        this.data = new Data(store.getMoney());
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("player_money")
        private int player_money;
    }
}
