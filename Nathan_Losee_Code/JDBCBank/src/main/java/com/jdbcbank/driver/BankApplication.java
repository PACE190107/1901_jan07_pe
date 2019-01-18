package com.jdbcbank.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.jdbcbank.models.Account;
import com.jdbcbank.models.Transaction;
import com.jdbcbank.models.User;
import com.jdbcbank.services.AccountServices;
import com.jdbcbank.services.TransactionServices;
import com.jdbcbank.services.UserServices;
import com.jdbcbank.util.BankErrors;
import com.jdbcbank.util.ConnectionManager;

public class BankApplication {
	private final static Logger log = Logger.getLogger(BankApplication.class);
	
	private static Scanner scanner = new Scanner(System.in);

	private static boolean isSuperUser;
	public static User currUser;
	
	private static int currentScreen = ScreenManager.HOME_SCREEN;
	private static boolean isReturning;
	
	
	private static void runBankApplication() throws Exception {
		while (currentScreen != -1) {
			try {
				log.info(ScreenManager.getScreen(currentScreen));
				primeForInput();
				processInput();
			} catch (BankErrors.ExistingUsernamePasswordException e) {
				log.error("Username has already been taken. \n");
			} catch (BankErrors.InvalidUsernamePasswordException e) {
				log.error("Username doesn't exist or Password was incorrect. \n");
			} catch (BankErrors.MalformedUsernamePasswordException e) {
				log.error("The username and password must be or be between between 1 and 20 characters each. \n");
			} catch (BankErrors.InvalidTypeException e) {
				log.error("Account type was invalid. \n");
			} catch (BankErrors.InvalidAccountIDException e) {
				log.error("AccountID was invalid. \n");
			} catch (BankErrors.OverdraftException e) {
				log.error("Cannot withdraw more funds than the remaining account balance. \n");
			} catch (BankErrors.InvalidAmountException e) {
				log.error("Cannot enter an amount less than or equal to 0.00. \n");
			} catch (BankErrors.NonEmptyAccountException e) {
				if (currentScreen == ScreenManager.CLOSING_SCREEN)
					log.error("Cannot close an account still holding funds. \n");
				else
					log.error("Cannot delete a user account with bank accounts still holding funds. \n");
			} catch (SQLException e) {
				log.error("Invalid SQL command given. \n");
				e.printStackTrace();
				break;
			}
		}
	}
	private static void primeForInput() {
		switch (currentScreen) {
		case ScreenManager.HOME_SCREEN:
		case ScreenManager.SUPER_SCREEN:
			System.out.print("Option: ");
			break;
		case ScreenManager.NEW_USER_SCREEN:
		case ScreenManager.LOGIN_SCREEN:
		case ScreenManager.DELETE_SCREEN:
			System.out.print("Username Password: ");
			break;
		case ScreenManager.ACCOUNT_SCREEN:
			System.out.print("Option: ");
			break;
		case ScreenManager.VIEW_SCREEN:
			System.out.print("");
			break;
		case ScreenManager.DEPOSIT_SCREEN:
		case ScreenManager.WITHDRAWAL_SCREEN:
			System.out.print("AccountID Amount: ");
			break;
		case ScreenManager.HISTORY_SCREEN:
		case ScreenManager.CLOSING_SCREEN:
			System.out.print("AccountID: ");
			break;
		case ScreenManager.TRANSFER_SCREEN:
			System.out.print("AccountID_FROM AccountID_TO Amount: ");
			break;
		case ScreenManager.CREATION_SCREEN:
			System.out.print("AccountType Amount: ");
			break;
		case ScreenManager.USER_SCREEN:
			System.out.print("CommandLetter Username Password: ");
			break;
		}
	}
	private static void processInput() throws SQLException {
		String[] input = acquireInput();
		switch (currentScreen) {
		
		case ScreenManager.HOME_SCREEN:
			switch (getInt(input[0])) {
			case 1:
				log.info("Creating new user account. \n");
				currentScreen = ScreenManager.NEW_USER_SCREEN;
				break;
			case 2:
				log.info("Logging into existing user account. \n");
				currentScreen = ScreenManager.LOGIN_SCREEN;
				break;
			case 3:
				log.info("Deleting existing user account. \n");
				currentScreen = ScreenManager.DELETE_SCREEN;
				break;
			case 4:
				log.info("Exiting application. \n");
				currentScreen = -1;
				break;
			default:
				log.info("Invalid selection. \n");
				break;
			}
			break;
			
		case ScreenManager.NEW_USER_SCREEN:
			if (testIfReturning(ScreenManager.HOME_SCREEN))
				break;
			if (input.length < 2) {
				log.info("Please input a username and a password separated by a space. \n");
				break;
			}
			if (input[0].length() <= 20 && input[1].length() <= 20 &&
				UserServices.createUser(input[0], input[1])) {
				log.info("Successfully created new user account. \n");
				currentScreen = ScreenManager.HOME_SCREEN;
			} else
				throw new BankErrors.MalformedUsernamePasswordException();
			break;
			
		case ScreenManager.LOGIN_SCREEN:
			if (testIfReturning(ScreenManager.HOME_SCREEN))
				break;
			if (input.length < 2) {
				log.info("Please input a username and a password separated by a space. \n");
				break;
			}
			if (testForSuperUser(input[0], input[1])) {
				log.info("Successfully logged into administrator account. \n");
				
				List<User> users = UserServices.readUsers();
				String userString = "";
				for (User user : users)
					userString += user + "\n";
				
				ScreenManager.updateScreen(ScreenManager.USER_SCREEN, userString);
				
				currentScreen = ScreenManager.SUPER_SCREEN;
			}
			else if (UserServices.readUser(input[0], input[1]) != null) {
				log.info("Successfully logged into user account. \n");

				ConnectionManager.setJDBCConnection(input[0], input[1]);

				currUser = new User();
				currUser.setUserID(UserServices.readUser(input[0], input[1]).getUserID());
				currUser.setUsername(input[0]);
				currUser.setPassword(input[1]);

				ScreenManager.updateScreen(ScreenManager.ACCOUNT_SCREEN, currUser.getUsername());
				
				currentScreen = ScreenManager.ACCOUNT_SCREEN;
			}
			break;
			
		case ScreenManager.DELETE_SCREEN:
			if (testIfReturning(ScreenManager.HOME_SCREEN))
				break;
			if (input.length < 2) {
				log.info("Please input a username and a password separated by a space. \n");
				break;
			}
			if (UserServices.deleteUser(input[0], input[1])) {
				log.info("User account successfully deleted. \n");
				currentScreen = ScreenManager.HOME_SCREEN;
			}
			break;
			
		case ScreenManager.ACCOUNT_SCREEN:
			switch (getInt(input[0])) {
			case 1:
				log.info("Viewing current account balances. \n");
				currentScreen = ScreenManager.VIEW_SCREEN;
				
				List<Account> accounts = AccountServices.readAccounts(currUser.getUserID());
				String accountString = "";
				for (Account account : accounts)
					accountString += account + "\n";
				ScreenManager.updateScreen(ScreenManager.DEPOSIT_SCREEN, accountString);
				break;
			case 2:
				log.info("Requesting a deposit. \n");
				currentScreen = ScreenManager.DEPOSIT_SCREEN;
				
				accounts = AccountServices.readAccounts(currUser.getUserID());
				accountString = "";
				for (Account account : accounts)
					accountString += account + "\n";
				ScreenManager.updateScreen(ScreenManager.DEPOSIT_SCREEN, accountString);
				break;
			case 3:
				log.info("Requesting a withdrawal. \n");
				currentScreen = ScreenManager.WITHDRAWAL_SCREEN;
				
				accounts = AccountServices.readAccounts(currUser.getUserID());
				accountString = "";
				for (Account account : accounts)
					accountString += account + "\n";
				ScreenManager.updateScreen(ScreenManager.WITHDRAWAL_SCREEN, accountString);
				break;
			case 4:
				log.info("Viewing account transaction history. \n");
				currentScreen = ScreenManager.HISTORY_SCREEN;
				
				accounts = AccountServices.readAccounts(currUser.getUserID());
				accountString = "";
				for (Account account : accounts)
					accountString += account + "\n";
				ScreenManager.updateScreen(ScreenManager.HISTORY_SCREEN, accountString);
				break;
			case 5:
				log.info("Requesting a transfer. \n");
				currentScreen = ScreenManager.TRANSFER_SCREEN;
				
				accounts = AccountServices.readAccounts(currUser.getUserID());
				accountString = "";
				for (Account account : accounts)
					accountString += account + "\n";
				ScreenManager.updateScreen(ScreenManager.TRANSFER_SCREEN, accountString);
				break;
			case 6:
				log.info("Creating a new bank account. \n");
				currentScreen = ScreenManager.CREATION_SCREEN;
				break;
			case 7:
				log.info("Closing an existing bank account. \n");
				currentScreen = ScreenManager.CLOSING_SCREEN;
				
				accounts = AccountServices.readAccounts(currUser.getUserID());
				accountString = "";
				for (Account account : accounts)
					accountString += account + "\n";
				ScreenManager.updateScreen(ScreenManager.CLOSING_SCREEN, accountString);
				break;
			case 8:
				log.info("Logging out of user account. \n");
				logout();
				break;
			default:
				log.info("Invalid selection. \n");
				break;
			}
			break;
			
		case ScreenManager.VIEW_SCREEN:
			testIfReturning(ScreenManager.ACCOUNT_SCREEN);
			break;
			
		case ScreenManager.DEPOSIT_SCREEN:
			if (testIfReturning(ScreenManager.ACCOUNT_SCREEN))
				break;
			if (input.length < 2) {
				log.info("Please input an account ID and an amount of funds separated by a space. \n");
				break;
			}
			if (AccountServices.updateAccount(getInt(input[0]), getAmount(input[1]), true)) {
				log.info("Successfully deposited the specified amount into the specified account. \n");
				currentScreen = ScreenManager.ACCOUNT_SCREEN;
			}
			break;
			
		case ScreenManager.WITHDRAWAL_SCREEN:
			if (testIfReturning(ScreenManager.ACCOUNT_SCREEN))
				break;
			if (input.length < 2) {
				log.info("Please input an account ID and an amount of funds separated by a space. \n");
				break;
			}
			if (AccountServices.updateAccount(getInt(input[0]), getAmount(input[1]), false)) {
				log.info("Successfully withdrew the specified amount from the specified account. \n");
				currentScreen = ScreenManager.ACCOUNT_SCREEN;
			}
			break;
			
		case ScreenManager.HISTORY_SCREEN:
			if (testIfReturning(ScreenManager.ACCOUNT_SCREEN))
				break;
			if (input.length < 1) {
				log.info("Please input an account ID. \n");
				break;
			}
			if (AccountServices.readAccount(getInt(input[0])) != null) {
				log.info("Viewing history for scpecific account. \n");
				currentScreen = ScreenManager.VIEW_SCREEN;
				
				Map<Timestamp, Transaction> transactions = TransactionServices.readTransactions(
						AccountServices.readAccount(getInt(input[0])).getAccountID());
				String transactionString = "";
				for (Timestamp timestamp : transactions.keySet())
					transactionString += timestamp + " === " + transactions.get(timestamp) + "\n";
				ScreenManager.updateScreen(ScreenManager.VIEW_SCREEN, transactionString);
			}
			break;
			
		case ScreenManager.TRANSFER_SCREEN:
			if (testIfReturning(ScreenManager.ACCOUNT_SCREEN))
				break;
			if (input.length < 3) {
				log.info("Please input two account IDs and an amount of funds, each separated by a space. \n");
				break;
			}
			if (AccountServices.readAccount(getInt(input[0])) != null &&
					AccountServices.readAccount(getInt(input[1])) != null) {
				if (AccountServices.updateAccount(getInt(input[0]), getAmount(input[2]), false)) {
					AccountServices.updateAccount(getInt(input[1]), getAmount(input[2]), true);
					log.info("Successfully transferred the specified amount between the specified accounts. \n");
					currentScreen = ScreenManager.ACCOUNT_SCREEN;
				} else
					log.info("Invalid amount. \n");
			}
			break;
			
		case ScreenManager.CREATION_SCREEN:
			if (testIfReturning(ScreenManager.ACCOUNT_SCREEN))
				break;
			if (input.length < 2) {
				log.info("Please input an account type and an initial deposit amount. \n");
				break;
			}
			if (AccountServices.createAccount(currUser.getUserID(), input[0], getAmount(input[1]))) {
				log.info("Successfully created new bank account. \n");
				currentScreen = ScreenManager.ACCOUNT_SCREEN;
			}
			break;
			
		case ScreenManager.CLOSING_SCREEN:
			if (testIfReturning(ScreenManager.ACCOUNT_SCREEN))
				break;
			if (input.length < 1) {
				log.info("Please input an account ID. \n");
				break;
			}
			if (AccountServices.deleteAccount(getInt(input[0]))) {
				log.info("Bank account successfully closed. \n");
				currentScreen = ScreenManager.ACCOUNT_SCREEN;
			}
			break;
			
		case ScreenManager.SUPER_SCREEN:
			switch (getInt(input[0])) {
			case 1:
				log.info("Viewing list of existing users. \n");
				currentScreen = ScreenManager.USER_SCREEN;
				break;
			case 2:
				isSuperUser = false;
				log.info("Logging out of administrator account. \n");
				currentScreen = ScreenManager.HOME_SCREEN;
				break;
			default:
				System.out.print("Invalid selection. \n");
				break;
			}
			break;
			
		case ScreenManager.USER_SCREEN:
			testIfReturning(ScreenManager.SUPER_SCREEN);
			if (input.length < 3) {
				log.info("Input a command letter followed by the username and password of the account you'd like to edit. \n");
				break;
			}
			switch (input[0]) {
			case "c":
				if (UserServices.createUser(input[1], input[2])) {
					log.info("Created user.");
					
					List<User> users = UserServices.readUsers();
					String userString = "";
					for (User user : users)
						userString += user + "\n";
					ScreenManager.updateScreen(ScreenManager.USER_SCREEN, userString);
					
				}
				break;
			case "r":
				if (UserServices.readUser(input[1], input[2]) != null) {
					log.info("Logging into user account. \n");
					currentScreen = ScreenManager.ACCOUNT_SCREEN;
					ConnectionManager.setJDBCConnection(input[1], input[2]);
					
					currUser = new User();
					currUser.setUserID(UserServices.readUser(input[1], input[2]).getUserID());
					currUser.setUsername(input[1]);
					currUser.setPassword(input[2]);

					ScreenManager.updateScreen(ScreenManager.ACCOUNT_SCREEN, currUser.getUsername());
					
				}
				break;
			case "u":
				if (UserServices.updateUser(input[1], input[2], input[3])) {
					log.info("Updated user.");
					
					List<User> users = UserServices.readUsers();
					String userString = "";
					for (User user : users)
						userString += user + "\n";
					ScreenManager.updateScreen(ScreenManager.USER_SCREEN, userString);
				}
				break;
			case "d":
				if (UserServices.deleteUser(input[1], input[2])) {
					log.info("Deleted user. \n");
					
					List<User> users = UserServices.readUsers();
					String userString = "";
					for (User user : users)
						userString += user + "\n";
					ScreenManager.updateScreen(ScreenManager.USER_SCREEN, userString);
				}
				break;
			case "back":
				log.info("Returning to administrator menu. \n");
				currentScreen = ScreenManager.USER_SCREEN;
				break;
			default:
				log.info("Incorrect command entered. \n");
				break;
			}
			break;
			
		}
	}
	
	private static String[] acquireInput() {
		String input = scanner.nextLine().trim();
		log.info(input);
		if (input.contentEquals("back")) {
			isReturning = true;
		}
		return input.split("[ ]+");
	}
	private static int getInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			log.error("Could not read the input as a number. \n");
		}
		return -1;
	}
	private static double getAmount(String input) throws SQLException {
		try {
			input = input.trim();
			if (input.charAt(0) == '$' || input.charAt(input.length() - 1) == '$')
				input = input.replace("$", "");
			double amount = Double.parseDouble(input);
			if (input.split(".").length > 1 && input.split(".")[1].length() > 2)
				throw new BankErrors.MalformedAmountException();
			if (amount <= 0.0)
				throw new BankErrors.InvalidAmountException();
			return Double.parseDouble(input);
		} catch (NumberFormatException nfe) {
			log.error("Could not read the input as a number. \n");
		}
		return 0.0;
	}
	
	private static boolean testForSuperUser(String username, String password) {
		try {
			File file = new File("src\\main\\resources\\jdbcbank.properties");
			FileInputStream fis = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fis);
			fis.close();
			
			if (username.equals(properties.getProperty("username")) &&
				password.equals(properties.getProperty("password"))) {
				isSuperUser = true;
				return true;
			}
			
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	private static boolean testIfReturning(byte returnScreen) {
		if (isReturning) {
			isReturning = false;
			log.info("Returning to previous menu. \n");
			currentScreen = returnScreen;
			return true;
		}
		return false;
	}
	
	private static void logout() {
		ConnectionManager.setJDBCConnection("BankAdmin", "bankadmin");
		if (isSuperUser)
			currentScreen = ScreenManager.SUPER_SCREEN;
		else
			currentScreen = ScreenManager.HOME_SCREEN;
	}
	
	public static void main(String[] args) throws Exception {
		runBankApplication();
	}
}
