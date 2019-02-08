package com.revature.tjordan.util;

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
		
		
//			try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\JDBCLoginInfo.properties"));) {
//			
//			urlFileInput = bf.readLine();
//			//System.out.println(urlFileInput);
//			userNameFileInput = bf.readLine();
//			//System.out.println(userNameFileInput);
//			
//			passwordFileInput = bf.readLine();
//			//System.out.println(passwordFileInput);
//			 url = urlFileInput;
//			 username = userNameFileInput;
//			 password = passwordFileInput;
//			
//			
//			
//		} catch(IOException e) {
//			System.out.println(" File not found.");
//		} 
		
		
		url = "jdbc:oracle:thin:@revatureers.ckqd9e80yjau.us-east-2.rds.amazonaws.com:1521:ORCL";
		username = "admin";
		password = "revature1901";
		
			//System.out.println(url);
			return DriverManager.getConnection(url,username,password);
	}
	
	
	
	
	
}
