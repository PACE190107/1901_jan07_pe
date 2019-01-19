package com.revature.jdbcbank;

import java.util.Scanner;

public class JDBCBank 
{
	Scanner scan = new Scanner(System.in);
	
	//clear command prompt
	//new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor() 
	
	public static void main(String[] args) 
	{
		new JDBCBank().welcome();
	}
	
	//Sign in or Register
	private void welcome()
	{
		System.out.println("Welcome to the JDBCBanking Portal!");
		System.out.println("[1] Sign In");
		System.out.println("[2] Register");
		
		int choice = scan.nextInt();
		
		if(choice == 1)
		{
			login();
		}
		else
		{
			//Register()
		}
	}
		
	//Login
	private void login()
	{
		System.out.println("Username: \n");
		String userName = scan.nextLine();
		
		System.out.println("Password: \n");
		String passW = scan.nextLine();
		
		/*if(userName == (SELECT userName FROM users) && passW == (SELECT passW FROM users))
		 * {
		 * 		//clear console...
		 * 		accounts()
		 * }
		 * else
		 * {
		 * 		throw new Exception();
		 * 		System.out.println("Invalid login credentials.)
		 * 		System.out.println("[1] Try again, [2] Register")
		 * 
		 * 		if(scan.nextInt() == 1)
		 * 		{
		 * 			//clear console...
		 * 			login()
		 * 		}
		 * 		else
		 * 		{
		 * 			//clear console...
		 * 			register()
		 * 		}
		 * }*/
	}
		
	//Register
	private void register()
	{
		System.out.println("Create new username:");
		String userName = scan.nextLine();
		//INSERT INTO users (userName) values(userName)
		
		System.out.println("Create new password:");
		String passW = scan.nextLine();
		//INSERT INTO users (passW) values(passW)
		
		//login()
	}
	
	//Accounts/Balances
	private void accounts()
	{
		//SELECT accounts, balances FROM users
		System.out.println("[1] Create a new account");
		System.out.println("[2] Delete an empty account");
		System.out.println("[3] Withdraw/Deposit");
		//System.out.println("[4] Transaction history");
		System.out.println("[5] Logout");
		
		int choice = scan.nextInt();
		
		switch(choice)
		{
		case 1: //newAccount()
				break;
		case 2: //deleteAccount()
				break;
		case 3: //withdrawDeposit()
				break;
		case 5: //logout()
				break;
		}
	}
		
	//Create New Account
	private void newAccount()
	{
		
	}
		
	//Delete Empty Account
	private void deleteAccount()
	{
		
	}
		
	//Withdraw/Deposit
	private void withdrawDeposit()
	{
		
	}
		
	//Logout
	private void logout()
	{
		
	}
}

/*package com.revature.helloworld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloWorld {

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
		String url="jdbc:oracle:thin:@marcellustraynorrds.cnsh5n4kw9eb.us-east-2.rds.amazonaws.com:1521:ORCL";	
		String user = "MTRevature";
		String pass = "Jasmine6,Jovi4";
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
}*/