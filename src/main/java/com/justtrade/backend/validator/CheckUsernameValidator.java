package com.justtrade.backend.validator;

import com.justtrade.backend.service.UserService;
import com.justtrade.backend.validator.constraint.UsernameAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CheckUsernameValidator implements ConstraintValidator<UsernameAlreadyExist,String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(userService.getDataUserByUsername(username));
    }
}
