package com.ers.dao;

import java.sql.SQLException;
import java.util.List;

import com.ers.models.ReimbursementRequest;

public interface ReimbursementRequestDAOInterface {
	public boolean createRequest(int eID, String description, double amount) throws SQLException;
	public List<ReimbursementRequest> readRequests(int eID) throws SQLException;
	public ReimbursementRequest readRequest(int rrID) throws SQLException;
	public boolean updateRequest(int rrid, int mID, boolean isApproved) throws SQLException;
}
