package com.bracket.tetring.global.dto.reponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FailResponseDto {
    private String status;
    private String error;
    private List<String> message;

    public FailResponseDto(String error, List<String> message) {
        status = "error";
        this.error = error;
        this.message = message;
    }
}
