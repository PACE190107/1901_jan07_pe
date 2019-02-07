package com.revature.dao;

import java.util.Date;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ResolvedReimbursement;

public interface ReimbursementDAO {
	public Reimbursement createReimbursement(int employee_id, double amount, Date expenseDate);
	public List<Reimbursement> getPendingReimbursements();
	public List<Reimbursement> getAllReimbursements();
	public List<ResolvedReimbursement> getResolvedReimbursements();
	public List<Reimbursement> getFilteredReimbursements(String user_name);
	public ResolvedReimbursement approveReimbursement(int reimbursementId, int manId);
	public ResolvedReimbursement denyReimbursement(int reimbursementId, int manId);
	
}
