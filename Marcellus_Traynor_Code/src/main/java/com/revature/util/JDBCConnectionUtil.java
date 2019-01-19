package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImplementation;

public class JDBCConnectionUtil 
{
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	
	static
	{
		try {
			log.info("JDBC driver is loaded/registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		//Put in properties file later
		String url="jdbc:oracle:thin:@marcellustraynorrds.cnsh5n4kw9eb.us-east-2.rds.amazonaws.com:1521:ORCL";	
		String user = "MTRevature";
		String pass = "Jasmine6,Jovi4";
		
		return DriverManager.getConnection(url, user, pass);
	}
}