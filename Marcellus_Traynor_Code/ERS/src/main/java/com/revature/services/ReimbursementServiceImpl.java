package com.revature.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService
{
	private final ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
	private final ObjectMapper mapper = new ObjectMapper();
	final static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	
	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) 
	{
		if(request.getMethod().equals("GET"))
		{
			log.info("inside the GET request of ReimbursementServiceImpl");
			//Get all reimbursements
			String[] path = request.getRequestURI().split("/");
			
			if (path.length == 4 & request.getRequestURI().contains("all")) 
			{		
				log.info("inside the GET/all request of ReimbursementServiceImpl");
				return reimbursementDao.getAllReimbursements();
			}
			
			//Get all resolved reimbursements
			if (path.length == 4 & request.getRequestURI().contains("resolved")) 
			{		
				log.info("inside the GET/resolved request of ReimbursementServiceImpl");
				return reimbursementDao.getResolvedReimbursements();
			}
			
			//Get single employee reimbursements
			if (path.length == 5) 
			{		
				log.info("inside the GET/employeeReimbursements request of ReimbursementServiceImpl");
				
				String username = String.valueOf(path[4]);
				return reimbursementDao.getEmployeeReimbursements(username);
			}
		}
		
		if(request.getMethod().equals("POST"))
		{
			log.info("inside the POST request of ReimbursementServiceImpl");
			//Create reimbursement
			if (request.getHeader("Content-Type").equals("application/json")) 
			{
				log.info("inside the POST/createReimbursement request of ReimbursementServiceImpl");
				try 
				{
					HttpSession session = request.getSession();
					Reimbursement reimbursement = mapper.readValue(request.getReader(), Reimbursement.class);
					reimbursementDao.createReimbursement((String)session.getAttribute("username"), reimbursement);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			
			try 
			{
				// 415 is an Unsupported Media Type
				response.sendError(415, "Please create using application/json");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(request.getMethod().equals("PUT"))
		{
			log.info("inside the PUT request of ReimbursementServiceImpl");
			//Update reimbursement
			
		}
		return null;
	}
}