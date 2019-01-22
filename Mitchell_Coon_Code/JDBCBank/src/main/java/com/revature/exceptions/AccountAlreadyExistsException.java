package com.revature.exceptions;

public class AccountAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Accout already exists";
	}

}