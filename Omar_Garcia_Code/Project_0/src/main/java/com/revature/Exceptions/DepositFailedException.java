package com.revature.Exceptions;

public class DepositFailedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepositFailedException() {
		System.out.println("Deposit failed, please try again");
	}
}
