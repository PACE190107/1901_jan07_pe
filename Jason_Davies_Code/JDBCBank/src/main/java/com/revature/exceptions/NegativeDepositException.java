package com.revature.exceptions;

public class NegativeDepositException extends Exception {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "You must deposit a value greater than 0.";
	}
}
