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
public class SellCoinResponseDto {

    private BigDecimal currentAmount;
    private BigDecimal gotAmount;
    private BigDecimal totalAmount;
    private CoinResponseDto coinData;
    private int numberOfSell;
    private int remainCoinData;
}
