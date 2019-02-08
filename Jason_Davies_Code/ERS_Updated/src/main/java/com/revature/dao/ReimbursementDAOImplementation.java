package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAOImplementation implements ReimbursementDAO {

	private static ReimbursementDAOImplementation instance;
	static Logger logger = Logger.getLogger(ReimbursementDAOImplementation.class);
	
	private ReimbursementDAOImplementation() {}
	
	public static ReimbursementDAOImplementation getReimbursementDAO() {
		if (instance == null) {
			instance = new ReimbursementDAOImplementation();
		}
		return instance;
	}
	
	@Override
	public Reimbursement insertReimbursement(Reimbursement reimbursement) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO REIMBURSEMENT values(?,?,NULL,?,?,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, reimbursement.getAuthorizerId());
				ps.setInt(2, reimbursement.getRequesterId());
				ps.setString(3, reimbursement.getType().toString());
				ps.setString(4, reimbursement.getStatus().toString());
				ps.setFloat(5, reimbursement.getAmount());
				ps.setString(6, reimbursement.getDate());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return reimbursement;
			}
		} catch (SQLException e) {
			logger.error("insertReimbursement() exception due to: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REIMBURSEMENT SET E_ID_AUTHORIZER = ?, E_ID_REQUESTER = ?, R_TYPE = ?, R_STATUS = ?, R_AMOUNT = ?, R_DATE = ? WHERE R_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, reimbursement.getAuthorizerId());
				ps.setInt(2, reimbursement.getRequesterId());
				ps.setString(3, reimbursement.getType().toString());
				ps.setString(4, reimbursement.getStatus().toString());
				ps.setFloat(5, reimbursement.getAmount());
				ps.setString(6, reimbursement.getDate());
				ps.setInt(7, reimbursement.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return reimbursement;
			}
		} catch (SQLException e) {
			logger.error("updateReimbursement() exception due to: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean deleteReimbursement(int reimbursementId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM REIMBURSEMENT WHERE R_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, reimbursementId);
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return true;
			}
		} catch (SQLException e) {
			logger.error("deleteReimbursement() exception due to: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteAllReimbursements(int employeeId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM REIMBURSEMENT WHERE E_ID_REQUESTER = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, employeeId);
				if (ps.executeUpdate() > 0) {
					throw new SQLException();
				}
				return true;
			}
		} catch (SQLException e) {
			logger.error("deleteAllReimbursements() exception due to: " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public Reimbursement getReimbursement(int reimbursementId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE R_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, reimbursementId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Reimbursement(
								rs.getInt("R_ID"),
								rs.getInt("E_ID_REQUESTER"), 
								rs.getInt("E_ID_AUTHORIZER"),
								ReimbursementType.valueOf(rs.getString("R_TYPE")),
								ReimbursementStatus.valueOf(rs.getString("R_STATUS")), 
								rs.getInt("R_AMOUNT"),
								rs.getString("R_DATE")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error("getReimbursement() exception due to: " + e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getAllReimbursements(int employeeId, ReimbursementStatus status) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE E_ID_REQUESTER = ? AND R_STATUS = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, employeeId);
				ps.setString(2, status.toString());
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
					while (rs.next()) {
						reimbursements.add(new Reimbursement(
								rs.getInt("R_ID"),
								employeeId,
								rs.getInt("E_ID_AUTHORIZER"),
								ReimbursementType.valueOf(rs.getString("R_TYPE")), // TODO: Your problem is here!!! 'TYPE' != PENDING
								status,
								rs.getFloat("R_AMOUNT"),
								rs.getString("R_DATE")
								));
					}
					return reimbursements;
				}
			}
		} catch (SQLException e) {
			logger.error("getAllReimbursements() exception due to: " + e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getAllReimbursements(ReimbursementStatus status) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE R_STATUS = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, status.toString());
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
					while (rs.next()) {
						reimbursements.add(new Reimbursement(
								rs.getInt("R_ID"),
								rs.getInt("E_ID_REQUESTER"),
								rs.getInt("E_ID_AUTHORIZER"),
								ReimbursementType.valueOf(rs.getString("R_TYPE")),
								status,
								rs.getFloat("R_AMOUNT"),
								rs.getString("R_DATE")
								));
					}
					return reimbursements;
				}
			}
		} catch (SQLException e) {
			logger.error("getAllReimbursements() exception due to: " + e.getMessage());
		}
		return null;
	}
}
