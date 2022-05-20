package com.justtrade.backend.dto;

import com.justtrade.backend.validator.contraint.IsUsernameExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @IsUsernameExist
    private String username;

    private String password;
}
