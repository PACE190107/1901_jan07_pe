package com.revature.exceptions;

public class NotEnoughMoneyException  extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Withdraw not possible";
	}

}