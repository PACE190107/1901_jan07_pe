package com.revature.projectzero.util;

public class BadAccountNumberException extends Exception {
	public BadAccountNumberException (String errorMessage) {
		super(errorMessage);
	}
}
