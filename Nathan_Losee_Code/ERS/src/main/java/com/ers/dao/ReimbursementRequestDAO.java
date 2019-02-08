package com.ers.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.ReimbursementRequest;
import com.ers.util.ConnectionManager;
import com.ers.util.ERSExceptions;

public class ReimbursementRequestDAO implements ReimbursementRequestDAOInterface {
	private static ReimbursementRequestDAO singleton = new ReimbursementRequestDAO();
	private ReimbursementRequestDAO() { }
	public static ReimbursementRequestDAO getDAO() {
		return singleton;
	}
	
	private PreparedStatement readRequestsStmnt;
	private PreparedStatement readRequestStmnt;
	private PreparedStatement updateRequestStmnt;
	public void resetStmnts() throws SQLException {
		String sql = "SELECT * FROM reimbursement_requests WHERE e_id = ?";
		readRequestsStmnt = ConnectionManager.getJDBCConnection().prepareStatement(sql);
		sql = "SELECT * FROM reimbursement_requests WHERE rr_id = ?";
		readRequestStmnt = ConnectionManager.getJDBCConnection().prepareStatement(sql);
		sql = "UPDATE reimbursement_requests "
				+ "SET m_id = ?, is_approved = ? "
				+ "WHERE rr_id = ?";
		updateRequestStmnt = ConnectionManager.getJDBCConnection().prepareStatement(sql);
	}
	
	@Override
	public boolean createRequest(int eID, String description, double amount) throws SQLException {
		try (CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL insert_request(?,?,?,?)")) {
			stmnt.setInt(1, eID);
			stmnt.setString(2, description);
			stmnt.setDouble(3, amount);
			stmnt.registerOutParameter(4, Types.INTEGER);
			
			stmnt.executeUpdate();
			return stmnt.getInt(4) > 0;
		} catch (SQLException e) {
			throw new ERSExceptions.InvalidEIDException();
		}
	}
	
	@Override
	public List<ReimbursementRequest> readRequests(int eID) throws SQLException {
		readRequestsStmnt.setInt(1, eID);
		try (ResultSet foundRequests = readRequestsStmnt.executeQuery()) {
			List<ReimbursementRequest> requests = new ArrayList<>();

			while (foundRequests.next()) {
				ReimbursementRequest request = new ReimbursementRequest();
				
				request.setRrID(foundRequests.getInt(1));
				request.seteID(foundRequests.getInt(2));
				request.setmID(foundRequests.getInt(3));
				request.setRrDescription(foundRequests.getString(4));
				request.setRrAmount(foundRequests.getDouble(5));
				request.setApproved(
						foundRequests.getString(6) != null && foundRequests.getString(6).equals("t"));
	
				requests.add(request);
			}
			
			return requests;
		} catch (SQLException e) {
			throw new ERSExceptions.InvalidEIDException();
		}
	}
	@Override
	public ReimbursementRequest readRequest(int rrID) throws SQLException {
		readRequestStmnt.setInt(1, rrID);
		try (ResultSet foundRequests = readRequestStmnt.executeQuery()) {
			if (foundRequests.next()) {
				ReimbursementRequest request = new ReimbursementRequest();
				
				request.setRrID(foundRequests.getInt(1));
				request.seteID(foundRequests.getInt(2));
				request.setmID(foundRequests.getInt(3));
				request.setRrDescription(foundRequests.getString(4));
				request.setRrAmount(foundRequests.getDouble(5));
				request.setApproved(
						foundRequests.getString(6) != null && foundRequests.getString(6).equals("t"));
	
				return request;
			} else {
				throw new ERSExceptions.InvalidRRIDException();
			}
		}
	}
	
	@Override
	public boolean updateRequest(int rrid, int mID, boolean isApproved) throws SQLException {
		try {
			updateRequestStmnt.setInt(1, mID);
			updateRequestStmnt.setString(2, (isApproved ? "t" : "f"));
			updateRequestStmnt.setInt(3, rrid);
			if (updateRequestStmnt.executeUpdate() > 0)
				return true;
			throw new SQLException();
		} catch (SQLException e) {
			throw new ERSExceptions.InvalidRRIDException();
		}
	}
}
