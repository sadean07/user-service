package com.justtrade.backend.validator.constraint;

import com.justtrade.backend.validator.CheckUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckUsernameValidator.class)
public @interface UsernameAlreadyExist {
    /**
     *
     * @return String
     */
    String message() default "Username Already Exist";

    /**
     *
     * @return class
     */
    Class<?>[] groups() default {};

    /**
     *
     * @return class
     */
    Class<? extends Payload>[] payload() default {};
}
