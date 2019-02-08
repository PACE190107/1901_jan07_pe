package com.revature.frontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDaoImp;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImp;
import com.revature.services.RequestService;
import com.revature.services.RequestServiceImp;

public class Dispatcher {

	private Dispatcher() {}
	
	private static final EmployeeService employeeService = new EmployeeServiceImp();
	private static final RequestService requestService = new RequestServiceImp();
	
	public static Object process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getRequestURI());
		if (req.getRequestURI().contains("employee"))
			return employeeService.process(req, res);
		else if (req.getRequestURI().contains("request"))
			return requestService.process(req, res);
		else return "Not yet implemented";
	}
}
