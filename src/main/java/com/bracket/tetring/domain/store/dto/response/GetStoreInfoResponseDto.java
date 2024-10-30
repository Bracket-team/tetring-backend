package com.bracket.tetring.domain.store.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.bracket.tetring.domain.block.dto.GetStoreBlockDto;
import com.bracket.tetring.domain.relic.dto.GetStoreRelicDto;
import com.bracket.tetring.domain.store.domain.Store;
import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.bracket.tetring.domain.store.dto.GetStoreDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.bracket.tetring.domain.block.dto.GetStoreBlockDto.convertToStoreBlockDtoList;
import static com.bracket.tetring.domain.relic.dto.GetStoreRelicDto.convertToStoreRelicDtoList;

@Getter
@AllArgsConstructor
public class GetStoreInfoResponseDto {
    private String status;
    private Data data;

    public GetStoreInfoResponseDto(Store store, int moneyLevelUpPrice, List<StoreBlock> storeBlocks, List<StoreRelic> storeRelics) {
        this.status = "success";
        this.data = new Data(new GetStoreDto(store, moneyLevelUpPrice), convertToStoreBlockDtoList(storeBlocks), convertToStoreRelicDtoList(storeRelics));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private GetStoreDto store;

        @JsonProperty("store_blocks")
        private List<GetStoreBlockDto> store_blocks;

        @JsonProperty("store_relics")
        private List<GetStoreRelicDto> store_relics;
    }
}
