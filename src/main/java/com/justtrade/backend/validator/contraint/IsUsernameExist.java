package com.justtrade.backend.validator.contraint;

import com.justtrade.backend.validator.CheckUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckUsernameValidator.class)
public @interface IsUsernameExist {
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
