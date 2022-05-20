package com.justtrade.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinResponseDto {
    private Long id;

    private String code;

    private String nama;

    private int harga;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
