package com.revature.exceptions;

public class incorrectPasswordException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 425111737757374855L;

	public incorrectPasswordException(String string) {
		super(string);
	}
}
