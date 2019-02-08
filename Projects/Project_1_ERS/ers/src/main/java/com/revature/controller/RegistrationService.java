package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.ers.database.*;

/**
 * Servlet implementation class RegistrationService
 */
public class RegistrationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationService() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employees newEmp = new Employees();
		
		String username = request.getParameter("fname");
		String password = request.getParameter("lname");
		String fname = request.getParameter("username");
		String lname = request.getParameter("password");
		int man;
		try {
			 man = Integer.parseInt(request.getParameter("isman").toString());
		}catch(NullPointerException e)
			{
				man = 0;
			}
		boolean reg = newEmp.register( fname, lname, username, password, man);
		
		if(reg == true)
			{
				response.sendRedirect("managerPage.jsp");
			}
		else
			{
				PrintWriter pw = response.getWriter();
				
				pw.print(" <html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"ISO-8859-1\" http-equiv=\"refresh\" content=\"10; URL= register.jsp\">\r\n" + 
						"<title>Insert title here</title>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"<h1>Registration Failed. Try a different username, if problem persists contact admin <br> Redirecting to registration...</h1>\r\n" + 
						"\r\n" +  
						"\r\n" + 
						"</body>\r\n" + 
						"</html>");
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
