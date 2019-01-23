package com.revature.exceptions;

public class IncorrectInputException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "We're sorry, but this value does not match our records.\nPlease try again.";
	}
}
