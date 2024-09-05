package com.bracket.tetring.domain.block.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UpdateBlockShapeResponseDto {
    private String status;

    public UpdateBlockShapeResponseDto() {
        this.status = "success";
    }
}
