package com.revature;

import java.util.Scanner;

import com.revature.dao.UserDAOImplementation;
import com.revature.exceptions.AccountAlreadyExistsException;
import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.AccountNotEmptyException;
import com.revature.exceptions.FailedLoginException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.NotEnoughMoneyException;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.models.User;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Console;

public class UserConsole {
	
	public static Scanner sc = new Scanner(System.in);
	static String userName = "";
	static User user= new User();
	static User superUser = new User();
	boolean isSuperUser = false;
	
	public void LoginScreen() {
		System.out.println("Hello, and welcome to the bank!");
		System.out.println("What would you like to do?");
		System.out.println("1: Login");
		System.out.println("2: Create a bank account");
		System.out.println("3: Exit application");
		
		
		String input;
		boolean validInput = false;
		
		do {
			
			input = sc.nextLine();

			switch (input) {
			case "1":
				validInput = true;
				enterUserInfo();
				return;
			case "2":
				validInput = true;
				createAccount();
				return;
			case "3":
				validInput = true;
				return;
			}
			
			if (!validInput) {
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter 1, 2, or 3");
				}
			}
			
		} while (!validInput);
		
	}

	public void enterUserInfo () {
		
		String username;
		//char [] charpass;
		String pass;
		String attempt = "";
		boolean validPassword;
		
		do {
			
			// Ask user for username and password:
			
			System.out.println("Please enter your username");
		
			username = sc.nextLine();
		
			//Console console = System.console();
			
			//charpass = console.readPassword("Please enter your password");
			
			//pass = charpass.toString();
					
			System.out.println("Please enter your password");
		
			pass = sc.nextLine();
			
			// Check if password is valid:
			
			validPassword = UserDAOImplementation.getUserDAO().verifyUser(username, pass);
			
			if (!validPassword) {
				
				// If the password is invalid, ask if the user wants to try again:
				
				try {
					throw new FailedLoginException();
				}
				catch (FailedLoginException e) {
					System.out.println(e.getMessage());
					System.out.println("Try again? (Y/N)");
				}
				
				attempt = sc.nextLine();
				
				if (attempt.equals("N")|| attempt.equals("n")) {
					LoginScreen();
					return;
				}
				else if (!((attempt.equals("Y") || (attempt.equals("y"))))){
					
					try {
						throw new InvalidInputException();
					}
					catch (InvalidInputException e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter Y or N");
					}
				}
			}
			else {
				attempt = "N";
			}
			
		} while ((attempt.equals("Y") || (attempt.equals("y"))));
		
		// Set the static user variable:
		
		user = UserService.getUserService().getUser(username);
		
		// Check if the user is a superuser:
		
		isSuperUser = verifySuperUser(user.getUserName(), user.getPassword());
			
		if (isSuperUser) {
			superUser = user;
		}
		
		// If the login was successful, move to the next part of the console:
		
		System.out.println("Login successful");
		
		accountActions();
	}
	
	public int retrieveID() {
		return UserService.getUserService().retrieveID(user.getUserName());
	}
	
	public boolean verifyUser(String username, String pass) {
		
		// Check if the user's username and password are valid:
		
		return UserService.getUserService().verifyUser(username, pass);
		
	}
	
	public boolean verifySuperUser(String username, String pass) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src\\main\\resources\\superuser.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String superUsername = "";
		String superPassword = "";
		
		try {
			superUsername = br.readLine(); 
			superPassword = br.readLine();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		return (username.equals(superUsername) && pass.equals(superPassword));
		
	}
	
	public boolean accountExists(String account_type) {
		
		// Checks if the account exists for this user:
		
		return UserService.getUserService().accountExists(user.getUserId(), account_type);
	}
	
	public void accountActions() {
		
		// Ask what the user would like to do:
		
		if(isSuperUser) {
			System.out.println("How may I be of service, master?");
		}
		else {
			System.out.println("How may I help you?");
		}
		
		System.out.println("1: Make a deposit");
		System.out.println("2: Make a withdrawal");
		System.out.println("3: View account balances");
		System.out.println("4: View transaction history");
		System.out.println("5: Add an account");
		System.out.println("6: Delete an account");
		System.out.println("7: Close bank account");
		System.out.println("8: Logout");
		
		if(isSuperUser) {
			
			// Superuser privileges:
			
			System.out.println("9: View users");
			System.out.println("10: Create users");
			System.out.println("11: Update users");
			System.out.println("12: Delete users");
		}
		
		boolean validInput = false;

		String input;

		do {
			
			input = sc.nextLine();

			switch (input) {
			case "1":
				deposit();
				return;
			case "2":
				withdraw();
				return;
			case "3":
				viewBalances();
				return;
			case "4":
				viewTransactions();
				return;
			case "5":
				addAccount();
				return;
			case "6":
				deleteAccount();
				return;
			case "7":
				closeAllAccounts();
				return;
			case "8":
				System.out.println("Thank you, and have a nice day!\n");
				LoginScreen();
				return;
			case "9":
				if(isSuperUser) {
					viewUsers();
				}
				else {
					System.out.println("Sorry, filthy casuals aren't allowed to do that");
				}
				return;
			case "10":
				if(isSuperUser) {
					createUsers();
				}
				else {
					System.out.println("Sorry, filthy casuals aren't allowed to do that");
				}
				return;
			case "11":
				if(isSuperUser) {
					updateUsers();
				}
				else {
					System.out.println("Sorry, filthy casuals aren't allowed to do that");
				}
				return;
			case "12":
				if(isSuperUser) {
					deleteUsers();
				}
				else {
					System.out.println("Sorry, filthy casuals aren't allowed to do that");
				}
				return;
			} 

			if(isSuperUser) {
				
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a number from 1-12");
				}
			}
			else {
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a number from 1-8");
				}
			}
		} while(!validInput);

	}
	
	public boolean verifyName(String input) {
		
		// If the input is too long or an empty string, it is not valid:

		if (input.length() > 40 || input.length() == 0) {
			return false;
		}
		
		// If all of the characters in the word are letters, it is a valid name
		
		for (int i = 0; i < input.length(); i++) {
			if (!(Character.isLetter(input.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyUsernameOrPassword(String input) {
		
		// If the input is too long or an empty string, it is not valid:
		
		if (input.length() > 40 || input.length() == 0) {
			return false;
		}
		
		// The input can contain letters, numbers, and underscores; anything else is invalid
		
		String alteredString = input.replaceAll("_","");
		
		for (int i = 0; i < alteredString.length(); i++) {
			if (!(Character.isLetter(alteredString.charAt(i)) || (Character.isDigit(alteredString.charAt(i))))) {
				return false;
			}
		}
		return true;
	}
	
	public void createAccount() {
		
		// Ask for user information:
		
		boolean validInput = false;
		String fName = "", lName = "", username = "", pass = "";
		
		System.out.println("Please enter your first name");
		
		do {
			fName = sc.nextLine();
			validInput = verifyName(fName);
			
			if(!validInput) {
				System.out.println("Please enter only letters for your name");
			}
			
		} while (!validInput);
		
		validInput = false;
		
		System.out.println("Please enter your last name");
		
		do {
			lName = sc.nextLine();
			validInput = verifyName(lName);
			
			if(!validInput) {
				System.out.println("Please enter only letters for your name");
			}
		
		}while(!validInput);
			
		validInput = false;
		
		System.out.println("Please enter a username");
		
		do {
			username = sc.nextLine();
			validInput = verifyUsernameOrPassword(username);
			
			if(!validInput) {
				System.out.println("Please enter only letters, numbers, or underscores for your username");
			}
		
		}while(!validInput);
		
		validInput = false;
		
		System.out.println("Please enter a password");
		
		do {
			pass = sc.nextLine();
			validInput = verifyUsernameOrPassword(pass);
			
			if(!validInput) {
				System.out.println("Please enter only letters, numbers, or underscores for your password");
			}
		
		}while(!validInput);
		
		boolean accountCreated = false;
		
		// Create account:
		
		accountCreated = UserService.getUserService().registerUserProcedure(new User(0,fName,lName,username,pass));
		
		if (accountCreated) {
			System.out.println("Bank account successfully created");
			user = UserService.getUserService().getUser(username);
			addAccount("Checking");
			addAccount("Savings");
			user = new User();
		}
		
		// Return to login screen:
		
		LoginScreen();
		
		return;
	}
	
	public boolean isValidNumber(String string) {
		
		// If the string is empty, it is not a valid:

		if (string.length() == 0) {
			return false;
		}
		
		// Split the string at the decimal:
		
		String []splitString = string.split("\\.");
		
		// If there is more than one decimal, the number is invalid:
		
		if (splitString.length > 2) {
			return false;
		}
		
		// Check if every character is a digit; if so, the number is valid:
		
		for (String segment : splitString) {
			for (int i = 0; i < segment.length(); i++) {
		    	if (!(Character.isDigit(string.charAt(i)))) {
		    		return false;
		    	}
		    }
			if (segment != splitString[0] && segment.length() > 2) {
	    		return false;
	    	}
			
		}
		
		try {
	    	if (Double.parseDouble(string) < Double.MAX_VALUE) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    catch (Exception e) {
	    	return false;
	    }
	}
	
	public double checkBalance(int user_Id, String accountType) {
		
		// Allows the user to check the balance of a specific account;
		
		return UserService.getUserService().checkBalance(user_Id, accountType);
	}
	
	public void deposit() {
		
		// Ask the user which account they want to deposit into:
		
		System.out.println("Which account would you like to make a deposit into?");
		
		boolean valid = false;
		
		String account_type = "";
		
		do {

			account_type = sc.nextLine();

			valid = accountExists(account_type);

			if (account_type.equals("back")) {
				accountActions();
				return;
			}
			else if (!valid) {
				try {
					throw new AccountDoesNotExistException();
				}
				catch (AccountDoesNotExistException e) {
					System.out.println(e.getMessage());
					System.out.println("Try entering \"Checking\", \"Savings\", or one of your other account names");
					System.out.println("You can also type \"back\" to go back");
				}
			}
			
		} while (!valid);
		
		valid = false;
		
		System.out.println("Enter the amount you would like to deposit");
		
		String amount = "";
		
		do {

			amount = sc.nextLine();

			valid = isValidNumber(amount);

			if (valid) {
				UserService.getUserService().deposit(Double.parseDouble(amount), user.getUserId(), account_type);
			}
			else {
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a number with at most two decimal places");
				}
			}
			
		} while (!valid);
		
		accountActions();
		
	}
	
	public void withdraw() {
		
		System.out.println("Which account would you like to withdraw from?");
		
		boolean valid = false;
		
		String account_type = "";
		
		do {

			account_type = sc.nextLine();

			valid = accountExists(account_type);

			if (account_type.equals("back")) {
				accountActions();
				return;
			}
			else if (!valid) {
				try {
					throw new AccountDoesNotExistException();
				}
				catch (AccountDoesNotExistException e) {
					System.out.println(e.getMessage());
					System.out.println("Try entering \"Checking\", \"Savings\", or one of your other account names");
					System.out.println("You can also type \"back\" to go back");
				}
			}
			
		} while (!valid);
		
		valid = false;
		
		System.out.println("Enter the amount you would like to withdraw");
		
		boolean success = false;
		boolean hasEnoughMoney = false;
		double balance = 0;
		String amount = "";
		
		do {

			amount = sc.nextLine();

			valid = isValidNumber(amount);

			if (valid) {
				balance = checkBalance(user.getUserId(), account_type);
				hasEnoughMoney = (balance >= Double.parseDouble(amount));
				if (hasEnoughMoney) {
					success = UserService.getUserService().withdraw(Double.parseDouble(amount), user.getUserId(), account_type);
					if (success) {
						System.out.println("Withdraw successful");
					}
				}
				else {
					try {
						throw new NotEnoughMoneyException();
					}
					catch (NotEnoughMoneyException e) {
						System.out.println(e.getMessage());
						System.out.println("You only have " + balance + " in that account");
					}
				}
			}
			else {
				
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a number with at most two decimal places");
				}
			}
			
		} while (!valid);
		
		accountActions();
		
	}
	
	public void viewBalances() {
		
		if (userExists(user.getUserId())) {
			System.out.println("Displaying account information:\n");
			System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
			System.out.println("User ID: " + user.getUserId());
			System.out.println("Username: " + user.getUserName() + "\n");
			
		}
		UserService.getUserService().viewBalances(user.getUserId());
		accountActions();
		
	}
	
	public void viewTransactions() {
		
	}
	
	public void addAccount() {
		
		String account_type = "";
		boolean valid;
		
		System.out.println("What would you like to call your new account?");
		
		do {

			account_type = sc.nextLine();

			valid = !accountExists(account_type);

			
			if (account_type.equals("back")) {
				accountActions();
				return;
			}
			else if (valid) {
				addAccount(account_type);
				accountActions();
				return;
			}
			else {
				try {
					throw new AccountAlreadyExistsException();
				}
				catch (AccountAlreadyExistsException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a different name or type \"back\" to go back");
				}
			}
			
		} while (!valid);
		
	}
	
	public void addAccount(String account_type) {
		
		// Overloaded operator for creating accounts with the specified name:
		
		boolean added = UserService.getUserService().addAccount(user.getUserId(), account_type);
		
		if (added) {
			System.out.println("Added \""+ account_type + "\" account");
		}
		
	}
	
	public void closeAllAccounts() {
		
		boolean emptyBalances = UserService.getUserService().checkBalancesEmpty(user.getUserId());
		boolean deleted = false;
		
		if (emptyBalances) {
			
			System.out.println("Are you sure? This will delete all of your records with this bank. (Y/N)");
			
			String input;
			boolean validInput = false;
			do {
				input = sc.nextLine();
				
				if (input.equals("N") || input.equals("n")) {
					validInput = true;
					accountActions();
					return;
				}
				else if (!((input.equals("Y") || (input.equals("y"))))){
					
					validInput = false;
					
					try {
						throw new InvalidInputException();
					}
					catch (InvalidInputException e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter Y or N");
					}
				}
				else {
					validInput = true;
				}
				
			}while(!validInput);
			
			if (input.equals("Y") || input.equals("y")) {
				deleted = UserService.getUserService().closeAllAccounts(user.getUserId());
				if (deleted) {
					System.out.println("Account deleted");
					System.out.println("Thank you, and have a nice day!\n");
				}
			}
			LoginScreen();
			return;
		}
		else {
			try {
				throw new AccountNotEmptyException();
			}
			catch (AccountNotEmptyException e) {
				System.out.println(e.getMessage());
			}
			accountActions();
			return;
		}
		
	}
	
	public void deleteAccount() {
		
		System.out.println("Which account would you like to delete?");
		
		boolean emptyBalance = false;
		boolean valid = false;
		boolean deleted = false;
		
		String account_type = "";
		
		do {

			account_type = sc.nextLine();

			valid = accountExists(account_type);
			if (account_type.equals("back")) {
				accountActions();
				return;
			}
			else if (!valid) {
				try {
					throw new AccountDoesNotExistException();
				}
				catch (AccountDoesNotExistException e) {
					System.out.println(e.getMessage());
					System.out.println("Try entering \"Checking\", \"Savings\", or one of your other account names");
					System.out.println("You can also type \"back\" to go back");
				}
			}
			
		} while (!valid);
		
		emptyBalance = UserService.getUserService().checkBalanceEmpty(user.getUserId(), account_type);
			
		if (emptyBalance) {
			deleted = UserService.getUserService().deleteAccount(user.getUserId(), account_type);
			if (deleted) {
				System.out.println("Account deleted");
			}
		}
		else {
			System.out.println("You must withdraw your money from this account before deleting it");
			accountActions();
			return;
		}
		accountActions();
		
	}
	
	
	public void viewUsers() {
		UserService.getUserService().viewUsers();
		accountActions();
	}
	
	public void createUsers() {
		
		// Ask for user information:
		
		System.out.println("Please enter the user's first name, master");
		
		String fName = sc.nextLine();
		
		System.out.println("Please enter the user's last name, master");
		
		String lName = sc.nextLine();
		
		System.out.println("Please enter the user's username, master");
		
		String username = sc.nextLine();
		
		System.out.println("Please enter the user's password, master");
		
		String pass = sc.nextLine();
		
		boolean accountCreated = false;
		
		// Create account:
		
		accountCreated = UserService.getUserService().registerUserProcedure(new User(0,fName,lName,username,pass));
		
		if (accountCreated) {
			System.out.println("Bank account successfully created");
			user = UserService.getUserService().getUser(username);
			addAccount("Checking");
			addAccount("Savings");
			user = new User();
		}
		
		// Return to the action menu:
		
		accountActions();
	}
	
	public void updateUsers() {
		
		System.out.println("Who would you like to update, master?");
		
		boolean valid = false;
		boolean updated = false;
		String user_id = "";
		
		do {

			user_id = sc.nextLine();
			
			if (user_id.equals("back")) {
				accountActions();
				return;
			}
			else if (!isPositiveInteger(user_id)) {
				valid = false;
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter an integer, master");
					System.out.println("You can also type \"back\" to go back");
				}
			}
			else if (!userExists(Integer.parseInt(user_id))) {
				valid = false;
				try {
					throw new UserDoesNotExistException();
				}
				catch (UserDoesNotExistException e) {
					System.out.println(e.getMessage());
					System.out.println("You can also type \"back\" to go back");
				}
			}
			else {
				valid = true;
			}
			
		} while (!valid);
		
		// Ask for user information:

		System.out.println("Please enter the user's new first name, master");

		String fName = sc.nextLine();

		System.out.println("Please enter the user's new last name, master");

		String lName = sc.nextLine();

		System.out.println("Please enter the user's new username, master");

		String username = sc.nextLine();

		System.out.println("Please enter the user's new password, master");

		String pass = sc.nextLine();

		User updatedUser = new User(0,fName,lName,username,pass);
		
		updated = UserService.getUserService().updateUser(Integer.parseInt(user_id), updatedUser);
		if (updated) {
			System.out.println("User updated");
		}
		
		accountActions();
	}
	
	public void deleteUsers() {
		
		System.out.println("Who would you like to delete, master?");
		
		boolean valid = false;
		boolean deleted = false;
		String user_id = "";
		
		do {

			user_id = sc.nextLine();
			
			if (user_id.equals("back")) {
				accountActions();
				return;
			}
			else if (!isPositiveInteger(user_id)) {
				valid = false;
				try {
					throw new InvalidInputException();
				}
				catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter an integer, master");
					System.out.println("You can also type \"back\" to go back");
				}
			}
			else if (!userExists(Integer.parseInt(user_id))) {
				valid = false;
				try {
					throw new UserDoesNotExistException();
				}
				catch (UserDoesNotExistException e) {
					System.out.println(e.getMessage());
					System.out.println("You can also type \"back\" to go back");
				}
			}
			else {
				valid = true;
			}
			
		} while (!valid);
		
		deleted = UserService.getUserService().closeAllAccounts(Integer.parseInt(user_id));
		if (deleted) {
			System.out.println("User deleted");
		}
		
		accountActions();
		
	}
	
	public boolean isPositiveInteger(String string) {
		
		// If the string is empty, it is not an integer, obviously:
		
		if (string.length() == 0) {
			return false;
		}
		
		// Check if every character is a digit; if so, the number is an integer:
		
	    for (int i = 0; i < string.length(); i++) {
	    	if (!(Character.isDigit(string.charAt(i)))) {
	    		return false;
	    	}
	    }
	    
	    // Only accept positive integers:
	    
	    try {
	    	if (Integer.parseInt(string) > 0) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    catch (Exception e) {
	    	return false;
	    }
	}
	
	public boolean userExists(int user_id) {
		
		// Checks if the user with specified ID exists:
		
		return UserService.getUserService().userExists(user_id);
	}
	
}
