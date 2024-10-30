package com.bracket.tetring.domain.store.controller;

import com.bracket.tetring.domain.block.service.BlockService;
import com.bracket.tetring.domain.relic.service.RelicService;
import com.bracket.tetring.domain.store.dto.request.PurchaseStoreBlockRequestDto;
import com.bracket.tetring.domain.store.dto.request.PurchaseStoreRelicRequestDto;
import com.bracket.tetring.domain.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;
    private final BlockService blockService;
    private final RelicService relicService;

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

    @PatchMapping("/blocks")
    public ResponseEntity<?> purchaseBlock(@Valid @RequestBody PurchaseStoreBlockRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // 에러 메시지 추출
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            // 에러 응답을 생성하여 반환
            return ResponseEntity.badRequest().body(errorMessages);
        }
        else {
            return blockService.purchaseBlock(requestDto.getSlotNumber());
        }
    }

    @PatchMapping("/relics")
    public ResponseEntity<?> purchaseRelic(@Valid @RequestBody PurchaseStoreRelicRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // 에러 메시지 추출
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            // 에러 응답을 생성하여 반환
            return ResponseEntity.badRequest().body(errorMessages);
        }
        else {
            return relicService.purchaseStoreRelic(requestDto.getSlotNumber());
        }
    }

    @GetMapping("/relics/reroll")
    public ResponseEntity<?> rerollRelics() {
        return relicService.rerollRelic();
    }
}
