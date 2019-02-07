package com.revature.exception;

public class EmployeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException(int employeeId) {
		super("Employee with id [" + employeeId + "] could not be found");
	}
}
