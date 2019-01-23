package com.revature.exceptions;

public class ThrowingAwayMoney extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 337269726627537026L;
	
	public String getMessage()
	{
		return "\nFUNDS STILL AVAILABLE: YOU MUST EMPTY YOUR ACCOUNT BEFORE IT CAN BE DELETED.";
	}
}