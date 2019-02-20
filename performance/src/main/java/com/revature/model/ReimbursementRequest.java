package com.revature.model;

public class ReimbursementRequest {

	private double amount;
	private String status;
	private String type;

	public ReimbursementRequest() {
	}

	public ReimbursementRequest(double amount, String status, String type) {
		super();
		this.amount = amount;
		this.status = status;
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
