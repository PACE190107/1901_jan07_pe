package com.revature.exceptions;

public class AccountNotEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "You must withdraw your money from your accounts before deleting them";
	}

}
