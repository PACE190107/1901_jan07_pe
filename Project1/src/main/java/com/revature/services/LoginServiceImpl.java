package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.UserDaoImpl;
import com.revature.model.User;

public class LoginServiceImpl implements LoginService{

	//private final DataSource dataSource = DataSource.getInstance();
	
	
	public User attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		
		System.out.println("LoginServiceImpl works for attemptAuthentication");
		User us = UserDaoImpl.getUsDa().getUser(username);
		//System.out.println("this is what is being compared password: " + password + "username: " + username);
		
		if(us != null) {
			if(us.getPassword().contentEquals(password)) {
				request.getSession().setAttribute("firstname", us.getFirstname());
				request.getSession().setAttribute("username", us.getUsername());
				request.getSession().setAttribute("id", us.getId());
				
				
				if(us.getIsManager() == 1) {
					try {
						request.getRequestDispatcher("/ManagerHome.jsp").forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						request.getRequestDispatcher("/EmployeeHome.jsp").forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				return us;
			}else {
				request.getSession().setAttribute("nope", "Does not exist");
				try {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			request.getSession().setAttribute("nope", "Does not exist");
			try {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
