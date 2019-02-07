package com.revature.model;

import java.sql.Date;

public class ResolvedReimbursement extends Reimbursement {
	private Date approvedDate;
	private boolean approved; //can't be null leaving me in a weird spot
	private int approvedId;
	
	public ResolvedReimbursement(int reimbursementId, int employeeId, double amount, Date submitDate, Date expenditureDate, boolean approved, Date resolvedOn, int approvedBy) {
		super(reimbursementId, employeeId, amount, submitDate, expenditureDate);
		setApproved(approved);
		setApprovedDate(resolvedOn);
		setApprovedId(approvedBy);
	}
	public ResolvedReimbursement() {
		super();
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public boolean getApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public int getApprovedId() {
		return approvedId;
	}
	public void setApprovedId(int approvedId) {
		this.approvedId = approvedId;
	}

}
