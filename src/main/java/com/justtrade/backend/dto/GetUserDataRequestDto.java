package com.justtrade.backend.dto;

import com.justtrade.backend.entity.DataUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDataRequestDto {

    private DataUser.Status status;
    private Boolean isActive;
    private String email;
}
