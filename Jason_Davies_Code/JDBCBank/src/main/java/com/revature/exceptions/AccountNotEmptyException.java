package com.revature.exceptions;

public class AccountNotEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The account you selected did not have a balance of 0.";
	}
}
