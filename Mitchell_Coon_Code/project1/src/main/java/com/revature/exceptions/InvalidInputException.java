package com.revature.exceptions;

public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Invalid input";
	}

}
