package com.bracket.tetring.domain.game.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.block.dto.GetGameBlockDto;
import com.bracket.tetring.domain.block.dto.GetStoreBlockDto;
import com.bracket.tetring.domain.game.domain.Game;
import com.bracket.tetring.domain.relic.domain.GameRelic;
import com.bracket.tetring.domain.game.dto.GetGameDto;
import com.bracket.tetring.domain.relic.dto.GetGameRelicDto;
import com.bracket.tetring.domain.relic.dto.GetStoreRelicDto;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.dto.GetStoreDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.bracket.tetring.domain.block.dto.GetGameBlockDto.convertToGameBlockDtoList;
import static com.bracket.tetring.domain.block.dto.GetStoreBlockDto.convertToStoreBlockDtoList;
import static com.bracket.tetring.domain.relic.dto.GetGameRelicDto.convertToGameRelicDtoList;
import static com.bracket.tetring.domain.relic.dto.GetStoreRelicDto.convertToStoreRelicDtoList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPlayGameResponseDto {
    private String status;
    private Data data;

    public GetPlayGameResponseDto(Game game, int round_goal, List<Block> playerBlocks, List<GameRelic> playerRelics, Store store, int moneyLevelUpPrice, List<StoreBlock> storeBlocks, List<StoreRelic> storeRelics) {
        this.status = "success";
        this.data = new Data(new GetGameDto(game, round_goal), convertToGameBlockDtoList(playerBlocks), convertToGameRelicDtoList(playerRelics), new GetStoreDto(store, moneyLevelUpPrice), convertToStoreBlockDtoList(storeBlocks), convertToStoreRelicDtoList(storeRelics));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private GetGameDto game;

        @JsonProperty("player_blocks")
        private List<GetGameBlockDto> playerBlocks;

        @JsonProperty("player_relics")
        private List<GetGameRelicDto> playerRelics;

        private GetStoreDto store;

        @JsonProperty("store_blocks")
        private List<GetStoreBlockDto> storeBlocks;

        @JsonProperty("store_relics")
        private List<GetStoreRelicDto> storeRelics;
    }
}
