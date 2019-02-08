package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.services.UserService;

public class LoginDelegate {
	
	public void goodLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//HAVE to be the same as the name in the form.
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println("Inside of login delegate");
		System.out.println("Email: " + email);
		System.out.println("Password: " + password );
		
		User tempUser = UserService.getUserService().goodLogin(email, password);
		
		System.out.println(tempUser);
		
		if(tempUser == null) {
			System.out.println("invalid login");
			resp.sendRedirect("login");
		} else {
			System.out.println("redirect to homedelegate");
			HttpSession sesh = req.getSession();
			sesh.setAttribute("currentUser", tempUser);
			resp.sendRedirect("home");
			
			
}
//		
//		if(login == null) {
//			resp.sendRedirect("login");
//		} else {
//			HttpSession session = req.getSession();
//			session.setAttribute("user", login);
//			resp.sendRedirect("home");
//		}
	}

	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null) {
			req.getRequestDispatcher("index.jsp").forward(req,resp);
		} else {
			resp.sendRedirect("home");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("login");
	}
	

}
