package com.bracket.tetring.domain.game.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class GetCheckPlayingResponseDto {
    private String status;
    private Data data;

    public GetCheckPlayingResponseDto(boolean isPlaying) {
        this.status = "success";
        this.data = new Data(isPlaying);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("is_playing")
        private boolean isPlaying;
    }
}
