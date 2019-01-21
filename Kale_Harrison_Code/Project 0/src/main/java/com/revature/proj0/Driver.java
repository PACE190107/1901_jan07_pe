package com.revature.proj0;

import java.sql.SQLException;
import java.util.Scanner;
import com.revature.functions.JDBCFunctionsImplementation;
import com.revature.user.AuthenticatedUser;

public class Driver {

	
	public static void main(String[] args) throws SQLException {	
		AuthenticatedUser user = new AuthenticatedUser();
		JDBCFunctionsImplementation tester = new JDBCFunctionsImplementation();
		Scanner sc = new Scanner(System.in);
		String username = "";
		String password = "";
		boolean loopBreaker = false;
		while(loopBreaker == false){
			tester.firstChoice();
			System.out.println("Please log in...");
			System.out.print("Enter Username: ");
			username = sc.next();
			System.out.print("Enter Password: ");
			password = sc.next();
			user.setUsername(username);
			user.setPassword(password);
			try {
				if (tester.logIn(username, password)) {
					if(tester.testSuper(user))
						tester.superMenu(user);
					else{
						try {
							tester.viewMenu(user);
							}catch (SQLException e) {
								e.printStackTrace();
							}
						}
				}
			}catch(SQLException e){
				System.out.println("catch for the logIn call");
				return;
			}
		}	
	}	
}
		
	
	

	
	
	
	

