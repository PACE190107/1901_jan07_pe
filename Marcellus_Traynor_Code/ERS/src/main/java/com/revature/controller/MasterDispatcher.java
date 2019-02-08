package com.revature.controller;



//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

//import com.revature.models.Employee;
//import com.revature.models.Reimbursement;
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
//		String value = request.getParameter("btn");
//		System.out.println(value);
//		switch(value)
//		{
//		case "Login": log.info("call authentication method");
//			Employee attempting = loginService.attemptAuthentication(request, response);
//			return attempting;
//		case "Logout": log.info("call logout method");
//			loginService.logout(request, response);
//			return null;
//		case "Edit": log.info("call updateEmployee method");
//			List<Employee> employeeInfo = employeeService.updateInfo(request, response);
//			return employeeInfo;
//		case "All": log.info("call empReimbursements method");
//			List<Reimbursement> list = reimburseService.empReimbursements(request, response);
//			return list;
//		case "Pending": log.info("call empPending method");
//			List<Reimbursement> list1 = reimburseService.empPendingReimbursements(request, response);
//			return list1;
//		case "Resolved": log.info("call empResolved method");
//			List<Reimbursement> list2 = reimburseService.empResolvedReimbursements(request, response);
//			return list2;
//		case "CreateNew": log.info("call createReimbursement method");
//			reimburseService.createReimbursement(request, response);
//			return null;
//		case "SingleEmployee": log.info("call manEmployeeReimbursements method");
//			List<Reimbursement> list3 = reimburseService.manEmployeeReimbursements(request, response);
//			return list3;
//		case "AllEmployees": log.info("call manAllReimbursements method");
//			List<Reimbursement> list4 = reimburseService.manAllReimbursements(request, response);
//			return list4;
//		case "PendingRequests": log.info("call manPending method");
//			List<Reimbursement> list5 = reimburseService.manPendingReimbursements(request, response);
//			return list5;
//		case "ResolvedRequests": log.info("call manResolved method");
//			List<Reimbursement> list6 = reimburseService.manResolvedReimbursements(request, response);
//			return list6;
//		case "Approve": log.info("call updateReimbursement/approve method");
//			reimburseService.updateReimbursement(request, response);
//			return null;
//		case "Deny": log.info("call updateReimbursement/deny method");
//			reimburseService.updateReimbursement(request, response);
//			return null;
//		default: log.info("Not yet implemented");
//			return null;
//		}
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
//				Employee e = new Employee();
//				return e;
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
//				Reimbursement r = new Reimbursement();
//				return r;
			}
			
			if(request.getParameter("btn").equals("SingleEmployee"))
			{
				log.info("call manEmployeeReim method");
				return reimburseService.manEmployeeReimbursements(request, response);
//				Reimbursement r = new Reimbursement();
//				return r;
			}
//			
//			if(request.getParameter("btn").equals("AllEmployees"))
//			{
//				log.info("call manAllReim method");
//				reimburseService.manAllReimbursements(request, response);
////				Reimbursement r = new Reimbursement();
////				return r;
//			}
//			
//			if(request.getParameter("btn").equals("PendingRequests"))
//			{
//				log.info("call manPendingReim method");
//				reimburseService.manPendingReimbursements(request, response);
////				Reimbursement r = new Reimbursement();
////				return r;
//			}
//			
//			if(request.getParameter("btn").equals("ResolvedRequests"))
//			{
//				log.info("call manResolvedReim method");
//				reimburseService.manResolvedReimbursements(request, response);
////				Reimbursement r = new Reimbursement();
////				return r;
//			}
			
			if(request.getParameter("btn").equals("Approve"))
			{
				log.info("call updateReimbursement/approve method");
				reimburseService.updateReimbursement(request, response);
//				Reimbursement r = new Reimbursement();
//				return r;
			}
			
			if(request.getParameter("btn").equals("Deny"))
			{
				log.info("call updateReimbursement/deny method");
				reimburseService.updateReimbursement(request, response);
//				Reimbursement r = new Reimbursement();
//				return r;
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