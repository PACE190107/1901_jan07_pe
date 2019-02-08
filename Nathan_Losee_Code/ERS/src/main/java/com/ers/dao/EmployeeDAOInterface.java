package com.ers.dao;

import java.sql.SQLException;
import java.util.List;

import com.ers.models.Employee;

public interface EmployeeDAOInterface {
	public boolean createEmployee(String firstName, String lastName, String email,
			String username, String password, boolean isManager) throws SQLException;
	public List<Employee> readEmployees() throws SQLException;
	public Employee readEmployee(int eID) throws SQLException;
	public Employee readEmployee(String username, String password) throws SQLException;
	public boolean updateEmployee(int eID, String credential, String newValue) throws SQLException;
}
