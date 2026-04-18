package com.intellifleet.exceptions;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3942922507557772769L;
	
	private static final String defaultMessage = "Session not found.";
	
	public EntityNotFoundException() {
		super(defaultMessage);
	}
	
	public EntityNotFoundException(Throwable cause) {
		super(defaultMessage, cause);
	}

}
