package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;

public interface ReimbursementDAO {
	public Reimbursement insertReimbursement(Reimbursement reimbursement);
	public Reimbursement updateReimbursement(Reimbursement reimbursement);
	public boolean deleteReimbursement(int reimbursementId);
	public boolean deleteAllReimbursements(int employeeId);
	
	public Reimbursement getReimbursement(int reimbursementId);
	public ArrayList<Reimbursement> getAllReimbursements(int employeeId, ReimbursementStatus status);
	public ArrayList<Reimbursement> getAllReimbursements(ReimbursementStatus status);
}