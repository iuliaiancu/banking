package com.sandbox.banking.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorDetails> errorsDetails = new ArrayList<>();
		if (ex.getBindingResult().hasFieldErrors()) {
			List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
			fieldErrors.forEach(fieldError -> {
				ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Validation failed",
						fieldError.getDefaultMessage());
				errorsDetails.add(errorDetails);
			});
		}
		return new ResponseEntity<>(errorsDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Http message not readable",
				ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<List<ErrorDetails>> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		List<ErrorDetails> errorsDetails = new ArrayList<>();
		ex.getConstraintViolations().forEach(exception -> {
			ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Validation failed",
					exception.getMessage());
			errorsDetails.add(errorDetails);

		});
		return new ResponseEntity<>(errorsDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<List<ErrorDetails>> handleConstraintViolationException(AccountNotFoundException ex,
			WebRequest request) {
		List<ErrorDetails> errorsDetails = new ArrayList<>();
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Account not found",
				ex.getLocalizedMessage());
		errorsDetails.add(errorDetails);
		return new ResponseEntity<>(errorsDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
