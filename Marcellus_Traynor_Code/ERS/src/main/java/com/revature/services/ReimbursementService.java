package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Reimbursement;

public interface ReimbursementService 
{
	public List<Reimbursement> empReimbursements(HttpServletRequest request, HttpServletResponse response);
	public List<Reimbursement> empPendingReimbursements(HttpServletRequest request, HttpServletResponse response);
	public List<Reimbursement> empResolvedReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void createReimbursement(HttpServletRequest request, HttpServletResponse response);
	public List<Reimbursement> manEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response);
	public List<Reimbursement> manResolvedReimbursements(HttpServletRequest request, HttpServletResponse response);
	public List<Reimbursement> manPendingReimbursements(HttpServletRequest request, HttpServletResponse response);
	public List<Reimbursement> manAllReimbursements(HttpServletRequest request, HttpServletResponse response);
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response);
}