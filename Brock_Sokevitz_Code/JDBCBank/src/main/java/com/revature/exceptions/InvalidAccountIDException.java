package com.revature.exceptions;

public class InvalidAccountIDException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6449054551984153932L;

	public String getMessage() {
		return "Invalid Account ID.";
	}
}
