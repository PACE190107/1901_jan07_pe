package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Reimbursement;



public interface ReimbursementDao {
	
	public Reimbursement getReimbursement(int id);
	public List<Reimbursement> getAllPendingRequestsSingle(int id) throws SQLException;
	public List<Reimbursement> getAllCompletedRequestsSingle(int id) throws SQLException;
	public List<Reimbursement> getAllReimbursement() throws SQLException;
	public List<Reimbursement> getAllCompletedRequests() throws SQLException;
	public List<Reimbursement> getAllPendingRequests() throws SQLException;
}
