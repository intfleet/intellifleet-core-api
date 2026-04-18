package com.intellifleet.exceptions;

public class NoRecordFoundException extends ApiException {
	
	private static final long serialVersionUID = 1L;

	public NoRecordFoundException() {
		super("Record Not Found");
	}

	public NoRecordFoundException(String message) {
		super(message);
	}

}