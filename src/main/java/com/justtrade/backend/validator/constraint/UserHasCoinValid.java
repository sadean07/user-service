package com.justtrade.backend.validator.constraint;

import com.justtrade.backend.validator.UserHasCoinValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UserHasCoinValidValidator.class})
@Target({PARAMETER,METHOD})
@Retention(RUNTIME)
public @interface UserHasCoinValid {
    /**
     * @return String
     */
    String message() default "User Doesn't Have This Much Coin";

    /**
     * @return class
     */
    Class<?>[] groups() default {};

    /**
     * @return class
     */
    Class<? extends Payload>[] payload() default {};
}
