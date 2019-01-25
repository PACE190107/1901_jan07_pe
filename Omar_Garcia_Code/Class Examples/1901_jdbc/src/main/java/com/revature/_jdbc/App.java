package com.revature._jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {
		try {
			connectToDatabaseUsingJDBC();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//how to connect to a database using jdbc?
	//1. load the driver
	//2. establish the connection
	//3. run the query
	//4. view the results
	//5. close connection
	
	static void connectToDatabaseUsingJDBC() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.OracleDriver");
		//AWS end point
		String url="jdbc:oracle:thin:@garcia-1901rds.csnuq8ktjehc.us-east-2.rds.amazonaws.com:1521:RDS1901";	
		String user = "Omar_Garcia";
		String pass = "";
		//how do you establish connection using jdbc? 
		Connection conn = DriverManager.getConnection(url, user, pass);
		//how to run the query?
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from employee_1901");
		//how to display the results
		while(rs.next()) {
			System.out.println(
					rs.getString(1) + " " +
					rs.getString(2) + " " +
					rs.getString(3) + " \n" 
					);
		}
		//how to close the connection?
		rs.close();
		stmt.close();
		conn.close();
	}

	
}