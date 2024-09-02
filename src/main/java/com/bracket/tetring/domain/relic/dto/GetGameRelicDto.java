package com.bracket.tetring.domain.relic.dto;

import com.bracket.tetring.domain.game.domain.GameRelic;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetGameRelicDto {
    private String name;
    private String rarity;
    private String effect;
    private double rate;

    @JsonProperty("slot_number")
    private int slotNumber;

    public GetGameRelicDto(GameRelic relic) {
        this.name = relic.getRelic().getName();
        this.rarity = relic.getRelic().getRarity();
        this.effect = relic.getRelic().getEffect();
        this.rate = relic.getRate();
        this.slotNumber = relic.getSlotNumber();
    }

    public static List<GetGameRelicDto> convertToGameRelicDtoList(List<GameRelic> relics) {
        List<GetGameRelicDto> list = new ArrayList<>();
        for(GameRelic relic : relics) {
            list.add(new GetGameRelicDto(relic));
        }
        return list;
    }
}