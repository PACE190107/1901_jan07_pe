package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;

public interface ReimbursementDao {
	
	public boolean createReimbursement(Reimbursement reimbursement);
	
	public Reimbursement getReimbursementById(int rId);
	
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getPendingReimbursements();
	
	public List<Reimbursement> getResolvedReimbursements();
	
	public boolean resolveReimbursement(ReimbursementStatus newStatus, int rId, int managerId);
	
	public List<Reimbursement> getReimbursementsByEmployeeId(int id);
	
	public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id);
	
}
