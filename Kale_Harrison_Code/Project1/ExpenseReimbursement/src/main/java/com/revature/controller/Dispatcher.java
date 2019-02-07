package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class Dispatcher {
	
	private Dispatcher() {
		
	}
	
	private static final EmployeeService employeeService = new EmployeeService();
	private static final ReimbursementService rebService = new ReimbursementService();
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getRequestURI().contains("employee"))
			return employeeService.process(request, response);
		else if (request.getRequestURI().contains("reimbursement"))
			return rebService.process(request, response);
		else 
			return "Not yet implemented";
	}
}
