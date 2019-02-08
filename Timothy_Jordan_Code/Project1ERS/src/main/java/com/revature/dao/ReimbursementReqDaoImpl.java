package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reavature.tjordan.models.ReimbursementReq;
import com.revature.tjordan.services.ReimbursementReqService;
import com.revature.tjordan.util.JDBCConnectionUtil;

public class ReimbursementReqDaoImpl {

	private static ReimbursementReqDaoImpl ReimbursementDao;
	final static Logger Reimburselog = Logger.getLogger(ReimbursementReqDaoImpl.class);

	private ReimbursementReqDaoImpl() {

	}

	public static ReimbursementReqDaoImpl getReimbursementDao() {
		if (ReimbursementDao == null) {
			ReimbursementDao = new ReimbursementReqDaoImpl();
		}

		return ReimbursementDao;
	}
	
	
	
	
	
	
	
	public ReimbursementReq getRequest(int req_Int) {
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from req_table where REQ_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, req_Int);
			
			ResultSet results = ps.executeQuery();
			

			ReimbursementReq currentReq = null;
			while (results.next()) {
				currentReq = (new ReimbursementReq(
						results.getInt("EMP_ID"),
						results.getInt("REQ_ID"),
						results.getString("REQ_SUB_DATE"),
						results.getString("REQ_TYPE"),
						results.getDouble("REQ_AMOUNT"),
						results.getString("REQ_STATUS"),
						results.getInt("REQ_MAN_ID"),
						results.getString("REQ_RESOLVED_DATE")
						
						));

			}
			return currentReq;
		} catch (SQLException e) {

		}

		return null;
		
		
		
		
		
		
	}
	public boolean updateReq(int req_ID, int man_ID,  String status, String date) {
		System.out.println("Request ID: " + req_ID);
		System.out.println("ManagerID: " + man_ID);
		System.out.println("Status: " + status);
		System.out.println("Date: " + date);
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			
			String sql = "UPDATE REQ_TABLE SET REQ_MAN_ID = ?, REQ_STATUS = ?, REQ_RESOLVED_DATE = ? where REQ_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, man_ID);
			ps.setString(2, status);
			ps.setString(3, date);
			ps.setInt(4, req_ID);
			ResultSet results = ps.executeQuery();
			
			if(results.next()) {
				Reimburselog.info("Successful request update. In updateReq()");
				return true;
			} else {
				Reimburselog.error("Request Update Failed");
				//Throw custom exception here
			}
			
		} catch (SQLException e) {
			Reimburselog.error("SQl Error in updateReq");
			Reimburselog.error(e.getMessage());
			Reimburselog.error(e.getStackTrace());
		}
		
		
		
		
		return false;
	}
	
	
	public List<ReimbursementReq> addRequest(int emp_ID, String subDate, String type, double amount) {
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "Call ADD_REQUEST(?,?,?,?)";
			CallableStatement callst = conn.prepareCall(sql);
			
			callst.setInt(1, emp_ID);
			callst.setString(2, subDate);
			callst.setString(3, type);
			callst.setDouble(4, amount);
			
			if(callst.executeUpdate() == 0) {
				//create a request object and return
				
				List<ReimbursementReq> updated = ReimbursementReqService.getReimbursementService().getAllPending(emp_ID);
				
				for(ReimbursementReq x : updated) {
					System.out.println(x);
				}
				return updated;
				
			} else {
				throw new SQLException();
			}
			
			
			
		} catch(SQLException e) {
			Reimburselog.error("SQL Exception in addRequest Method");
			Reimburselog.error(e.getMessage());
			Reimburselog.error(e.getStackTrace());
		}
		
		
		return new ArrayList<ReimbursementReq>();
	}
	
	
	
	
	
	
	
	public List<ReimbursementReq> getAllSpecificRequests(int emp_ID){
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from req_table where EMP_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_ID);
			
			ResultSet results = ps.executeQuery();
			

			List<ReimbursementReq> pendingRequest = new ArrayList<>();
			while (results.next()) {
				pendingRequest.add(new ReimbursementReq(
						results.getInt("EMP_ID"),
						results.getInt("REQ_ID"),
						results.getString("REQ_SUB_DATE"),
						results.getString("REQ_TYPE"),
						results.getDouble("REQ_AMOUNT"),
						results.getString("REQ_STATUS"),
						results.getInt("REQ_MAN_ID"),
						results.getString("REQ_RESOLVED_DATE")
						
						));

			}
			return pendingRequest;
		} catch (SQLException e) {

		}

		return new ArrayList<ReimbursementReq>();
		
		
		
	}
	
	
	
	public List<ReimbursementReq> getResolvedGlobal() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from req_table where REQ_Status != ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "pending");
			
			ResultSet results = ps.executeQuery();
			

			List<ReimbursementReq> resolvedRequest = new ArrayList<>();
			while (results.next()) {
				resolvedRequest.add(new ReimbursementReq(
						results.getInt("EMP_ID"),
						results.getInt("REQ_ID"),
						results.getString("REQ_SUB_DATE"),
						results.getString("REQ_TYPE"),
						results.getDouble("REQ_AMOUNT"),
						results.getString("REQ_STATUS"),
						results.getInt("REQ_MAN_ID"),
						results.getString("REQ_RESOLVED_DATE")
						
						));

			}
			return resolvedRequest;
		} catch (SQLException e) {

		}

		return new ArrayList<ReimbursementReq>();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public List<ReimbursementReq> getAllResolved(int emp_ID) {
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from req_table where EMP_ID = ? AND REQ_STATUS != ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_ID);
			ps.setString(2, "pending");
			ResultSet results = ps.executeQuery();
			

			List<ReimbursementReq> pendingRequest = new ArrayList<>();
			while (results.next()) {
				pendingRequest.add(new ReimbursementReq(
						results.getInt("EMP_ID"),
						results.getInt("REQ_ID"),
						results.getString("REQ_SUB_DATE"),
						results.getString("REQ_TYPE"),
						results.getDouble("REQ_AMOUNT"),
						results.getString("REQ_STATUS"),
						results.getInt("REQ_MAN_ID"),
						results.getString("REQ_RESOLVED_DATE")
						
						));

			}
			return pendingRequest;
		} catch (SQLException e) {

		}

		return new ArrayList<ReimbursementReq>();
	}

		public List<ReimbursementReq> getAll() {
			try (Connection conn = JDBCConnectionUtil.getConnection()) {
				String sql = "select * from req_table";
				PreparedStatement ps = conn.prepareStatement(sql);
				
				
				ResultSet results = ps.executeQuery();
				

				List<ReimbursementReq> pendingRequest = new ArrayList<>();
				while (results.next()) {
					pendingRequest.add(new ReimbursementReq(
							results.getInt("EMP_ID"),
							results.getInt("REQ_ID"),
							results.getString("REQ_SUB_DATE"),
							results.getString("REQ_TYPE"),
							results.getDouble("REQ_AMOUNT"),
							results.getString("REQ_STATUS"),
							results.getInt("REQ_MAN_ID"),
							results.getString("REQ_RESOLVED_DATE")
							
							));

				}
				return pendingRequest;
			} catch (SQLException e) {

			}

			return new ArrayList<ReimbursementReq>();
		}
	
	public List<ReimbursementReq> getAllPendingMan() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from req_table where REQ_STATUS = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "pending");
			
			ResultSet results = ps.executeQuery();
			

			List<ReimbursementReq> pendingRequest = new ArrayList<>();
			while (results.next()) {
				pendingRequest.add(new ReimbursementReq(
						results.getInt("EMP_ID"),
						results.getInt("REQ_ID"),
						results.getString("REQ_SUB_DATE"),
						results.getString("REQ_TYPE"),
						results.getDouble("REQ_AMOUNT"),
						results.getString("REQ_STATUS"),
						results.getInt("REQ_MAN_ID"),
						results.getString("REQ_RESOLVED_DATE")
						
						));

			}
			return pendingRequest;
		} catch (SQLException e) {

		}

		return new ArrayList<ReimbursementReq>();
	}
	
		
	

	public List<ReimbursementReq> getAllPending(int emp_ID) {

		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from req_table where EMP_ID = ? AND REQ_STATUS = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp_ID);
			ps.setString(2, "pending");
			ResultSet results = ps.executeQuery();
			

			List<ReimbursementReq> pendingRequest = new ArrayList<>();
			while (results.next()) {
				pendingRequest.add(new ReimbursementReq(
						results.getInt("EMP_ID"),
						results.getInt("REQ_ID"),
						results.getString("REQ_SUB_DATE"),
						results.getString("REQ_TYPE"),
						results.getDouble("REQ_AMOUNT"),
						results.getString("REQ_STATUS"),
						results.getInt("REQ_MAN_ID"),
						results.getString("REQ_RESOLVED_DATE")
						
						));

			}
			return pendingRequest;
		} catch (SQLException e) {

		}

		return new ArrayList<ReimbursementReq>();
	}

}
