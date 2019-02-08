package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.data.DataSource;
import com.revature.exception.RequestNotFoundException;
import com.revature.models.Employee;
import com.revature.models.Request;

public class RequestDaoImpl implements RequestDao {

	final static Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	private final DataSource dataSource = DataSource.getInstance();
	
	// SELECT * FROM REQUESTS;
	@Override
	public List<Request> getAllRequests() {
		return dataSource.getRequestTable();
	}
	
	public Request getRequestById(int id) {
		return dataSource.getRequestTable().stream()
				.filter(request -> request.getId() == id)
				.findFirst().orElseThrow(() -> new RequestNotFoundException(id));
	}
	
	@Override
	public Request createRequest(Request request) {
		if (dataSource.getRequestTable().add(request)) {
			return request;
		}
		return null;
	}

	@Override
	public Request updateRequest(Request request) {
		// Get a reference to existing request
		Request updated = getRequestById(request.getId());
		
		// Update the request's state
		updated.setApproved(request.getApproved());
		updated.setApproveBy(request.getApproveBy());
		
		// Store the updated version
		for (int i = 0; i < dataSource.getRequestTable().size(); i++) {
			if (dataSource.getRequestTable().get(i).getId() == request.getId()) {
				dataSource.getRequestTable().set(i, updated);
				return updated;
			}
		}
		return null;
	}
	
	@Override
	public Request deleteRequest(int id) {
		// Reference existing id
		Request toBeRemoved = getRequestById(id);
		
		// For every employee in our table, remove if the todo's id equals the parameter
		dataSource.getRequestTable().removeIf(anything -> anything.getId() == id);
		return toBeRemoved;
	}
}
