package com.revature.services;

import java.util.List;

import com.revatue.dao.ReimbursementDaoImplementation;
import com.revatue.dao.UserDaoImplementation;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class RequestService {

	private static RequestService reqService;

	private RequestService() {
	}

	public static RequestService getRequestService() {
		if (reqService == null) {
			reqService = new RequestService();
		}
		return reqService;
	}
	
	
	
//Methods I'm working with in ReimbursementDaoImplementation.getreimbDao//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public Reimbursement newRequest(int e_id, String date_received, String type, double amount, String description) {
		return ReimbursementDaoImplementation.getreimbDao().newRequest(e_id, date_received, type, amount, description);
	}
	
	
	public List<Reimbursement> viewRequests(int e_id) {
		return ReimbursementDaoImplementation.getreimbDao().viewRequests(e_id);
	}
	
	public List<Reimbursement> viewEmployeeRequests(int e_id){
		return ReimbursementDaoImplementation.getreimbDao().viewEmployeeRequests(e_id);
	};

	
	
	public List<Reimbursement> viewPendingRequests(){
		return ReimbursementDaoImplementation.getreimbDao().viewPendingRequests();
	}
	

	public List<Reimbursement> viewResolvedRequests(){
		return ReimbursementDaoImplementation.getreimbDao().viewResolvedRequests();
	}
	
	
	
	public void approveDeny (int seq_id, String status, int manager_id){
		return;
	}
}
	

