package com.bracket.tetring.domain.block.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBlockShapeResponseDto {
    private String status;

    public UpdateBlockShapeResponseDto() {
        this.status = "success";
    }
}
