package com.revature.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.data.DataSource;
import com.revature.models.Employee;
import com.revature.util.ERSConnectionUtil;

public class LoginServiceImpl implements LoginService {
	
	private final DataSource dataSource = DataSource.getInstance();
	
	@Override
	public Object attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		for (Employee employee : dataSource.getEmployeeTable())
			if (employee.getUsername().equals(username) && getHashedPassword(employee.getUsername(), employee.getPassword()).equals(password))
				return employee;
		return null;
	}	
	
	private String getHashedPassword(String username, String password) {
		try (Connection conn = ERSConnectionUtil.getConnection()){
			String sql = "call RETURN_BANK_CUST_HASH(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			String hashedpwd = cs.getString(3);
			return hashedpwd;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}