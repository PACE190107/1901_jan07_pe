package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class LoginServiceImpl implements LoginService
{
	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	final static Logger log = Logger.getLogger(LoginServiceImpl.class);
	
	@Override
	public void attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	{
		
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		
		String hashPass = getHashedPassword(username, password);
			
		log.info("authenticating employee");
		String attempting = employeeDao.employeeLogin(username, hashPass);
		
			
		if ((attempting != null) && (attempting.equalsIgnoreCase("Employee"))) 
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			try 
			{
				log.info("employee_home");
				request.getRequestDispatcher("employee_home.html").forward(request, response);
			} 
			catch (ServletException e) 
			{
				log.error("error occured in ServletException catch block in LoginServiceImpl attemptAuthentication");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			} 
			catch (IOException e) 
			{
				log.error("error occured in IOException catch block in LoginServiceImpl attemptAuthentication");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
		}
		else if ((attempting != null) && (attempting.equalsIgnoreCase("Manager"))) 
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			try 
			{
				log.info("manager_home");
				request.getRequestDispatcher("manager_home.html").forward(request, response);
			} 
			catch (ServletException e) 
			{
				log.error("error occured in ServletException catch block in LoginServiceImpl attemptAuthentication");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
			catch (IOException e) 
			{
				log.error("error occured in IOException catch block in LoginServiceImpl attemptAuthentication");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
		}
		else
		{
			try 
			{
				log.info("invalid login, return to login screen");
				response.sendRedirect("/ERS");
			} 
			catch (IOException e) 
			{
				log.error("error occured in IOException catch block in LoginServiceImpl loginRedirect");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
		}
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response)
	{
		log.info("invalidating session");
		request.getSession().invalidate();

		try 
		{
			response.sendRedirect("/ERS");
		}
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in LoginServiceImpl logoutRedirect");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	public String getHashedPassword(String username, String password) 
	{
		log.info("hashing password");
		return employeeDao.hash(username, password);
	}
}
