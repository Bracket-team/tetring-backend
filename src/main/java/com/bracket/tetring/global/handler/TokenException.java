package com.bracket.tetring.global.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class TokenException extends RuntimeException {
    private final HttpStatus errorCode;
    private final String message;

    public TokenException(HttpStatus errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
