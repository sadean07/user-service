package com.justtrade.backend.validator;

import com.justtrade.backend.service.UserService;
import com.justtrade.backend.validator.constraint.IsPasswordStrongEnough;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPasswordValidator implements ConstraintValidator<IsPasswordStrongEnough,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        Pattern passwordMatcher = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}");
        Matcher matcher = passwordMatcher.matcher(password);
        return matcher.find();
    }
}
