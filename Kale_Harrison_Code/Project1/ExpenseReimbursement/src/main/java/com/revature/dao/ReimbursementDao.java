package com.revature.dao;


import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	
	
	public boolean submitRequest(double amount, String description, int e_id);
	public List<Reimbursement> viewPending();
	public List<Reimbursement> viewPending(int e_id);
	
	public List<Reimbursement> viewResolved();
	public List<Reimbursement> viewResolved(int e_id);
	
	public boolean approveRequest(int re_id, int e_id);
	public boolean denyRequest(int re_id, int e_id);
	public List<Reimbursement> viewRequestsSingleEmployee(int e_id);
	
	
}
