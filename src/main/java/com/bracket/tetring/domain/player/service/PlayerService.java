package com.bracket.tetring.domain.player.service;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.dto.response.GetPlayerFoundRelicsResponseDto;
import com.bracket.tetring.domain.player.repository.PlayerFoundRelicsRepository;
import com.bracket.tetring.domain.player.repository.PlayerRepository;
import com.bracket.tetring.global.handler.CustomValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.bracket.tetring.global.error.ErrorCode.PLAYER_NOT_FOUND;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerFoundRelicsRepository playerFoundRelicsRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllFoundRelics() {
        Player player = findPlayer();
        return ResponseEntity.status(HttpStatus.OK).body(new GetPlayerFoundRelicsResponseDto(playerFoundRelicsRepository.findRelicsByPlayer(player)));
    }

    @Transactional
    public Player findPlayer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Player player = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            String email = null;
            // principal이 UserDetails의 구현체인 경우 (일반적으로 UserDetailsService로 로드된 사용자 정보)
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername(); // UserDetails의 getUsername()이 이메일로 사용되는 경우
            }
            // principal이 String으로 이메일인 경우
            else if (principal instanceof String) {
                email = (String) principal;
            }

            player = playerRepository.findPlayerByEmail(email).orElse(null);
        }
        return player;
    }
}
