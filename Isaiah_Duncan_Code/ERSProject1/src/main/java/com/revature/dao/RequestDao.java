package com.revature.dao;

import java.util.List;

import com.revature.model.Request;

public interface RequestDao {

	boolean addExpenseRequest(int employeeId, String typeArg, String descArg, double amountArg);
	List<Request> getResolvedRequests(int employeeId);
	List<Request> getUnresolvedRequests(int employeeId);
	
}
