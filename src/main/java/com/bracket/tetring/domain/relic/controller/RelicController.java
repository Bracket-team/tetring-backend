package com.bracket.tetring.domain.relic.controller;

import com.bracket.tetring.domain.relic.dto.request.DeleteRelicRequestDto;
import com.bracket.tetring.domain.relic.dto.request.GetRelicExistRequestDto;
import com.bracket.tetring.domain.relic.service.RelicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/games")
public class RelicController {
    private final RelicService relicService;

    @GetMapping("/relics")
    public ResponseEntity<?> getPlayerRelics() {
        return relicService.getGameRelics();
    }

    @GetMapping("/relics/check")
    public ResponseEntity<?> checkPlayerRelic(@Valid @RequestBody GetRelicExistRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // 에러 메시지 추출
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            // 에러 응답을 생성하여 반환
            return ResponseEntity.badRequest().body(errorMessages);
        }
        else {
            return relicService.hasRelic(requestDto.getRelicNumber());
        }
    }

    @DeleteMapping("/relics")
    public ResponseEntity<?> throwPlayerRelic(@Valid @RequestBody DeleteRelicRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // 에러 메시지 추출
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            // 에러 응답을 생성하여 반환
            return ResponseEntity.badRequest().body(errorMessages);
        }
        else {
            return relicService.throwRelic(requestDto.getSlotNumber());
        }
    }
}
