package com.sandbox.banking.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus code;

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String message, HttpStatus code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getCode() {
		return code;
	}

}
