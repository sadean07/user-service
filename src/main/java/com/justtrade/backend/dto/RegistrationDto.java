package com.justtrade.backend.dto;

import com.justtrade.backend.validator.constraint.EmailAlreadyExist;
import com.justtrade.backend.validator.constraint.IsPasswordStrongEnough;
import com.justtrade.backend.validator.constraint.UsernameAlreadyExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    @UsernameAlreadyExist
    private String username;

    @Email
    @EmailAlreadyExist
    private String email;

    @IsPasswordStrongEnough
    private String password;
}
