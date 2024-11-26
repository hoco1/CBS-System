package com.example.CBS.exception;

public class CSRAlreadyExistsException extends RuntimeException {
	public CSRAlreadyExistsException(String message) {
		super(message);
	}
	public CSRAlreadyExistsException(String message,Throwable cause) {
		super(message,cause);
	}
}
