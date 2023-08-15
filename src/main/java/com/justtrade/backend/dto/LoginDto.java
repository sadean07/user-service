package com.justtrade.backend.dto;

import com.justtrade.backend.validator.constraint.UsernameAlreadyExist;
import com.justtrade.backend.validator.constraint.UsernameNotFound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @UsernameNotFound
    private String username;

    private String password;
}
