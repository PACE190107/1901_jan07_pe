package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.module.Employee;

public interface EmployeeDao {
		List<Employee> viewEmployee() throws SQLException;
		Employee login(String username, String password) throws SQLException;
		Employee register(Employee newEmp) throws SQLException;
		Employee update(Employee emp) throws SQLException;
		Employee getEmployee(int id) throws SQLException;
}
