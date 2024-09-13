package com.bracket.tetring.global.handler;

import com.bracket.tetring.global.error.ErrorCode;
import lombok.*;

import java.io.Serial;
import java.util.List;

@Getter
public class CustomValidationException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public CustomValidationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public CustomValidationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
