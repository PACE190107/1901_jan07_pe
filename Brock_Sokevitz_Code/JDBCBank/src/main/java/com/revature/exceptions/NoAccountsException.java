package com.revature.exceptions;

import java.io.Serializable;

public class NoAccountsException extends Throwable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5613246574751722025L;

		public String getMessage() {
			return "You haven't made any accounts yet.";
		}
}
