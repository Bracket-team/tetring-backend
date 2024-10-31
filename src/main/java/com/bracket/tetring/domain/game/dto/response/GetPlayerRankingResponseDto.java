package com.bracket.tetring.domain.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        List<PlayerScoreDto> players;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerScoreDto {
        private String name;

        private Long score;

        private int ranking;
    }

}
