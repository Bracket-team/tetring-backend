package com.bracket.tetring.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "잘못된 JWT 서명입니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),

    INVALID_GAME_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 게임 아이디입니다."),

    PLAYER_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이어를 찾을 수 없습니다."),
    GAME_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이 중인 게임을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이 중인 상점을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
