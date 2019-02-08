package com.revature.model;


public class Reimbursement {
	private int id;
	private int requesterId;
	private int authorizerId;
	private ReimbursementType type;
	private ReimbursementStatus status;
	private float amount;
	private String date;
	
	public Reimbursement(int id, int requesterId, int authorizorId, ReimbursementType type, ReimbursementStatus status,
			float amount, String date) {
		super();
		this.id = id;
		this.requesterId = requesterId;
		this.authorizerId = authorizorId;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}

	public int getAuthorizerId() {
		return authorizerId;
	}

	public void setAuthorizerId(int authorizorId) {
		this.authorizerId = authorizorId;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + authorizerId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + requesterId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (authorizerId != other.authorizerId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (requesterId != other.requesterId)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", requesterId=" + requesterId + ", authorizerId=" + authorizerId + ", type="
				+ type + ", status=" + status + ", amount=" + amount + ", date=" + date + "]";
	}
}
