package com.revature.exceptions;

public class UsernameKeywordException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 180610870411592132L;

	public String getMessage() {
		return "This is a keyword and can't be used as a username or password.";
	}
}
