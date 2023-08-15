package com.justtrade.backend.validator.constraint;

import com.justtrade.backend.validator.UsernameNotFoundValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UsernameNotFoundValidator.class)
public @interface UsernameNotFound {
    /**
     *
     * @return String
     */
    String message() default "Username Not Found";

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
