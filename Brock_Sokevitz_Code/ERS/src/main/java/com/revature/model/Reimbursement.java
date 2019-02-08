package com.revature.model;

import com.revature.enums.Status;

public class Reimbursement {

	private int reimbursement_id;
	private String username;
	private double amount;
	private Status status;
	private String approvingManager;
	private int image;
	public Reimbursement() {
		super();
	}
	public Reimbursement(String username, double amount, Status status, String approvingManager, int image) {
		super();
		this.username = username;
		this.amount = amount;
		this.status = status;
		this.approvingManager = approvingManager;
		this.image = image;
	}
	public Reimbursement(String username, double amount, Status status) {
		super();
		this.username = username;
		this.amount = amount;
		this.status = status;
	}
	public Reimbursement(int reimbursement_id, String username, double amount, Status status, String approvingManager,
			int image) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.username = username;
		this.amount = amount;
		this.status = status;
		this.approvingManager = approvingManager;
		this.image = image;
	}
	public int getReimbursement_id() {
		return reimbursement_id;
	}
	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getApprovingManager() {
		return approvingManager;
	}
	public void setApprovingManager(String approvingManager) {
		this.approvingManager = approvingManager;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approvingManager == null) ? 0 : approvingManager.hashCode());
		result = prime * result + image;
		result = prime * result + reimbursement_id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (approvingManager == null) {
			if (other.approvingManager != null)
				return false;
		} else if (!approvingManager.equals(other.approvingManager))
			return false;
		if (image != other.image)
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		if (status != other.status)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
