package com.bracket.tetring.global.handler;

import com.bracket.tetring.global.dto.reponse.FailResponseDto;
import com.bracket.tetring.global.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<FailResponseDto> validationException(CustomValidationException e) {
        return toResponse(e.getErrorCode(), e.getMessage());
    }


    private static ResponseEntity<FailResponseDto> toResponse(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new FailResponseDto(errorCode, message));
    }
}
