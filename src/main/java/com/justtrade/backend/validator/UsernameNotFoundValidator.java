package com.justtrade.backend.validator;

import com.justtrade.backend.service.UserService;
import com.justtrade.backend.validator.constraint.UsernameAlreadyExist;
import com.justtrade.backend.validator.constraint.UsernameNotFound;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UsernameNotFoundValidator implements ConstraintValidator<UsernameNotFound,String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if(username.contains("@")){
            return Objects.nonNull(userService.getDataUserByEmail(username));
        }
        return Objects.nonNull(userService.getDataUserByUsername(username));
    }
}
