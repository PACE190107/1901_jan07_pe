package com.revature.exceptions;

public class InvalidOptionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "We're sorry, but that doesn't seem to be an option.\nPlease try again.";
	}
}
