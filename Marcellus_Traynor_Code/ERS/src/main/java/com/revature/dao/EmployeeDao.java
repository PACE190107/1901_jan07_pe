package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao 
{
	public String employeeLogin(String username, String password);
	public List<Employee> getEmployeeInfo(String username);
	public Employee updateEmployee(String username, String newFname, String newLname, String newPassword);
	public List<Employee> getAllEmployeeInfo();
	public String hash(String username, String password);
}