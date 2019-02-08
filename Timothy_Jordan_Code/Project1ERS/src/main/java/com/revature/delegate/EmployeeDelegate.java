package com.revature.delegate;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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



public class EmployeeDelegate {
	
	
//		public Employee info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			//HttpSession session = req.getSession();
//			//Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
//			Employee tempEmployee = EmployeeService.getEmployeeService().getEmployee("twjordan");
//			return tempEmployee;
//			
//			
//		}
//		
//		public Object processAVC(HttpServletRequest req, HttpServletResponse resp) {
//			HttpSession session = req.getSession();
//			Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
//			//Employee currentEmployee = EmployeeService.getEmployeeService().getEmployee("twjordan");
//			return currentEmployee;
//			
//			
//		}
	
	
	
	
	
		
		private final ObjectMapper mapper = new ObjectMapper();
		
		
		public void getAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Make call to get all users
			List<Employee> allEmployees = EmployeeService.getEmployeeService().getAllEmployees();
			
			resp.setContentType("application/json");
			resp.getWriter().append(mapper.writeValueAsString(allEmployees));
			
		}
		
		
		
		
		public void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Get the current session/employee
			HttpSession session = req.getSession();
			Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
			
			int emp_ID = currentEmployee.getEmp_ID();
			
			String userName = currentEmployee.getEmp_userName();
			
			String sessionPassword = currentEmployee.getEmp_password();
			System.out.println("sessionPassword"  + sessionPassword);
			
			
			//Get values from the form submit
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String inputPassword = req.getParameter("password");
			String password = "";
			
			
			//Compare new password with previous password.
			
			//if not same then add.
			if(!(inputPassword.equals(sessionPassword))) {
				//Encrypt the new password
				 password = MD5.getMd5(inputPassword);
			} else {
				password = sessionPassword;
			}
			
			//Call update method and pass in parameters
			Employee updatedEmp = EmployeeService.getEmployeeService().updateEmployee(emp_ID, firstName, userName, lastName, email, password);
			
			//Update Session Variable
			session.setAttribute("currentEmployee", updatedEmp);
			
			//Check if manager or employee
			currentEmployee = (Employee)session.getAttribute("currentEmployee");
			if(currentEmployee.getIsManager().equals("Y")) {
				req.getRequestDispatcher("static/ManagerIndex.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("static/EmployeeIndex.jsp").forward(req, resp);
			}
			
			
			
		}
		
		
		public void getAllResolved(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
			List<ReimbursementReq> resolvedReq = ReimbursementReqService.getReimbursementService().getAllResolved(currentEmployee.getEmp_ID());
			for(ReimbursementReq x : resolvedReq) {
				System.out.println(x);
			}
			
			resp.setContentType("application/json");
			resp.getWriter().append(mapper.writeValueAsString(resolvedReq));
		}
		
		
		public void getAllPending(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
			List <ReimbursementReq> pendingReq = ReimbursementReqService.getReimbursementService().getAllPending(currentEmployee.getEmp_ID());
			resp.setContentType("application/json");
			resp.getWriter().append(mapper.writeValueAsString(pendingReq));
		}
	
	
		public void editProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			System.out.println("inside employeedelegate");
			
			System.out.println(session.getAttribute("currentEmployee"));
			Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
			if(session.getAttribute("currentEmployee") == null) {
				resp.sendRedirect("login");
			}  if(currentEmployee.getIsManager().equals("Y")) {
				resp.sendRedirect("login");
				req.getSession().invalidate();
			}else {
				
				
				//Get all Pending Request of the session employee
				try {
//					req.getRequestDispatcher("static/EmployeeHomePage.html").forward(req,resp);
					req.getRequestDispatcher("static/editProfile.jsp").forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
			resp.sendRedirect("login");
			req.getSession().invalidate();
		}else {
			
			
			//Get all Pending Request of the session employee
			try {
//				req.getRequestDispatcher("static/EmployeeHomePage.html").forward(req,resp);
				req.getRequestDispatcher("static/profileView.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	public void goHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		// Get our session information
				HttpSession session = req.getSession();
				System.out.println("inside employeedelegate");
				
				System.out.println(session.getAttribute("currentEmployee"));
				Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
				if(session.getAttribute("currentEmployee") == null) {
					System.out.println("in the if");
					req.getRequestDispatcher("index.html").forward(req, resp);
					//resp.sendRedirect("login");
				}  if(currentEmployee.getIsManager().equals("Y")) {
					resp.sendRedirect("login");
					req.getSession().invalidate();
				}else {
					
					
					//Get all Pending Request of the session employee
					try {
//						req.getRequestDispatcher("static/EmployeeHomePage.html").forward(req,resp);
						req.getRequestDispatcher("static/EmployeeIndex.jsp").forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				
				
	}
}
