package com.justtrade.backend.controller.advice;

import com.justtrade.backend.controller.AuthController;
import com.justtrade.backend.controller.TransactionController;
import com.justtrade.backend.controller.UserController;
import com.justtrade.backend.dto.error.DefaultErrorResponse;
import com.justtrade.backend.dto.error.DetailError;
import org.apache.tomcat.util.buf.StringUtils;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice(assignableTypes = {UserController.class, AuthController.class, TransactionController.class})
public class UserCotrollerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @RequestScope
    @ExceptionHandler({BindException.class, Exception.class})
    public DefaultErrorResponse handleException(BindException exception,
                                                                HttpServletRequest request){

        BindingResult result = exception.getBindingResult();
        List<ObjectError> objectErrorList = result.getAllErrors();

        List<String> propertyPaths = new ArrayList<>();
        List<DetailError> detailErrorList = objectErrorList.stream().map(error -> {
                    ConstraintViolationImpl<?> constraintViolation = error.unwrap(ConstraintViolationImpl.class);
                    String propertyPath = constraintViolation.getPropertyPath().toString();

                    String errorCode = constraintViolation.getConstraintDescriptor().getAttributes()
                            .getOrDefault("code", error.getCode()).toString();

                    propertyPaths.add(propertyPath);
                    return DetailError.builder()
                            .field(propertyPath)
                            .rejectedValue(constraintViolation.getInvalidValue())
                            .objectName(error.getObjectName())
                            .code(errorCode)
                            .defaultMessage(error.getDefaultMessage())
                            .build();
                })
                .collect(Collectors.toList());

        return DefaultErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed for " + exception.getObjectName() + "(" + StringUtils.join(propertyPaths, ',') + ")" + ".")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .details(detailErrorList)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public DefaultErrorResponse handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        List<DetailError> detailErrors = ex.getConstraintViolations().stream().map(
                error -> {
                    String errorCode = error.getConstraintDescriptor().getAttributes().getOrDefault("code",
                            error.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()).toString();

                    return DetailError.builder()
                            .code(errorCode)
                            .field(
                                    Objects.requireNonNull(StreamSupport.stream(
                                                            error.getPropertyPath().spliterator(), false).
                                                    reduce((first, second) -> second).
                                                    orElse(null)).
                                            toString()
                            )
                            .rejectedValue(error.getInvalidValue())
                            .defaultMessage(error.getMessage())
                            .objectName(error.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName())
                            .build();
                }
        ).collect(toList());

        return DefaultErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .details(detailErrors)
                .build();
    }
}
