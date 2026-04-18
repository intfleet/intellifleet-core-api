package com.intellifleet.exceptions;

public class RecordCanceledException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RecordCanceledException (String message) {
		super(message);
	}
	
	public RecordCanceledException (String message, Throwable cause) {
		super(message, cause);
	}

}
