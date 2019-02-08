package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.ers.database.*;

 
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		EmployeeModel ed = new EmployeeModel();
		
		boolean success = ed.login(un, pw);
		
		if(success == false)
			{
				response.sendRedirect("invalid.jsp");
			}
		else
			{
				request.getSession().setAttribute("name", ed.getName());
				request.getSession().setAttribute("id", ed.getID());
				request.getSession().setAttribute("manager", ed.checkManager());
				
				if(ed.checkManager() > 0)
					{
						RequestDispatcher dispatcher = request.getRequestDispatcher("managerPage.jsp");
						dispatcher.forward(request, response);
					}
				else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("employeePage.jsp");
					dispatcher.forward(request, response);
				}
				
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
