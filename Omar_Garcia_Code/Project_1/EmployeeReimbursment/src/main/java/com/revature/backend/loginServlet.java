package com.revature.backend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDaoImplementation;
import com.revature.module.Employee;

public class loginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("works");
		Employee emp = null;
		try {
			emp = EmployeeDaoImplementation.getEmployeeDao().login(req.getParameter("username"),
					req.getParameter("password"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (emp != null) {
			resp.setStatus(300);
			req.getSession().setAttribute("username", emp.getUsername());
			req.getSession().setAttribute("firstname", emp.getFirstName());
			req.getSession().setAttribute("lastname", emp.getLastName());
			req.getSession().setAttribute("email", emp.getEmail());
			req.getSession().setAttribute("id", emp.getId());
			req.getSession().setAttribute("manager", emp.isManager());
			resp.sendRedirect("home.jsp");
			// req.getRequestDispatcher("home.jsp").forward(req, resp);
			// req.getRequestDispatcher("HTML/employeePage.html").forward(req, resp);
			/*if (emp.isManager()) {
				System.out.println("Manager");
				// req.getRequestDispatcher("employeePage.jsp").forward(req, resp);
				// resp.sendRedirect("HTML/managerPage.html");
				resp.sendRedirect("managerPage.jsp");
			} else if (!emp.isManager()) {
				System.out.println("Employee");
				resp.sendRedirect("employeePage.jsp");
			}
*/
		} else {
			resp.setStatus(300);
			req.getSession().setAttribute("error", "Invalid credentials");
			//req.getRequestDispatcher("HTML/.html").forward(req, resp);
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
