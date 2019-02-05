package com.revature.models;

public class Reimbursement 
{
	private String username;
	private int rId;
	private String reason;
	private double amount;
	private String status;
	private String approved_by;
	
	public Reimbursement() 
	{
		super();
	}
	
	public Reimbursement(String username, String reason, double amount, String status, String approved_by) 
	{
		super();
		this.username = username;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
		this.approved_by = approved_by;
	}
	
	public Reimbursement(String username, int rId, String reason, double amount, String status, String approved_by) 
	{
		super();
		this.username = username;
		this.rId = rId;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
		this.approved_by = approved_by;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public int getrId() 
	{
		return rId;
	}
	
	public void setrId(int rId) 
	{
		this.rId = rId;
	}
	
	public String getReason() 
	{
		return reason;
	}
	
	public void setReason(String reason) 
	{
		this.reason = reason;
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public String getApproved_by() 
	{
		return approved_by;
	}
	
	public void setApproved_by(String approved_by) 
	{
		this.approved_by = approved_by;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approved_by == null) ? 0 : approved_by.hashCode());
		result = prime * result + rId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		
		if (approved_by == null) 
		{
			if (other.approved_by != null)
				return false;
		} 
		else if (!approved_by.equals(other.approved_by))
			return false;
		
		if (rId != other.rId)
			return false;
		
		if (reason == null) 
		{
			if (other.reason != null)
				return false;
		} 
		else if (!reason.equals(other.reason))
			return false;
		
		if (status == null) 
		{
			if (other.status != null)
				return false;
		} 
		else if (!status.equals(other.status))
			return false;
		
		if (username == null)
		{
			if (other.username != null)
				return false;
		} 
		else if (!username.equals(other.username))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() 
	{
		return "Reimbursement [username=" + username + ", rId=" + rId + ", reason=" + reason + ", amount=" + amount
				+ ", status=" + status + ", approved_by=" + approved_by + "]";
	}
}