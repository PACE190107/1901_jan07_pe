package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.exception.EmployeeNotFoundException;

public class EmployeeServiceImp implements EmployeeService{
	
	private static EmployeeDao dao = new EmployeeDaoImp();
	private static EmployeeServiceImp empServ;
	private final ObjectMapper mapper = new ObjectMapper();
	
	public Object process(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getMethod().contentEquals("GET")) {
			String[] path = req.getRequestURI().split("/");
			if (path.length == 4) {
				System.out.println(dao.getAllEmployees());
				return dao.getAllEmployees();
			}
			System.out.println(path.length);
			if (path.length == 5) {
				try {
					int employeeId = Integer.valueOf(path[4]);
					System.out.println(path.length);
					return dao.getEmployee((int) req.getSession().getAttribute("user_id"));
				} catch (NumberFormatException e) {
					return "Cannot convert " + path[4] + " into a number";
				} catch (EmployeeNotFoundException e) {
					return e.getMessage();
				}
			}
		}
//		req.getSession().getAttribute("user_id");
		if (req.getMethod().contentEquals("PUT")) {
			
		}
		return null;
	}
	
	

}
