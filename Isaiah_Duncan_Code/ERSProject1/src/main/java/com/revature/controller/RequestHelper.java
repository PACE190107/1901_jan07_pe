package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.delegate.HomeDelegate;
import com.revature.delegate.LoginDelegate;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class RequestHelper {
	
	//create instance of logger
	final static Logger Log = Logger.getLogger(ConnectionUtil.class);	
	
	//create instances of delegates to access delegate methods
	private HomeDelegate hd = new HomeDelegate();
	private LoginDelegate ld = new LoginDelegate();
	
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get session instance
		HttpSession session = req.getSession();
		Employee employeeDetails = (Employee) session.getAttribute("currentUser");
		
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		
		//switch case to redirect based on HTTP URI
		switch(switchString) {
		case "home": 
			hd.goHome(req, resp); 
			break;
		case "login": 
			Log.info("Login switch case triggered!");
			if("POST".equals(req.getMethod())) {
				ld.login(req, resp);
			} else {
				ld.getPage(req, resp);
			} 
			break;
		case "logout": ld.logout(req, resp); break;
		case "managerhome":
			Log.info("Manager home switch case triggered!");
			hd.goHome(req, resp);
			break;
		/*
		 * 
		 * EMPLOYEE CASES
		 * 
		 * */
		case "addexpense":
			Log.info("addexpense switch case triggered");
			hd.addExpense(req, resp);
			break;
		case "getemployeeresolved":
			Log.info("getemployeeresolved switch case triggered!");
			hd.getResolvedRequests(req, resp);
			break;
		case "getemployeeunresolved":
			Log.info("getemployeeunresolved switch case triggered!");
			hd.getPendingRequests(req, resp);
			break;
		case "editprofile":
			Log.info("editprofile switch case triggered!");
			hd.updateProfile(req, resp);
			break;
		/*
		 * 
		 * MANAGER CASES
		 * 
		 * */
		case "viewallemployees":
			Log.info("viewallemployees switch case triggered!");
			hd.viewAllEmployees(req, resp);
			break;
		case "viewallpendingrequests":
			Log.info("viewallpendingrequests switch case triggered!");
			hd.viewAllPendingRequests(req, resp);
			break;
		case "viewallresolvedrequests":
			Log.info("viewallresolvedrequests switch case triggered!");
			hd.viewAllResolvedRequests(req, resp);
			break;
		case "createemployee":
			Log.info("viewallresolvedrequests switch case triggered!");
			hd.createEmployee(req, resp);
			break;
		case "viewallrequests": 
			Log.info("viewallresolvedrequests switch case triggered!");
			hd.viewAllRequests(req, resp);
			break;
		case "resolverequest":
			Log.info("resolverequest switch case triggered!");
			hd.resolveRequest(req, resp);
			break;
		default: 
			Log.info("Default switch case triggered!");
			break;
		}
	}
}
