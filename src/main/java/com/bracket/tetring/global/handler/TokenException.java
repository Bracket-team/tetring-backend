package com.bracket.tetring.global.handler;

import com.bracket.tetring.global.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class TokenException extends CustomValidationException {

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
