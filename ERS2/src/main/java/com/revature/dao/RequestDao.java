package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Request;

public interface RequestDao {
	
	// Read one
	Request getRequestById(int id);
	
	//Read all
	List<Request> getAllRequests();
	
	// Create
	Request createRequest(Request request);
	
	// Update
	Request updateRequest(Request request);

	Request deleteRequest(int id);

}
