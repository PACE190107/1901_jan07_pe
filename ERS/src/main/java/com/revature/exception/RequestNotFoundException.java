package com.revature.exception;

public class RequestNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RequestNotFoundException(int id) {
		super("Request with id [" + id + "] could not be found");
	}
}