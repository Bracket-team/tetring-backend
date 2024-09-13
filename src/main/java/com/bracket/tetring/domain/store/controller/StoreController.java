package com.bracket.tetring.domain.store.controller;

import com.bracket.tetring.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("")
    public ResponseEntity<?> getStoreInfo() {
        return storeService.getStoreDetails();
    }

    @GetMapping("/money")
    public ResponseEntity<?> getPlayerMoney() {
        return storeService.getGameMoney();
    }

    @PatchMapping("/level-up")
    public ResponseEntity<?> updateMoneyLevelUp() {
        return storeService.patchMoneyLevelUp();
    }
}
