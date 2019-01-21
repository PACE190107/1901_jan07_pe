package com.revature.exceptions;

public class InvalidOption extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -787968124426885599L;

	public String getMessage()
	{
		return "\nINVALID INPUT: THE OPTION YOU HAVE PICKED DOESN'T EXIST. REVIEW THE OPTIONS AND TRY AGAIN.\n";
	}
}