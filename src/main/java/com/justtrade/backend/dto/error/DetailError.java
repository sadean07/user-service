package com.justtrade.backend.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailError {
    private String code;
    private String objectName;
    private String defaultMessage;
    private String field;
    private Object rejectedValue;
}
