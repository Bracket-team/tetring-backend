package com.bracket.tetring.domain.game.dto.response;

import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.bracket.tetring.domain.store.domain.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

import static com.bracket.tetring.domain.relic.dto.GetGameRelicDto.convertToGameRelicDtoList;

@Getter
@AllArgsConstructor
public class GetGameResultResponseDto {
    private String status;
    private Data data;

    public GetGameResultResponseDto(int roundNumber, int bestScore, int blockCount, Store store, List<GameRelic> relics) {
        this.status = "success";
        this.data = new Data(roundNumber, bestScore, blockCount, store.getMoney(), convertToGameRelicDtoList(relics));
    }

    @Getter
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("round_number")
        private int roundNumber;

        @JsonProperty("best_score")
        private int bestScore;

        @JsonProperty("block_count")
        private int blockCount;

        private int money;

        private List<GetGameRelicDto> relics;
    }
}
