package com.revature;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.exceptions.incorrectPasswordException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;

public class Driver extends Throwable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1160321606827018044L;

	public static void login(Scanner in) {
		User existUser = new User();
		String username = validateStrings(in,"Enter your username: TYPE 'EXIT' to return to the main menu.",false,true);
		existUser.setUserName(username);
		System.out.println("Checking username...");
		while (!UserService.getUserService().findID(existUser)) {
			System.out.println("The username is not found.");
			existUser.setUserName(validateStrings(in,"Enter another username: TYPE 'EXIT' to return to the main menu",false,true));
			System.out.println("Checking username...");
		}
		System.out.println("Username found!");
		String password = validateStrings(in,"Enter your password: TYPE 'EXIT' to return to the main menu",true,true);
		existUser.setPassword(password);
		while (!UserService.getUserService().confirmPass(existUser)) {
			try {
				System.out.println("Checking password...");
				if (!UserService.getUserService().confirmPass(existUser)) {
					throw new incorrectPasswordException("User entered incorrect password. -User");
				}
			} catch (incorrectPasswordException e) {
				System.out.println("The password is incorrect.");
				existUser.setPassword(validateStrings(in,"Enter your password: TYPE 'EXIT' to return to the main Menu. ",true,true));
			}
		}
		System.out.println("You are now logged in!");
		Menus.accountMenu(in, existUser);
	}
	//Takes care of strings
	public static String validateStrings(Scanner in, String question, boolean spec, boolean num) {
		//Check for special characters
		System.out.println(question);
		String input = in.nextLine();
		if (input.toLowerCase().equals("exit")) {
			Menus.startMenu(in);
		}
		Pattern check_special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher match = check_special.matcher(input);
		boolean isSpecial = match.find();
		if (input.contains(" ")) {
			System.out.println("Sorry, we don't allow spaces.");
			return validateStrings(in,question,spec,num);
		}
		if(isSpecial && !spec) {
			System.out.println("Sorry, we don't allow any special characters here.");
			return validateStrings(in,question,spec,num);
		}
		else if (input.matches(".*\\d+.*") && !num) {
			System.out.println("Sorry, we don't allow any numbers here.");
			return validateStrings(in,question,spec,num);
		}
			return input;
//		System.out.println("Is this correct? YES | NO: " +input);
//		switch (in.nextLine().toLowerCase()) {
//			case "yes":
//				return input;
//			case "no":
//				return validateStrings(in,question,spec,num);
//			default:
//				System.out.println("Invalid input, please re-enter your name.");
//				return validateStrings(in,question,spec,num);		
//			}
		
	}
	
	//Display all accounts
	public static void displayAccounts(List<Account> accList) {
		for (Account x: accList) {
		System.out.println("| Account ID: " +x.getAccountID() +" | Account Type: "
		+x.getAccountType()+" | Account Balance: $"+x.getAccountBalance());
		System.out.println("-----------------------------------------------------------------------");
		}
	}
	
	//Display all users
	public static void displayUsers(List<User> userList) {
	}

	//Administrator login
	public static void adminLogin(Scanner in) {
		User admin = UserService.getUserService().getSuperUser();
		System.out.println("Enter admin username: 'EXIT' to go back");
		String username = in.nextLine();
		if (username.toLowerCase().equals("exit")) {
			Menus.startMenu(in);
		}
		System.out.println("Enter admin password: 'EXIT' to go back");
		String password = in.nextLine();
		if (password.toLowerCase().equals("exit")) {
			Menus.startMenu(in);
		}
		
		try {
			if (admin.getUserName().equals(username) && admin.getPassword().equals(password)) {
				System.out.println("Logging in....");
				Menus.superUserMenu(in,admin);
			}
			else {
				throw new incorrectPasswordException("User entered incorrect password. -Admin menu");
			}
		} catch (incorrectPasswordException e) {
			System.out.println("Sorry, your credentials are incorrect.");
			Menus.startMenu(in);
		}
	}
	
	//Main method
	public static void main(String[] args) {
		System.out.println("Good Morning!");
		Scanner in = new Scanner(System.in);
		Menus.startMenu(in);	
	}
}
			