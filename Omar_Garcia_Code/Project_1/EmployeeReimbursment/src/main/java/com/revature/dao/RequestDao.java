package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.module.Request;

public interface RequestDao {
	
	List<Request> getAllRequest(int i) throws SQLException;
	Request addRequest(Request req) throws SQLException;
	List<Request> viewEmpRequest(int id, int aproval) throws SQLException;
	Request updateRequest(Request req) throws SQLException;

}
