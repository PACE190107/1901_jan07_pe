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
	//JDBC - java database connectivity
	
	public static Connection getConnection() throws SQLException {

		//use own credentials - can only access Yuvi's DB in office
		
		//hardcoding credentials are not good practice - should come from file
		String url="jdbc:oracle:thin:@rds1901.cugpj0vbdsnx.us-east-1.rds.amazonaws.com:1521:ORCL";	
		String user = "engineer";
		String pass = "p4ssword";
		return DriverManager.getConnection(url, user, pass);
	}
}
