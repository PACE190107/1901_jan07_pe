package com.revature.exceptions;

public class UserExistsException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1609488154697326613L;

	public String getMessage() {
		return "Username already exists, please enter a different username.";
	}
}
