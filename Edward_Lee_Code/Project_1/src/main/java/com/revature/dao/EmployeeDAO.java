package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDAO {
	public Employee updateEmployee(int ID, String fName, String lName,  String email);
	public Employee newEmployee(String fName, String lName, String uname, String pass, String email);
	public List<Employee> getAllEmployees();	
	public Employee LogIn(String username, String password);
}
