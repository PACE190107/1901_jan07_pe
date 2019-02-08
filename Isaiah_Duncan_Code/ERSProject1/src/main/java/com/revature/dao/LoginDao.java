package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Login;

public interface LoginDao {
	Login login(String username, String password);
	Employee getEmpDetails(String username);
	
	//manager methods
	boolean createLogin(Login login);
}
