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
	public void createReimbursement(String username, Reimbursement reimbursement) 
	{
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "call create_reimbursement(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, reimbursement.getReason());
			cs.setDouble(3, reimbursement.getAmount());
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
	public List<Reimbursement> getAllReimbursements() 
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
			log.error("error occured in catch block in ReimbursementDaoImpl getAllReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return allReimbursementInfo;
	}

	@Override
	public List<Reimbursement> getEmployeeReimbursements(String username) 
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
			log.error("error occured in catch block in ReimbursementDaoImpl getEmployeeReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return reimbursementInfo;
	}

	@Override
	public void updateReimbursement(String status, int rId) 
	{
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "UPDATE Reimbursements SET reimbursement_status = ? WHERE "
					+ "reimbursement_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, rId);
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
	public List<Reimbursement> getResolvedReimbursements()
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
			log.error("error occured in catch block in ReimbursementDaoImpl getResolvedReimbursements method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return resolvedReimbursementInfo;
	}
}