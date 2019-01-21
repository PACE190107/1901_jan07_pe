package com.revature.exceptions;

public class UserDoesntExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7471058784427825051L;

	public String getMessage() {
		return "Username doesn't exist. Please enter a valid username or exit.";
	}
}
