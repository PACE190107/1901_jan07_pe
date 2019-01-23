package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.exceptions.LoginFailedException;
import com.revature.exceptions.OutstandingBalanceException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.ATMService;

public class BankATM {
	private static ATMService atm;
	private static User currentUser;
	private static ArrayList<Account> currentUserAccounts;
	private static Account selectedAccount;
	private static int currentAccount;
	private static boolean isSuperUser;
	private static Scanner input = new Scanner(System.in);
	private static String usernameInput;
	private static String passwordInput;
	private static String accountInput;
	private static ArrayList<User> usersList;
	
	public static void main(String[] args) {
		atm = atm.getATMService();
		blankSpace();
		loginScreen();
	}
	
	private static void loginScreen() {
		System.out.println("Welcome to JDBC Bank");
		System.out.println("[0] Close ATM");
		System.out.println("[1] Login for existing user.");
		System.out.println("[2] Register as a new user.");
		System.out.println("Enter number to select option:");
		int option = 99;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			loginScreen();
		}
		switch(option) {
			case 0:
				blankSpace();
				input.close();
				System.out.println("ATM Closed");
				break;
			case 1:
				blankSpace();
				existingUserMenu();
				break;
			case 2:
				blankSpace();
				registerNewUser();
				break;
			default:
				blankSpace();
				invalidMenuSelection(option);
				loginScreen();
		}
		
	}
	
	private static void existingUserMenu() {
		System.out.println("Existing user login.");
		System.out.print("Enter username: ");
		usernameInput = input.nextLine();
		System.out.print("Enter password: ");
		passwordInput = input.nextLine();
		try {
			currentUser = atm.verifyLogin(new User(usernameInput, passwordInput));
		} catch (LoginFailedException e) {
			blankSpace();
			System.out.println("\nLogin Failed, try again or register a new account.");
			loginScreen();
		}
		if(currentUser.isSuperUser()) {
			blankSpace();
			System.out.println("Super User Login Successful.\n");
			superUserActions();
		}
		else {
			blankSpace();
			System.out.println("Login Successful.\n");
			userActions();
		}
	}
		
	
	private static void registerNewUser() {
		System.out.println("Register New User");
		System.out.println("Enter username: ");
		usernameInput = input.nextLine();
		System.out.print("Enter password: ");
		passwordInput = input.nextLine();
		if(atm.registerNewUser(new User(usernameInput, passwordInput))) {
			blankSpace();
			System.out.println("\nAccount registered successfully.");
			loginScreen();
		} else { 
			blankSpace();
			System.out.println("\nUnable to register provided username.");
			loginScreen();
		}
	}
	
	private static void invalidMenuSelection(int selectedIndex) {
		System.out.println("Option: ["+selectedIndex+"] is not a valid option.");
	}
	
	private static void userActions() {
		getUserAccounts();
		System.out.println("[0] Log out of account.");
		System.out.println("[1] List accounts and balances");
		System.out.println("[2] Create new account.");
		System.out.println("[3] Withdraw from an account.");
		System.out.println("[4] Deposit from an account.");
		System.out.println("[5] Close out an account.");
		System.out.println("Enter number to select option:");
		int option = 99;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			loginScreen();
		}
		switch(option) {
			case 0:
				blankSpace();
				loginScreen();
				break;
			case 1:
				blankSpace();
				listAccounts();
				break;
			case 2:
				blankSpace();
				createAccountRequest();
				break;
			case 3:
				if(currentUserAccounts.size() < 1) {
					blankSpace();
					System.out.println("You do not have any open accounts.");
					System.out.println("Create an account to withdraw.");
					userActions();
				}
				else {
					blankSpace();
					selectAccountWithdraw();
				}
				break;
			case 4:
				if(currentUserAccounts.size() < 1) {
					blankSpace();
					System.out.println("You do not have any open accounts.");
					System.out.println("Create an account to deposit.\n");
					userActions();
				}
				else {
					blankSpace();
					selectAccountDeposit();
				}
				break;
			case 5:
				if(currentUserAccounts.size() < 1) {
					blankSpace();
					System.out.println("You do not have any open accounts to delete.\n");
					userActions();
				} else {
					blankSpace();
					deleteAccountRequest();
				}
				break;
			default:
				blankSpace();
				invalidMenuSelection(option);
				userActions();
		}
	}
	
	private static void listAccounts() {
		blankSpace();
		currentUserAccounts = atm.getUserAccounts(currentUser);
		int index = 0;
		if(currentUserAccounts.size() > 0) {
			System.out.println("List of open accounts.");
			for(Account a: currentUserAccounts) {
				System.out.println("Name: "+a.getAccountName()+"; Balance: "+a.getBalance());
				index++;
			}
		} else {
			System.out.println("User does not have any open accounts.\nTry creating a new account.");
		}
		System.out.println();
		userActions();
	}
	
	private static void selectAccountWithdraw() {
		int index = 0;
		System.out.println("List of open accounts.");
		for(Account a: currentUserAccounts) {
			System.out.println("["+index+"] Name: "+a.getAccountName()+"; Balance: "+a.getBalance());
			index++;
		}
		System.out.println("["+index+"] Cancel Withdraw.");
		System.out.println("Select account to withdraw from:");
		int option = 99999999;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			selectAccountWithdraw();
		}
		if(option == currentUserAccounts.size()) {
			blankSpace();
			System.out.println("Withdraw request cancelled.");
			userActions();
		} else if(option >= 0 && option < currentUserAccounts.size()) {
			selectedAccount = currentUserAccounts.get(option);
			blankSpace();
			selectWithdrawAmount();
		} else {
			blankSpace();
			System.out.println("["+option+"] is not a valid choice.\n");
			selectAccountWithdraw();
		}
	}
	
	private static void selectWithdrawAmount() {
		System.out.println("Name: "+selectedAccount.getAccountName()+"; Balance: "+selectedAccount.getBalance());
		System.out.println("Enter amount to withdraw:");
		double option = 0;
		try{
			option = Double.parseDouble(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			selectWithdrawAmount();
		}
		try {
			if(atm.withdraw(selectedAccount, option)) {
				blankSpace();
				System.out.println("Withdraw of $"+option+" completed.");
				System.out.println("Name: "+selectedAccount.getAccountName()+"; Balance: "+selectedAccount.getBalance());
				userActions();
			};
		} catch (OverdraftException e) {
			blankSpace();
			System.out.println("Insufficient funds for withdraw.");
			selectAccountWithdraw();
		}
	}
	
	private static void selectAccountDeposit() {
		blankSpace();
		int index = 0;
		System.out.println("List of open accounts.");
		for(Account a: currentUserAccounts) {
			System.out.println("["+index+"] Name: "+a.getAccountName()+"; Balance: "+a.getBalance());
			index++;
		}
		System.out.println("["+index+"] Cancel deposit.");
		System.out.println("Select account to deposit to:");
		int option = 99999999;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			selectAccountDeposit();
		}
		if(option == currentUserAccounts.size()) {
			blankSpace();
			System.out.println("Deposit request cancelled.");
			userActions();
		} else if(option >= 0 && option < currentUserAccounts.size()) {
			selectedAccount = currentUserAccounts.get(option);
			blankSpace();
			selectDepositAmount();
		} else {
			blankSpace();
			System.out.println("["+option+"] is not a valid choice.\n");
			selectAccountDeposit();
		}
	}
	
	private static void selectDepositAmount() {
		System.out.println("Name: "+selectedAccount.getAccountName()+"; Balance: "+selectedAccount.getBalance());
		System.out.println("Enter amount to deposit:");
		double option = 0;
		try{
			option = Double.parseDouble(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			selectDepositAmount();
		}
		if(atm.deposit(selectedAccount, option)) {
			blankSpace();
			System.out.println("Deposit of $"+option+" completed.");
			System.out.println("Name: "+selectedAccount.getAccountName()+"; Balance: "+selectedAccount.getBalance());
			userActions();
		} else {
			blankSpace();
			System.out.println("Deposit of $"+option+" failed.");
			userActions();
		}
	}

	private static void createAccountRequest() {
		System.out.println("Create new account.");
		System.out.println("Enter account name:");
		accountInput = input.nextLine();
		if(atm.createAccountRequest(accountInput, currentUser)){
			blankSpace();
			System.out.println("Created account "+accountInput+" successfully.");
			userActions();
		} else {
			blankSpace();
			System.out.println("Failed to create account "+accountInput+", try another name.");
			userActions();
		}
	}
	
	private static void deleteAccountRequest() {
		int index = 0;
		for(Account a: currentUserAccounts) {
			System.out.println("["+index+"] Name: "+a.getAccountName()+"; Balance: "+a.getBalance());
			index++;
		}
		System.out.println("["+index+"] Cancel delete request.");
		System.out.println("Select account to delete:");
		int option = 99999999;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			deleteAccountRequest();
		}
		if(option == currentUserAccounts.size()) {
			blankSpace();
			System.out.println("Delete request cancelled.");
			userActions();
		} else if(option >= 0 && option < currentUserAccounts.size()) {
			selectedAccount = currentUserAccounts.get(option);
			try {
				if(atm.deleteAccountRequest(selectedAccount)){
					blankSpace();
					System.out.println("Deleted account "+selectedAccount.getAccountName()+" successfully.");
					userActions();
				}
			} catch (OutstandingBalanceException e) {
				blankSpace();
				System.out.println("Unable to delete account without a 0 balance.");
				userActions();
			}
		} else {
			blankSpace();
			System.out.println("["+option+"] is not a valid choice.\n");
			deleteAccountRequest();
		}
			
	}
	
	private static void superUserActions() {
		System.out.println("[0] Log out of account.");
		System.out.println("[1] View All Users");
		System.out.println("[2] Create New User.");
		System.out.println("[3] Update User account.");
		System.out.println("[4] Delete a User account.");
		System.out.println("[5] Delete all User accounts.");
		System.out.println("Enter number to select option:");
		int option = 99999999;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			superUserActions();
		}
		switch(option) {
			case 0: //working
				blankSpace();
				loginScreen();
				break;
			case 1: //working
				blankSpace();
				usersList = atm.listAllUsers();
				for(User u: usersList) {
					System.out.println("USER ID: " + u.getUserID() +"; Username: "+ u.getUsername()+"; Password: "+u.getPassword());
				}
				superUserActions();
				break;
			case 2: //need to implement Create User
				blankSpace();
				superCreateUser();
				break;
			case 3: //need to implement Update User
				blankSpace();
				superUpdateUser();
				break;
			case 4: //need to implement Delete a User
				blankSpace();
				superDeleteUser();
				break;
			case 5: //need to implement Delete all Users
				blankSpace();
				superDeleteAllUsers();
				break;
			default:
				blankSpace();
				invalidMenuSelection(option);
				superUserActions();
		}
		
	}
	
	private static void superCreateUser() {
		String newUsername;
		String newPassword;
		int newID = 0;
		
		System.out.println("Create new user.");
		System.out.println("Enter username:");
		newUsername = input.nextLine();
		System.out.println("Enter password:");
		newPassword = input.nextLine();
		System.out.println("Enter user ID:");
		try{
			newID = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("ID must be a number.");
			superCreateUser();
		}
		if(atm.createUser(new User(newID, newUsername, newPassword, 0))) {
			blankSpace();
			System.out.println("New user created successfully.");
			superUserActions();
		} else {
			blankSpace();
			System.out.println("Failed to create new user.");
			superUserActions();
		}
		
	}
	
	private static void superUpdateUser() {
		User updateUser;
		usersList = atm.listAllUsers();
		int index = 0;
		for(User a: usersList) {
			System.out.println("["+index+"] ID: "+a.getUserID()+"; Username: "+a.getUsername()+"; Password: "+a.getPassword());
			index++;
		}
		System.out.println("["+index+"] Cancel update.");
		System.out.println("Select account to delete:");
		int option = 99999999;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			superDeleteUser();
		}
		if(option == usersList.size() || option == 0) {
			blankSpace();
			System.out.println("Update account request cancelled.");
			userActions();
		} else if(option >= 0 && option < usersList.size()) {
			blankSpace();
			updateUser = usersList.get(option);
			System.out.println("Current username: "+updateUser.getUsername());
			System.out.println("Enter new username:");
			updateUser.setUsername(input.nextLine());
			System.out.println("Current password: "+updateUser.getPassword());
			System.out.println("Enter new password:");
			updateUser.setPassword(input.nextLine());
			if(atm.udpateUser(updateUser)) {
				blankSpace();
				System.out.println("Updated "+updateUser.getUserID()+" "+updateUser.getUsername()+" "+updateUser.getPassword()+ " successfully.");
				superUserActions();
			} else {
				blankSpace();
				System.out.println("Failed to update selected account.");
				superUserActions();
			}
		} else {
			blankSpace();
			System.out.println("["+option+"] is not a valid choice.\n");
			superUpdateUser();
		}
	}
	
	private static void superDeleteUser() {
		usersList = atm.listAllUsers();
		int index = 0;
		for(User a: usersList) {
			System.out.println("["+index+"] ID: "+a.getUserID()+"; Username: "+a.getUsername()+"; Password: "+a.getPassword());
			index++;
		}
		System.out.println("["+index+"] Cancel deletion.");
		System.out.println("Select account to delete:");
		int option = 99999999;
		try{
			option = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException e) {
			blankSpace();
			System.out.println("Input must be a number.");
			superDeleteUser();
		}
		if(option == usersList.size() || option == 0) {
			blankSpace();
			System.out.println("Delete account request cancelled.");
			userActions();
		} else if(option >= 0 && option < usersList.size()) {
			blankSpace();
			if(atm.deleteUser(usersList.get(option))) {
				blankSpace();
				System.out.println("Deleted "+usersList.get(option).getUserID()+" "+usersList.get(option).getUsername()+" "+usersList.get(option).getPassword()+ " successfully.");
				superUserActions();
			} else {
				blankSpace();
				System.out.println("Failed to delete selected account.");
				superUserActions();
			}
		} else {
			blankSpace();
			System.out.println("["+option+"] is not a valid choice.\n");
			superDeleteUser();
		}
	}
	
	private static void superDeleteAllUsers() {
		if(atm.deleteAllUsers()) {
			blankSpace();
			System.out.println("All user accounts deleted.");
			superUserActions();
		} else {
			blankSpace();
			System.out.println("Unable to delete all user accounts.");
			superUserActions();
		}
	}
	
	private static void getUserAccounts() {
		currentUserAccounts = atm.getUserAccounts(currentUser);
	}
	
	private static void blankSpace() {
		for(int x = 0; x < 25; x++) {
			System.out.println();
		}
	}
}
