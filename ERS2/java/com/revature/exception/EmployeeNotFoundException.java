package com.revature.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(int id) {
		super("Employee with id [" + id + "] could not be found");
	}
}