package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.UserDaoImp;
import com.revature.models.Accounts;
import com.revature.models.User;
import com.revature.services.AccountsService;
import com.revature.services.UserService;

public class Driver {
	static Scanner sc = new Scanner(System.in);
	private static UserService userService = UserService.getUserService();
	
	public static void main(String[] args) {
		boolean goOn = true;
		while(goOn) {
			System.out.println("Select an option:");
			System.out.println("1: Registered User Login");
			System.out.println("2: Create New User");
			System.out.println("3: Exit");
			String userInput = sc.nextLine();
			
			switch(userInput) {
				case "1":
					System.out.println("Login");
					break;
				case "2":
					System.out.println("Create New User");
					createUser();
					break;
				case "3":
					System.out.println("Exiting. GoodBye");
					goOn = false;
					break;
					
				default: System.out.println("Not a Valid Selection");
			
			}
		
		}
		//add account
		//System.out.println(AccountsService.getAccountsService().registerAccountsProcedure(new Accounts(121)));
		
//		view accounts
//		List<Accounts> allAccounts = AccountsService.getAccountsService().getAllAccountsDetails();
//		for (Accounts accounts : allAccounts) {
//			System.out.println(accounts);
//		}
		
		//make deposit or withdrawal
		//System.out.println(AccountsService.getAccountsService().makeDepositProcedure(new Accounts(23, 100, 0)));
		
//		delete account
//		System.out.println(AccountsService.getAccountsService().removeAccountsProcedure(new Accounts(23)));
		
//		delete user
//		System.out.println(UserService.getUserService().removeUserProcedure(new User(119)));
		
//		view users
//		List<User> allUsers = UserService.getUserService().getAllUserDetails();
//		for (User user : allUsers) {
//			System.out.println(user);
//		}
	
//		create user
//		System.out.println(UserService.getUserService().registerUserProcedure(new User("Steven", "Stevens", "SSTEVENS", "stevenstevens")));
	}
	
	private static void createUser() {
		boolean goOn = true;
		while (goOn) {
		System.out.println("Enter username");
		String username = sc.nextLine();
		System.out.println("Enter password");
		
		
		String password = sc.nextLine();
		User newUser = new User("","",username, password, 0);
		userService.registerUser(newUser);
		goOn = false;
		System.out.println("Enter First Name");
		String firstName = sc.nextLine();
		System.out.println("Enter Last Name");
		String lastName = sc.nextLine();
				
		}
		
		
		
	}
}
