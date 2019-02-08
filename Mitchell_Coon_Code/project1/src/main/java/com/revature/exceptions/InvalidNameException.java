package com.revature.exceptions;

public class InvalidNameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Invalid name";
	}

}
