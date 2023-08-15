package com.justtrade.backend.validator;

import com.justtrade.backend.service.UserService;
import com.justtrade.backend.validator.constraint.EmailAlreadyExist;
import com.justtrade.backend.validator.constraint.UsernameAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

    public class CheckEmailValidator implements ConstraintValidator<EmailAlreadyExist,String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(userService.getDataUserByEmail(email));
    }
}
