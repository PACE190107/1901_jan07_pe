package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.services.ERSService;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class ReimbursementDaoImpl implements ReimbursementDao{

	final static Logger log = Logger.getLogger(ERSService.class);
	
	private static ReimbursementDaoImpl reimbursementDao = null;
	
	private ReimbursementDaoImpl() {
		
	}
	
	public static ReimbursementDaoImpl getReimbursementDao() {
		if(reimbursementDao == null) {
			reimbursementDao = new ReimbursementDaoImpl();
		}
		
		return reimbursementDao;
	}
	
	//tested
	public boolean createReimbursement(Reimbursement reimbursement) {
		boolean executed = false;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "call insert_reimbursement(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursement.getEmployeeId());
			cs.setString(2, reimbursement.getSubject());
			cs.setInt(3, reimbursement.getAmount());
			cs.setString(4, reimbursement.getDescription());
		
			if(cs.executeUpdate() == 0) {
				executed = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			executed = false;
		}
		return executed;
	}

	public Reimbursement getReimbursementById(int rId) {
		Reimbursement reimbursement = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where reimbursement_id = " + rId;
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//DB order not the same as constructor in Reimbursement class.
				reimbursement = 
						new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getInt(6), rs.getString(5), rs.getString(7),rs.getInt(4));
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			reimbursement = null;
		}
		
		return reimbursement;
	}
	
	public List<Reimbursement> getAllReimbursements(){
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//DB order not the same as constructor in Reimbursement class.
				Reimbursement nextInQuery = 
						new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getInt(6), rs.getString(5), rs.getString(7),rs.getInt(4));
				reimbursements.add(nextInQuery);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			reimbursements = null;
		}
		
		return reimbursements;
	}
	
	public List<Reimbursement> getPendingReimbursements() {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where status_id = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//DB order not the same as constructor in Reimbursement class.
				Reimbursement nextInQuery = 
						new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getInt(6), rs.getString(5), rs.getString(7),rs.getInt(4));
				reimbursements.add(nextInQuery);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			reimbursements = null;
		}
		
		return reimbursements;
	}

	public List<Reimbursement> getResolvedReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where status_id in (2,3)";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//DB order not the same as constructor in Reimbursement class.
				Reimbursement nextInQuery = 
						new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getInt(6), rs.getString(5), rs.getString(7),rs.getInt(4));
				reimbursements.add(nextInQuery);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			reimbursements = null;
		}
		
		return reimbursements;
	}

	//tested
	public boolean resolveReimbursement(ReimbursementStatus newStatus, int rId, int managerId) {
		
		boolean resolved = false;
		int statusId = 0;
		
		switch(newStatus) {
			case APPROVED: statusId = 2;
				break;
			case DENIED: statusId = 3;
				break;
			default: log.info("Invalid Entry in Class ReimbursementDaoImpl method resolveReimbursement");
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE reimbursement SET status_id = " + statusId 
					+ ", approver_id = " + managerId + " where reimbursement_id = " + rId;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if(ps.executeUpdate() == 1) {
				resolved = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			resolved = false;
		}
		
		return resolved;
	}

	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where user_id = " + id;
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//DB order not the same as constructor in Reimbursement class.
				Reimbursement nextInQuery = 
						new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getInt(6), rs.getString(5), rs.getString(7),rs.getInt(4));
				reimbursements.add(nextInQuery);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			reimbursements = null;
		}
		
		return reimbursements;
	}

	//tested
	public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursement where status_id in (2,3) and user_id = " + id;
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//DB order not the same as constructor in Reimbursement class.
				Reimbursement nextInQuery = 
						new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getInt(6), rs.getString(5), rs.getString(7),rs.getInt(4));
				reimbursements.add(nextInQuery);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			reimbursements = null;
		}
		
		return reimbursements;
	}

}
