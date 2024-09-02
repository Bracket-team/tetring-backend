package com.bracket.tetring.domain.relic.dto;

import com.bracket.tetring.domain.store.domain.StoreRelic;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetStoreRelicDto {
    private String name;
    private String rarity;
    private String effect;
    private int price;

    @JsonProperty("slot_number")
    private int slotNumber;

    public GetStoreRelicDto(StoreRelic relic) {
        this.name = relic.getRelic().getName();
        this.rarity = relic.getRelic().getRarity();
        this.effect = relic.getRelic().getEffect();
        this.price = relic.getPrice();
        this.slotNumber = relic.getSlotNumber();
    }
    public static List<GetStoreRelicDto> convertToStoreRelicDtoList(List<StoreRelic> relics) {
        List<GetStoreRelicDto> list = new ArrayList<>();
        for(StoreRelic relic : relics) {
            list.add(new GetStoreRelicDto(relic));
        }
        return list;
    }
}
