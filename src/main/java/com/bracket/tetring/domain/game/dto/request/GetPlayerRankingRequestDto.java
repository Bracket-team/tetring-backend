package com.bracket.tetring.domain.game.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPlayerRankingRequestDto {
    @Min(0)
    private int number;
}
