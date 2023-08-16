package com.justtrade.backend.dto.transaction;

import com.justtrade.backend.dto.CoinResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellAllCoinResponseDto {

    private BigDecimal currentAmount;
    private BigDecimal gotAmount;
    private BigDecimal totalAmount;
    private List<CoinResponseDto> coinData;
}
