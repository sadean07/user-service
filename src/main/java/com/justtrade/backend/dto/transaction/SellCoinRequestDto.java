package com.justtrade.backend.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellCoinRequestDto {
    private String coinCode;
    @Min(value = 1)
    private int numberOfSell;
}
