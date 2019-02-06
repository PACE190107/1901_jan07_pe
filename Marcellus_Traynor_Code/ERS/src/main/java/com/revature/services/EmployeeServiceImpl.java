package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService
{
	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	final static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		final String newFname = request.getParameter("newFname");
		final String newLname = request.getParameter("newLname");
		final String newPassword = request.getParameter("newPassword");
		
		employeeDao.updateEmployee(username, newFname, newLname, newPassword);

		try 
		{
			request.getRequestDispatcher("employee_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in EmployeeServiceImpl updateInfo");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in EmployeeServiceImpl updateInfo");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public void getEmployeeInfo(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		employeeDao.getEmployeeInfo(username);

		try 
		{
			request.getRequestDispatcher("employee_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in EmployeeServiceImpl getEmployeeInfo");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in EmployeeServiceImpl getEmployeeInfo");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
}