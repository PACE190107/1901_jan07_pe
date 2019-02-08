package com.revature.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String[] path = request.getRequestURI().split("/");
		
		if(request.getParameter("btn") != null)
		{
			if(request.getParameter("btn").equals("Login"))
			{
				log.info("call authentication method");
				loginService.attemptAuthentication(request, response);
			}
			
			if(request.getParameter("btn").equals("Logout"))
			{
				log.info("call logout method");
				loginService.logout(request, response);
			}
			
			if(request.getParameter("btn").equals("Edit"))
			{
				log.info("call updateEmployee method");
				return employeeService.updateInfo(request, response);
			}
			
			if(request.getParameter("btn").equals("CreateNew"))
			{
				log.info("call createReimbursement method");
				reimburseService.createReimbursement(request, response);
			}
			
			if(request.getParameter("btn").equals("SingleEmployee"))
			{
				log.info("call assign empUsername to session");
				String empUsername = request.getParameter("empUsername");
				
				HttpSession session = request.getSession();
				session.setAttribute("empUsername", empUsername);
				
				try 
				{
					request.getRequestDispatcher("manager_home.html").forward(request, response);
				} 
				catch (ServletException e) 
				{
					log.error("error occured in ServletException catch block in ReimbursementServiceImpl create");
					log.error(e.getMessage());
					log.error(e.getStackTrace());
				} 
				catch (IOException e) 
				{
					log.error("error occured in IOException catch block in ReimbursementServiceImpl create");
					log.error(e.getMessage());
					log.error(e.getStackTrace());
				}
			}
			
			if(request.getParameter("btn").equals("Approve"))
			{
				log.info("call updateReimbursement/approve method");
				reimburseService.updateReimbursement(request, response);
			}
			
			if(request.getParameter("btn").equals("Deny"))
			{
				log.info("call updateReimbursement/deny method");
				reimburseService.updateReimbursement(request, response);
			}
		}
		
		log.info(path.length);
		if(path.length == 4)
		{
			System.out.println(path[3]);
			if(path[3].equals("empReimbursements"))
			{
				log.info("call empReimbursements method");
				return reimburseService.empReimbursements(request, response);
			}
			
			if(path[3].equals("getEmployeeInfo"))
			{
				log.info("call getEmployeeInfo method");
				return employeeService.getEmployeeInfo(request, response);
			}
			
			if(path[3].equals("getAllEmployeeInfo"))
			{
				log.info("call getAllEmployeeInfo method");
				return employeeService.getAllEmployeeInfo(request, response);
			}
			
			if(path[3].equals("empPending"))
			{
				log.info("call empPending method");
				return reimburseService.empPendingReimbursements(request, response);
			}
			
			if(path[3].equals("empResolved"))
			{
				log.info("call empResolved method");
				return reimburseService.empResolvedReimbursements(request, response);
			}
			
			if(path[3].equals("singleEmployeeReim"))
			{
				log.info("call manEmployeeReimbursements method");
				return reimburseService.manEmployeeReimbursements(request, response);	
			}
			
			if(path[3].equals("allEmployeeReim"))
			{
				log.info("call manAllReimbursements method");
				return reimburseService.manAllReimbursements(request, response);
			}
			
			if(path[3].equals("allPendingReim"))
			{
				log.info("call manPending method");
				return reimburseService.manPendingReimbursements(request, response);
			}
			
			if(path[3].equals("allResolvedReim"))
			{
				log.info("call manResolved method");
				return reimburseService.manResolvedReimbursements(request, response);
			}
		}
		return null;
	}
}