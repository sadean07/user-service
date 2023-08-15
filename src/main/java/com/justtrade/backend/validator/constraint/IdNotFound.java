package com.justtrade.backend.validator.constraint;

import com.justtrade.backend.validator.IdNotFoundValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IdNotFoundValidator.class)
public @interface IdNotFound {
    /**
     *
     * @return String
     */
    String message() default "Id Not Found";

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
