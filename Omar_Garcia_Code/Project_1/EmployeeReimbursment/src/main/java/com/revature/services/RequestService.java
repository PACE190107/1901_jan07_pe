package com.revature.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.RequestDaoImplementation;
import com.revature.module.Request;

public class RequestService {
	
	private static RequestService service;
	private final ObjectMapper mapper = new ObjectMapper();
	
	private RequestService() {};
	
	public static RequestService getRequestService() {
		if(service == null) {
			service = new RequestService();
		}
		return service;
	}
	
	public List<Request> getAllRequest(int i) throws SQLException{
		return RequestDaoImplementation.getDaoImplementation().getAllRequest(i);
	}
	
	public Request addRequest(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		Request request;
			try {
				request = mapper.readValue(req.getReader(), Request.class);
				return RequestDaoImplementation.getDaoImplementation().addRequest(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public Request reqChange(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		Request request = null;
		try {
			request = mapper.readValue(req.getReader(), Request.class);
		return RequestDaoImplementation.getDaoImplementation().updateRequest(request);
				} catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}
	
	public List<Request> getEmpReq(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
		int id = 0;
		int approval = 0;
		
		if(req.getMethod().equals("GET")) {
			return RequestDaoImplementation.getDaoImplementation().viewEmpRequest(Integer.parseInt(req.getParameter("id")), Integer.parseInt(req.getParameter("approval"))); 
		} else if (req.getMethod().equals("POST")) {
			try {
				Request request = mapper.readValue(req.getReader(), Request.class);
				id = request.getId();
				approval = request.getApproved();
				
				System.out.println(id + " " + approval);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return RequestDaoImplementation.getDaoImplementation().viewEmpRequest(id, approval);
	}

}
