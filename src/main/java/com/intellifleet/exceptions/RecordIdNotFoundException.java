package com.intellifleet.exceptions;

public class RecordIdNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public RecordIdNotFoundException(String message) {
		super(message);
	}
	
	public RecordIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}