package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao
{
	final static Logger log = Logger.getLogger(ReimbursementDaoImpl.class);
	
	@Override
	public void createReimbursement(String username, String reason, double amount) 
	{
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "call create_reimbursement(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, reason);
			cs.setDouble(3, amount);
			cs.setString(4, "Pending");
			cs.registerOutParameter(5, Types.INTEGER);
			cs.executeUpdate();
			
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in catch block in ReimbursementDaoImpl createReimbursement method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public List<Reimbursement> manAllReimbursements() 
	{
		List<Reimbursement> allReimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM Reimbursements";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				allReimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status"),
						rs.getString("approved_by")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in catch block in ReimbursementDaoImpl manAllReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return allReimbursementInfo;
	}

	@Override
	public List<Reimbursement> manEmployeeReimbursements(String username) 
	{
		List<Reimbursement> reimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM Reimbursements WHERE u_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				reimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status"),
						rs.getString("approved_by")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in SQLException catch block in ReimbursementDaoImpl manEmployeeReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return reimbursementInfo;
	}

	@Override
	public void updateReimbursement(String status, int rId, String username) 
	{
		if(status.equals("Approve"))
		{
			status = "Approved";
		}
		else
		{
			status = "Denied";
		}
		
		System.out.println(status + " " + username);
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "UPDATE Reimbursements SET reimbursement_status = ?, approved_by = ? WHERE "
					+ "reimbursement_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, username);
			ps.setInt(3, rId);
			ps.executeUpdate();
			
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in catch block in ReimbursementDaoImpl updateReimbursement method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	@Override
	public List<Reimbursement> manResolvedReimbursements()
	{
		List<Reimbursement> resolvedReimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM Reimbursements WHERE reimbursement_status = ? OR reimbursement_status = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Approved");
			ps.setString(2, "Denied");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				resolvedReimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status"),
						rs.getString("approved_by")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in SQLException catch block in ReimbursementDaoImpl manResolvedReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return resolvedReimbursementInfo;
	}
	
	@Override
	public List<Reimbursement> manPendingReimbursements()
	{
		List<Reimbursement> pendingReimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT * FROM Reimbursements WHERE reimbursement_status = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Pending");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				pendingReimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status"),
						rs.getString("approved_by")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in SQLException catch block in ReimbursementDaoImpl manPendingReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return pendingReimbursementInfo;
	}
	
	@Override
	public List<Reimbursement> empEmployeeReimbursements(String username) 
	{
		List<Reimbursement> reimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT u_name, reimbursement_id, reason, amount, reimbursement_status"
					+ " FROM Reimbursements WHERE u_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				reimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in SQLException catch block in ReimbursementDaoImpl empEmployeeReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return reimbursementInfo;
	}
	
	@Override
	public List<Reimbursement> empResolvedReimbursements(String username)
	{
		List<Reimbursement> resolvedReimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT u_name, reimbursement_id, reason, amount, reimbursement_status"
					+ " FROM Reimbursements WHERE u_name = ? AND (reimbursement_status = ? OR reimbursement_status = ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, "Approved");
			ps.setString(3, "Denied");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				resolvedReimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in SQLException catch block in ReimbursementDaoImpl empResolvedReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return resolvedReimbursementInfo;
	}
	
	@Override
	public List<Reimbursement> empPendingReimbursements(String username)
	{
		List<Reimbursement> pendingReimbursementInfo = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT u_name, reimbursement_id, reason, amount, reimbursement_status"
					+ " FROM Reimbursements WHERE u_name = ? AND reimbursement_status = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, "Pending");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				pendingReimbursementInfo.add(new Reimbursement(rs.getString("u_name"), rs.getInt("reimbursement_id"),
						rs.getString("reason"), rs.getDouble("amount"), rs.getString("reimbursement_status")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in SQLException catch block in ReimbursementDaoImpl empPendingReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return pendingReimbursementInfo;
	}
}