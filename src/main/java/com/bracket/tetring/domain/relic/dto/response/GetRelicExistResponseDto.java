package com.bracket.tetring.domain.relic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRelicExistResponseDto {
    private String status;
    private Data data;

    public GetRelicExistResponseDto(boolean relicExist) {
        this.status = "success";
        this.data = new Data(relicExist);
    }

    @Getter
    @AllArgsConstructor
    public static class Data {
        private boolean exist;
    }
}
