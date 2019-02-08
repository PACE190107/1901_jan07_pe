package com.revature.tjordan.services;

import java.util.List;

import com.reavature.tjordan.models.Employee;
import com.revature.dao.EmployeeDaoImplementation;

public class EmployeeService {
	
	private static EmployeeService currentEmployeeService;

	
	private EmployeeService() {
		
	}
	
	public static EmployeeService getEmployeeService() {
		if(currentEmployeeService == null) {
			currentEmployeeService = new EmployeeService();
		}
		
		return currentEmployeeService;
	}
	
	
	
	
	
	
	public Employee validateLogin(String userName, String password) {
		return EmployeeDaoImplementation.getEmployeeDao().validateLogin(userName, password);
	}
	
	
	public Employee getEmployee(int emp_ID) {
		return EmployeeDaoImplementation.getEmployeeDao().getEmployee(emp_ID);
	}
	
	public Employee getEmployee(String userName) {
		
		return EmployeeDaoImplementation.getEmployeeDao().getEmployee(userName);
	}
	
	public boolean checkUser(String userName, String password) {
		return EmployeeDaoImplementation.getEmployeeDao().checkUser(userName, password);
	}
	
	public Employee updateEmployee(int emp_ID, String firstName, String userName, String lastName, String email, String password) {
		return EmployeeDaoImplementation.getEmployeeDao().updateEmployee(emp_ID, firstName, userName, lastName, email, password);
	}
	
	
	public List<Employee> getAllEmployees() {
		return EmployeeDaoImplementation.getEmployeeDao().getAllEmployees();
	}
	
	
	public boolean addEmployee(String userName, String firstName, String lastName, String password, String email, String isManager) {
		return EmployeeDaoImplementation.getEmployeeDao().addEmployee(userName, firstName, lastName, password, email, isManager);
	}
	
	
	
}
