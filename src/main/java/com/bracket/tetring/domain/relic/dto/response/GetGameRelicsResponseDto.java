package com.bracket.tetring.domain.relic.dto.response;

import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.bracket.tetring.domain.relic.dto.GetGameRelicDto.convertToGameRelicDtoList;

@Getter
@AllArgsConstructor
public class GetGameRelicsResponseDto {
    private String status;
    private Data data;

    public GetGameRelicsResponseDto(List<GameRelic> relics) {
        this.status = "success";
        this.data = new Data(convertToGameRelicDtoList(relics));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("player_relics")
        List<GetGameRelicDto> playerRelics;
    }
}
