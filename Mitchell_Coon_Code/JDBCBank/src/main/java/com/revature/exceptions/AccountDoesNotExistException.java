package com.revature.exceptions;

public class AccountDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Account does not exist";
	}

}
