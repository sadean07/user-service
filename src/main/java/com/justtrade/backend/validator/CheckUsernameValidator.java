package com.justtrade.backend.validator;

import com.justtrade.backend.repository.DataUserRepository;
import com.justtrade.backend.validator.contraint.IsUsernameExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUsernameValidator implements ConstraintValidator<IsUsernameExist,String> {
    @Autowired
    private DataUserRepository dataUserRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return dataUserRepository.findByUsername(s).isPresent();
    }
}
