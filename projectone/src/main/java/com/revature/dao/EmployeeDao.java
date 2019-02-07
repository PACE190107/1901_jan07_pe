package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {

	public Employee getEmployee(int employeeid);
	public List<Employee> getAllEmployees();
	public boolean updateEmployeeFName(int employeeId, String firstName);
	public boolean updateEmployeeLName(int employeeId, String lastName);
	public boolean updateEmployeeUsername(int employeeId, String userName);
	public boolean updateEmployeePassword(int employeeId, String passw0rd);
	public boolean updateEmployeeEmail(int employeeId, String emailAddress);
}
