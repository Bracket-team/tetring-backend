package com.bracket.tetring.domain.game.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UpdateEndRoundResponseDto {
    private String status;
    private Data data;

    public UpdateEndRoundResponseDto(boolean isWin, int nextRoundNumber, int nextRoundGoal, int money) {
        this.status = "success";
        this.data = new Data(isWin, nextRoundNumber, nextRoundGoal, money);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("is_win")
        private boolean isWin;

        @JsonProperty("next_round_number")
        private int nextRoundNumber;

        @JsonProperty("next_round_goal")
        private int nextRoundGoal;

        private int money;
    }
}
