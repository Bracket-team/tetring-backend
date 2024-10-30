package com.bracket.tetring.domain.block.dto;

import com.bracket.tetring.domain.block.domain.StoreBlock;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetStoreBlockDto {
    @JsonProperty("block_id")
    private long blockId;

    private String color;

    private String shape;

    @JsonProperty("slot_number")
    private int slotNumber;

    public GetStoreBlockDto(StoreBlock block) {
        this.blockId = block.getBlockId();
        this.color = block.getColor();
        this.shape = block.getShape();
        this.slotNumber = block.getSlotNumber();
    }

    public static List<GetStoreBlockDto> convertToStoreBlockDtoList(List<StoreBlock> blocks) {
        List<GetStoreBlockDto> list = new ArrayList<>();
        for(StoreBlock block : blocks) {
            list.add(new GetStoreBlockDto(block));
        }
        return list;
    }
}