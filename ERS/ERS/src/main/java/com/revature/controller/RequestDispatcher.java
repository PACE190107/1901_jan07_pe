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
		case "managerhome":
			homeDelegate.goHome(req,resp);
		case "login":
			if("POST".equals(req.getMethod())) {
				System.out.println("Login post");
			loginDelegate.goodLogin(req, resp);
		} else {
			System.out.println("login getpage");
			loginDelegate.getPage(req, resp);
		} break;
		case "logout":
			System.out.println("logout");
			loginDelegate.logout(req, resp);
			break;
		case "viewEmployees":
			System.out.println("viewEmployees");
			homeDelegate.viewEmployees(req, resp);
			
		case "newRequest":
			System.out.println("newRequest");
			homeDelegate.newRequest(req, resp);
		case "viewRequests":
			homeDelegate.viewRequests(req, resp);
			break;
		case"viewEmployeeRequests":
			System.out.println("viewEmployeeRequests");
			homeDelegate.viewEmployeeRequests(req, resp);
			break;
		
		case "viewPendingRequests":
			System.out.println("viewPendingRequests");
			homeDelegate.viewPendingRequests(req,resp);
			break;
		case "viewResolvedRequests":
			System.out.println("viewResolvedRequests");
			homeDelegate.viewResolvedRequests(req, resp);
			break;
		case "approveDeny":
			System.out.println("approveDeny");
			homeDelegate.approveDeny(req, resp);
		case "updateUser":
			System.out.println("updateUser");
			homeDelegate.updateUser(req,resp);
			break;
			
		default:
			break;
		}
	}

}
