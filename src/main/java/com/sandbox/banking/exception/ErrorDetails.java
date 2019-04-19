package com.sandbox.banking.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private String timestamp;
	private Integer status;
	private String error;
	private String message;

	public ErrorDetails(Integer status, String message, String error) {
		this.timestamp = LocalDateTime.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + "]";
	}

}