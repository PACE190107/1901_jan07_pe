package com.revature.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDaoImplementation;
import com.revature.module.Employee;
import com.revature.module.Request;
import com.revature.module.login;

public class EmployeeService {
	private static EmployeeService employee;
	private final ObjectMapper mapper = new ObjectMapper();

	private EmployeeService() {

	}

	public static EmployeeService getEmployeeService() {
		if (employee == null) {
			employee = new EmployeeService();
		}
		return employee;
	}

	public Employee login(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		Employee emp = null;
		if(req.getMethod().contentEquals("GET")) {
		emp = EmployeeDaoImplementation.getEmployeeDao().login(req.getParameter("username"), req.getParameter("password"));
		} else if (req.getMethod().equals("POST")) {
			try {
				login log = mapper.readValue(req.getReader(), login.class);
				emp = EmployeeDaoImplementation.getEmployeeDao().login(log.getUsername(), log.getPassword());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}
	

	public Employee register(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		Employee emp;
		try {
			emp = mapper.readValue(req.getReader(), Employee.class);
			return EmployeeDaoImplementation.getEmployeeDao().register(emp);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Employee> viewAllEmployees() throws SQLException {
		return EmployeeDaoImplementation.getEmployeeDao().viewEmployee();
	}

	public Employee updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		Employee emp;
		try {
			emp = mapper.readValue(req.getReader(), Employee.class);
			return EmployeeDaoImplementation.getEmployeeDao().update(emp);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee getEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		Employee emp = null;
		if(req.getMethod().contentEquals("GET")) {
			emp = EmployeeDaoImplementation.getEmployeeDao().getEmployee( Integer.parseInt(req.getParameter("id")));
			} else if (req.getMethod().equals("POST")) {
				try {
					emp = mapper.readValue(req.getReader(), Employee.class);
					int id = emp.getId();
					return EmployeeDaoImplementation.getEmployeeDao().getEmployee(id);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return emp;
	}

}
