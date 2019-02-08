package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Request;
import com.revature.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {

	final static Logger Log = Logger.getLogger(ConnectionUtil.class);
	
	@Override
	public boolean addExpenseRequest(int employeeId, String typeArg, String descArg, double amountArg) {
		//assign parameters to variables
		int empId = employeeId;
		String type = typeArg;
		String desc = descArg;
		double amount = amountArg;
		//convert decimals to only hold two decimal places
		DecimalFormat df2 = new DecimalFormat(".##");
		String amountDf2String = df2.format(amount);
		double amountD2 = Double.parseDouble(amountDf2String);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		String timestamp = dtf.format(localDate); //11/16/2016
		
		String sql = "INSERT INTO requests (employee_id, type, description, amount, status, date_submitted, date_resolved) "
				+ "VALUES (?,?,?,?, 'PENDING', ?, 'NOT YET RESOLVED')";
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,empId);
			ps.setString(2,type);
			ps.setString(3,desc);
			ps.setDouble(4,amountD2);
			ps.setString(5, timestamp);
			
			if(ps.executeUpdate() == 1) {
				return true;
			} else {
				throw new SQLException("SQL Exception: addExpenseRequest method failed!");
			}
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Request> getResolvedRequests(int employeeId) {
		//create array list to store results in
		List<Request> empResolvedRequests = new ArrayList<>();
		
		String sql = "SELECT request_id, type, description, amount, status, date_resolved FROM requests WHERE employee_id = ? AND status IN('APPROVED','DECLINED') ORDER BY request_id DESC";
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet results = ps.executeQuery();
			while(results.next()) {
				empResolvedRequests.add(new Request(results.getInt("request_id"), results.getString("type"), 
						results.getString("description"), results.getDouble("amount"), results.getString("status"),
						 results.getString("date_resolved")));
			}
			Log.info("RequestDaoImpl => getResolvedRequests(): Resolved Requests were selected!");
			return empResolvedRequests;
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getUnresolvedRequests(int employeeId) {
		//create array list to store results in
				List<Request> empUnresolvedRequests = new ArrayList<>();
				
				String sql = "SELECT * FROM requests WHERE employee_id = ? AND status = 'PENDING' ORDER BY request_id DESC";
				
				try (Connection conn = ConnectionUtil.getConnection()){
					
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, employeeId);
					ResultSet results = ps.executeQuery();
					while(results.next()) {
						empUnresolvedRequests.add(new Request(results.getInt("request_id"),results.getInt("employee_id"), results.getString("type"), 
								results.getString("description"), results.getDouble("amount"), results.getString("status"),
								results.getString("date_submitted"),results.getString("date_resolved"),results.getInt("resolved_by_id")));
					}
					System.out.println("RequestDaoImpl => getResolvedRequests(): Unresolved Requests were selected!");
					return empUnresolvedRequests;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return null;
	}


	



}
