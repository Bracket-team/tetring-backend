package com.bracket.tetring.domain.relic.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRelicRequestDto {
    @Min(value = 1, message = "슬롯 번호는 1보다 크거나 같아야 함")
    @Max(value = 5, message = "슬롯 번호는 5보다 작거나 같아야 함")
    @JsonProperty("slot_number")
    public int slotNumber;
}
