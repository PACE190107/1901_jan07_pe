package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.service.LoginService;
import com.revature.service.LoginServiceImpl;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

public class LoginDispatcher {

	private LoginDispatcher() {}
	
	// Command and Mediator Design Pattern
	
	private static final LoginService loginService = new LoginServiceImpl();
	private static final ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	final static Logger log = Logger.getLogger(LoginDispatcher.class);
	
	public static Object process(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException, ServletException {

		if(req.getRequestURI().contains("reimbursements")) {
			return reimbursementService.process(req, resp);
		}else if(req.getRequestURI().contains("login")) {
			return loginService.process(req, resp);
		} else {
			return "Not yet implemented";
		}
	}
}
