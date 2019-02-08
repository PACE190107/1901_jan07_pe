package com.revature.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceImp;

public class LoginServlet extends HttpServlet{
	private final ObjectMapper mapper = new ObjectMapper();
	private static final long serialVersionUID = 1L;

	private final LoginService service = new LoginServiceImp();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doGet");
		doPost(req, resp);
}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String username = req.getParameter("username");
		final String passw0rd = req.getParameter("passw0rd");// <input name="passw0rd" >
		System.out.println(username);
//		Employee testEmployee = null;
//		testEmployee = mapper.readValue(req.getReader(), Employee.class);
//		final String username = testEmployee.getUserName();
//		final String passw0rd = testEmployee.getPassword();
		System.out.println("servlet line 31");
		
		Employee attempting = service.attemptAuthentication(username, passw0rd);
		System.out.println("servlet line 33");
		System.out.println("username" +username);
		System.out.println("attempting" +attempting);
		//System.out.println(attempting.getFirstName());
		//System.out.println(attempting.getIsManager());
		if (attempting != null && attempting.getIsManager()==1) {
			System.out.println("in if for manager");
			req.getSession().setAttribute("user_id", attempting.getUser_id());
			req.getRequestDispatcher("manager.html").forward(req, resp);
			
		} else if(attempting != null && attempting.getIsManager()==0){req.getSession().setAttribute("user_id", attempting.getUser_id());
				req.getRequestDispatcher("employee.html").forward(req, resp);
		}
	}


}
