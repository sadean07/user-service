package com.justtrade.backend.dto.transaction;

import com.justtrade.backend.dto.CoinResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyCoinResponseDto {

    private BigDecimal remainAmount;
    private BigDecimal usedAmount;
    private CoinResponseDto coinData;
    private int numberOfBuy;
    private int currentCoinData;
}
