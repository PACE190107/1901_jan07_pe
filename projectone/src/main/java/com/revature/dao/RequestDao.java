package com.revature.dao;

import java.util.List;

import com.revature.models.Request;

public interface RequestDao {

	public boolean submitRequest(int requestAmount, int employeeId);
	public boolean reviewRequest(int status, int managerId, int requestNum);
	public List<Request> getEmpPending(int eid);
	public List<Request> getEmpResolved(int eid);
	public List<Request> getSpecificEmpReq(int eid);
	public List<Request> getPendingReqs();
	public List<Request> getResolvedReqs();
}
