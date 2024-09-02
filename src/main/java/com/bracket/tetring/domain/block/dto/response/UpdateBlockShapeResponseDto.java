package com.bracket.tetring.domain.block.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UpdateBlockShapeResponseDto {
    private String status;

    public UpdateBlockShapeResponseDto() {
        this.status = "success";
    }
}
