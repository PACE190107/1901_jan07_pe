package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao 
{
	//login an employee
	public Employee employeeLogin(String username, String password);
	
	//view their own info
	public List<Employee> getEmployeeInfo(String username);
	
	//update info
	public void updateEmployee(String username, String newUsername, String newPassword);
	
	//Manager -- view all employees
	public List<Employee> getAllEmployeeInfo();
}