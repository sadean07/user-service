package com.justtrade.backend.validator.constraint;

import com.justtrade.backend.validator.CheckEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface EmailAlreadyExist {
    /**
     *
     * @return String
     */
    String message() default "Email Already Exist";

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
