package com.bracket.tetring.domain.game.dto.response;

import com.bracket.tetring.domain.game.domain.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetPlayerRankingResponseDto {
    private String status;
    private Data data;

    public GetPlayerRankingResponseDto(List<PlayerScoreDto> playerScores) {
        this.status = "success";
        this.data = new Data(playerScores);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("player_scores")
        List<PlayerScoreDto> playerScores;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerScoreDto {
        @JsonProperty("player_name")
        private String name;

        @JsonProperty("best_score")
        private Long score;

        private int ranking;
    }

}
