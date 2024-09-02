package com.bracket.tetring.domain.relic.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class DeleteRelicResponseDto {
    private String status;

    public DeleteRelicResponseDto() {
        this.status = "success";
    }
}
