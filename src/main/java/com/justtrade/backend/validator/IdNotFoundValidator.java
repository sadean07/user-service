package com.justtrade.backend.validator;

import com.justtrade.backend.service.UserService;
import com.justtrade.backend.validator.constraint.IdNotFound;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class IdNotFoundValidator implements ConstraintValidator<IdNotFound,Long> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(userService.getDataUserById(userId));
    }
}
