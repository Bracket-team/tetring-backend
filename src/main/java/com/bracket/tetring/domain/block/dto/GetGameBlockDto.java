package com.bracket.tetring.domain.block.dto;

import com.bracket.tetring.domain.block.domain.Block;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetGameBlockDto {
    @JsonProperty("block_id")
    private long blockId;

    private String color;

    private String shape;

    public GetGameBlockDto(Block block) {
        this.blockId = block.getBlockId();
        this.color = block.getColor();
        this.shape = block.getShape();
    }

    public static List<GetGameBlockDto> convertToGameBlockDtoList(List<Block> blocks) {
        List<GetGameBlockDto> list = new ArrayList<>();
        for(Block block : blocks) {
            list.add(new GetGameBlockDto(block));
        }
        return list;
    }
}