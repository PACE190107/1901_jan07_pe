package com.revature.projectzero.util;

@SuppressWarnings("serial")
public class AccountNotEmptyException extends Exception {
	public AccountNotEmptyException (String errorMessage) {
		super(errorMessage);
	}
}