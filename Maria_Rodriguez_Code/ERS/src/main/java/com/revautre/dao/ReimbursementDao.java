package com.revautre.dao;

import java.util.Date;

import com.revature.models.Reimbursement;
public interface ReimbursementDao {

	public Reimbursement newRequest(Date date_received, String type, int amount, String description);
	
	
	
}
