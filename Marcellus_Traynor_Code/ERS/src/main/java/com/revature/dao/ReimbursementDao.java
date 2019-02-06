package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao 
{
	public void createReimbursement(String username, String reason, double amount);
	public List<Reimbursement> manEmployeeReimbursements(String username);
	public void updateReimbursement(String status, int rId, String username);
	public List<Reimbursement> manAllReimbursements();
	public List<Reimbursement> manResolvedReimbursements();
	public List<Reimbursement> manPendingReimbursements();
	public List<Reimbursement> empResolvedReimbursements(String username);
	public List<Reimbursement> empPendingReimbursements(String username);
	public List<Reimbursement> empEmployeeReimbursements(String username);
}