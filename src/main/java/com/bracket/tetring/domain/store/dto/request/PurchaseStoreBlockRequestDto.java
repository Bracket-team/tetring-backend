package com.bracket.tetring.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseStoreBlockRequestDto {
    @Range(min = 1, max = 3, message = "1에서 3 사이의 값을 입력하세요.")
    @JsonProperty("slot_number")
    private int slotNumber;
}
