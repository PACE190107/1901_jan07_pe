package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceImpl;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

public class MasterDispatcher 
{
	private MasterDispatcher() {}
	
	private static final ReimbursementService reimburseService = new ReimbursementServiceImpl();
	private static final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final LoginService loginService = new LoginServiceImpl();
	final static Logger log = Logger.getLogger(MasterDispatcher.class);
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) 
	{
		if(request.getParameter("btn").equals("Login"))
		{
			log.info("call authentication method");
			loginService.attemptAuthentication(request, response);
		}
		else if(request.getParameter("btn").equals("Logout"))
		{
			log.info("call logout method");
			loginService.logout(request, response);
		}
		else if(request.getParameter("btn").equals("Edit"))
		{
			log.info("call updateEmployee method");
			employeeService.updateInfo(request, response);
		}
		else if(request.getParameter("btn").equals("Refresh"))
		{
			log.info("call getEmployeeInfo method");
			employeeService.getEmployeeInfo(request, response);
		}
		else if(request.getParameter("btn").equals("All"))
		{
			log.info("call empReimbursements method");
			reimburseService.empReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("Pending"))
		{
			log.info("call empPending method");
			reimburseService.empPendingReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("Resolved"))
		{
			log.info("call empResolved method");
			reimburseService.empResolvedReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("CreateNew"))
		{
			log.info("call createReimbursement method");
			reimburseService.createReimbursement(request, response);
		}
		else if(request.getParameter("btn").equals("SingleEmployee"))
		{
			log.info("call manEmployeeReimbursements method");
			reimburseService.manEmployeeReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("AllEmployees"))
		{
			log.info("call manAllReimbursements method");
			reimburseService.manAllReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("PendingRequests"))
		{
			log.info("call manPending method");
			reimburseService.manPendingReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("ResolvedRequests"))
		{
			log.info("call manResolved method");
			reimburseService.manResolvedReimbursements(request, response);
		}
		else if(request.getParameter("btn").equals("Approve"))
		{
			log.info("call updateReimbursement/approve method");
			reimburseService.updateReimbursement(request, response);
		}
		else if(request.getParameter("btn").equals("Deny"))
		{
			log.info("call updateReimbursement/approve method");
			reimburseService.updateReimbursement(request, response);
		}
		else 
		{
			return "Not yet implemented";
		}
		return null;
	}
}