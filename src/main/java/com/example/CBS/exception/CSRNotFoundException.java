package com.example.CBS.exception;

public class CSRNotFoundException extends RuntimeException {
	public CSRNotFoundException(String message) {
		super(message);
	}
	public CSRNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}
}
