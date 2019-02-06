package com.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.models.Employee;
import com.ers.services.EmployeeService;
import com.ers.services.LoginService;
import com.ers.services.ManagerService;
import com.ers.util.ConnectionManager;

public class RequestHelper {
	private RequestHelper() { }
	
	public static void processPOST(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String[] switchStrings = req.getRequestURI().split("/");
		String switchString = switchStrings[switchStrings.length - 1];
		
		switch (switchString) {
		case "login":
			LoginService.login(req, resp);
			break;
		case "register":
			ManagerService.registerEmployee(req, resp);
			break;
		case "request":
			EmployeeService.makeRequest(req, resp);
			break;
		case "requestsSingle":
			if (((Employee) req.getSession().getAttribute("user")).isManager())
				ManagerService.getReimbursementsSingle(req, resp);
			break;
		case "employee":
			ManagerService.getEmployee(req, resp);
			break;
		default:
			throw new RuntimeException("URI not recognized.");
		}
	}

	public static void processGET(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String[] switchStrings = req.getRequestURI().split("/");
		String switchString = switchStrings[switchStrings.length - 1];
		switch (switchString) {
		case "session":
			if (req.getSession().getAttribute("user") == null)
				req.getRequestDispatcher("/webfiles/login/login.html").forward(req, resp);
			else {
				ConnectionManager.setJDBCConnection(
						((Employee) req.getSession().getAttribute("user")).geteUsername(),
						((Employee) req.getSession().getAttribute("user")).getePassword());
				if (((Employee) req.getSession().getAttribute("user")).isManager())
					req.getRequestDispatcher("/webfiles/manager/manager.html").forward(req, resp);
				else {
					if (((Employee) req.getSession().getAttribute("user")).isConfirmed())
						req.getRequestDispatcher("/webfiles/employee/employee.html").forward(req, resp);
					else
						req.getRequestDispatcher("/webfiles/passwordChange/passwordChange.html").forward(req, resp);
				}
			}
			break;
		case "username":
			EmployeeService.getUsername(req, resp);
			break;
		case "alter":
			req.getRequestDispatcher("/webfiles/settings/settings.html").forward(req, resp);
			break;
		case "settings":
			EmployeeService.getSettings(req, resp);
			break;
		case "requests":
			if (((Employee) req.getSession().getAttribute("user")).isManager())
				ManagerService.getReimbursements(req, resp);
			else
				EmployeeService.getReimbursements(req, resp);
			break;
		case "request":
			req.getRequestDispatcher("/webfiles/request/request.html").forward(req, resp);
			break;
		case "employees":
			ManagerService.getEmployees(req, resp);
			break;
		case "registration":
			if (((Employee) req.getSession().getAttribute("user")).isManager())
				req.getRequestDispatcher("/webfiles/register/register.html").forward(req, resp);
			break;
		case "ERS":
			System.out.println("called");
			LoginService.login(req, resp);
			resp.sendRedirect("/ERS/");
			break;
		case "logout":
			LoginService.logout(req, resp);
			break;
		default:
			throw new RuntimeException("URI not recognized.");
		}
	}
	
	public static void processPUT(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String[] switchStrings = req.getRequestURI().split("/");
		String switchString = switchStrings[switchStrings.length - 1];

		switch (switchString) {
		case "request":
			ManagerService.stampRequest(req, resp);
			break;
		case "settings":
			EmployeeService.updateSettings(req, resp);
			break;
		default:
			throw new RuntimeException("URI not recognized.");
		}
	}
}
