package com.bracket.tetring.domain.block.controller;

import com.bracket.tetring.domain.block.dto.request.UpdateBlockShapeRequestDto;
import com.bracket.tetring.domain.block.service.BlockService;
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
public class BlockController {
    private final BlockService blockService;

    @GetMapping("/blocks")
    public ResponseEntity<?> getPlayerBlocks() {
        return blockService.getGameBlocks();
    }

    @PatchMapping("/block")
    public ResponseEntity<?> changePlayerBlock(@Valid @RequestBody UpdateBlockShapeRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // 에러 메시지 추출
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            // 에러 응답을 생성하여 반환
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            return blockService.changeBlockShape(requestDto.getBlockId(), requestDto.getShape());
        }
    }
}
