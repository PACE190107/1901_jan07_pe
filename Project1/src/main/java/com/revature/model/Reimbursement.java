package com.revature.model;

public class Reimbursement {

	private int userReimId;
	private int userId;
	private int managerAssigned;
	private int processed;
	private int amount;
	private String type;
	private String description;
	
	public Reimbursement() {
		
	}
	
	public Reimbursement(int userReimId, int userId, int managerAssigned, int processed, int amount, String type, String description) {
		this.userReimId = userReimId;
		this.userId = userId;
		this.managerAssigned = managerAssigned;
		this.processed = processed;
		this.amount = amount;
		this.type = type;
		this.description = description;
	}

	public int getUserReimId() {
		return userReimId;
	}

	public void setUserReimId(int userReimId) {
		this.userReimId = userReimId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getManagerAssigned() {
		return managerAssigned;
	}

	public void setManagerAssigned(int managerAssigned) {
		this.managerAssigned = managerAssigned;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + managerAssigned;
		result = prime * result + processed;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + userId;
		result = prime * result + userReimId;
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
		if (amount != other.amount)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (managerAssigned != other.managerAssigned)
			return false;
		if (processed != other.processed)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userId != other.userId)
			return false;
		if (userReimId != other.userReimId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [userReimId=" + userReimId + ", userId=" + userId + ", managerAssigned=" + managerAssigned
				+ ", processed=" + processed + ", amount=" + amount + ", type=" + type + ", description=" + description
				+ "]";
	}
	
}
