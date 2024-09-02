package com.bracket.tetring.domain.relic.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRelicExistRequestDto {
    @JsonProperty("relic_number")
    private int relicNumber;
}
