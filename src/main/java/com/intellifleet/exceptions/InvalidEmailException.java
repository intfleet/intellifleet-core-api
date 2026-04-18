package com.intellifleet.exceptions;

public class InvalidEmailException extends ApiException {
	
	private static final long serialVersionUID = 1L;

	public InvalidEmailException() {
		super("Invalid Email");
	}

	public InvalidEmailException(String message) {
		super(message);
	}
}
