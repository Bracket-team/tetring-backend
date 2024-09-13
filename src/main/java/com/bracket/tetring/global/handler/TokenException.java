package com.bracket.tetring.global.handler;

import com.bracket.tetring.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenException extends CustomException {

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
