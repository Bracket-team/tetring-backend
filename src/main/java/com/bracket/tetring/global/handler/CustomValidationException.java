package com.bracket.tetring.global.handler;

import lombok.*;

import java.io.Serial;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomValidationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> errors;
}
