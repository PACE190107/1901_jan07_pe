package com.revature.exceptions;

public class EmptyOrNullInputException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Input cannot be empty.\nPlease try again.";
	}
}
