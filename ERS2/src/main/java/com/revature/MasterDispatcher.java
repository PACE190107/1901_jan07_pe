package com.revature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;
import com.revature.service.LoginService;
import com.revature.service.LoginServiceImpl;
import com.revature.service.RequestService;
import com.revature.service.RequestServiceImpl;

public class MasterDispatcher {

	private MasterDispatcher() {}
	
	private static final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final RequestService requestService = new RequestServiceImpl();
	private static final LoginService loginService = new LoginServiceImpl();
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getRequestURI().contains("employees"))
			return employeeService.process(request, response);
		else if (request.getRequestURI().contains("requests"))
			return requestService.process(request, response);
		else if (request.getRequestURI().contains("login"))
			return loginService.attemptAuthentication(request, response);
		else 
			return null;
	}
}