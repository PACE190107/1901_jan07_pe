package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.module.Request;
import com.revature.util.JDBCConnectionUtil;

public class RequestDaoImplementation implements RequestDao {

	private static RequestDaoImplementation request;

	private RequestDaoImplementation() {
	};

	public static RequestDaoImplementation getDaoImplementation() {
		if (request == null) {
			request = new RequestDaoImplementation();
		}
		return request;
	}

	@Override
	public List<Request> getAllRequest(int i) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql;
			if (i == 0) {
				sql = "SELECT * FROM REQUEST";
			} else {
				sql = "SELECT * FROM REQUEST WHERE R_APPROVALE = 1 OR R_APPROVALE = 2";
			}
			Statement stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(sql);
			List<Request> allReq = new ArrayList<Request>();
			while (results.next()) {
				allReq.add(new Request(results.getInt(1), results.getString(2), results.getInt(3), results.getString(4),
						results.getInt(5), results.getInt(6), results.getInt(7)));
			}
			return allReq;
		}
	}

	@Override
	public Request addRequest(Request req) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "CALL INSERT_REQUEST(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, req.getType());
			cs.setInt(2, req.getAmount());
			cs.setString(3, req.getDescription());
			cs.setInt(4, req.getUserId());
			cs.execute();
		}
		return req;
	}

	@Override
	public Request viewEmpRequest(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
