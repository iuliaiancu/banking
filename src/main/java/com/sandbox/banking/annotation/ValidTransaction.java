package com.sandbox.banking.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sandbox.banking.validator.TransactionValidator;

@Constraint(validatedBy = TransactionValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidTransaction {

	String message() default "Transaction can't be performed. Check if the accounts are valid and / or the account from which the transfer gets done has enough money";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}