package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReimbursementService 
{
	public void empReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void empPendingReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void empResolvedReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void createReimbursement(HttpServletRequest request, HttpServletResponse response);
	public void manEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void manResolvedReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void manPendingReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void manAllReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response);
}