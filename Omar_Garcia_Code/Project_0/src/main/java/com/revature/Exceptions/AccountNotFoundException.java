package com.revature.Exceptions;

public class AccountNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException() {
		System.out.println("Account could not be found");
	}
}
