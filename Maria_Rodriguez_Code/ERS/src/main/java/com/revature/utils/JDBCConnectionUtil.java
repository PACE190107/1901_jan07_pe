package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;




public class JDBCConnectionUtil {

	final static Logger log = Logger.getLogger(JDBCConnectionUtil.class);
	//JDBC - java database connectivity
	static {
		try {
			log.info("JDBC driver is loaded / registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		//use your credentials -why?
		//you have access to my database as long as you are in this building
		
		//hard coding credentials are not good practice 
		//String url="jdbc:oracle:thin:@rds1901.chueiwozbnfz.us-east-1.rds.amazonaws.com:1521:ORCL";
		
		
		//String url="jdbc:oracle:thin:project0database.cvglk9owqsfj.us-east-1.rds.amazonaws.com:1521:ORCL";
		String url="jdbc:oracle:thin:@//expensereinbursementdb.cvglk9owqsfj.us-east-1.rds.amazonaws.com:1521/ORCL";
		//String url="project0database.cvglk9owqsfj.us-east-1.rds.amazonaws.com:1521:ORCL";

		//jdbc:oracle:thin:@//oracle.hostserver2.mydomain.ca:1522/ABCD
		
		String username = "mrodriguez"; 
		String password = "abundanceserenitynow";
		return DriverManager.getConnection(url,username,password);
	}
}
