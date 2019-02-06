package com.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ers.dao.EmployeeDAO;
import com.ers.dao.ReimbursementRequestDAO;

public class ConnectionManager {
	private static final Logger log = Logger.getLogger(ConnectionManager.class);
	private static Connection jdbcCon;

	public static Connection getJDBCConnection() {
		return jdbcCon;
	}
	public static void setJDBCConnection(String username, String password) {
		try {
			if (jdbcCon != null && !jdbcCon.isClosed())
				jdbcCon.close();
			jdbcCon = DriverManager.getConnection(
				"jdbc:oracle:thin:@revature-project1.cyitp3sizf7l.us-east-2.rds.amazonaws.com:1521:P01",
				username, password);
			EmployeeDAO.getDAO().resetStmnts();
			ReimbursementRequestDAO.getDAO().resetStmnts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeJDBCConnection() {
		try {
			if (jdbcCon != null && !jdbcCon.isClosed())
				jdbcCon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			setJDBCConnection("Admin01", "s9d5j1q8");
		} catch (ClassNotFoundException e) {
			log.error(e.getStackTrace());
		}
	}
	
	private ConnectionManager() { }
}
