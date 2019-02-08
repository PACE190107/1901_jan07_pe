package com.revature.exceptions;

public class FailedLoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Failed login attempt";
	}

} 