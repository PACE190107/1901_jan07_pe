package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

public class MasterDispatcher {

	private static Logger logger = Logger.getLogger(MasterDispatcher.class);
	private static final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private MasterDispatcher() {}
	public static Object process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getRequestURI();
		if (uri.endsWith("Home")) {
			if (request.getParameter("btn").equals("Login")) {
				return employeeService.login(request, response);
			} else if (request.getParameter("btn").equals("Register")) {
				return employeeService.register(request, response);
			} else if (request.getParameter("btn").equals("Back")) {
				return employeeService.getEmployee(request, response);
			}
			return null;
		} else if (uri.endsWith("getEmployee")) {
			return employeeService.getEmployee(request, response);
		} else if (uri.endsWith("getAllEmployees")) {
			return mapper.writeValueAsString(employeeService.getAllEmployees(request, response).toArray());
		} else if (uri.endsWith("getAllReimbursements")) { // Get all reimbursements
			Object id = request.getSession().getAttribute("r_id");
			if (id != null && Integer.parseInt(id.toString()) > 0) {
				return mapper.writeValueAsString(reimbursementService.getAllReimbursements(
						Integer.parseInt(request.getSession().getAttribute("r_id").toString()),
						request, response).toArray());
			} else {
				return mapper.writeValueAsString(reimbursementService.getAllReimbursements(request, response).toArray());
			}
		} else if (uri.endsWith("getAllReimbursementsSingle")) { // Get all reimbursements for a single employee
			return null;
		} else if (uri.endsWith("getMyReimbursements")) { // Get all MY reimbursements
			return mapper.writeValueAsString(reimbursementService.getAllReimbursements(
					Integer.parseInt(request.getSession().getAttribute("id").toString()), 
					request, response).toArray());
		} else if (uri.endsWith("insertReimbursement")) {
			return reimbursementService.insertReimbursement(request, response);
		} else if (uri.endsWith("updateReimbursement")) {
			return reimbursementService.updateReimbursement(request, response);
		} else if (uri.endsWith("updateEmployee")) {
			return employeeService.updateEmployee(request, response);
		} else if (uri.endsWith("logout")){
			return employeeService.logout(request, response);
		}
		return null;
	}
}
