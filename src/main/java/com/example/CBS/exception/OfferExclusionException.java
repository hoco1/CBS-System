package com.example.CBS.exception;

public class OfferExclusionException extends RuntimeException{
	public OfferExclusionException(String message) {
		super(message);
	}
	public OfferExclusionException(String message,Throwable cause) {
		super(message,cause);
	}
}
