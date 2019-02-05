package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceImpl;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

public class MasterDispatcher 
{
	private MasterDispatcher() {}
	
	private static final ReimbursementService reimburseService = new ReimbursementServiceImpl();
	private static final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final LoginService loginService = new LoginServiceImpl();
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) 
	{
		if(request.getRequestURI().contains("reimbursement"))
		{
			return reimburseService.process(request, response);
		}
		else if(request.getRequestURI().contains("employee"))
		{
			return employeeService.process(request, response);
		}
		else if(request.getRequestURI().contains("login"))
		{
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");
			
			Employee attempting = loginService.attemptAuthentication(username, password);
			
			if (attempting != null) {
				try {
					request.getRequestDispatcher("home.html").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else 
		{
			return "Not yet implemented";
		}
		return null;
	}
}