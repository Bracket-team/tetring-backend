package com.bracket.tetring.domain.game.dto.response;

import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetPlayerRankingResponseDto {
    private String status;
    private Data data;

    public GetPlayerRankingResponseDto(List<Game> games) {
        this.status = "success";
        this.data = convertGamesToData(games);
    }

    private Data convertGamesToData(List<Game> games) {
        List<getRankingDto> players = new ArrayList<>();
        int ranking = 1;
        for (Game game : games) {
            String playerName = (game.getPlayer() != null && game.getPlayer().getUsername() != null)
                    ? game.getPlayer().getUsername()
                    : "Unknown";
            Long bestScore = (game.getBestScore() != null) ? game.getBestScore() : 0L;
            getRankingDto rankingDto = new getRankingDto(playerName, bestScore, ranking++);
            players.add(rankingDto);
        }
        return new Data(players);
    }

    @Getter
    @AllArgsConstructor
    public static class Data {
        List<getRankingDto> players;
    }

    @Getter
    @AllArgsConstructor
    public static class getRankingDto {
        private String name;

        private Long score;

        private int ranking;

    }

}
