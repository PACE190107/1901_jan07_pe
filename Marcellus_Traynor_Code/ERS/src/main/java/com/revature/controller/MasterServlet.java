package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceImpl;

public class MasterServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4610864359249189402L;
	
	// This is the object responsible for marshalling/unmarshalling POJOS to JSON
	private final ObjectMapper mapper = new ObjectMapper();
	private final LoginService loginService = new LoginServiceImpl();
	final static Logger log = Logger.getLogger(MasterServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("application/json");
		resp.getWriter().append(mapper.writeValueAsString(MasterDispatcher.process(req, resp)));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		if(req.getRequestURI().endsWith("/home"))
		{
			final String username = req.getParameter("username");
			final String password = req.getParameter("password");
			
			log.info("authenticating employee");
			Employee attempting = loginService.attemptAuthentication(username, password);
			
			if ((attempting != null) && (attempting.getJob().equalsIgnoreCase("Employee"))) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				req.getRequestDispatcher("employee_home.html").forward(req, resp);
			}
			else if ((attempting != null) && (attempting.getJob().equalsIgnoreCase("Manager"))) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				req.getRequestDispatcher("manager_home.html").forward(req, resp);
			}
			else
			{
				resp.sendRedirect("/ERS");
			}
		}
		else
		{
			doGet(req, resp);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}
}