package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService
{
	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	final static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) 
	{
		if(request.getMethod().equals("GET"))
		{
			log.info("inside the GET request of EmployeeServiceImpl");
			
			//Get all employees
			String[] path = request.getRequestURI().split("/");
			
			if (path.length == 4) 
			{		
				log.info("inside the GET/all request of EmployeeServiceImpl");
				return employeeDao.getAllEmployeeInfo();
			}
			
			//Get single employee
			if (path.length == 5) 
			{		
				log.info("inside the GET/singleEmployee request of EmployeeServiceImpl");

				String username = String.valueOf(path[4]);
				return employeeDao.getEmployeeInfo(username);
			}
		}
		
		if(request.getMethod().equals("PUT"))
		{
			log.info("inside the PUT request of EmployeeServiceImpl");
			//Update employee info
			
		}
		return null;
	}
}