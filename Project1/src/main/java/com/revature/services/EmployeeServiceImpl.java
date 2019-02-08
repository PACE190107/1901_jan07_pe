package com.revature.services;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;

public class EmployeeServiceImpl implements EmployeeService{

	
	
	@Override
	public Object viewInformation(HttpServletRequest request, HttpServletResponse response) {
		String obj = (String)request.getSession().getAttribute("username");
		return UserDaoImpl.getUsDa().getUser(obj);

	}

	@Override
	public Object viewResolvedRequests(HttpServletRequest request, HttpServletResponse response) {
		int obj = (int)request.getSession().getAttribute("id");
		try {
			return ReimbursementDaoImpl.getReim().getAllCompletedRequestsSingle(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getSQLState();
			e.getErrorCode();
			e.printStackTrace();
		}
		System.out.println("returned null for viewResolvedRequests");
		return null;
	}

	@Override
	public Object viewPendingRequests(HttpServletRequest request, HttpServletResponse response) {
		
		int obj = (int)request.getSession().getAttribute("id");
		try {
			return ReimbursementDaoImpl.getReim().getAllPendingRequestsSingle(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getSQLState();
			e.getErrorCode();
			e.printStackTrace();
		}
		System.out.println("returned null for viewPendingRequests");
		return null;
	}

	@Override
	public Object submitReimbursement(HttpServletRequest request, HttpServletResponse response) {
		int amnt = Integer.parseInt(request.getParameter("amount"));
		String desc = request.getParameter("description");
		int id = (int)request.getSession().getAttribute("id");
		
		return ReimbursementDaoImpl.getReim().submitReim(id, amnt, desc);
	}

	@Override
	public Object updateInformation(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
