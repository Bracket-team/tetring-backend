package com.bracket.tetring.domain.player.dto.response;

import com.bracket.tetring.domain.relic.domain.Relic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetPlayerFoundRelicsResponseDto {
    private String status;
    private Data data;

    public GetPlayerFoundRelicsResponseDto(List<Relic> playerFoundRelics) {
        this.status = "success";
        this.data = new Data(convertToDtoList(playerFoundRelics));
    }

    private List<GetPlayerFoundRelicsDto> convertToDtoList(List<Relic> relics) {
        List<GetPlayerFoundRelicsDto> list = new ArrayList<>();
        for(Relic relic : relics) {
            list.add(new GetPlayerFoundRelicsDto(relic));
        }
        return list;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        List<GetPlayerFoundRelicsDto> relics;
    }

    @Getter
    @NoArgsConstructor
    public static class GetPlayerFoundRelicsDto {
        private String name;
        private String rarity;
        private String effect;

        public GetPlayerFoundRelicsDto(Relic relic) {
            this.name = relic.getName();
            this.rarity = relic.getRarity();
            this.effect = relic.getEffect();
        }
    }
}
