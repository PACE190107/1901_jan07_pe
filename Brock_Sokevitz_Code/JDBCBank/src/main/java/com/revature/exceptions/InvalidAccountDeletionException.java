package com.revature.exceptions;

public class InvalidAccountDeletionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4508495837595489742L;

	public String getMessage() {
		return "Invalid Account for deletion.";
	}
}
