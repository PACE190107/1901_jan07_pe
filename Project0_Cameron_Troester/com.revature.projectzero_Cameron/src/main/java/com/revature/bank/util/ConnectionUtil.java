package com.revature.bank.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

//import com.revature.ultis.JDBCConnectionUtil;

public class ConnectionUtil {

	private static Connection connection;
	final static Logger log = Logger.getLogger(ConnectionUtil.class);

	private ConnectionUtil() {
		super();
	}
	
	public static Connection getConnection() throws IOException, SQLException {
		String url = "jdbc:oracle:thin:@camerontoast.c0qjhytuba1p.us-east-2.rds.amazonaws.com"
				+ ":1521:ORCL";
		String username = "CTRevature";
		String password = "Epicfail#1";
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	
}