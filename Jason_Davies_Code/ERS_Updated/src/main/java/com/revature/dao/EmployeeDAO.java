package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Employee;

public interface EmployeeDAO {
	public Employee insertEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public Employee deleteEmployee(Employee employee);	
	public Employee getEmployee(String username);
	public ArrayList<Employee> getAllEmployees();
	
	public boolean insertCredentials(Employee employee);
	public boolean updateCredentials(Employee employee);
	public boolean deleteCredentials(Employee employee);
	
	public boolean grantDBPermissions(Employee employee);
	public boolean revokeDBPermissions(Employee employee);
}
