package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;

public class Menus {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 57806945867040742L;
	
	//Super user UI
	public static void superUserMenu(Scanner in, User user) {
		System.out.println("Welcome admin! Please make an inquiry");
		System.out.println("1. See all users.");
		System.out.println("2. Add a user");
		System.out.println("3. Modify a user");
		System.out.println("4. Remove a user.");
		System.out.println("5. Logout");
		switch(in.nextLine()) {
		case "1":
		case "1.":
			AccountManip.getAllUser(in,user);
			superUserMenu(in,user);
			break;
		case "2":
		case "2.":
			AccountManip.addUser(in, user);
		break;
		case "3":
		case "3.":
			AccountManip.modifyUser(in, user);
			break;
		case "4":
		case "4.":
			AccountManip.deleteUser(in, user,true);
		case "5":
		case "5.":
			System.out.println("Logout? YES | NO");
			switch (in.nextLine().toLowerCase()) {
				case "yes":
					user = null;
					System.out.println("Logging out...");
					startMenu(in);
				case "no":
					superUserMenu(in,user);
				default:
					System.out.println("Command not recognized, returning to menu.");
					superUserMenu(in,user);
			}
		break;
		default:
			System.out.println("Command not recognized. Please try again.");
			superUserMenu(in,user);
		}
	}
	//Start menu where users can create accounts and login.
	public static void startMenu(Scanner in) {
		String input = "";
		System.out.println("---------------MAIN MENU---------------------");
		System.out.println("What would you like to do?");
		System.out.println("Type \"Join\" to create an account.");
		System.out.println("Type \"Login\" to login to your account.");
		System.out.println("Type \"Admin\" to login (for admin only).");
		System.out.println("Type \"Exit\" to exit the program.");
		
		input = in.nextLine();	
		switch (input.toLowerCase()) {
		case "join":
			AccountManip.createUser(in);
			break;
		case "login": //Login
			Driver.login(in);
			break;
		case "admin":
			Driver.adminLogin(in);
			break;
		case "exit": //Log out
			System.out.println("Exit? YES | NO");
			switch(in.nextLine().toLowerCase()) {
				case "yes":
					System.out.println("Thank you! Please come again!");
					System.exit(0);
					break;
				case "no":
					startMenu(in);
					break;
				default:
					System.out.println("Invalid command: Going to start menu.");
					startMenu(in);
			}
			break;
		default:
			System.out.println("Invalid command. Please enter a valid command.");
			startMenu(in);
			break;
		}
	}
	//Account menu where users can see their accounts
	public static void accountMenu(Scanner in, User user) {
		user.setUser_id(UserService.getUserService().getID(user));
		System.out.println("----------WELCOME " +user.getUserName()+"!--------------");
		System.out.println("Your unique ID: " +user.getUser_id());
		List<Account> accList = new ArrayList<Account>();
		accList = UserService.getUserService().getAllAccounts(user);
		System.out.println("You have "+accList.size()+" account(s) with us.");
		System.out.println("-----------------------------------------------------------------------");
		//Format Table
		Driver.displayAccounts(accList);
		queryMenu(in,user, accList);
	}
	//Transfer money menu
	public static void transferMenu(Scanner in, User user, List<Account> accList) {
		if (accList.isEmpty()) {
			System.out.println("Looks like you have no accounts with us. Please make a account first!");
			queryMenu(in,user,accList);
		}
		System.out.println("What would you like to do?");
		System.out.println("1. Withdraw from an account");
		System.out.println("2. Deposit to an account");
		switch(in.nextLine()) {
		case "1":
		case "1.":
			AccountManip.withdraw(in,user,accList);
			break;
		case "2":
		case "2.":
			AccountManip.deposit(in,user,accList);
			break;
		default:
			System.out.println("Invalid input. Please enter a valid command.");
			transferMenu(in,user,accList);
		}
	}
	//Query menu
	public static void queryMenu(Scanner in, User user, List<Account> accList) {
		System.out.println("What would you like to do?");
		System.out.println("1. Deposit or withdraw funds");
		System.out.println("2. Create an Account");
		System.out.println("3. Delete an Account");
		System.out.println("4. Logout");
		switch(in.nextLine()) {
		case "1":
		case "1.":
			transferMenu(in,user,accList);
			break;
		case "2":
		case "2.":
			AccountManip.createAccount(in,user);
			break;
		case "3":
		case "3.":
			AccountManip.deleteAccount(in,user, accList);
			break;
		case "4":
		case "4.":
			System.out.println("Logout? YES | NO");
			switch(in.nextLine().toLowerCase()) {
			case "yes":
				user = new User();
				accList = null;
				System.out.println("You have successfully logged out!");
				startMenu(in);
				break;
			case "no":
				queryMenu(in,user,accList);
			default:
				queryMenu(in,user,accList);
			}
			break;
		default:
			System.out.println("Invalid command. Please try again.");
			queryMenu(in,user,accList);
			
		}
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
