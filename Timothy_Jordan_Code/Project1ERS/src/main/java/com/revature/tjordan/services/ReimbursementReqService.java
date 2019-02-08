package com.revature.tjordan.services;

import java.util.List;

import com.reavature.tjordan.models.ReimbursementReq;
import com.revature.dao.ReimbursementReqDaoImpl;

public class ReimbursementReqService {
		
	
private static ReimbursementReqService ReimbursementService;

	
	private ReimbursementReqService() {
		
	}
	
	
	
	public static ReimbursementReqService getReimbursementService() {
		if(ReimbursementService == null) {
			ReimbursementService = new ReimbursementReqService();
		}
		
		return ReimbursementService;
	}
	
	
	public ReimbursementReq getRequest(int req_ID) {
		return ReimbursementReqDaoImpl.getReimbursementDao().getRequest(req_ID);
	}
	
	
	public boolean updateReq(int req_ID, int man_ID,  String status, String date) {
		return ReimbursementReqDaoImpl.getReimbursementDao().updateReq(req_ID, man_ID, status, date);
	}
	public List<ReimbursementReq> getAll() {
		return ReimbursementReqDaoImpl.getReimbursementDao().getAll();
	}
	
	
	public List<ReimbursementReq> getResolvedGlobal() {
		return ReimbursementReqDaoImpl.getReimbursementDao().getResolvedGlobal();
	}
	
	public List<ReimbursementReq> getAllSpecificRequests(int emp_ID) {
		return ReimbursementReqDaoImpl.getReimbursementDao().getAllSpecificRequests(emp_ID);
	}
	
	
	public List<ReimbursementReq> getAllPendingMan() {
		return ReimbursementReqDaoImpl.getReimbursementDao().getAllPendingMan();
	}
	
	public List<ReimbursementReq> getAllPending(int emp_ID) {
		return ReimbursementReqDaoImpl.getReimbursementDao().getAllPending(emp_ID);
	}
	
	public List<ReimbursementReq> getAllResolved(int emp_ID) {
		return ReimbursementReqDaoImpl.getReimbursementDao().getAllResolved(emp_ID);
	}
	public List<ReimbursementReq> addRequest(int emp_ID, String subDate, String type, double amount) {
		return ReimbursementReqDaoImpl.getReimbursementDao().addRequest(emp_ID, subDate, type, amount);
	}
}
