package com.revature.exceptions;

public class BadNumberException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The number you input was not valid";
	}

}
