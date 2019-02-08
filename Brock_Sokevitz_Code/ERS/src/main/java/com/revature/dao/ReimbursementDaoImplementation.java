package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.enums.Status;
import com.revature.model.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDaoImplementation implements ReimbursementDao {
	
	private static ReimbursementDaoImplementation reimbursementDao;
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	final static Logger log = Logger.getLogger(ReimbursementDaoImplementation.class);

	private ReimbursementDaoImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	public static ReimbursementDaoImplementation getReimbursementDao() {
		
		if(reimbursementDao == null)
			reimbursementDao = new ReimbursementDaoImplementation();
		
		return reimbursementDao;
	}
	
	@Override
	public boolean insertReimbursement(String username, double amount) {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			String sql = "insert into reimbursements values(new_reimbursement_id.nextval,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setDouble(2, amount);	
			ps.setString(3, "pending");		
			ps.setString(4, "n/a");	
			ps.setInt(5, 0);	
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public boolean deleteReimbursement(String name) {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			String sql = "delete from reimbursements where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setString(1, name);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public Reimbursement getReimbursement(int reimbursementId) {
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "select * from reimbursements where reimbursement_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbursementId);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return new Reimbursement(reimbursementId, results.getString("username"), results.getDouble("amount"), 
						Status.valueOf(results.getString("status")), results.getString("approving_manager"), results.getInt("image"));
				
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return new Reimbursement();
	}

	@Override
	public List<Reimbursement> getAllReimbursements(String username) {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			String sql = "select * from reimbursements where username = ? order by reimbursement_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet results = ps.executeQuery();			
			List<Reimbursement> allAccounts = new ArrayList<>();
			while(results.next()) {
				allAccounts.add(new Reimbursement(results.getInt("reimbursement_id"), results.getString("username"), 
						results.getDouble("amount"), Status.valueOf(results.getString("status")), results.getString("approving_manager"),
						results.getInt("image")));
			}
			
			return allAccounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<Reimbursement> getAllPendingReimbursements() {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			String sql = "select * from reimbursements where status='pending' order by reimbursement_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, "pending");
			ResultSet results = ps.executeQuery();
			List<Reimbursement> allAccounts = new ArrayList<>();
			while(results.next()) {
				allAccounts.add(new Reimbursement(results.getInt("reimbursement_id"), results.getString("username"), 
						results.getDouble("amount"), Status.valueOf(results.getString("status")),results.getString("approving_manager"),
						results.getInt("image")));
			}
			
			return allAccounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<Reimbursement> getAllResolvedReimbursements() {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			String sql = "select * from reimbursements where status='declined' or status='approved' order by reimbursement_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, "pending");
			ResultSet results = ps.executeQuery();
			List<Reimbursement> allAccounts = new ArrayList<>();
			while(results.next()) {
				allAccounts.add(new Reimbursement(results.getInt("reimbursement_id"), results.getString("username"), 
						results.getDouble("amount"), Status.valueOf(results.getString("status")),results.getString("approving_manager"),
						results.getInt("image")));
			}
			
			return allAccounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	@Override
	public boolean updateReimbursement(int reimbursementId, String status, String manager) {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "update reimbursements set status = ?, approving_manager=? where reimbursement_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, manager);
			ps.setInt(3, reimbursementId);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return true;				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateUsernameReimbursement(String newUsername, String oldUsername) {
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "update reimbursements set username = ? where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			
			ResultSet results = ps.executeQuery();
			
			if (results.next()) {
				return true;				
			}
		}catch (Exception e) {
			log.error(e);
		}
		return false;
		
	}
	@Override
	public int getMostRecentReimbursement() {
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			String sql = "select MAX(reimbursement_id) from reimbursements";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, "pending");
			ResultSet results = ps.executeQuery();
			int id = 0;
			if(results.next()) {
				id = results.getInt(1);
			}			
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean addImage(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "update reimbursements set image = ? where reimbursement_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 68);
			ps.setInt(2, id);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return true;				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
