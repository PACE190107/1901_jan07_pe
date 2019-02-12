package com.revature.models;

public class Reimbursement {
	
	private int seq_id;
	private int e_id;
	private String date_received;
	private String req_type;
	private int amount;
	private String req_description;
	private String status;
	private int manager_id;
	
	
	public Reimbursement() {
		super();
	}


	
	public Reimbursement(int seq_id, int e_id, String date_received, String req_type, int amount,
			String req_description, String status, int manager_id) {
		super();
		this.seq_id = seq_id;
		this.e_id = e_id;
		this.date_received = date_received;
		this.req_type = req_type;
		this.amount = amount;
		this.req_description = req_description;
		this.status = status;
		this.manager_id = manager_id;
	}


	public int getSeq_id() {
		return seq_id;
	}


	public void setSeq_id(int seq_id) {
		this.seq_id = seq_id;
	}


	public int getE_id() {
		return e_id;
	}


	public void setE_id(int e_id) {
		this.e_id = e_id;
	}


	public String getDate_received() {
		return date_received;
	}


	public void setDate_received(String date_received) {
		this.date_received = date_received;
	}


	public String getReq_type() {
		return req_type;
	}


	public void setReq_type(String req_type) {
		this.req_type = req_type;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getReq_description() {
		return req_description;
	}


	public void setReq_description(String req_description) {
		this.req_description = req_description;
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
	public String toString() {
		return "Reimbursement [seq_id=" + seq_id + ", e_id=" + e_id + ", date_received=" + date_received + ", req_type="
				+ req_type + ", amount=" + amount + ", req_description=" + req_description + ", status=" + status
				+ ", manager_id=" + manager_id + "]";
	}


	
	

	
	
	

}