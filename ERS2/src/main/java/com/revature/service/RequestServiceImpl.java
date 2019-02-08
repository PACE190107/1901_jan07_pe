package com.revature.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.RequestDao;
import com.revature.dao.RequestDaoImpl;
import com.revature.exception.EmployeeNotFoundException;
import com.revature.exception.RequestNotFoundException;
import com.revature.models.Request;

public class RequestServiceImpl implements RequestService {
	
	private final RequestDao dao = new RequestDaoImpl();
	private final ObjectMapper mapper = new ObjectMapper();
	/*servlet URI:
	*0-blank
	*1-ERS
	*2-rest
	*3-requests
	*4-pending
	*5-employee id
	*/
	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			// GET ALL LOGIC
			String[] path = request.getRequestURI().split("/");
			if (path.length == 4) {		// execute if the request is /PaceServletExamples/rest/Requests
				return dao.getAllRequests();
			}
			// GET ONE LOGIC
			if (path.length == 5) {		// execute if request looks like /PaceServletExamples/rest/employees/3
				try {
					int requestId = Integer.valueOf(path[4]);
					return dao.getRequestById(requestId);
				} catch (NumberFormatException e) {
					return "Cannot convert " + path[4] + " into a number";
				} catch (EmployeeNotFoundException e) {
					return e.getMessage();
				}
			}
		}
		
		if (request.getMethod().equals("POST")) {
			// CREATE LOGIC
			if (request.getHeader("Content-Type").equals("application/json")) {
				try {
					Request Request = mapper.readValue(request.getReader(), Request.class);
					return dao.createRequest(Request);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				// 415 is an Unsupported Media Type
				response.sendError(415, "Please create using application/json");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (request.getMethod().equals("UPDATE")) {
			// UPDATE LOGIC
			if (request.getHeader("Content-Type").equals("application/json")) {
				try {
					Request Request = mapper.readValue(request.getReader(), Request.class);
					return dao.updateRequest(Request);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				// 415 is an Unsupported Media Type
				response.sendError(415, "Please create using application/json");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (request.getMethod().equals("DELETE")) {
			// DELETE LOGIC
			if (request.getHeader("Content-Type").equals("application/json")) {
				String[] path = request.getRequestURI().split("/");
				try {
					int requestId = Integer.valueOf(path[4]);
					return dao.deleteRequest(requestId);
				} catch (NumberFormatException e) {
					return "Cannot convert " + path[4] + " into a number";
				} catch (EmployeeNotFoundException e) {
					return e.getMessage();
				}
			}
			try {
				// 415 is an Unsupported Media Type
				response.sendError(415, "Please create using application/json");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}