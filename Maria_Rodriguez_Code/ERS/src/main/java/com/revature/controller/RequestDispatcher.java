package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDispatcher {
	
	//instance of delegates
	private HomeDelegate homeDelegate = new HomeDelegate();
	private LoginDelegate loginDelegate = new LoginDelegate();
	
	
	
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
		
		System.out.println(" switchString :" + switchString);
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		switch(switchString) {
		case "home":
			homeDelegate.goHome(req, resp);
			break;
		case "login":
			if("POST".equals(req.getMethod())) {
				System.out.println("Login post");
			loginDelegate.login(req, resp);
		} else {
			System.out.println("login getpage");
			loginDelegate.getPage(req, resp);
		} break;
		case "logout":
			System.out.println("logout");
			loginDelegate.logout(req, resp);
			break;
		default:
			break;
		}
	}

}
