package com.revature.services;

import java.util.Date;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ResolvedReimbursement;

public interface reimbursementService {
	public List<Reimbursement> getEmployeeReimbursements(String employeeUsername);
	public Reimbursement newReimbursement(int employee_id, double amount, Date expenseDate);
	public Employee editUser(int user_id, String fname, String lname, String email);
	public List<Reimbursement> getPendingReimbursement();
	public List<ResolvedReimbursement> getResolvedReimbursements();
	public ResolvedReimbursement approveReimbursement(int reimbursementId, int manId);
	public ResolvedReimbursement denyReimbursement(int reimbursementId, int manId);
}
