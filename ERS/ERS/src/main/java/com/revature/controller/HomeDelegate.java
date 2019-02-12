package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.services.RequestService;
import com.revature.services.UserService;

public class HomeDelegate {
	
	private final ObjectMapper mapper= new ObjectMapper();
	
final static Logger requestLogger = Logger.getLogger(HomeDelegate.class);
	//works!
	//method to make a new reimbursement///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Reimbursement newRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
		System.out.println("Inside method");
		
		//Get our session information
		HttpSession session = req.getSession();
		System.out.println(session);
		User tempUser = (User) session.getAttribute("currentUser");
		System.out.println(session.getAttribute("currentUser"));
		
		
		
		
	
		
				
		//HAVE to be the same as the name in the form.
			
				
				//int seq_id = 0;
				int e_id = tempUser.getE_id();
				System.out.println(e_id);
				String date_received = req.getParameter("date_received");
				String type = req.getParameter("type");
				double amount = (Double.parseDouble(req.getParameter("amount"))) ;
				String description = req.getParameter("description");
			
			
		
				
				
				Reimbursement request = RequestService.getRequestService().newRequest( e_id,  date_received, type,  amount, description); 
				
				
				
				if(request== null) {
					System.out.println("invalid request try again");
					resp.sendRedirect("home");
				} else { 
					System.out.println("redirect to homedelegate");
					
					HttpSession sesh = req.getSession();
					sesh.setAttribute("currentUser", tempUser);
					resp.sendRedirect("home");
				}
				return request;			
		
	}

	
	
	
	
	
	
//works!
// method to view all employees' requests/////////////////////////////////////////////////////////
			
			public void viewRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
				
				

				//Get our session information
				HttpSession session = req.getSession();
				User tempUser = (User) session.getAttribute("currentUser");
				
				//get e_id to check for requests
				int e_id = tempUser.getE_id();
			
				
				//where am I sending this method and what do i want back?
				List<Reimbursement> temprequest = RequestService.getRequestService().viewRequests( e_id ); 

				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(temprequest));
			
			}
			
//this one			
// Method to view all requests from a single employee/////////////////////////////////////////////////////////
	public void viewEmployeeRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
				
				

				//Get our session information
				HttpSession session = req.getSession();
				User tempUser = (User) session.getAttribute("currentUser");
				System.out.println (session.getAttribute("currentUser"));
				//get e_id to check for requests
				int e_id = tempUser.getE_id();
						//Integer.parseInt(req.getParameter("viewEmployeeRequests"));
//				String status = req.getParameter("viewEmployeeRequests");
//				int manager_id = tempUser.getE_id();
//			
				System.out.println(e_id);
				
				//where am I sending this method and what do i want back?
				List<Reimbursement> temprequest = RequestService.getRequestService().viewEmployeeRequests(e_id); 
				System.out.println(RequestService.getRequestService().viewEmployeeRequests(e_id));
				System.out.println(temprequest);
				
				
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(temprequest));
			
			}
			
			
			
			
			
			
//Manager Method to view all pending requests///////////////////////////////////////////////////////////////		
			public void viewPendingRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
				//Get our session information
				HttpSession session = req.getSession();
				User tempUser = (User) session.getAttribute("currentUser");
					
				
				//where am I sending this method and what do i want back?
				List<Reimbursement> temprequest = RequestService.getRequestService().viewPendingRequests( ); 
				
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(temprequest));
				
				
				
				
				
				
				
				
			}
			
			
			
//Manager Method to view all resolved requests///////////////////////////////////////////////////////////////			
			
			public void viewResolvedRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
				
				HttpSession session = req.getSession();
				User tempUser = (User) session.getAttribute("currentUser");
				
				//where am I sending this method and what do i want back?
				List<Reimbursement> temprequest = RequestService.getRequestService().viewResolvedRequests( ); 
				
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(temprequest));
				
				
				
			}
			
			
			
			
//Manager Method to view all employees/////////////////////////////////////////////////////////////		
			
			public void viewEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				
				// Get our session information
				HttpSession session = req.getSession();
				User tempUser = (User) session.getAttribute("currentUser");
				
				
				
				//where am I sending this method and what do i want back?
				ArrayList<User> request = UserService.getUserService().viewEmployees(); 
				
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(request));
				
				
			}
			
//works!	
//Method to direct user to the right home page//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void goHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("inside goHome");
		
		// Get our session information
		HttpSession session = req.getSession();
		User tempUser = (User) session.getAttribute("currentUser");
		System.out.println(tempUser);
		int isManager = tempUser.getManager();
		System.out.println(isManager);
		if(isManager == 1) {
			resp.sendRedirect("managerhome");
			try {
				req.getRequestDispatcher("static/managerHome.jsp").forward(req,resp);
			} catch (ServletException e) {
				
				e.printStackTrace();
			}
		}else {
			//System.out.println("in else of goHome");
		
			try {
				req.getRequestDispatcher("static/employeeHome.jsp").forward(req,resp);
			} catch (ServletException e) {
			
				e.printStackTrace();
			}
		}
		

	
		
}
	
	
	//approveDeny//////////////////////////////////////////////////////////////////////////
	
	public void approveDeny(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		// Get our session information
		HttpSession session = req.getSession();
		User tempUser = (User) session.getAttribute("currentUser");
		
		
		
	}
		
	
	//updateUser/////////////////////////////////////////////////////////////////////////////////
	
	public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		// Get our session information
		HttpSession session = req.getSession();
		User tempUser = (User) session.getAttribute("currentUser");
	System.out.println("in updateUser Method");
		
		//HAVE to be the same as the name in the form.
		int e_id = tempUser.getE_id();
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email") ;
		
	System.out.println("got parameters");
	
		User updateUser = UserService.getUserService().updateUser(e_id, firstName, lastName, email);
		 
	}
	
	
	
	
	
	
}

	
