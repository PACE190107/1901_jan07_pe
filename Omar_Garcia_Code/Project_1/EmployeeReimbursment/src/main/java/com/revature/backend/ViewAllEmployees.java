package com.revature.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.EmployeeService;

public class ViewAllEmployees extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
		System.out.println("Viewing all users" );
		rep.setContentType("JSON");
		PrintWriter writer=rep.getWriter();
		String response;
		try {
			response = EmployeeService.getEmployeeService().viewAllEmployees();
			
			if(response != null) {
				rep.setStatus(200);
				writer.append(response);
			}
			else {
				rep.setStatus(400);
				writer.append("No Users Found");
			}
		} catch (SQLException e) {
			rep.setStatus(500);
			writer.append("SQL error");
		}
	}
}
