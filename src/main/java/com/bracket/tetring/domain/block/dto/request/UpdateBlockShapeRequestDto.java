package com.bracket.tetring.domain.block.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBlockShapeRequestDto {
    @JsonProperty("block_id")
    private Long blockId;
    @Length(min = 16, max = 16, message = "블록 모양의 길이는 16이어야 함")
    private String shape;
}
