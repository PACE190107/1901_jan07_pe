package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	public boolean insertReimbursement(String username, double amount);
	public Reimbursement getReimbursement(int reimbursementId);	
	public List<Reimbursement> getAllReimbursements(String username);
	public List<Reimbursement> getAllPendingReimbursements();
	boolean updateReimbursement(int reimbursementId, String status, String manager);
	List<Reimbursement> getAllResolvedReimbursements();
	boolean updateUsernameReimbursement(String newUsername, String oldUsername);
	int getMostRecentReimbursement();
	boolean addImage(int id);
	boolean deleteReimbursement(String name);
}
