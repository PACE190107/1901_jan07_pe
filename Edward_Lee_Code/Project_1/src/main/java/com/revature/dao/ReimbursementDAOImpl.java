package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.model.ResolvedReimbursement;
import com.revature.utilities.DAOUtilities;

import oracle.jdbc.OracleTypes;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	Connection connection = null;	// Our connection to the database
	private static ReimbursementDAOImpl employeeDao;
	final static Logger log = LogManager.getLogger(ReimbursementDAOImpl.class);
	private ReimbursementDAOImpl() {
	}
	
	public static ReimbursementDAOImpl getReimbursementDAOImpl() {
		if (employeeDao == null) {
			employeeDao = new ReimbursementDAOImpl();
		}
		return employeeDao;
	}
	
//--------------------------------------------------------------------------------
	@Override
	public Reimbursement createReimbursement(int employee_id, double amount, Date expenseDate) {
		Reimbursement re = null;
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "CALL NEW_REIMBURSEMENT(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employee_id);
			cs.setDouble(2, amount);
			cs.setTimestamp(3, new java.sql.Timestamp( expenseDate.getTime()) );
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(4);
			while(rs.next()) {
				if (rs.getInt(6)==-1) {
					re = new Reimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5));
				}else if (rs.getInt(6) ==1){
					re = new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5), true, rs.getDate(7), rs.getInt(8));
				}else if(rs.getInt(6) ==0) {
					re = new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5), false, rs.getDate(7), rs.getInt(8));
				}
			}
			
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return re;
	}

//---------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> getPendingReimbursements() {
			List<Reimbursement> pending = new ArrayList<>();
			try(Connection conn = DAOUtilities.getConnection()){
				String sql = "CALL GET_PENDING(?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				cs.execute();
				ResultSet rs = (ResultSet) cs.getObject(1);
				while(rs.next()) {
					pending.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4), rs.getDate(5)));
				}
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		return pending;
	}

//---------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "SELECT * FROM REIMBURSEMENT";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getInt(6)==-1) {
					reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5)));
				}else if (rs.getInt(6) ==1){
					reimbursements.add(new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5), true, rs.getDate(7), rs.getInt(8)));
				}else if(rs.getInt(6) ==0) {
					reimbursements.add( new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5), false, rs.getDate(7), rs.getInt(8)));
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return reimbursements;
	}

//---------------------------------------------------------------------------------
	@Override
	public List<ResolvedReimbursement> getResolvedReimbursements() {
		List<ResolvedReimbursement> reimbursements = new ArrayList<>();
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "CALL GET_RESOLVED(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			while(rs.next()) {
				if (rs.getInt(6) ==1){
					reimbursements.add(new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5), true, rs.getDate(7), rs.getInt(8)));
				}else if(rs.getInt(6) ==0) {
					reimbursements.add( new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5), false, rs.getDate(7), rs.getInt(8)));
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return reimbursements;
	}

//---------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> getFilteredReimbursements(String user_name) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "CALL GET_EMPLOYEE_REIMBURSEMENTS(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user_name);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(2);
			while(rs.next()) {
				if (rs.getInt(6)==-1) {
					reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5)));
				}else if (rs.getInt(6) ==1){
					reimbursements.add(new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5), true, rs.getDate(7), rs.getInt(8)));
				}else if(rs.getInt(6) ==0) {
					reimbursements.add( new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getDouble(3), rs.getDate(4),rs.getDate(5), false, rs.getDate(7), rs.getInt(8)));
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}finally {
			this.closeResources();
		}
		return reimbursements;
	}

//---------------------------------------------------------------------------------
	@Override
	public ResolvedReimbursement approveReimbursement(int reimbursementId, int manId) {
		ResolvedReimbursement resolved = null;
		try(Connection conn = DAOUtilities.getConnection()) {
			String sql = "CALL APPROVE_REIMBURSEMENT(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursementId);
			cs.setInt(2, manId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(3);
			while(rs.next()) {
				if (rs.getInt(6) ==1){
					resolved = new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5), true, rs.getDate(7), rs.getInt(8));
				}else if(rs.getInt(6) ==0) {
					resolved = new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5), false, rs.getDate(7), rs.getInt(8));
				}
			}			
		}catch(Exception e) {
			log.error(e.getMessage());
		}finally {
			this.closeResources();
		}
		return resolved;
	}
	
//---------------------------------------------------------------------------------
	@Override
	public ResolvedReimbursement denyReimbursement(int reimbursementId, int manId) {
		ResolvedReimbursement resolved = null;
		try(Connection conn = DAOUtilities.getConnection()) {
			String sql = "CALL DENY_REIMBURSEMENT(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, reimbursementId);
			cs.setInt(2, manId);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(3);
			while(rs.next()) {
				if (rs.getInt(6) ==1){
					resolved = new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5), true, rs.getDate(7), rs.getInt(8));
				}else if(rs.getInt(6) ==0) {
					resolved = new ResolvedReimbursement(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getDate(5), false, rs.getDate(7), rs.getInt(8));
				}
			}			
		}catch(Exception e) {
			log.error(e.getMessage());
		}finally {
			this.closeResources();
		}
		return resolved;
	}
	
//---------------------------------------------------------------------------------
	private void closeResources() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
//switch to logger
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
