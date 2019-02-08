package com.reavature.tjordan.models;

public class ReimbursementReq {
	private int emp_ID;
	private int req_ID;
	private String sub_date;
	private String req_type;
	private double req_amount;
	private String req_status;
	private int req_man_id;
	private String resolvedDate;
	
	
	public ReimbursementReq() {
		super();
	}


	public ReimbursementReq(int emp_ID, int req_ID, String sub_date, String req_type, double req_amount, String req_status,
			int req_man_id, String resolvedDate) {
		super();
		this.emp_ID = emp_ID;
		this.req_ID = req_ID;
		this.sub_date = sub_date;
		this.req_type = req_type;
		this.req_amount = req_amount;
		this.req_status = req_status;
		this.req_man_id = req_man_id;
		this.resolvedDate = resolvedDate;
	}


	public int getEmp_ID() {
		return emp_ID;
	}


	public void setEmp_ID(int emp_ID) {
		this.emp_ID = emp_ID;
	}


	public int getReq_ID() {
		return req_ID;
	}


	public void setReq_ID(int req_ID) {
		this.req_ID = req_ID;
	}


	public String getSub_date() {
		return sub_date;
	}


	public void setSub_date(String sub_date) {
		this.sub_date = sub_date;
	}


	public String getReq_type() {
		return req_type;
	}


	public void setReq_type(String req_type) {
		this.req_type = req_type;
	}


	public double getReq_amount() {
		return req_amount;
	}


	public void setReq_amount(double req_amount) {
		this.req_amount = req_amount;
	}


	public String getReq_status() {
		return req_status;
	}


	public void setReq_status(String req_status) {
		this.req_status = req_status;
	}


	public int getReq_man_id() {
		return req_man_id;
	}


	public void setReq_man_id(int req_man_id) {
		this.req_man_id = req_man_id;
	}


	public String getResolvedDate() {
		return resolvedDate;
	}


	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + emp_ID;
		result = prime * result + req_ID;
		long temp;
		temp = Double.doubleToLongBits(req_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + req_man_id;
		result = prime * result + ((req_status == null) ? 0 : req_status.hashCode());
		result = prime * result + ((req_type == null) ? 0 : req_type.hashCode());
		result = prime * result + ((resolvedDate == null) ? 0 : resolvedDate.hashCode());
		result = prime * result + ((sub_date == null) ? 0 : sub_date.hashCode());
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
		ReimbursementReq other = (ReimbursementReq) obj;
		if (emp_ID != other.emp_ID)
			return false;
		if (req_ID != other.req_ID)
			return false;
		if (Double.doubleToLongBits(req_amount) != Double.doubleToLongBits(other.req_amount))
			return false;
		if (req_man_id != other.req_man_id)
			return false;
		if (req_status == null) {
			if (other.req_status != null)
				return false;
		} else if (!req_status.equals(other.req_status))
			return false;
		if (req_type == null) {
			if (other.req_type != null)
				return false;
		} else if (!req_type.equals(other.req_type))
			return false;
		if (resolvedDate == null) {
			if (other.resolvedDate != null)
				return false;
		} else if (!resolvedDate.equals(other.resolvedDate))
			return false;
		if (sub_date == null) {
			if (other.sub_date != null)
				return false;
		} else if (!sub_date.equals(other.sub_date))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RequestModel [emp_ID=" + emp_ID + ", req_ID=" + req_ID + ", sub_date=" + sub_date + ", req_type="
				+ req_type + ", req_amount=" + req_amount + ", req_status=" + req_status + ", req_man_id=" + req_man_id
				+ ", resolvedDate=" + resolvedDate + "]";
	}
	
	
	

}
