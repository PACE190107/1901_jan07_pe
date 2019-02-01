package com.revature.module;

public class Request {

	private int id;
	private String type;
	private int amount;
	private String description;
	private int userId;
	private int manager = 0;
	private int approved = 0;
	
	public Request() {
	}

	public Request(String type, int amount, String description, int userId) {
		super();
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.userId = userId;
	}

	public Request(int id, String type, int amount, String description, int userId, int manager, int approved) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.userId = userId;
		this.manager = manager;
		this.approved = approved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + approved;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + manager;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + userId;
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
		Request other = (Request) obj;
		if (amount != other.amount)
			return false;
		if (approved != other.approved)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (manager != other.manager)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", type=" + type + ", amount=" + amount + ", description=" + description
				+ ", userId=" + userId + ", manager=" + manager + ", approved=" + approved + "]";
	}

	
	
	
}
