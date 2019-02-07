package com.revature.services;

import java.util.Date;
import java.util.List;

import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ResolvedReimbursement;

public class reimbursementServiceImpl implements reimbursementService {
	@Override
	public List<Reimbursement> getEmployeeReimbursements(String employeeUsername) {
		return ReimbursementDAOImpl.getReimbursementDAOImpl().getFilteredReimbursements(employeeUsername);
	}

	@Override
	public Reimbursement newReimbursement(int employee_id, double amount, Date expenseDate) {
		return ReimbursementDAOImpl.getReimbursementDAOImpl().createReimbursement(employee_id, amount, expenseDate);
	}

	@Override
	public Employee editUser(int user_id, String fname, String lname, String email) {
		return EmployeeDAOImpl.getEmployeeDao().updateEmployee(user_id, fname, lname, email);
	}

	@Override
	public List<Reimbursement> getPendingReimbursement() {
		return ReimbursementDAOImpl.getReimbursementDAOImpl().getPendingReimbursements();
	}

	@Override
	public List<ResolvedReimbursement> getResolvedReimbursements() {
		return ReimbursementDAOImpl.getReimbursementDAOImpl().getResolvedReimbursements();
	}
	@Override
	public ResolvedReimbursement approveReimbursement(int reimbursementId, int manId) {
		return ReimbursementDAOImpl.getReimbursementDAOImpl().approveReimbursement(reimbursementId, manId);
	}
	@Override
	public ResolvedReimbursement denyReimbursement(int reimbursementId, int manId) {
		return ReimbursementDAOImpl.getReimbursementDAOImpl().denyReimbursement(reimbursementId, manId);
	}
}
