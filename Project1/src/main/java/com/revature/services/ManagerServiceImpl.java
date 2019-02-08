package com.revature.services;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;

public class ManagerServiceImpl implements ManagerService{

	@Override
	public Object approveDeny(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object viewAllPending(HttpServletRequest request, HttpServletResponse response) {
		try {
			return ReimbursementDaoImpl.getReim().getAllPendingRequests();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("returned null in viewAllPending");
		return null;
	}

	@Override
	public Object viewAllResolved(HttpServletRequest request, HttpServletResponse response) {
		try {
			return ReimbursementDaoImpl.getReim().getAllCompletedRequests();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("returned null in viewCompletedPending");
		return null;
	}

	@Override
	public Object viewAllEmployees(HttpServletRequest request, HttpServletResponse response) {
		try {
			return UserDaoImpl.getUsDa().getAllUsers();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("returned null in viewAllEmployees");
		return null;
	}

	@Override
	public Object viewReimSingleEmployee(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("thishsforih" + request.getParameter("number"));
		
		int num = Integer.parseInt(request.getParameter("number"));
		System.out.println("this is the number: " + num);
		try {
			return ReimbursementDaoImpl.getReim().singleUserReim(num);
			} catch (SQLException e) {
				e.getSQLState();
				e.getErrorCode();
				e.printStackTrace();
		}
		System.out.println("returned null in viewreimsingleemployee");
		return null;
	}

}
