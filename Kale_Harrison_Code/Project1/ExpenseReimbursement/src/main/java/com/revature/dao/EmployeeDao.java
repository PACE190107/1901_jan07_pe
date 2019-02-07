package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	
	
	/*
	 *View account info
	 *update account info
	 *	change username
	 *	change password 
	 *	change email
	 * 
	 ******* manager only*****
	 * view all employees (include managers?)
	 * register employee(optional)
	 * 
	 */
	
	public Employee viewSingleEmployee(int e_id);
	public boolean changeUsername(String e_username, int e_id);
	public boolean changePassword(String e_password, int e_id);
	public boolean changeEmail(String e_email, int e_id);
	
	public List<Employee> viewAllEmployees();
	//public boolean createEmployee();
	

}
