package com.revature.projectzero.util;

public class AccountNotEmptyException extends Exception {
	public AccountNotEmptyException (String errorMessage) {
		super(errorMessage);
	}
}