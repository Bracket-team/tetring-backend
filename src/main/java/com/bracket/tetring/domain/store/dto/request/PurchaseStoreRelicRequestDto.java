package com.bracket.tetring.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseStoreRelicRequestDto {
    @JsonProperty("slot_number")
    @Range(min = 1, max = 2, message = "상점 슬롯 번호는 1에서 2 입니다.")
    private int slotNumber;
}
