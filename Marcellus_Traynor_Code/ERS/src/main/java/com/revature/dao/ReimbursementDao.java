package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao 
{
	//Create new reimbursement
	public void createReimbursement(String username, Reimbursement reimbursement);
	
	//View all reimbursements (pending, resolved, and resolving manager)
	public List<Reimbursement> getAllReimbursements();
	
	//Manager -- View reimbursements from a single employee
	public List<Reimbursement> getEmployeeReimbursements(String username);
		 
	//Manager -- approve or deny reimbursements (Update reimbursement)
	public void updateReimbursement(String status, int rId);
		
	//Manager -- view all resolved reimbursements and which manager resolved
	public List<Reimbursement> getResolvedReimbursements();
}