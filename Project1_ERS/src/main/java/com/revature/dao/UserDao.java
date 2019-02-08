package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface UserDao {

	public boolean createEmployee(Employee employee);
	
	public Employee getUserById(int id);
	
	public Employee getUserByUsernamePassword(String username, String password);
	
	public List<Employee> getAllEmployees();
	
	
	
}
