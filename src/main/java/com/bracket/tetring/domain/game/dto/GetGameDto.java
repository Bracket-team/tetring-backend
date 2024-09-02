package com.bracket.tetring.domain.game.dto;

import com.bracket.tetring.domain.game.domain.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGameDto {
    @JsonProperty("game_id")
    private long gameId;

    @JsonProperty("round_number")
    private int roundNumber;

    @JsonProperty("round_goal")
    private int roundGoal;

    @JsonProperty("is_store")
    private boolean isStore;

    public GetGameDto(Game game, int roundGoal) {
        this.gameId = game.getGameId();
        this.roundNumber = game.getRoundNumber();
        this.roundGoal = roundGoal;
        this.isStore = game.getIsStore();
    }
}
