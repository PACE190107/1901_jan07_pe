package com.revature.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService
{
	private final ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
	final static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	
	@Override
	public List<Reimbursement> empReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		log.info("getting the employee's reimbursements");
		List<Reimbursement> list = reimbursementDao.empEmployeeReimbursements(username);
		
		return list;
	}
	
	@Override
	public List<Reimbursement> empPendingReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		log.info("getting the employee's pending reimbursements");
		List<Reimbursement> list = reimbursementDao.empPendingReimbursements(username);
		
		return list;
	}
	
	@Override
	public List<Reimbursement> empResolvedReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		
		log.info("getting the employee's resolved reimbursements");
		List<Reimbursement> list = reimbursementDao.empResolvedReimbursements(username);
		
		return list;
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
	public List<Reimbursement> manEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String empUsername = (String)session.getAttribute("empUsername");

		log.info("getting a single employee's reimbursements");
		List<Reimbursement> list = reimbursementDao.manEmployeeReimbursements(empUsername);
		
		return list;
	}

	@Override
	public List<Reimbursement> manResolvedReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info("getting all resolved reimbursements");
		List<Reimbursement> list = reimbursementDao.manResolvedReimbursements();
		
		return list;
	}

	@Override
	public List<Reimbursement> manPendingReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info("getting all pending reimbursements");
		List<Reimbursement> list = reimbursementDao.manPendingReimbursements();
		
		return list;
	}
	
	@Override
	public List<Reimbursement> manAllReimbursements(HttpServletRequest request, HttpServletResponse response) 
	{
		log.info("getting all reimbursements");
		List<Reimbursement> list = reimbursementDao.manAllReimbursements();
		
		return list;
	}

	@Override
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession();
		final String username = (String)session.getAttribute("username");
		final int id = Integer.parseInt(String.valueOf(request.getParameter("rId")));
		final String status = request.getParameter("btn");
		
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