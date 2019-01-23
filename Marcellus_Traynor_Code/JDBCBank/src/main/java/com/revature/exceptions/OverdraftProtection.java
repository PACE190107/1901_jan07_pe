package com.revature.exceptions;

public class OverdraftProtection extends RuntimeException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -54820551221614115L;
	
	public String getMessage()
	{
		return "\nOVERDRAFT WARNING: YOU CANNOT WITHDRAW MORE THAN WHAT IS IN YOUR ACCOUNT.";
	}
}