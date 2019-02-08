package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Login;
import com.revature.util.ConnectionUtil;
import com.revature.util.MD5;

public class LoginDelegate {
public LoginDao ld = new LoginDaoImpl();
final static Logger Log = Logger.getLogger(ConnectionUtil.class);
	
	/**
	 * PURPOSE: if logged in, send to login area. else establish session and redirect to emp or manager home
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Log.info("login method in login delegate triggered!");
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		Login login = ld.login(username, password);
		if(login == null) {
			resp.sendRedirect("login");
		} else {
			Employee empDetails = ld.getEmpDetails(login.getUsername());
			HttpSession session = req.getSession();
			session.setAttribute("user", login);
			session.setAttribute("currentUser", empDetails);
			Employee employeeDetails = (Employee) session.getAttribute("currentUser");
			if(employeeDetails.getIsManager().equals("Y")) {
				resp.sendRedirect("managerhome");
			} else {
				resp.sendRedirect("home");
			}
			
		}
	}
	
	/**
	 * PURPOSE: redirect user depending on login state and if they're a manager
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null) {
			req.getRequestDispatcher("static/login.html").forward(req,resp);
		} else {
			Employee employeeDetails = (Employee) session.getAttribute("currentUser");
			if(employeeDetails.getIsManager().equals("Y")) {
				resp.sendRedirect("managerhome");
			} else {
				resp.sendRedirect("home");
			}
		}
	}
	
	
	/**
	 * PURPOSE: invalidates session and redirects to login area
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("login");
	}
}
