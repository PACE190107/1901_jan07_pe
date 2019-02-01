package com.revature.backend;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.EmployeeService;
import com.revature.services.RequestService;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static Object process(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		if (req.getRequestURI().contains("login")) {
			return EmployeeService.getEmployeeService().login(req, resp);
		} else if (req.getRequestURI().contains("register")) {
			return EmployeeService.getEmployeeService().register(req, resp);
		} else if (req.getRequestURI().contains("addRequest")) {
			return RequestService.getRequestService().addRequest(req, resp);
		} else if (req.getRequestURI().contains("viewAllUsers")) {
			return EmployeeService.getEmployeeService().viewAllEmployees();
		} else if (req.getRequestURI().contains("viewAllRequests")) {
			return RequestService.getRequestService().getAllRequest(0);
		} else if (req.getRequestURI().contains("viewAllResolvedRequests")) {
			return RequestService.getRequestService().getAllRequest(1);
		} 
		return null;
	}
}
