package com.revature.services;

import java.sql.SQLException;

import com.revature.dao.EmployeeDaoImplementation;
import com.revature.module.Employee;

public class EmployeeService {
	private static EmployeeService employee;

	private EmployeeService() {

	}

	public static EmployeeService getEmployeeService() {
		if (employee == null) {
			employee = new EmployeeService();
		}
		return employee;
	}

	public Employee login(String username, String password) throws SQLException {
		return EmployeeDaoImplementation.getEmployeeDao().login(username, password);
		}

	public Employee register(String first, String last, String username, String password, String email) {
		return null;
	}

	public String viewAllEmployees() throws SQLException {
			return EmployeeDaoImplementation.getEmployeeDao().viewEmployee();
	}

	public void updatePassword(String password) {

	}

}
