package com.bracket.tetring.domain.relic.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRelicExistRequestDto {
    @JsonProperty("relic_number")
    private int relicNumber;
}
