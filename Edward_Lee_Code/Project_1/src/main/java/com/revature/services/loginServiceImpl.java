package com.revature.services;

import com.revature.dao.EmployeeDAOImpl;
import com.revature.model.Employee;

public class loginServiceImpl implements loginService{

	@Override
	public Employee attemptLogin(String username, String password){
		return EmployeeDAOImpl.getEmployeeDao().LogIn(username, password);
	}

}
