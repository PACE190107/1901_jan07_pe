package revature.timjordan.projectZero.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JDBCConnectionUtil {
	
	final static Logger log = Logger.getLogger(JDBCConnectionUtil.class);
	
	static {
		try {
			//log.info("");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static Connection getConnection() throws SQLException {
		
		String urlFileInput = "";
		String userNameFileInput
 = "";
		String passwordFileInput = "";
		String url = "", username = "", password = "";
		
		
			try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\JDBCLoginInfo.properties"));) {
			
			urlFileInput = bf.readLine();
			//System.out.println(urlFileInput);
			userNameFileInput = bf.readLine();
			//System.out.println(userNameFileInput);
			
			passwordFileInput = bf.readLine();
			//System.out.println(passwordFileInput);
			 url = urlFileInput;
			 username = userNameFileInput;
			 password = passwordFileInput;
			
			
			
		} catch(IOException e) {
			System.out.println(" File not found.");
		} 
		
			//System.out.println(url);
			return DriverManager.getConnection(url,username,password);
	}

}
