package com.sandbox.banking.exception;

public class AccountNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -510380456937723670L;

	private String message;

	public AccountNotFoundException() {
	}

	public AccountNotFoundException(String message) {
		this.message = message;
	}

}
