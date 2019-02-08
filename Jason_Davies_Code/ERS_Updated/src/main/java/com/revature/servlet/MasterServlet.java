package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;

public class MasterServlet extends DefaultServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(MasterServlet.class);
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		logger.info("- request.getMethod(): " + request.getMethod() + "\n- URI: " + uri);
		
		Object object = MasterDispatcher.process(request, response);
		
		if (object instanceof Employee) {
			Employee employee = (Employee)object;
	
			if (uri.endsWith("Home")) {
				response.setContentType("text/html");	
				if (employee.getIsManager()) {
					request.getRequestDispatcher("home_manager.html").forward(request, response);
				} else {
					request.getRequestDispatcher("home_employee.html").forward(request, response);	
				}
			} else if (uri.endsWith("getEmployee")) {
				response.setContentType("application/json");
				response.getWriter().append(mapper.writeValueAsString(object));
			} else if (uri.endsWith("updateEmployee")) {
				response.setContentType("text/html");
//				if (employee.getIsManager()) {
//					request.getRequestDispatcher("home_manager.html").forward(request, response);
//				} else {
//					request.getRequestDispatcher("home_employee.html").forward(request, response);	
//				} 
				request.getRequestDispatcher("personal.html").forward(request, response);
			} else if (uri.endsWith("logout")) {
				response.sendRedirect("/ERS_Updated"); // Used to transition after logout
			}
		} else {
			if (uri.endsWith("getAllEmployees")) {
				response.setContentType("application/json");
				response.getWriter().append(object.toString());
			} else if (uri.endsWith("getMyReimbursements")) {
				response.setContentType("application/json");
				response.getWriter().append(object.toString());
			} else if (uri.endsWith("getAllReimbursements")) {
				response.setContentType("application/json");
				response.getWriter().append(object.toString());
			} else if (uri.endsWith("getAllReimbursementsSingle")) {
				try {
					request.getSession().setAttribute("r_id", Integer.parseInt(request.getParameter("requesterId")));	
				} catch (NumberFormatException e) {}
				request.getRequestDispatcher("reimbursement_manager.html").forward(request, response);
				//request.getRequestDispatcher("home_manager.html").forward(request, response);
			} else if (uri.endsWith("insertReimbursement")) {
				request.getRequestDispatcher("reimbursement_employee.html").forward(request, response);
				//request.getRequestDispatcher("home_employee.html").forward(request, response);
			} else if (uri.endsWith("updateReimbursement")) {
				request.getRequestDispatcher("reimbursement_manager.html").forward(request, response);
				//request.getRequestDispatcher("home_manager.html").forward(request, response);
			} else if (uri.endsWith("Personal")) {
				response.setContentType("text/html");
				request.getRequestDispatcher("personal.html").forward(request, response);
			} else if (uri.endsWith("ViewEmployees"))  {
				response.setContentType("text/html");
				request.getRequestDispatcher("employees.html").forward(request, response);
			} else if (uri.endsWith("ViewReimbursements")) {
				response.setContentType("text/html");
				request.getRequestDispatcher("reimbursement_manager.html").forward(request, response);
			} else if (uri.endsWith("MyReimbursements")) {
				response.setContentType("text/html");
				request.getRequestDispatcher("reimbursement_employee.html").forward(request, response);
			} else {
				response.sendRedirect("/ERS_Updated"); // Used to remove the /Home from failed login
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
