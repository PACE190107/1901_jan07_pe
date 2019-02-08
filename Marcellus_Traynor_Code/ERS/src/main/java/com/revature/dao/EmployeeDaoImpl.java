package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao
{
	final static Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	
	@Override
	public String employeeLogin(String username, String password) 
	{
		String job;
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "Select job_description from Employee_Info "
					+ "where user_name = ? AND user_password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			job = rs.getString("job_description");
			conn.close();
			return job;
		} 
		catch (SQLException e1) 
		{
			log.error("error occured in catch block in EmployeeDaoImpl employeeLogin method");
			log.error(e1.getMessage());
			log.error(e1.getStackTrace());
			return null;
		}
		
	}
	
	@Override
	public List<Employee> getEmployeeInfo(String username) 
	{
		log.info("Retrieving single employe info");
		List<Employee> employeeInfo = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "SELECT first_name, last_name, job_description, user_name FROM Employee_Info "
					+ "WHERE user_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				employeeInfo.add(new Employee(rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("job_description"), rs.getString("user_name")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in catch block in EmployeeDaoImpl getEmployeeInfo method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return employeeInfo;
	}

	@Override
	public Employee updateEmployee(String username, String newFname, String newLname, String newPassword)
	{
		Employee employeeInfo = null;
		
		log.info("updating employee info");
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "UPDATE Employee_Info SET first_name = ?, last_name = ?, user_password = ? "
					+ "WHERE user_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newFname);
			ps.setString(2, newLname);
			ps.setString(3, newPassword);
			ps.setString(4, username);
			ps.executeUpdate();
			
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in catch block in EmployeeDaoImpl updateEmployee method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return employeeInfo;
	}

	@Override
	public List<Employee> getAllEmployeeInfo() 
	{
		List<Employee> allEmployeeInfo = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			//String sql = "SELECT first_name, last_name, job_description, user_name FROM Employee_Info";
			String sql = "SELECT * FROM Employee_Info";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				allEmployeeInfo.add(new Employee(rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("job_description"), rs.getString("user_name"), rs.getString("user_password")));
			}
			conn.close();
		} 
		catch (SQLException e) 
		{
			log.error("error occured in catch block in EmployeeDaoImpl getAllEmployeeInfo method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		System.out.println(allEmployeeInfo.toString());
		return allEmployeeInfo;
	}
}