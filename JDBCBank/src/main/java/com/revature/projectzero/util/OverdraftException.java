package com.revature.projectzero.util;

@SuppressWarnings("serial")
public class OverdraftException extends Exception {
	public OverdraftException(String errorMessage) {
		super(errorMessage);
	}
}
