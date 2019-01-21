package com.revature.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.InvalidAccountDeletionException;
import com.revature.exceptions.InvalidAccountIDException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.UserExistsException;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.TransactionService;
import com.revature.services.UserService;
import com.revature.utils.JDBCConnectUtil;

public class SuperuserLoggedIn {

	public static void login(User user){
		boolean loggedIn = true;
		
		System.out.println("Would you like to view all users, delete a user, create a user, edit a user, or logout?");
		System.out.println("Please enter view, delete, create, edit, or logout.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		while(loggedIn) {
			try {				
				if(input.toLowerCase().equals("view")){
					System.out.println("Printing a list of all the users");
					viewAllUsers();
					System.out.println("Please enter view, delete, create, edit, or logout.");
					input = sc.nextLine();
					
				}else if(input.toLowerCase().equals("delete")){
					viewAllUsers();
					deleteUser();
					System.out.println("Please enter view, delete, create, edit, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("create")){
					viewAllUsers();
					createUser();
					System.out.println("Please enter view, delete, create, edit, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("edit")){
					viewAllUsers();
					editUser();
					System.out.println("Please enter view, delete, create, edit, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("logout")){
					System.out.println("Logging out.");
					loggedIn = false;
					try {
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new InvalidInputException();
				}
				
			}catch(InvalidInputException e){
				System.out.println(e.getMessage() + " Please enter view, create, delete, deposit, withdraw, or logout.");
				input = sc.nextLine();
			}
		}
	}

	static void viewAllUsers() {
		List<User> userList = UserService.getUserService().getAllUserDetails();
		for(User user: userList) {
			System.out.println(user.getUserID()+", "+user.getUsername());
		}
	}
	
	private static boolean deleteUser() {
		boolean accountDeleted = false;
		
		System.out.println("Please enter an user ID to delete.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		while(!accountDeleted && !input.equalsIgnoreCase("exit")) {
			try {
				if(!UserService.getUserService().checkUserID(Integer.parseInt(input)) || Integer.parseInt(input) == 1) {					
					throw new InvalidAccountDeletionException();					
				}else {				
					int userID = Integer.parseInt(input);
					User user = UserService.getUserService().getUserDetails(userID);					
					UserService.getUserService().deleteUserConnection(user.getUsername());					
					TransactionService.getTransactionService().deleteAllTransactions(userID);
					AccountService.getAccountService().deleteAllAccounts(userID);
					accountDeleted = UserService.getUserService().deleteUser(userID);	
					System.out.println("User deleted.");
					return accountDeleted;				
				}					
			}catch(InvalidAccountDeletionException u) {
				System.out.println(u.getMessage() + " Please enter another Account ID");
				input = sc.nextLine();
			}catch(NumberFormatException nf) {
				System.out.println(" Please enter a valid number.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println(" Please enter a valid number.");
				input = sc.nextLine();
			}
		}
		return false;
	}	
	
	static void createUser() {		

		String username = establishUsername();
		if(!username.equalsIgnoreCase("exit")) {
			String password = establishPassword();
		
			UserService.getUserService().registerUser(new User(username, password, 0));
			System.out.println("Creating User.");
			UserService.getUserService().createUserConnection(new User(username, password, 0));
		}
}

	static String establishUsername() {
		boolean usernameEstablished = false;
	
		System.out.println("Please enter a username.");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
	
		while(!usernameEstablished && !username.equalsIgnoreCase("exit")) {
			try {		
			
				if(username.equals("") || username.equalsIgnoreCase("exit")) {
					throw new InvalidInputException();
				}else if(UserService.getUserService().checkUsername(username)) {
					throw new UserExistsException();
				}else {
					usernameEstablished = true;
				}
				
			}catch(InvalidInputException e) {
				System.out.println(e.getMessage() + " Please enter a valid username or type exit.");
				username = sc.nextLine();
			}catch(UserExistsException u) {
				System.out.println(u.getMessage() + " Please enter a valid username or type exit.");
				username = sc.nextLine();
			}
		}
		return username;
	}

	static String establishPassword() {
		boolean passwordEstablished = false;
	
		System.out.println("Please enter a password.");
		char password[] = System.console().readPassword();
		String strPassword = new String(password);
	
		while(!passwordEstablished) {
			try {
				if(strPassword.equals("")) {
					throw new InvalidInputException();
				}else {
					passwordEstablished = true;
				}
			}catch(InvalidInputException e) {
				System.out.println(e.getMessage() + " Please enter a valid password.");
				password = System.console().readPassword();
				strPassword = new String(password);
			}
			
		}
		return strPassword;
	}

	private static void editUser() {
		boolean editMade = false;
	
		System.out.println("Please enter a userID to edit.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while(!editMade && !input.equalsIgnoreCase("exit")) {
			try {
				if(!UserService.getUserService().checkUserID(Integer.parseInt(input))) {
					
					throw new InvalidAccountIDException();
				}else {
					editUser(Integer.parseInt(input));
					return;
				}
				
			}catch(InvalidAccountIDException id) {
				System.out.println(id.getMessage() + "Please enter another User ID");
				input = sc.nextLine();
			}catch(NumberFormatException nf) {
				System.out.println("Please enter a valid User ID.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println("Please enter a valid User ID.");
				input = sc.nextLine();
			}
		}
	}

	private static void editUser(int userID) {
		
		User user = UserService.getUserService().getUserDetails(userID);
		String oldUsername = user.getUsername();
		String oldPassword = user.getPassword();
		user.setUsername(establishUsername());
		user.setPassword(establishPassword());
		UserService.getUserService().editUserDetails(oldUsername, oldPassword, user);
		UserService.getUserService().deleteUserConnection(oldUsername);
		UserService.getUserService().createUserConnection(user);
		System.out.println("User successfully edited.");
	}
}
