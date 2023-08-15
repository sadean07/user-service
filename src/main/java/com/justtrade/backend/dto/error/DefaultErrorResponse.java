package com.justtrade.backend.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultErrorResponse {
    private int status;
    private String message;
    private String error;
    private String path;
    private String timestamp;
    private List<DetailError> details;
}
