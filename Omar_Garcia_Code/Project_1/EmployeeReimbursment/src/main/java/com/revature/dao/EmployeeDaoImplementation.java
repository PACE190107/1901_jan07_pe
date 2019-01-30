package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.module.Employee;
import com.revature.util.JDBCConnectionUtil;

public class EmployeeDaoImplementation implements EmployeeDao {
	private static EmployeeDaoImplementation employeeImp;
	
	private EmployeeDaoImplementation() {
		
	}
	
	public static EmployeeDaoImplementation getEmployeeDao() {
		if(employeeImp == null) {
			employeeImp = new EmployeeDaoImplementation();
		}
		return employeeImp;
	}
	
	public String viewEmployee() throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			Statement stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(sql);
			String json = "[\n";
			while (results.next()) {
				json += "\n{\n\"E_ID\" : " + results.getInt(1)  +
						",\n\"E_FIRST\": \"" + results.getString(2) +"\"" +
						",\n\"E_LAST\": \"" + results.getString(3) +"\"" +
						",\n\"E_USERNAME\": \"" + results.getString(4) +"\"" +
						",\n\"E_PASSWORD\": \"" + results.getString(5) +"\"" +
						",\n\"E_MANAGER\": \"" + results.getInt(6) +"\"" +
						",\n\"E_EMAIL\": \"" + results.getString(7) +"\"},";
			}
			json =json.substring(0, json.length() - 1) + "\n]";
			System.out.print(json);
			return json;
		}
	}

	public Employee login(String username, String password) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE E_PASSWORD LIKE GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				System.out.println("found");
				return new Employee(result.getInt("E_ID"), result.getString("E_FIRST"), result.getString("E_LAST"),
						result.getString("E_USERNAME"), result.getString("E_PASSWORD"), result.getInt("E_MANAGER"), result.getString("EMAIL"));
			}
		}
		System.out.println("not found\n" + username + "\n" + password);
		return null;
	}

}
