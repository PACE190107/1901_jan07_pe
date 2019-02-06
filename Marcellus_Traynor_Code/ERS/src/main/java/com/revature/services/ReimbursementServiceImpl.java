package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;

public class ReimbursementServiceImpl implements ReimbursementService
{
	private final ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
	final static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	
	@Override
	public void empReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		log.info("getting the employee's reimbursements");
		reimbursementDao.empEmployeeReimbursements(username);
		
		try 
		{
			request.getRequestDispatcher("employee_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl empAll");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl empAll");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@Override
	public void empPendingReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		log.info("getting the employee's pending reimbursements");
		reimbursementDao.empPendingReimbursements(username);
		
		try 
		{
			request.getRequestDispatcher("employee_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl empPending");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl empPending");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@Override
	public void empResolvedReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		log.info("getting the employee's resolved reimbursements");
		reimbursementDao.empResolvedReimbursements(username);
		
		try 
		{
			request.getRequestDispatcher("employee_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl empResolved");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl empResolved");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@Override
	public void createReimbursement(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		final String reason = request.getParameter("reason");
		final double amount = Double.parseDouble(String.valueOf(request.getParameter("amount")));
		
		log.info("creating a new reimbursement");
		reimbursementDao.createReimbursement(username, reason, amount);
		
		try 
		{
			request.getRequestDispatcher("employee_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl create");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl create");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public void manEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		final String empUsername = request.getParameter("empUsername");
		log.info("getting a single employee's reimbursements");
		reimbursementDao.manEmployeeReimbursements(empUsername);
		
		try 
		{
			request.getRequestDispatcher("manager_home.html").forward(request, response);
		} 
		catch (ServletException e)
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl manEmployee");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl manEmployee");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public void manResolvedReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info("getting all resolved reimbursements");
		reimbursementDao.manResolvedReimbursements();
		
		try 
		{
			request.getRequestDispatcher("manager_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl manResolved");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e)
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl manResolved");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public void manPendingReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info("getting all pending reimbursements");
		reimbursementDao.manPendingReimbursements();
		
		try 
		{
			request.getRequestDispatcher("manager_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl manPending");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl manPending");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@Override
	public void manAllReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info("getting all reimbursements");
		reimbursementDao.manAllReimbursements();
		
		try 
		{
			request.getRequestDispatcher("manager_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl manAll");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl manAll");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		final int id = Integer.parseInt(String.valueOf(request.getParameter("rId")));
		final String status = request.getParameter("btn");
		System.out.println(status + " " + id);
		
		log.info("resolving the reimbursement");
		reimbursementDao.updateReimbursement(status, id, username);
		
		try 
		{
			request.getRequestDispatcher("manager_home.html").forward(request, response);
		} 
		catch (ServletException e) 
		{
			log.error("error occured in ServletException catch block in ReimbursementServiceImpl update");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		} 
		catch (IOException e) 
		{
			log.error("error occured in IOException catch block in ReimbursementServiceImpl update");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
}