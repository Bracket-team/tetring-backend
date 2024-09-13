package com.bracket.tetring.domain.relic.controller;

import com.bracket.tetring.domain.relic.service.RelicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/games")
public class RelicController {
    private RelicService relicService;

    @GetMapping("/relics")
    public ResponseEntity<?> getPlayerRelics() {
        return relicService.getGameRelics();
    }


}
