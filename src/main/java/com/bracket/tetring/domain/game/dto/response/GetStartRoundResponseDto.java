package com.bracket.tetring.domain.game.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.dto.GetGameBlockDto;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.game.domain.GameRelic;
import com.bracket.tetring.domain.game.dto.GetGameDto;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.bracket.tetring.domain.block.dto.GetGameBlockDto.convertToGameBlockDtoList;
import static com.bracket.tetring.domain.relic.dto.GetGameRelicDto.convertToGameRelicDtoList;

@Getter
@AllArgsConstructor
public class GetStartRoundResponseDto {
    private String status;
    private Data data;

    public GetStartRoundResponseDto(Game game, int roundGoal, List<Block> blocks, List<GameRelic> relics) {
        this.status = "success";
        this.data = new Data(new GetGameDto(game, roundGoal), convertToGameBlockDtoList(blocks), convertToGameRelicDtoList(relics));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private GetGameDto game;

        @JsonProperty("player_blocks")
        private List<GetGameBlockDto> playerBlocks;

        @JsonProperty("player_relics")
        private List<GetGameRelicDto> playerRelic;
    }
}
