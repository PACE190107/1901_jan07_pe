package com.revature.exceptions;

public class TooManyLoginAttemptsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3087604082439670880L;

	public String getMessage() {
		return "Too many password attempts.";
	}
}
