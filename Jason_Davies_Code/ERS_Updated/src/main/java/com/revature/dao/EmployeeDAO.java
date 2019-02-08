package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Employee;

public interface EmployeeDAO {
	public Employee insertEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public Employee deleteEmployee(Employee employee);	
	public Employee getEmployee(String username);
	public ArrayList<Employee> getAllEmployees();
	
	public boolean insertCredentials(String username, String password);
	public boolean updateCredentials(String username, String password);
	public boolean deleteCredentials(Employee employee);
	
	public boolean grantDBPermissions(Employee employee);
	public boolean revokeDBPermissions(Employee employee);
	public String hashPassword(String username, String password);
}
