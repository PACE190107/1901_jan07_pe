package com.revature.services;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class LoginServiceImpl implements LoginService
{
	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@Override
	public Employee attemptAuthentication(String username, String password)
	{
		Employee employee = employeeDao.employeeLogin(username, password);
		return employee;
	}

//	//This is where you would check against stored hashed passwords
//	private String getHashedPassword(String password) 
//	{
//		// invoke a Stored Procedure to perform the one-way hash of your password
//		return null;
//	}
}
