package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.util.JDBSCConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{
	
private static ReimbursementDaoImpl reim;
	
	private ReimbursementDaoImpl() {
		
	}
	
	public static ReimbursementDaoImpl getReim() {
		if(reim == null) {
			reim = new ReimbursementDaoImpl();
		}
		
		return reim;
	}
	
	public boolean submitReim(int id, int amn, String descr) {
		System.out.println("submitreimbursement is called properly");
		System.out.println("this is what is passed: id: " + id + "amn: " + amn + "descr: " + descr);
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			String sql = "call SUB_REQUEST(?,?,?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, id);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.setInt(4 , amn);
			ps.setString(5, "travel");
			ps.setString(6, descr);
			ps.executeUpdate();
			System.out.println("finished submit reimbursement");
			return true;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		System.out.println("does not submit properly");
		return false;
	}

	public List<Reimbursement> singleUserReim(int id) throws SQLException {
		System.out.println("getAllRequestsSingle is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where USER_ID = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet results = stmt.executeQuery();
			
			List<Reimbursement> allReimbursement = new ArrayList<>();
			while(results.next()) {
				allReimbursement.add(new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION_")));
			}
			return allReimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("returned an empty arraylist for getallrequestssingle");
		return new ArrayList<>();
	}
	@Override
	public Reimbursement getReimbursement(int id) {
		System.out.println("getReimbursement is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "Select * from REIMBURSEMENT where USER_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				
				return new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION")
								);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("returned null");
		return null;
	}

	@Override
	public List<Reimbursement> getAllPendingRequestsSingle(int id) throws SQLException {
		System.out.println("getAllPendingRequestsSingle is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where USER_ID = ? and PROCESSED = 0";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			System.out.println("id: " + id);
			ResultSet results = stmt.executeQuery();
			
			List<Reimbursement> allReimbursement = new ArrayList<>();
			while(results.next()) {
				allReimbursement.add(new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION_")));
			}
			System.out.println("retrieved all pending requests");
			return allReimbursement;
			
		}catch(SQLException e) {
			e.getSQLState();
			e.getErrorCode();
			e.printStackTrace();
		}
		System.out.println("returned an empty array of pending requests single");
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursement> getAllCompletedRequestsSingle(int id) throws SQLException {
		System.out.println("getAllCompletedRequestsSingle is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where USER_ID = ? and PROCESSED = 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet results = stmt.executeQuery();
			
			List<Reimbursement> allReimbursement = new ArrayList<>();
			while(results.next()) {
				allReimbursement.add(new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION_")));
			}
			return allReimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("returned an empty arraylist for getallcompletedrequestssingle");
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursement> getAllReimbursement() throws SQLException {
		System.out.println("getAllReimbursement is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			//log.info("executing the query");
			Statement stmt = conn.createStatement();
			String sql = "select * from REIMBURSEMENT";
			
			//log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			//log.info("viewing the results");
			List<Reimbursement> allReimbursement = new ArrayList<>();
			while(results.next()) {
				allReimbursement.add(new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION_")));
			}
			return allReimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursement> getAllCompletedRequests() throws SQLException {
		System.out.println("getAllCompletedRequests is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where PROCESSED = 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet results = stmt.executeQuery();
			
			List<Reimbursement> allReimbursement = new ArrayList<>();
			while(results.next()) {
				allReimbursement.add(new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION_")));
			}
			return allReimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("returned an empty arraylist for getallcompletedrequests");
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursement> getAllPendingRequests() throws SQLException {
		System.out.println("getAllPendingRequests is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where PROCESSED = 0";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet results = stmt.executeQuery();
			
			List<Reimbursement> allReimbursement = new ArrayList<>();
			while(results.next()) {
				allReimbursement.add(new Reimbursement(
						results.getInt("USER_REIM"),
						results.getInt("USER_ID"),
						results.getInt("MANAGER_ASSIGNED"),
						results.getInt("PROCESSED"),
						results.getInt("AMOUNT"),
						results.getString("TYPE_"),
						results.getString("DESCRIPTION_")));
			}
			System.out.println("retrieved all pending requests");
			return allReimbursement;
			
		}catch(SQLException e) {
			e.getSQLState();
			e.getErrorCode();
			e.printStackTrace();
		}
		System.out.println("returned an empty array of pending requests");
		return new ArrayList<>();
	}

}
