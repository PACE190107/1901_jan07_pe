package com.revature.models;

import java.util.Date;

public class Reimbursement {
	
	private int e_id;
	private Date date_received;
	private Date date_resolved;
	private String type;
	private int amount;
	private String description;
	private String status;
	private int manager_id;
	
	
	public Reimbursement() {
		super();
	}


	public Reimbursement(int e_id, Date date_received, Date date_resolved, String type, int amount, String description,
			String status, int manager_id) {
		super();
		this.e_id = e_id;
		this.date_received = date_received;
		this.date_resolved = date_resolved;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.manager_id = manager_id;
	}


	public int getE_id() {
		return e_id;
	}


	public void setE_id(int e_id) {
		this.e_id = e_id;
	}


	public Date getDate_received() {
		return date_received;
	}


	public void setDate_received(Date date_received) {
		this.date_received = date_received;
	}


	public Date getDate_resolved() {
		return date_resolved;
	}


	public void setDate_resolved(Date date_resolved) {
		this.date_resolved = date_resolved;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
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


	public int getManager_id() {
		return manager_id;
	}


	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((date_received == null) ? 0 : date_received.hashCode());
		result = prime * result + ((date_resolved == null) ? 0 : date_resolved.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + e_id;
		result = prime * result + manager_id;
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
		if (amount != other.amount)
			return false;
		if (date_received == null) {
			if (other.date_received != null)
				return false;
		} else if (!date_received.equals(other.date_received))
			return false;
		if (date_resolved == null) {
			if (other.date_resolved != null)
				return false;
		} else if (!date_resolved.equals(other.date_resolved))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (e_id != other.e_id)
			return false;
		if (manager_id != other.manager_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reimbursement [e_id=" + e_id + ", date_received=" + date_received + ", date_resolved=" + date_resolved
				+ ", type=" + type + ", amount=" + amount + ", description=" + description + ", status=" + status
				+ ", manager_id=" + manager_id + "]";
	}

	
	
	
	
}
