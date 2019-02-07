package com.revature.models;

public class Request {

	private int requestNum;
	private int requestAmount;
	private int user_id;
	private int isApproved;
	private int resolvedBy;
	
	public Request() {
		super();
	}

	public Request(int requestNum, int requestAmount, int user_id, int isApproved, int resolvedBy) {
		super();
		this.requestNum = requestNum;
		this.requestAmount = requestAmount;
		this.user_id = user_id;
		this.isApproved = isApproved;
		this.resolvedBy = resolvedBy;
	}

	public int getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(int requestNum) {
		this.requestNum = requestNum;
	}

	public int getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(int requestAmount) {
		this.requestAmount = requestAmount;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public int getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	@Override
	public String toString() {
		return "Request [requestNum=" + requestNum + ", requestAmount=" + requestAmount + ", user_id=" + user_id
				+ ", isApproved=" + isApproved + ", resolvedBy=" + resolvedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isApproved;
		result = prime * result + requestAmount;
		result = prime * result + requestNum;
		result = prime * result + resolvedBy;
		result = prime * result + user_id;
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
		if (isApproved != other.isApproved)
			return false;
		if (requestAmount != other.requestAmount)
			return false;
		if (requestNum != other.requestNum)
			return false;
		if (resolvedBy != other.resolvedBy)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
	
}
