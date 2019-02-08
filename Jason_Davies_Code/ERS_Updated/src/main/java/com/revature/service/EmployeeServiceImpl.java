package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplementation;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeServiceImpl implements EmployeeService {

	private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	private final EmployeeDAO dao = EmployeeDAOImplementation.getEmployeeDAO();
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Employee login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || username.equals("") || password == null || password == "") {
			logger.info("login() - failed");
			return null;
		}
		Employee exists = dao.getEmployee(username);
		if (exists != null && exists.getPassword().equals(password)) {
			ConnectionUtil.setCredentials(exists);
			request.getSession().setAttribute("id", exists.getId());
			request.getSession().setAttribute("username", exists.getUsername());
			logger.info("login() - succeeded");
			return exists;
		}
		logger.info("login() - failed");
		return null;
	}

	@Override
	public Employee logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		Employee exists = dao.getEmployee(request.getSession().getAttribute("username").toString());
		if (exists != null) {
			ConnectionUtil.defaultCredentials();
			request.getSession().invalidate();
			logger.info("logout() - succeeded");
			return exists;
		}
		logger.info("logout() - failed");
		return null;
	}

	@Override
	public Employee register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || username.equals("") || password == null || password == "") {
			logger.info("register() - failed");
			return null;
		}
		Employee exists = dao.getEmployee(username);
		if (exists == null) {
			exists = dao.insertEmployee(new Employee(-1, username, password, "empty", "empty", "empty", false));
			dao.insertCredentials(exists);
			dao.grantDBPermissions(exists);
			ConnectionUtil.setCredentials(exists);
			request.getSession().setAttribute("id", exists.getId());
			request.getSession().setAttribute("username", exists.getUsername());
			logger.info("register() - succeeded");
			return exists;
		}
		logger.info("register() - failed");
		return null;
	}

	@Override
	public Employee updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getSession().getAttribute("username").toString();
		String password = request.getParameter("passwordUpdate");
		String email = request.getParameter("emailUpdate");
		String firstName = request.getParameter("firstNameUpdate");
		String lastName = request.getParameter("lastNameUpdate");
		Employee exists = dao.getEmployee(username);
		if (exists != null) {
			if (!password.equals("")) {
				exists.setPassword(password);
			} 
			if (!email.equals("")) {
				exists.setEmail(email);
			}
			if (!firstName.equals("")) {
				exists.setFirstName(firstName);
			}
			if (!lastName.equals("")) {
				exists.setLastName(lastName);
			}
			ConnectionUtil.defaultCredentials();
			exists = dao.updateEmployee(exists);
			dao.updateCredentials(exists);
			ConnectionUtil.setCredentials(exists);
			logger.info("updateEmployee() - succeeded");
			return dao.updateEmployee(exists);
		}
		logger.info("updateEmployee() - failed");
		return null;
	}

	@Override
	public Employee getEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return dao.getEmployee(request.getSession().getAttribute("username").toString());
	}

	@Override
	public ArrayList<Employee> getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return dao.getAllEmployees();
	}
}
