package com.ers.models;

import java.io.Serializable;

public class ReimbursementRequest implements Serializable {
	private static final long serialVersionUID = -7064023042138814466L;
	
	private int rrID;
	private int eID;
	private int mID;
	private String rrDescription;
	private double rrAmount;
	private boolean isApproved;
	
	public int getRrID() {
		return rrID;
	}
	public void setRrID(int rrID) {
		this.rrID = rrID;
	}
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public int getmID() {
		return mID;
	}
	public void setmID(int mID) {
		this.mID = mID;
	}
	public String getRrDescription() {
		return rrDescription;
	}
	public void setRrDescription(String rrDescription) {
		this.rrDescription = rrDescription;
	}
	public double getRrAmount() {
		return rrAmount;
	}
	public void setRrAmount(double rrAmount) {
		this.rrAmount = rrAmount;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	public ReimbursementRequest() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eID;
		result = prime * result + (isApproved ? 1231 : 1237);
		result = prime * result + mID;
		long temp;
		temp = Double.doubleToLongBits(rrAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rrDescription == null) ? 0 : rrDescription.hashCode());
		result = prime * result + rrID;
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
		ReimbursementRequest other = (ReimbursementRequest) obj;
		if (eID != other.eID)
			return false;
		if (isApproved != other.isApproved)
			return false;
		if (mID != other.mID)
			return false;
		if (Double.doubleToLongBits(rrAmount) != Double.doubleToLongBits(other.rrAmount))
			return false;
		if (rrDescription == null) {
			if (other.rrDescription != null)
				return false;
		} else if (!rrDescription.equals(other.rrDescription))
			return false;
		if (rrID != other.rrID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReimbursementRequest [rrID=" + rrID + ", eID=" + eID + ", mID=" + mID + ", rrDescription="
				+ rrDescription + ", rrAmount=" + rrAmount + ", isApproved=" + isApproved + "]";
	}
}
