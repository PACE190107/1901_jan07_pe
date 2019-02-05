package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;

public class HomeDelegate {
	
	public void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("inside HomeDelegate");
		// Get our session information
		HttpSession session = req.getSession();
		User tempUser = (User) session.getAttribute("currentUser");
		
		
		
		//if tempUser if a manager
		
		//redirect to managerHome.jsp
		
		//if not a manager send to employeeHome.jsp
		
		
		
		
		
		//sends to employeehome.jsp
		try {
			req.getRequestDispatcher("employeeHome.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// Give a personalized response
		

//		if (login == null) {
//			resp.sendRedirect("login");
//		} else {
//			PrintWriter pw = resp.getWriter();
//			pw.write("<!DOCTYPE html><html><head>" + "<meta charset=\"ISO-8859-1\"><title>HelloWorld</title>"
//					+ "</head><body>");
//
//			pw.write("<div><div style=\"background-color:" + login.getFavColor() + "\">" + "<h4>Hello "
//					+ login.getUsername() + "</h4></div>" + "<form action=\"logout\" method=\"post\">"
//					+ "<input type=\"submit\" value=\"Logout\"/>" + "</form></div>");
//
//			pw.write("</body></html>");
//		}
//	}

}
}
