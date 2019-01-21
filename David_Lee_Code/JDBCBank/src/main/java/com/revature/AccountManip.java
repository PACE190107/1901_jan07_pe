package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;

public class AccountManip {
	//Deposit method
	public static void deposit(Scanner in, User user, List<Account> accList) {
		if (accList.isEmpty()) {
			System.out.println("You have no active accounts with us. Please create an account first!");
			Menus.accountMenu(in, user);
		}
		Driver.displayAccounts(accList);
		System.out.println("Which account would you like to make a deposit on? Enter ID: ");
		int accountID = checkaccountID(in, accList);
		System.out.println("Enter the amount you would like to deposit (Minimum $1):");
		double amount = checkAmountBalance(in, accountID);
		while (amount < 1) {
			System.out.println("You must add at least $1.00. ");
			System.out.println("Enter the amount you would like to deposit (Minimum $1):");
			amount = checkAmountBalance(in, accountID);
		}
		System.out.println("Adding funds...");
		UserService.getUserService().transferAmount(amount,accountID);
		System.out.println("Added $" +amount +" to account " +accountID+"!");
		Menus.accountMenu(in,user);
	}
	
	//Withdraw method
	public static void withdraw(Scanner in, User user, List<Account> accList)  {
		if (accList.isEmpty()) {
			System.out.println("You have no active accounts with us. Please create an account first!");
			Menus.accountMenu(in, user);
		}
		Driver.displayAccounts(accList);
		System.out.println("Which account would you like to withdraw from? Enter ID: ");
		int accountID = checkaccountID(in,accList);
		double bankBalance = 0;
		for (Account x: accList) {
			if (x.getAccountID() == accountID) {
				bankBalance = x.getAccountBalance();
			}
		}
		if (bankBalance == 0) {
			System.out.println("Sorry, you have no funds to withdraw.");
			Menus.accountMenu(in, user);
		}
		System.out.println("You have $" +bankBalance +" in this account.");
		System.out.println("Enter the amount you would like to withdraw:");
		double amount = checkAmountBalance(in,accountID);
		while (amount < 0) {
			System.out.println("You cannot enter a negative amount.");
			System.out.println("Enter the amount you would like to withdraw:");
			amount = checkAmountBalance(in,accountID);
		}
		while (amount > bankBalance) {
			try {
				if (amount > bankBalance) {
					throw new OverdraftException("User withdraws more than allowed.");
				}
			} catch (OverdraftException e) {
				System.out.println("It looks like you are trying to withdraw more than you have!");
				System.out.println("Please enter a valid amount to withdraw: ");
				amount = checkAmountBalance(in,accountID);
			}
		}
		System.out.println("Withdrawing funds...");
		UserService.getUserService().transferAmount(-amount,accountID);
		System.out.println("Withdrew $" +amount +" from Account " +accountID +"!");
		Menus.accountMenu(in, user);
	}
	
	//Checks account ID
	public static int checkaccountID(Scanner in, List<Account> accList) {
		List<Integer> accountIDs = new ArrayList<Integer>();
 		for (Account x : accList) {
			accountIDs.add(x.getAccountID());
		}
 		String acc_ID = in.nextLine();
 		while (!acc_ID.matches("-?(0|[1-9]\\d*)")) {
 			System.out.println("That is not a valid input! Please enter a valid account ID: ");
 			return checkaccountID(in,accList);
 		}
 		int account = Integer.parseInt(acc_ID);
 		while (!accountIDs.contains(account)) {
 			System.out.println("Sorry, that account ID does not exist. Please enter a valid account ID:");
 			return checkaccountID(in,accList);
 		}
 		return account;
	}
	
	//Checks account Balance
	public static double checkAmountBalance(Scanner in, int acc_ID) {
		double amount = 0;
		String input = in.nextLine();
			try {
				amount = Double.parseDouble(input);
			} catch(Exception e) {
				System.out.println("Invalid input. Please enter a valid amount.");
				return checkAmountBalance(in,acc_ID);
			}
		return Math.round(amount * 100.0) / 100.0;
	}

	//Creates a new user
	public static void createUser(Scanner in) {
		System.out.println("Welcome to the team! We are happy you decided to join us!");
		System.out.println("Let us get to know you a little bit.");
		User newUser = new User();
		String firstName = Driver.validateStrings(in,"What is your first name? TYPE 'EXIT' to return to the main menu ",false,false);
		newUser.setFirstName(firstName);
		String lastName = Driver.validateStrings(in,"What is your last name? TYPE 'EXIT' to return to the main menu ",false,false);
		newUser.setLastName(lastName);
		System.out.println("Welcome " +firstName +" " +lastName +"!");
		System.out.println("Lets create an account for you.");
		
		String userName = Driver.validateStrings(in,"Please enter a username. TYPE 'EXIT' to return to the main menu ",false,true);
		newUser.setUserName(userName);
		System.out.println("Checking username...");
		while (UserService.getUserService().findID(newUser)) {
			System.out.println("Sorry this username already exists. TYPE 'EXIT' to return to the main menu");
			newUser.setUserName(Driver.validateStrings(in,"Enter another username. TYPE 'EXIT' to return to the main menu ",false,true));
			System.out.println("Checking username...");
		}
		System.out.println("Username can be used!");
		String password = Driver.validateStrings(in,"Please enter a password. TYPE 'EXIT' to return to the main menu ",true,true);
		newUser.setPassword(password);
		UserService.getUserService().createUser(newUser);
		System.out.println("Your account is created! Please login.");
		Menus.startMenu(in);
	}

	//Creates a new account
	public static void createAccount(Scanner in, User user) {
		Account newAcct = new Account(null, user.getUser_id(), 0, 0);
		System.out.println("What kind of account would you like to make? ('EXIT' to go back)");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		switch(in.nextLine().toLowerCase()) {
		case "1.":
		case "1":
			newAcct.setAccountType("Checking");
			break;
		case "2.":
		case "2":
			newAcct.setAccountType("Savings");
			break;
		case "exit":
			Menus.accountMenu(in,user);
		default:
			System.out.println("Please enter a valid number.");
			createAccount(in,user);
		}
		System.out.println("Creating account...");
		UserService.getUserService().createAccount(user, newAcct);
		System.out.println("Account created!");
		Menus.accountMenu(in,user);
	}
	//Deletes a empty bank account for the user
	
	//deletes an account
	public static void deleteAccount(Scanner in, User user, List<Account> accList) {
		List<Integer> acc_Ids = new ArrayList<Integer>();
		System.out.println("************************************");
		for (int i = 0; i < accList.size(); i++) {
			if (accList.get(i).getAccountBalance() == 0.00) {
				acc_Ids.add(accList.get(i).getAccountID());
				System.out.println("Account ID: " +accList.get(i).getAccountID());
				System.out.println("Account Type: "+accList.get(i).getAccountType());
				System.out.println("************************************");
			}
		}
		if (acc_Ids.isEmpty()) {
			System.out.println("You have no empty accounts. Please withdraw all funds before removing an account.");
			Menus.accountMenu(in,user);
		}
		System.out.println("These accounts are empty. Specify the account ID you like to delete: 'EXIT' to go back");	
		try {
			String input = in.nextLine();
			if (input.toLowerCase().equals("exit")) {
				Menus.accountMenu(in,user);
			}
			int acc_ID = Integer.parseInt(input);
			if (!acc_Ids.contains(acc_ID)) {
				throw new Exception();	
			}
			else {
				System.out.println("Deleting....");
				UserService.getUserService().deleteAccount(new Account(acc_ID));
				System.out.println("Done! Returning to Account Menu.");
				Menus.accountMenu(in,user);
			}	
		}catch(Exception e) {
			System.out.println("Invalid input. Please enter a valid account ID or type EXIT.");
			deleteAccount(in,user,accList);
		}	
	}
	
	
	//SUPER USER--------------------------------------------------------------------------------
	//Adds a user
	public static void addUser(Scanner in, User user) {
		User newUser = new User();
		String firstName = Driver.validateStrings(in,"User first name: ",false,false);
		newUser.setFirstName(firstName);
		String lastName = Driver.validateStrings(in,"User last name: ",false,false);
		newUser.setLastName(lastName);
		String userName = Driver.validateStrings(in,"Username : ",false,true);
		newUser.setUserName(userName);
		while (UserService.getUserService().findID(newUser)) {
			System.out.println("Sorry this username already exists.");
			newUser.setUserName(Driver.validateStrings(in,"Enter another username: ",false,true));
		}
		String password = Driver.validateStrings(in,"Please enter a password: ",true,true);
		newUser.setPassword(password);
		UserService.getUserService().createUser(newUser);
		System.out.println("User Created.");
		Menus.superUserMenu(in, user);
	}
	//delete a user
	public static void deleteUser(Scanner in, User user, boolean enter) {
		if (enter) {
			getAllUser(in,user);
		}
		List<User> all_user = UserService.getUserService().getAllUsers();
		List<Integer> all_ids = new ArrayList<Integer>();
		List<String> all_usernames = new ArrayList<String>();
		User deleteUser = new User();
		for (User x: all_user) {
			all_ids.add((x.getUser_id()));
			all_usernames.add(x.getUserName());
		}
		System.out.println("Please choose an account ID to delete: 'EXIT' to return to the menu ");
		String user_in = in.nextLine();
		if (user_in.toLowerCase().equals("exit")) {
			Menus.superUserMenu(in, user);
		}
 		while (!user_in.matches("-?(0|[1-9]\\d*)")) {
 			System.out.println("That is not a valid input! Please enter a valid user ID: ");
 			deleteUser(in,user,enter = false);
 			if (user_in.toLowerCase().equals("exit")) {
 				Menus.superUserMenu(in, user);
 			}
 		}
 		int input = Integer.parseInt(user_in);
 		while (!all_ids.contains(input)) {
 			System.out.println("Sorry, that User ID does not exist. Please enter a valid account ID: 'EXIT' to return to menu ");
 			deleteUser(in,user, enter = false);
 			if (user_in.toLowerCase().equals("exit")) {
 				Menus.superUserMenu(in, user);
 			}
 		}
 		deleteUser.setUser_id(input);
 		System.out.println("Deleting users is irreversible. You sure you want to delete this user? YES | NO");
 		switch(in.nextLine().toLowerCase()) {
 			case "yes":
 				System.out.println("Deleting user....");
 				UserService.getUserService().deleteUser(deleteUser);
 				System.out.println("User deleted!");
 				Menus.superUserMenu(in, user);
 				break;
 			case "no":
 				Menus.superUserMenu(in,user);
 				break;
 			default:
 				System.out.println("Invalid input. Resetting...");
 				deleteUser(in,user,enter = false);
 					
 		}
	}
	//get all users
	public static void getAllUser(Scanner in, User user) {
		List<User> all_users = UserService.getUserService().getAllUsers();
		System.out.println("-------------------------------------------");
		for (User x : all_users) {
			System.out.println("User ID: " +x.getUser_id());
			System.out.println("User first name: " +x.getFirstName());
			System.out.println("User last name: " +x.getLastName());
			System.out.println("User username: " +x.getUserName());
			System.out.println("-------------------------------------------");
		}
	}
	//Modify user data
	public static void modifyUser(Scanner in, User user) {
		List<User> users = UserService.getUserService().getAllUsers();
		List<Integer> user_id = new ArrayList<Integer>();
		for (User u : users ) {
			user_id.add(u.getUser_id());
			System.out.println("User ID: " +u.getUser_id());
			System.out.println("User First Name: " +u.getFirstName());
			System.out.println("User Last Name: " +u.getLastName());
			System.out.println("User name: " +u.getUserName());
			System.out.println("----------------------------------------");
		}
		System.out.println("Please enter a User ID you wish to modify: 'EXIT' to exit ");
		String input = in.nextLine();
		if (input.toLowerCase().equals("exit")) {
			Menus.superUserMenu(in, user);
		}
		while (!input.matches("-?(0|[1-9]\\d*)")) {
 			System.out.println("That is not a valid input! Please enter a valid account ID: ");
 			modifyUser(in,user);
 			if (input.toLowerCase() == "exit") {
 				Menus.superUserMenu(in, user);
 			}
 		}
		int id = Integer.parseInt(input);
		while(!user_id.contains(id)) {
			System.out.println("The specified user ID does not exist.");
			modifyUser(in,user);
		}
		User changeUser = new User();
		changeUser.setUser_id(id);
		System.out.println("What parameter would you like to modify?");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. User Name");
		
		switch(in.nextLine()) {
			case "1":
			case "1.":
				String newFirst = Driver.validateStrings(in, "What is the new first name?", false, false);
				changeUser.setFirstName(newFirst);
				System.out.println("Modifying first name...");
				boolean t = UserService.getUserService().modifyUser(changeUser,1);
				if (t) {
					System.out.println("Success!");
				}
				else {
					System.out.println("Error! Cannot modify first name!");
				}
				Menus.superUserMenu(in, user);
				break;
			case "2":
			case "2.":
				System.out.println("Modifying last name...");
				String newLast = Driver.validateStrings(in, "What is the new last name?", false, false);
				changeUser.setLastName(newLast);
				boolean d = UserService.getUserService().modifyUser(changeUser,2);
				if (d) {
					System.out.println("Success!");
				}
				else {
					System.out.println("Error! Cannot modify last name!");
				}
				Menus.superUserMenu(in, user);
				break;
			case "3":
			case "3.":
				String newUser = Driver.validateStrings(in, "What is the new username name?", false, true);
				changeUser.setUserName(newUser);
				System.out.println("Modifying user name...");
				boolean b= UserService.getUserService().modifyUser(changeUser,3);
				if (b) {
					System.out.println("Success!");
				}
				else {
					System.out.println("Error! Cannot modify username! Username already exists!");
				}
				Menus.superUserMenu(in, user);
				break;
			default: 
				System.out.println("Invalid command. Returning to menu.");
				Menus.superUserMenu(in, user);
		}
	}
}
