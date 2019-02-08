package com.revature.driver;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.exceptions.AccountNotEmptyException;
import com.revature.exceptions.EmptyOrNullInputException;
import com.revature.exceptions.IncorrectInputException;
import com.revature.exceptions.InvalidOptionException;
import com.revature.exceptions.NegativeDepositException;
import com.revature.exceptions.NegativeWithdrawlException;
import com.revature.exceptions.OverdrawException;
import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.utilities.ConnectionUtil;

public class Driver {
	
	private static User user; // cached user
	private static Scanner scanner;
	
	private static final String NEED_INPUT = "Please enter the required information below.";
	private static final String ENTER_ANY_KEY = "Enter any key to continue.";
	private static final String NUMBER_FORMAT_EXCEPTION = "You must enter a NUMBER corresponding with an option.";

	public static void main(String[] args) {
		Driver driver = new Driver();
		scanner = new Scanner(System.in);
		UIState state = UIState.WELCOME;
		do {
			switch(state) {
				case WELCOME:
					state = driver.viewWelcome();
					break;
				case REGISTER:
					state = driver.viewRegister();
					break;
				case LOGIN:
					state = driver.viewLogin();
					break;
				case USER_MENU:
					state = driver.viewUserMenu();
					break;
				case SUPERUSER_MENU:
					state = driver.viewSuperuserMenu();
					break;
				case VIEW_USERS:
					state = driver.viewUsers();
					break;
				case VIEW_BALANCES:
					state = driver.viewBalances();
					break;
				case CREATE_USER:
					state = driver.viewCreateUser();
					break;
				case CREATE_ACCOUNT:
					state = driver.viewCreateAccount();
					break;
				case UPDATE_USER:
					state = driver.viewUpdateUser();
					break;
				case DELETE_USER:
					state = driver.viewDeleteUser();
					break;
				case DELETE_ACCOUNT:
					state = driver.viewDeleteAccount();
					break;
				case CREATE_TRANSACTION:
					state = driver.viewCreateTransaction();
					break;
				case LOGOUT:
					state = driver.viewLogout();
					break;
				case EXIT:
					break;
			}
		} while (state != UIState.EXIT);
		scanner.close();
	}
	
	public UIState viewWelcome() {
		clearCMD();
		printFormattedMessage("Welcome to BankOfJason! How can I help you today?", false);
		
		// Login or Register
		do {
			System.out.println("1 - Login to an existing account");
			System.out.println("2 - Register a new account");
			System.out.println("0 - Exit application");
			try {	
				switch(Integer.parseInt(scanner.nextLine())) {
					case 1: 
						return UIState.LOGIN;
					case 2:
						return UIState.REGISTER;
					case 0:
						return UIState.EXIT;
					default:
						throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}
			
		} while (true);
	}
	
	public UIState viewRegister() {	
		clearCMD();
		printFormattedMessage(NEED_INPUT, false);
		
		// Collect unique username
		int failCounter = 0;
		String userNameInput = null;
		do {
			System.out.println("Username: ");
			try {
				userNameInput = scanner.nextLine();
				if (userNameInput.equals("")) {
					throw new EmptyOrNullInputException();
				}
				user = UserService.getUserService().getUser(userNameInput);
				if (user != null) {
					clearCMD();
					printFormattedMessage("We're sorry, but that username has already been selected."
							+ "\nPlease choose another.", false);
				}
			} catch (EmptyOrNullInputException eonie) {
				clearCMD();
				printFormattedMessage(eonie.getMessage(), true);
				failCounter++;
				if (failCounter > 3) {
					return UIState.WELCOME;
				}
			}
		} while (user != null);
		clearCMD();
		printFormattedMessage("Username accepted.", false);
		
		// Collect new password
		failCounter = 0;
		String passwordInput = null;
		do {
			System.out.println("Password: ");
			try {
				passwordInput = scanner.nextLine();
				if (passwordInput.equals("")) {
					throw new EmptyOrNullInputException();
				}
				break;
			} catch (EmptyOrNullInputException eonie) {
				clearCMD();
				printFormattedMessage(eonie.getMessage(), true);
				failCounter++;
				if (failCounter > 3) {
					return UIState.WELCOME;
				}
			}
		} while (true);

		// Register the user in the table
		User u = new User(userNameInput, passwordInput, false);
		UserService.getUserService().insertUser(u);

		// Register the user credentials
		UserService.getUserService().createCredentials(u);
		
		clearCMD();
		printFormattedMessage("You have successfully registered your account\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();
		return UIState.WELCOME;
	}
	
	public UIState viewLogin() {
		clearCMD();
		printFormattedMessage(NEED_INPUT, false);
		
		// Collect username for login
		int failCounter = 0;
		do {
			System.out.println("Username: ");
			try {
	 			user = UserService.getUserService().getUser(scanner.nextLine());
				if (user == null) {
					throw new IncorrectInputException();	
				}
				break;
			} catch (IncorrectInputException iie) {
				clearCMD();
				printFormattedMessage(iie.getMessage(), true);
				failCounter++;
				if (failCounter > 3) {
					return UIState.WELCOME;
				}
			}
		} while (true);	
		clearCMD();
		printFormattedMessage("Username accepted.\n" + NEED_INPUT, false);

		// Collect password for login
		failCounter = 0;
		do {
			System.out.println("Password: ");
			try {
				if (user.getPassword().equals(scanner.nextLine())) {
					user.setAccounts(AccountService.getAccountService().getAllAccounts(user.getId()));
					break;
				} else {
					throw new IncorrectInputException();	
				}	
			} catch (IncorrectInputException iie) {
				clearCMD();
				printFormattedMessage(iie.getMessage(), true);
				failCounter++;
				if (failCounter > 3) {
					return UIState.WELCOME;
				}
			}
		} while (true);	
		clearCMD();
		printFormattedMessage("You have successfully logged in.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();
		
		// Switch the credentials for connection
		ConnectionUtil.setCredentials(user);
		
		if (user.getIsSuper()) {
			return UIState.SUPERUSER_MENU;
		} else {
			return UIState.USER_MENU;
		}
	}

	public UIState viewUserMenu() {	
		clearCMD();
		printFormattedMessage("Hello " + user.getUserName() + ", how can I help you today?\n" 
				+ NEED_INPUT, false);
		
		// Present user menu options
		do {
			System.out.println("1 - View existing account balances");
			System.out.println("2 - Create a new account");
			System.out.println("3 - Delete an empty account");
			System.out.println("4 - Deposit or Withdraw from an account");
			System.out.println("0 - Logout");
			try {		
				switch(Integer.parseInt(scanner.nextLine())) {
					case 1: 
						return UIState.VIEW_BALANCES;
					case 2:
						return UIState.CREATE_ACCOUNT;
					case 3:
						return UIState.DELETE_ACCOUNT;
					case 4:
						return UIState.CREATE_TRANSACTION;
					case 0:
						return UIState.LOGOUT;
					default:
						throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}	
		} while (true);
	}
	
	public UIState viewSuperuserMenu() {
		clearCMD();
		printFormattedMessage("Hello " + user.getUserName() + ", how can I help you today?\n" 
				+ NEED_INPUT, false);
		
		// Present superuser menu options
		do {
			System.out.println("1 - View all users");
			System.out.println("2 - Create a new user");
			System.out.println("3 - Update a user");
			System.out.println("4 - Delete a user");
			System.out.println("0 - Logout");
			try {		
				// Follow through with selected option.
				switch(Integer.parseInt(scanner.nextLine())) {
					case 1: 
						return UIState.VIEW_USERS;
					case 2:
						return UIState.CREATE_USER;
					case 3:
						return UIState.UPDATE_USER;
					case 4:
						return UIState.DELETE_USER;
					case 0:
						return UIState.LOGOUT;
					default:
						throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}	
		} while (true);
	}
	
	public UIState viewUsers() {
		clearCMD();
		printFormattedMessage("Here are all of the active users and superusers.", false);	
		
		// Get all the super/users and display them
		ArrayList<User> users = UserService.getUserService().getAllUsers();
		for (User u : users) {
			System.out.println("\t--- (" + (u.getIsSuper() ? "Superuser" : "User") + ") " + u.getUserName());
		}
		
		System.out.println("\n" + ENTER_ANY_KEY);
		scanner.nextLine();
		return UIState.SUPERUSER_MENU;
	}
	
	public UIState viewBalances() {
		clearCMD();
		printFormattedMessage("Here are all of your active accounts and their balances.", false);	
		
		// Get all the accounts for this user and display them
		NumberFormat formatter = new DecimalFormat("#0.00");     
		for (Account a : user.getAccounts()) {
			System.out.println("\t--- (" + a.getType().toString() + ") " 
					+ a.getName() + " - $" + formatter.format(a.getBalance()));
		}
		
		System.out.println("\n" + ENTER_ANY_KEY);
		scanner.nextLine();
		return UIState.USER_MENU;
	}

	public UIState viewCreateUser() {
		clearCMD();
		printFormattedMessage(NEED_INPUT, false);
		
		// Collect unique username
		int failCounter = 0;
		String userNameInput = null;
		do {
			System.out.println("Username: ");
			try {
				userNameInput = scanner.nextLine();
				if (userNameInput.equals("")) {
					throw new EmptyOrNullInputException();
				}
				if (UserService.getUserService().getUser(userNameInput) != null) {
					clearCMD();
					printFormattedMessage("We're sorry, but that username has already been selected."
							+ "\nPlease choose another.", false);	
				} else {
					break;
				}
			} catch (EmptyOrNullInputException eonie) {
				clearCMD();
				printFormattedMessage(eonie.getMessage(), true);
				failCounter++;
				if (failCounter > 3) {
					return UIState.SUPERUSER_MENU;
				}
			}
		} while (true);
		clearCMD();
		
		// Collect new password
		printFormattedMessage("Username accepted.\n" + NEED_INPUT, false);
		failCounter = 0;
		String passwordInput;
		do {
			System.out.println("Password: ");
			try {
				passwordInput = scanner.nextLine();
				if (passwordInput.equals("")) {
					throw new EmptyOrNullInputException();
				}
				break;
			} catch (EmptyOrNullInputException eonie) {
				clearCMD();
				printFormattedMessage(eonie.getMessage(), true);
				failCounter++;
				if (failCounter > 3) {
					return UIState.SUPERUSER_MENU;
				}
			}
		} while (true);
		
		// Collect isSuper user value
		printFormattedMessage("Password accepted.\n" + NEED_INPUT, false);
		boolean isSuperInput;
		do {
			System.out.println("Superuser?");
			System.out.println("1 - Yes");
			System.out.println("2 - No");
			try {
				switch(Integer.parseInt(scanner.nextLine())) {
					case 1: 
						isSuperInput = true;
						break;
					case 2:
						isSuperInput = false;
						break;
					default:
						throw new InvalidOptionException();
				}
				break;
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}
		} while (true);

		// Register the user in the table
		User u = new User(userNameInput, passwordInput, isSuperInput);
		UserService.getUserService().insertUser(u);
		
		// Register the user connection
		UserService.getUserService().createCredentials(u);
		
		clearCMD();
		printFormattedMessage("You have successfully created a user.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();
		return UIState.SUPERUSER_MENU;
	}
	
	public UIState viewCreateAccount() {
		clearCMD();
		printFormattedMessage("Which account type would you like to create?", false);
		
		// Collect account type
		AccountType type = AccountType.CHECKINGS;
		do {
			System.out.println("1 - " + AccountType.CHECKINGS.toString());
			System.out.println("2 - " + AccountType.SAVINGS.toString());
			System.out.println("3 - " + AccountType.MORTGAGE.toString());
			System.out.println("0 - Menu");
			try {
				switch(Integer.parseInt(scanner.nextLine())) {
					case 1: 
						type = AccountType.CHECKINGS;
						break;
					case 2:
						type = AccountType.SAVINGS;
						break;
					case 3:
						type = AccountType.MORTGAGE;
						break;
					case 0:
						return UIState.USER_MENU;
					default:
						throw new InvalidOptionException();
				}
				break;
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}	
		} while (true);
		
		// Collect the bank account name
		printFormattedMessage("What name would you like to give the account?", false);
		String name;
		do {
			System.out.println("Name: ");
			try {
				name = scanner.nextLine();
				if (name.equals("")) {
					throw new EmptyOrNullInputException();
				}
				break;
			} catch (EmptyOrNullInputException eonie) {
				clearCMD();
				printFormattedMessage(eonie.getMessage(), true);
			}
		} while (true);

		// Insert the new account and pull all the accounts to the cache	
		AccountService.getAccountService().insertAccount(new Account(user.getId(), name, type, 0));
		user.setAccounts(AccountService.getAccountService().getAllAccounts(user.getId()));
		
		printFormattedMessage("You have successfully created an account.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();		
		return UIState.USER_MENU;
	}
	
	public UIState viewDeleteUser() {
		clearCMD();
		printFormattedMessage("Which user would you like to delete?\n"
				+ "Please select the number/id associated with their username.", false);
		
		// Collect index for account to delete
		int selection;
		ArrayList<User> users = UserService.getUserService().getAllUsers();
		User toDelete = null;
		do {
			for (User u : users) {
				if (!u.getIsSuper()) {
					System.out.println("" + u.getId() + " - " + u.getUserName());	
				}
			}
			System.out.println("0 - Return to Menu");
			
			try {
				selection = Integer.parseInt(scanner.nextLine());
				if (selection == 0) {
					return UIState.SUPERUSER_MENU;
				} 
				for (User u : users) {
					if (u.getId() == selection) {
						toDelete = u;
						break;
					}
				}
				if (toDelete != null) {
					break;
				} else {
					throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}
		} while (true);
		
		// Delete all the accounts and then the user
		AccountService.getAccountService().deleteAllAccount(toDelete.getId());
		UserService.getUserService().deleteUser(toDelete.getUserName());

		// Delete the user credentials
		UserService.getUserService().deleteCredentials(toDelete);
		
		printFormattedMessage("You have successfully deleted a user and their accounts.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();
		
		return UIState.SUPERUSER_MENU;
	}

	public UIState viewDeleteAccount() {
		clearCMD();
		printFormattedMessage("Which account would you like to delete?\n"
				+ "Don't forget to withdraw your balance beforehand!", false);
		
		NumberFormat formatter = new DecimalFormat("#0.00");
		// Collect index for account to delete
		int selection;
		do {
			int i = 1;
			for (Account a : user.getAccounts()) {
				System.out.println("" + i + " - (" + a.getType().toString() + ") " 
						+ a.getName() + " - $" + formatter.format(a.getBalance()));
				i++;
			}
			System.out.println("0 - Return to Menu");
			
			try {
				selection = Integer.parseInt(scanner.nextLine());
				if (selection > 0 && selection <= user.getAccounts().size()) {
					if (user.getAccounts().get(selection-1).getBalance() != 0) {
						throw new AccountNotEmptyException();
					}
					break;
				} else if (selection == 0) {
					return UIState.USER_MENU;
				} else {
					throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (AccountNotEmptyException | InvalidOptionException e) {
				clearCMD();
				printFormattedMessage(e.getMessage(), true);
			}
		} while (true);
		
		// Remove the account from cache AND from table
		Account toRemove = user.getAccounts().remove(selection-1);
		AccountService.getAccountService().deleteAccount(toRemove.getId());

		printFormattedMessage("You have successfully deleted an account.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();		
		return UIState.USER_MENU;
	}
	
	public UIState viewUpdateUser() {
		clearCMD();
		printFormattedMessage("Which user would you like to update?", false);
		
		// Collect index for account to delete
		int selection;
		ArrayList<User> users = UserService.getUserService().getAllUsers();
		User toUpdate = null;
		do {
			for (User u : users) {
				if (!u.getIsSuper()) {
					System.out.println("" + u.getId() + " - " + u.getUserName());	
				}
			}
			System.out.println("0 - Return to Menu");
			
			try {
				selection = Integer.parseInt(scanner.nextLine());
				if (selection == 0) {
					return UIState.SUPERUSER_MENU;
				} 
				for (User u : users) {
					if (u.getId() == selection) {
						toUpdate = u;
						break;
					}
				}
				if (toUpdate != null) {
					break;
				} else {
					throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);
			}
		} while (true);
		clearCMD();
		printFormattedMessage("What would you like to update?", false);
		
		// Collect a selection for what to update
		String input;
		do {
			try {
				System.out.println("1 - Password");
				System.out.println("0 - Return to Menu");
				selection = Integer.parseInt(scanner.nextLine());
				switch (selection) {
					case 1:
						clearCMD();
						printFormattedMessage(NEED_INPUT, false);
						System.out.println("Password: ");
						input = scanner.nextLine();
						if (input.equals("")) {
							throw new EmptyOrNullInputException();
						} else {
							toUpdate.setPassword(input);
						}
						break;
					case 0:
						return UIState.SUPERUSER_MENU;
					default:
						throw new InvalidOptionException();
				}
				break;
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException | EmptyOrNullInputException e) {
				clearCMD();
				printFormattedMessage(e.getMessage(), true);
			}
		} while (true);
		
		// Update the user account
		UserService.getUserService().updateUser(toUpdate);
		
		// Update the user credentials
		UserService.getUserService().deleteCredentials(toUpdate);
		UserService.getUserService().createCredentials(toUpdate);
		
		clearCMD();
		printFormattedMessage("You have successfully updated a user.\n" + ENTER_ANY_KEY, false);
		scanner.hasNextLine();
		return UIState.SUPERUSER_MENU;
	}
	
	public UIState viewCreateTransaction() {
		clearCMD();
		if (user.getAccounts().size() == 0) {
			printFormattedMessage("You have no active accounts.\n"
					+ "Please create one before making a transaction.\n"
					+ ENTER_ANY_KEY, false);
			scanner.nextLine();
			return UIState.USER_MENU;
		}
		printFormattedMessage("Please select an account to deposit or withdraw from.", false);
		
		NumberFormat formatter = new DecimalFormat("#0.00");
		// Collect index for account to update
		int selection;
		do {
			int i = 1;
			for (Account a : user.getAccounts()) {
				System.out.println("" + i + " - (" + a.getType().toString() + ") " 
						+ a.getName() + " - $" + formatter.format(a.getBalance()));
				i++;
			}
			System.out.println("0 - Return to Menu");
			
			try {
				selection = Integer.parseInt(scanner.nextLine());
				if (selection > 0 && selection <= user.getAccounts().size()) {
					break;
				} else if (selection == 0) {
					return UIState.USER_MENU;
				} else {
					throw new InvalidOptionException();
				}
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException ioe) {
				clearCMD();
				printFormattedMessage(ioe.getMessage(), true);			}
		} while (true);	
		printFormattedMessage("What would you like to do with this account?", false);
		
		Account account = user.getAccounts().get(selection-1);
		double result;
		double amount;
		do {
			System.out.println("1 - Deposit");
			System.out.println("2 - Withdrawl");
			System.out.println("0 - Return to Menu");
			clearCMD();
			
			try {
				selection = Integer.parseInt(scanner.nextLine());
				switch(selection) {
					case 1: 
						clearCMD();
						printFormattedMessage("How much would you like to deposit?", false);
						System.out.println("Amount: ");
						// TODO - null not allowed.
						amount = Double.parseDouble(scanner.nextLine());
						result = account.getBalance() + amount;
						if (amount <= 0) {
							throw new NegativeDepositException();
						}
						break;
					case 2:
						clearCMD();
						printFormattedMessage("How much would you like to withdraw?", false);		
						System.out.println("Amount: ");
						
						amount = Double.parseDouble(scanner.nextLine());
						result = account.getBalance() - amount;
						if (result < 0.0) {
							throw new OverdrawException();
						} else if (amount < 0) {
							throw new NegativeWithdrawlException();
						}
						break;
					case 0:
						return UIState.USER_MENU;
					default:
						throw new InvalidOptionException();
				}
				break;
			} catch (NumberFormatException nfe) {
				clearCMD();
				printFormattedMessage(NUMBER_FORMAT_EXCEPTION, true);
			} catch (InvalidOptionException | OverdrawException 
						| NegativeDepositException | NegativeWithdrawlException e) {
				clearCMD();
				printFormattedMessage(e.getMessage(), true);
			}
		} while (true);
		
		account.setBalance(result);
		AccountService.getAccountService().updateAccount(account);
		
		printFormattedMessage("You have successfully made a transaction.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();
		return UIState.USER_MENU;
	}
	
	public UIState viewLogout() {
		// Reset credentials for admin connection
		ConnectionUtil.defaultCredentials();
		
		printFormattedMessage("You have successfully logged out.\n" + ENTER_ANY_KEY, false);
		scanner.nextLine();
		return UIState.WELCOME;
	}
	
	// Helper method for CMD
	private void clearCMD() {
//		try {
//			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // runs cls command in cmd
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
	}

	// Helper method for printing messages
	private void printFormattedMessage(String message, boolean isException) {
		if (isException) {
			System.out.println("********************************************************\n"
					+ message
					+ "\n********************************************************");
		} else {
			System.out.println("========================================================\n"
					+ message
					+ "\n========================================================");
		}
	}
}
