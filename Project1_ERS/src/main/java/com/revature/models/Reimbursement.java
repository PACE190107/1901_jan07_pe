package com.revature.models;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class Reimbursement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -591099251344006474L;

	final static Logger log = Logger.getLogger(Reimbursement.class);
	
	private int requestId;
	private int employeeId;
	private int approverId;
	private int amount;
	private String subject;
	private String description;
	private ReimbursementStatus status;
	
	public Reimbursement() {};
	//information for insert / create new reimbursement constructor
	public Reimbursement(int employeeId, int amount, String subject, String description) {
		
		this.employeeId = employeeId;
		this.amount = amount;
		this.subject = subject;
		this.description = description;
		
		
	}
	
	
	//get from db constructor
	public Reimbursement(int id, int employeeId, int approverId, 
						int amount, String subject, String description, int status) {
		this.requestId = id;
		this.employeeId = employeeId;
		this.approverId = approverId;
		this.subject = subject;
		this.description = description;
		switch(status) {
			case 1: this.status = ReimbursementStatus.PENDING;
				break;
			case 2: this.status = ReimbursementStatus.APPROVED;
				break;
			case 3: this.status = ReimbursementStatus.DENIED;
				break;
			default: 
				log.info("Error in Reimbursement constructor");
		}
		
		this.amount = amount;
		
	}
	public void setStatus(String newStatus) {
		if(newStatus.equalsIgnoreCase("APPROVED")){
			status = ReimbursementStatus.APPROVED;
		}else if(newStatus.equalsIgnoreCase("DENIED")){
			status = ReimbursementStatus.DENIED;
		}
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
	public int getApproverId() {
		return approverId;
	}
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ReimbursementStatus getStatus() {
		return status;
	}
	public String getStatusString() {
		String statusString = null;
		if(status != null) {
			statusString = "" + status;
		}
		
		return statusString;
	}
	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
	
	
	
}
