package com.justtrade.backend.validator.constraint;

import com.justtrade.backend.validator.CheckPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckPasswordValidator.class)
public @interface IsPasswordStrongEnough {
    /**
     *
     * @return String
     */
    String message() default "Password not strong enough.";

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
