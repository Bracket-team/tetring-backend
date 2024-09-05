package com.bracket.tetring.domain.block.dto.response;

import com.bracket.tetring.domain.block.domain.Block;
import com.bracket.tetring.domain.block.dto.GetGameBlockDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.bracket.tetring.domain.block.dto.GetGameBlockDto.convertToGameBlockDtoList;

@Getter
@AllArgsConstructor
public class GetGameBlocksResponseDto {
    private String status;
    private Data data;

    public GetGameBlocksResponseDto(List<Block> blocks) {
        this.status = "success";
        this.data = new Data(convertToGameBlockDtoList(blocks));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("player_blocks")
        List<GetGameBlockDto> playerBlocks;
    }

}
