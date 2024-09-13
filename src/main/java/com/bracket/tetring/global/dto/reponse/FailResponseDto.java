package com.bracket.tetring.global.dto.reponse;

import com.bracket.tetring.global.error.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FailResponseDto {
    private String status;
    private ErrorCode error;
    private String message;

    public FailResponseDto(ErrorCode error, String message) {
        status = "error";
        this.error = error;
        this.message = message;
    }
}
