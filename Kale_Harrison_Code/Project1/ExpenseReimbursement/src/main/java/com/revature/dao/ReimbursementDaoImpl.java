package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static ReimbursementDaoImpl rebDao;
	final static Logger log = Logger.getLogger(ReimbursementDaoImpl.class);
	
	public static ReimbursementDaoImpl getRebDao() {
		if (rebDao == null) {
			rebDao = new ReimbursementDaoImpl();
		}
		return rebDao;
	}
	
	
	@Override
	public boolean submitRequest(double amount, String description, int e_id ) {
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "call newRequest(?,?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(1, amount);
			cs.setString(2, description);
			cs.setInt(3, e_id);
			cs.executeUpdate();
		} catch (SQLException e) {
			log.error("submitRequest failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public List<Reimbursement> viewPending() {
		Reimbursement singleReb = new Reimbursement();
		List<Reimbursement> rebList = new ArrayList();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "SELECT * FROM REIMBURSEMENT WHERE r_status = 'Pending'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				singleReb = new Reimbursement(
						rs.getInt("r_id"),
						rs.getDouble("r_amount"),
						rs.getString("r_description"),
						rs.getString("r_status"),
						rs.getInt("r_resolverId"),
						rs.getInt("e_id"));
					rebList.add(singleReb);	
			}
			
		}catch (SQLException e) {
			log.error("viewPending failed");
			e.printStackTrace();
		}
		return rebList;
	}
	

	@Override
	public List<Reimbursement> viewPending(int e_id) {
		Reimbursement singleReb = new Reimbursement();
		List<Reimbursement> rebList = new ArrayList();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "SELECT * FROM REIMBURSEMENT WHERE r_status = 'Pending' AND e_id = " + e_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				singleReb = new Reimbursement(
						rs.getInt("r_id"),
						rs.getDouble("r_amount"),
						rs.getString("r_description"),
						rs.getString("r_status"),
						rs.getInt("r_resolverId"),
						rs.getInt("e_id"));
					rebList.add(singleReb);	
			}
			
		}catch (SQLException e) {
			log.error("viewPending failed");
			e.printStackTrace();
		}
		return rebList;
	}
	
	@Override
	public List<Reimbursement> viewResolved() {
		Reimbursement singleReb = new Reimbursement();
		List<Reimbursement> rebList = new ArrayList();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "SELECT * FROM REIMBURSEMENT WHERE r_status = 'Approved'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				singleReb = new Reimbursement(
						rs.getInt("r_id"),
						rs.getDouble("r_amount"),
						rs.getString("r_description"),
						rs.getString("r_status"),
						rs.getInt("r_resolverId"),
						rs.getInt("e_id"));
					rebList.add(singleReb);	
			}
			
		}catch (SQLException e) {
			log.error("viewResolved failed");
			e.printStackTrace();
		}
		return rebList;
	}
	@Override
	public List<Reimbursement> viewResolved(int e_id) {
		Reimbursement singleReb = new Reimbursement();
		List<Reimbursement> rebList = new ArrayList();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "SELECT * FROM REIMBURSEMENT WHERE r_status = 'Approved' AND e_id = " + e_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				singleReb = new Reimbursement(
						rs.getInt("r_id"),
						rs.getDouble("r_amount"),
						rs.getString("r_description"),
						rs.getString("r_status"),
						rs.getInt("r_resolverId"),
						rs.getInt("e_id"));
					rebList.add(singleReb);	
			}
			
		}catch (SQLException e) {
			log.error("viewResolved failed");
			e.printStackTrace();
		}
		return rebList;
	}
	

	@Override
	public boolean approveRequest(int re_id, int e_id) {
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "call approval(?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, re_id);
			cs.setInt(2, e_id);
			cs.executeUpdate();
		}catch (SQLException e) {
			log.error("approveRequest failed");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean denyRequest(int re_id, int e_id) {
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "call denied(?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, re_id);
			cs.setInt(2, e_id);
			cs.executeUpdate();
		}catch (SQLException e) {
			log.error("denyRequest failed");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<Reimbursement> viewRequestsSingleEmployee(int e_id) {
		Reimbursement singleReb = new Reimbursement();
		List<Reimbursement> rebList = new ArrayList();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "SELECT * from REIMBURSEMENT WHERE e_id = " + e_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				singleReb = new Reimbursement(
						rs.getInt("r_id"),
						rs.getDouble("r_amount"),
						rs.getString("r_description"),
						rs.getString("r_status"),
						rs.getInt("r_resolverId"),
						rs.getInt("e_id"));
					rebList.add(singleReb);	
			}
			
		}catch (SQLException e) {
			log.error("viewRequestsSingleEmployee failed");
			e.printStackTrace();
		}
		return rebList;

	}

}
