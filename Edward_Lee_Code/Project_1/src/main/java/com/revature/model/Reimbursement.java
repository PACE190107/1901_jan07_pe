package com.revature.model;

import java.io.Serializable;
import java.sql.Date;

public class Reimbursement implements Serializable{
	private int reimbursementId;
	private int employeeId;
	private double amount;
	private Date submitDate;
	private Date expenditureDate;
	
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int reimbursementId, int employeeId, double amount, Date submitDate, Date expenditureDate) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.submitDate = submitDate;
		this.expenditureDate = expenditureDate;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getExpenditureDate() {
		return expenditureDate;
	}
	public void setExpenditureDate(Date expenditureDate) {
		this.expenditureDate = expenditureDate;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", amount=" + amount
				+ ", submitDate=" + submitDate + ", expenditureDate=" + expenditureDate + "]";
	}

	
}
