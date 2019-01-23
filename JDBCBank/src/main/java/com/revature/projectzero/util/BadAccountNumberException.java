package com.revature.projectzero.util;

@SuppressWarnings("serial")
public class BadAccountNumberException extends Exception {
	public BadAccountNumberException (String errorMessage) {
		super(errorMessage);
	}
}
