package com.revature.exceptions;

public class NotBooleanException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Please type either 0 or 1 in the Manager box";
	}

}
