package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.reavature.tjordan.models.Employee;

import com.revature.tjordan.base.MD5;
import com.revature.tjordan.services.EmployeeService;

/**
 * @author Timothy Jordan
 * @Purpose Used to handle the login functionality being passed from the
 *          dispatcher class.
 * 
 *
 */
public class LoginDelegate {

	final static Logger loginLogger = Logger.getLogger(LoginDelegate.class);

	/**
	 * @Purpose Handles Login from a post request. Calls
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get User input from input form.
		String username = req.getParameter("user");
		String password = req.getParameter("pass");

		// Take the input password and apply the encryption to it.
		String encrypt = MD5.getMd5(password);

		// Create an Employee object from call to employeeDao method that returns
		Employee currentEmployee = EmployeeService.getEmployeeService().validateLogin(username, encrypt);
		// Check for invalid login.
		if (currentEmployee == null) {
			// If currentEmployee is null then invalid login.

			loginLogger.error("Invalid Login Credentails");
			loginLogger.info("Invalid Login Credentials");
			resp.sendRedirect("login");
		} else {
			loginLogger.info("Successful Login");
			// If not null then valid login.
			// Store Employee object as session variable.
			HttpSession session = req.getSession();
			session.setAttribute("currentEmployee", currentEmployee);
			// Determine if the employee object is a manager.

			if (currentEmployee.getIsManager().equals("Y")) {
				loginLogger.info("Redirecting to ManagerDelegate From Login Delegate.");
				// If manager then redirect to dispatcher with "manHome".
				resp.sendRedirect("manHome");
			} else {
				loginLogger.info("Redirecting to EmployeeDelegate From Login Delegate");
				// If not manager then redirect to dispatcher with "empHome".
				resp.sendRedirect("empHome");
			}
		}

	}

	/**
	 * Purpose: Redirect for invalid login.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();

		HttpSession session = req.getSession();
		if (session.getAttribute("currentEmployee") == null) {
			loginLogger.info("Session is null, redirect to homepage.");
			req.getSession().invalidate();
			req.getRequestDispatcher("index.html").forward(req, resp);
		} else {
			loginLogger.info("Invalid Login, Redirect to Login.");
			resp.sendRedirect("login");
			req.getSession().invalidate();

		}

	}

	/**
	 * @Purpose Used to execute the login function.
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		loginLogger.info("Session Invalidated.");
		resp.sendRedirect("login");

	}
}
