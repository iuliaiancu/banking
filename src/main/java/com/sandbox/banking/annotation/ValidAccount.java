package com.sandbox.banking.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sandbox.banking.validator.AccountValidator;

@Constraint(validatedBy = AccountValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidAccount {

	String message() default "Account creation can't be performed. Cause: customer doesn't exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}