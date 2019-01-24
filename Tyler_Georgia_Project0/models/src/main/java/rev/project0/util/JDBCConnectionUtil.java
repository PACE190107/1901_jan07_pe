package rev.project0.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class JDBCConnectionUtil {
	
//	final static Logger log = Logger.getLogger(JDBCConnectionUtil.class);
	static {
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
//			log.info("JDBC driver is loaded / registered");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		
		//hard coding credentials are not good practice 
		String url="jdbc:oracle:thin:@mycloudrds.cdqezmhyyq5y.us-east-1.rds.amazonaws.com:1521:MYREVRDS";
		String username = "tgeorgia"; 
		String password = "Alex97135862";

		return DriverManager.getConnection(url,username,password);
	}
	
}
