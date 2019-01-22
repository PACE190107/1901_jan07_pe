package com.revature.exceptions;

public class UserDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "That user does not exist, master";
	}

}
