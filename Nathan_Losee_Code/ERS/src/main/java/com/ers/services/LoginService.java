package com.ers.services;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.EmployeeDAO;
import com.ers.models.Employee;
import com.ers.util.ConnectionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LoginService {
	private LoginService() { }
	
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
		boolean isConfirming = false;
		String username;
		String password;
		
		if (req.getSession().getAttribute("temp") != null) {
			username = ((String) req.getSession().getAttribute("temp")).replace("/", "");
			password = username;
			isConfirming = true;
		} else {
			ObjectMapper mapper = new ObjectMapper();
			String inJson = req.getReader().readLine();
			ObjectNode node = (ObjectNode)mapper.readTree(inJson);
			
			username = node.get("user").asText();
			password = node.get("pass").asText();
		}
		
		Employee employee = EmployeeDAO.getDAO().readEmployee(username, password);
		if (employee != null) {
			req.getSession().setAttribute("user", employee);
			ConnectionManager.setJDBCConnection(employee.geteUsername(), employee.getePassword());
			if (employee.isManager())
				req.getRequestDispatcher("/components/manager/manager.html").forward(req, resp);
			else if (employee.isConfirmed())
				req.getRequestDispatcher("/components/employee/employee.html").forward(req, resp);
			else if (isConfirming)
				req.getRequestDispatcher("/components/passwordChange/passwordChange.html").forward(req, resp);
			else
				req.getRequestDispatcher("/components/needConfirmation/needConfirmation.html").forward(req, resp);
		} else {
			req.getRequestDispatcher("/components/login/login.html").forward(req, resp);
		}
	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ConnectionManager.setJDBCConnection("Admin01", "s9d5j1q8");
		req.getSession().invalidate();
		req.getRequestDispatcher("/components/login/login.html").forward(req, resp);
	}
}