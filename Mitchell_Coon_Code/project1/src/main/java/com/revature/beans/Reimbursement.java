package com.revature.beans;

public class Reimbursement {
	private int reimbursement_id;
	private int employee_id;
	private String purpose;
	private double amount;
	private String approved_status;
	private String approved_by;

	public Reimbursement(int reimbursement_id, int employee_id, String purpose, double amount, String approved_status,
			String approved_by) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.employee_id = employee_id;
		this.purpose = purpose;
		this.amount = amount;
		this.approved_status = approved_status;
		this.approved_by = approved_by;
	}

	public int getReimbursement_id() {
		return reimbursement_id;
	}

	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getApproved_status() {
		return approved_status;
	}

	public void setApproved_status(String approved_status) {
		this.approved_status = approved_status;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approved_by == null) ? 0 : approved_by.hashCode());
		result = prime * result + ((approved_status == null) ? 0 : approved_status.hashCode());
		result = prime * result + employee_id;
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + reimbursement_id;
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
		if (approved_by == null) {
			if (other.approved_by != null)
				return false;
		} else if (!approved_by.equals(other.approved_by))
			return false;
		if (approved_status == null) {
			if (other.approved_status != null)
				return false;
		} else if (!approved_status.equals(other.approved_status))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursement_id=" + reimbursement_id + ", employee_id=" + employee_id + ", purpose="
				+ purpose + ", amount=" + amount + ", approved_status=" + approved_status + ", approved_by="
				+ approved_by + "]";
	}
}
