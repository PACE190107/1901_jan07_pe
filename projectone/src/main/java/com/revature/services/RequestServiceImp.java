package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.RequestDao;
import com.revature.dao.RequestDaoImp;

public class RequestServiceImp implements RequestService{
	
	private static RequestDao dao = new RequestDaoImp();
	private static RequestServiceImp reqServ;
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object process(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getMethod().contentEquals("GET")) {
			String[] path = req.getRequestURI().split("/");
			if (path.length == 5 && req.getRequestURI().contains("pending")) {
				System.out.println(dao.getPendingReqs());
				return dao.getPendingReqs();
			}
		}
		
		return null;
	}

	
	

}
