package com.revature.exceptions;

public class InvalidSpecialNameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Invalid username or password";
	}

}
