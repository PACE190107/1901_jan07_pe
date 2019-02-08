package com.revature.exceptions;

public class InputTooLongException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Input too long";
	}

}
