package com.revature.model;

public class Reimbursement {
	
	private int r_id;
	private double r_amount;
	private String description;
	private String status;
	private int resolverId;
	private int e_id;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int r_id, double r_amount, String description, String status, int resolverId, int e_id) {
		super();
		this.r_id = r_id;
		this.r_amount = r_amount;
		this.description = description;
		this.status = status;
		this.resolverId = resolverId;
		this.e_id = e_id;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public double getR_amount() {
		return r_amount;
	}

	public void setR_amount(double r_amount) {
		this.r_amount = r_amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + e_id;
		long temp;
		temp = Double.doubleToLongBits(r_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + r_id;
		result = prime * result + resolverId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (e_id != other.e_id)
			return false;
		if (Double.doubleToLongBits(r_amount) != Double.doubleToLongBits(other.r_amount))
			return false;
		if (r_id != other.r_id)
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [r_id=" + r_id + ", r_amount=" + r_amount + ", description=" + description + ", status="
				+ status + ", resolverId=" + resolverId + ", e_id=" + e_id + "]";
	}
	
	
	
}
