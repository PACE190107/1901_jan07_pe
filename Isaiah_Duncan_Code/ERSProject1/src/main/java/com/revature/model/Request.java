package com.revature.model;

public class Request {

	private int requestId;
	private int employeeId;
	private String requestType;
	private String desc;
	private double amount;
	private String status;
	private String dateSubmitted;
	private String dateResolved;
	private int resolvedBy;
	public Request() {
		super();
	}
	
	
	public Request(int requestId, int employeeId, String requestType, String desc, double amount, String status,
			String dateSubmitted) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.requestType = requestType;
		this.desc = desc;
		this.amount = amount;
		this.status = status;
		this.dateSubmitted = dateSubmitted;
	}


	public Request(int requestId, String requestType, String desc, double amount, String status, String dateResolved) {
		super();
		this.requestId = requestId;
		this.requestType = requestType;
		this.desc = desc;
		this.amount = amount;
		this.status = status;
		this.dateResolved = dateResolved;
	}

	public Request(int requestId, int employeeId, String requestType, String desc, double amount, String status,
			String dateSubmitted, String dateResolved, int resolvedBy) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.requestType = requestType;
		this.desc = desc;
		this.amount = amount;
		this.status = status;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.resolvedBy = resolvedBy;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
	}
	public int getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateResolved == null) ? 0 : dateResolved.hashCode());
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + employeeId;
		result = prime * result + requestId;
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result + resolvedBy;
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
		Request other = (Request) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (dateResolved == null) {
			if (other.dateResolved != null)
				return false;
		} else if (!dateResolved.equals(other.dateResolved))
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (requestId != other.requestId)
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		if (resolvedBy != other.resolvedBy)
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
		return "Request [requestId=" + requestId + ", employeeId=" + employeeId + ", requestType=" + requestType
				+ ", desc=" + desc + ", amount=" + amount + ", status=" + status + ", dateSubmitted=" + dateSubmitted
				+ ", dateResolved=" + dateResolved + ", resolvedBy=" + resolvedBy + "]";
	}
	
	
}
