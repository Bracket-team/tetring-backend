package com.bracket.tetring.global.handler;

import com.bracket.tetring.global.dto.reponse.FailResponseDto;
import com.bracket.tetring.global.error.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<FailResponseDto> customException(CustomException e) {
        return toResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FailResponseDto> exception(Exception e) {
        return toResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private static ResponseEntity<FailResponseDto> toResponse(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new FailResponseDto(errorCode, message));
    }
}
