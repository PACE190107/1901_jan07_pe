package com.revatue.dao;




import java.util.List;

import com.revature.models.Reimbursement;
public interface ReimbursementDao {

	public Reimbursement newRequest(int e_id, String date_received, String type, double amount, String description);
	public boolean updateRequest(String date_resolved, String status, int manager_id);
	public  List<Reimbursement> viewRequests(int e_id);
	public  List<Reimbursement> viewEmployeeRequests(int e_id);
	public  List<Reimbursement> viewPendingRequests();
	public  List<Reimbursement> viewResolvedRequests();
	public void approveDeny (int seq_id, String status, int manager_id);
	

	
}
