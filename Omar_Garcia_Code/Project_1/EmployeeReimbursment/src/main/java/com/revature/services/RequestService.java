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

}
