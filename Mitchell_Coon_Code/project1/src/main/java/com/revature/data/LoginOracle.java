package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.beans.Login;
import com.revature.exceptions.FailedLoginException;
import com.revature.util.ConnectionUtil;

public class LoginOracle implements LoginDao {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public Login login(String username, String password) {
		Login login = null;
		Connection conn = null;
		conn = cu.getConnection();

		String sql = "select employee_id, firstname, lastname, username, pass, isManager from Employees where Employees.username = '"+ username +"' and Employees.pass = get_User_Hash('"+ username +"', '"+ password +"')";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				boolean isManager = rs.getInt("isManager") == 1 ? true:false;
				login = new Login(rs.getInt("employee_id"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("username"),
						rs.getString("pass"),
						isManager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}
}
