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
    INVALID_BLOCK_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 블록 아이디입니다."),

    ALREADY_END_GAME(HttpStatus.BAD_REQUEST, "이미 종료된 게임입니다."),
    ALREADY_IN_ROUND(HttpStatus.BAD_REQUEST, "플레이어가 이미 라운드를 시작했습니다."),
    ALREADY_IN_STORE(HttpStatus.BAD_REQUEST, "플레이어가 이미 상점에 있습니다."),

    ARE_SLOTS_FULL(HttpStatus.BAD_REQUEST, "슬롯이 가득차 있습니다."),
    PLAYER_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이어를 찾을 수 없습니다."),
    GAME_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이 중인 게임을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이 중인 상점을 찾을 수 없습니다."),
    BLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이 중인 블록을 찾을 수 없습니다."),
    STORE_BLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "상점 블록을 찾을 수 없습니다."),
    RELIC_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이 중인 유물을 찾을 수 없습니다."),
    STORE_RELIC_NOT_FOUND(HttpStatus.NOT_FOUND, "상점 유물을 찾을 수 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 입니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
