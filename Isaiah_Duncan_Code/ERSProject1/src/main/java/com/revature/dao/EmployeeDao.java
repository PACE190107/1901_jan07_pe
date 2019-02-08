package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Request;

public interface EmployeeDao {

	//employee methods
	List<Request> getEmployeeResolved(Employee emp);
	boolean updateEmployee(int employeeId, String username, String firstname, String lastname, String email);
	boolean updateLogin(String currentUsername, String username, String password);
	
	//manager methods
	List<Employee> getAllEmployees();
	List<Request> getAllPendingRequests();
	List<Request> getAllResolvedRequests();
	List<Request> getAllRequests();
	List<Request> getAnEmployeesRequests();
	
	boolean resolveEmployeeRequest(int requestId, String status, int managerId);
	
	boolean createEmployee(Employee emp);
	
	
}
