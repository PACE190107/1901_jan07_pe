package com.revature.models;

public class Request {
	private int id;
	private int submitBy;
	private String vendor;
	private String desc;
	private double amount;
	private int pending;
	private int approveBy;
	private int approved;
	
	public Request() {
	}

	public Request( int pending, int approveBy, int id,  int submitBy, String vendor, String desc, double amount,
			int approved) {
		super();
		this.pending = pending;
		this.approveBy = approveBy;
		this.id = id;
		this.submitBy = submitBy;
		this.vendor = vendor;
		this.desc = desc;
		this.amount = amount;
		this.approved = approved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubmitBy() {
		return submitBy;
	}

	public void setSubmitBy(int submitBy) {
		this.submitBy = submitBy;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(int approveBy) {
		this.approveBy = approveBy;
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
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + approveBy;
		result = prime * result + approved;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + pending;
		result = prime * result + submitBy;
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
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
		if (approveBy != other.approveBy)
			return false;
		if (approved != other.approved)
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (pending != other.pending)
			return false;
		if (submitBy != other.submitBy)
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", submitBy=" + submitBy + ", vendor=" + vendor + ", desc=" + desc + ", amount="
				+ amount + ", pending=" + pending + ", approveBy=" + approveBy + ", approved=" + approved + "]";
	}
	
	
}
