package com.revature.delegate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reavature.tjordan.models.Employee;
import com.reavature.tjordan.models.ReimbursementReq;
import com.revature.tjordan.base.MD5;
import com.revature.tjordan.services.EmployeeService;
import com.revature.tjordan.services.ReimbursementReqService;

public class ManagerDelegate {
	
	
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	public void getAllPending(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<ReimbursementReq> allPending = ReimbursementReqService.getReimbursementService().getAllPendingMan();
		
		resp.setContentType("application/json");
		resp.getWriter().append(mapper.writeValueAsString(allPending));
		
		
	}
	
	
	
	public void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get all values from form
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String isManager = req.getParameter("type");
		String encrypt = MD5.getMd5(password);
		boolean wasAdded = EmployeeService.getEmployeeService().addEmployee(userName, firstName, lastName, encrypt, email, isManager);
		
		
		if(wasAdded) {
			req.getRequestDispatcher("static/ManagerIndex.jsp").forward(req, resp);
		} else {
			System.out.println("Didnt Add Throw Exception here or throw to error page");
		}
		
		
		
		
		
	}
	
	
	
	public void editProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println("inside employeedelegate");
		
		System.out.println(session.getAttribute("currentEmployee"));
		Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
		if(session.getAttribute("currentEmployee") == null) {
			resp.sendRedirect("login");
		}  if(currentEmployee.getIsManager().equals("Y")) {
			//Get all Pending Request of the session employee
			try {
//				req.getRequestDispatcher("static/EmployeeHomePage.html").forward(req,resp);
				req.getRequestDispatcher("static/editProfile.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			resp.sendRedirect("login");
			req.getSession().invalidate();
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void viewProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println("inside employeedelegate");
		
		System.out.println(session.getAttribute("currentEmployee"));
		Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
		if(session.getAttribute("currentEmployee") == null) {
			resp.sendRedirect("login");
		}  if(currentEmployee.getIsManager().equals("Y")) {
			try {
//				req.getRequestDispatcher("static/EmployeeHomePage.html").forward(req,resp);
				req.getRequestDispatcher("static/profileView.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			resp.sendRedirect("login");
			req.getSession().invalidate();
			
			
			
		}
	}
	
	
	
	
	
	
	
	
public void goHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in goHome");
		// Get our session information
	System.out.println(req.getSession());
				HttpSession session = req.getSession();
				System.out.println(session);
				System.out.println(session.getAttribute("currentEmployee"));
				if(session.getAttribute("currentEmployee") == null) {
					resp.sendRedirect("/Project1ERS/");
					req.getSession().invalidate();
				} else {
					Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
					System.out.println("currentEmployee in managerdelegate: " + currentEmployee.getEmp_userName());
					//System.out.println("inside employeedelegate");
					if(currentEmployee.getIsManager().equals("Y")) {
						try {
//							req.getRequestDispatcher("static/ManagerHome.html").forward(req,resp);
							req.getRequestDispatcher("static/ManagerIndex.jsp").forward(req, resp);
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						//resp.sendRedirect("login");
						req.getRequestDispatcher("static/unauthorized.html").forward(req, resp);
						req.getSession().invalidate();
					}
				}
				
				
	}
}
