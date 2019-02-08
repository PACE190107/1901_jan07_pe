package com.revature.delegate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.Login;
import com.revature.data.LoginDao;
import com.revature.data.LoginOracle;
import com.revature.exceptions.FailedLoginException;
import com.revature.util.ConnectionUtil;

public class LoginDelegate {
	public LoginDao ld = new LoginOracle();
	final static Logger log = Logger.getLogger(ConnectionUtil.class);
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		Login login = ld.login(username, password);
		if(login == null) {
			try {
				throw new FailedLoginException();
			}
			catch (FailedLoginException e) {
				log.info(e.getMessage());
				log.info("Login failed");
			}
			resp.sendRedirect("login");
		} else {
			log.info("Login successful");
			HttpSession session = req.getSession();
			session.setAttribute("user", login);
			resp.sendRedirect("home");
		}
	}
	
	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null) {
			req.getRequestDispatcher("static/login.html").forward(req,resp);
		} else {
			resp.sendRedirect("home");
		}
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("login");
	}
}