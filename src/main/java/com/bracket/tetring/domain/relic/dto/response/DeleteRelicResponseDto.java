package com.bracket.tetring.domain.relic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteRelicResponseDto {
    private String status;

    public DeleteRelicResponseDto() {
        this.status = "success";
    }
}
