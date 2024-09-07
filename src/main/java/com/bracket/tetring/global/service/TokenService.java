package com.bracket.tetring.global.service;

import com.bracket.tetring.global.handler.TokenException;
import com.bracket.tetring.global.redis.entity.Token;
import com.bracket.tetring.global.redis.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional
    public void deleteRefreshToken(String memberKey) {
        tokenRepository.deleteById(memberKey);
    }

    @Transactional
    public void saveOrUpdate(String memberKey, String refreshToken, String accessToken) {
        Token token = tokenRepository.findByAccessToken(accessToken)
                .map(o -> o.updateRefreshToken(refreshToken))
                .orElseGet(() -> new Token(memberKey, refreshToken, accessToken));

        tokenRepository.save(token);
    }

    public Token findByAccessTokenOrThrow(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new TokenException(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다."));
    }

    @Transactional
    public void updateToken(String accessToken, Token token) {
        token.updateAccessToken(accessToken);
        tokenRepository.save(token);
    }
}
