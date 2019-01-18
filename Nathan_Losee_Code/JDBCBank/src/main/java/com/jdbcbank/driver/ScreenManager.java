package com.jdbcbank.driver;

public class ScreenManager {
	public static final byte HOME_SCREEN = 0, NEW_USER_SCREEN = 1, LOGIN_SCREEN = 2, DELETE_SCREEN = 3, ACCOUNT_SCREEN = 4,
			VIEW_SCREEN = 5, DEPOSIT_SCREEN = 6, WITHDRAWAL_SCREEN = 7, HISTORY_SCREEN = 8,
			TRANSFER_SCREEN = 9, CREATION_SCREEN = 10, CLOSING_SCREEN = 11,
			SUPER_SCREEN = 12, USER_SCREEN = 13;
	private static String SCREEN_UPPER =
			"\n================================================================================================================" +
			"\n################################################################################################################\n";
	private static String SCREEN_LOWER =
			"################################################################################################################" +
			"\n================================================================================================================\n";
	
	private static String[] screens = {
			//0 = HOME_SCREEN
			"\n" +
			"Welcome to JDBC Online Banking! \n" +
			"Please select from the options below to get started. \n" +
			String.format("%-40s", "1. Create a new user account") + String.format("%-40s", "3. Delete an existing user account") + "\n" +
			String.format("%-40s", "2. Login to an existing user account") + String.format("%-40s", "4. Exit application") + "\n" +
			"\n",

			//NEW_USER_SCREEN
			"\n" +
			"Hello new customer! \n" +
			"\n" +
			"Please create a new username and password. These cannot exceed more than 20 character each. \n" +
			"Enter 'back' to return to the home menu. \n" +
			"\n",

			//LOGIN_SCREEN
			"\n" +
			"Welcome back! \n" +
			"\n" +
			"Please enter your username and password. \n" +
			"Enter 'back' to return to the home menu. \n" +
			"\n",

			//DELETE_SCREEN
			"\n" +
			"Enter the username and password of the user account you would like deleted. \n" +
			"NOTICE: ALL BANK ACCOUNTS OF THE SELECTED USER ACCOUNT MUST BE EMPTY FOR DELETION TO SUCCEED. \n" +
			"Enter 'back' to return to the home menu. \n" +
			"\n" +
			"\n",

			//ACCOUNT_SCREEN
			"Hello, [Username]! \n" +
			"Here is the list of available options for you to choose from: \n" +
			String.format("%-40s", "1. View account balance") + String.format("%-40s", "5. Transfer funds between accounts") + "\n" +
			String.format("%-40s", "2. Make account deposit") + String.format("%-40s", "6. Create new account") + "\n" +
			String.format("%-40s", "3. Make account withdrawal") + String.format("%-40s", "7. Close existing account") + "\n" +
			String.format("%-40s", "4. View account transaction history") + String.format("%-40s", "8. Logout") + "\n",

			//VIEW_SCREEN
			"\n" +
			"\n" +
			"[AccountID*, AccountType*, AccountBalance*] \n" +
			"\n" +
			"Enter 'back' to return to the account menu." +
			"\n" +
			"\n",

			//DEPOSIT_SCREEN
			"\n" +
			"\n" +
			"Choose a bank account to deposit funds into and enter the amount of funds to deposit. \n" +
			"[AccountID*, AccountType*, AccountBalance*] \n" +
			"Enter 'back' to return to the account menu. \n" +
			"\n" +
			"\n",

			//WITHDRAWAL_SCREEN
			"\n" +
			"\n" +
			"Choose a bank account to withdraw funds from and enter the amount of funds to withdraw. \n" +
			"[AccountID*, AccountType*, AccountBalance*] \n" +
			"Enter 'back' to return to the account menu. \n" +
			"\n" +
			"\n",

			//HISTORY_SCREEN
			"\n" +
			"Choose a bank account you would like to view the transaction history of. \n" +
			"[AccountID*, AccountType*, AccountBalance*] \n" +
			"Enter 'back' to return to the account menu. \n" +
			"\n" +
			"\n",

			//TRANSFER_SCREEN
			"\n" +
			"Choose a bank account to withdraw funds from, a bank account to deposit to, and enter an amount to transfer. \n" +
			"\n" +
			"[AccountID*, AccountType*, AccountBalance*] \n" +
			"\n" +
			"Enter 'back' to return to the account menu. \n" +
			"\n",

			//CREATION_SCREEN
			"\n" +
			"Enter the type of bank account you would like to create from the options below, then enter the initial deposit amount. \n" +
			"Checking \n" +
			"Savings \n" +
			"Enter 'back' to return to the account menu. \n" +
			"\n",

			//CLOSING_SCREEN
			"\n" +
			"Choose the bank account you would like to close. \n" +
			"NOTICE: THE SELECTED BANK ACCOUNT MUST BE EMPTY FOR CLOSING TO SUCCEED. \n" +
			"[AccountID*, AccountType*, AccountBalance*] \n" +
			"Enter 'back' to return to the account menu. \n" +
			"\n" +
			"\n",

			//SUPER_SCREEN
			"\n" +
			"Greetings Administrator. \n" +
			"Choose an option from below. \n" +
			"1. Edit existing user accounts \n" +
			"2. Logout \n" +
			"\n",
			
			//USER_SCREEN
			"\n" +
			"[UserID*, Username*, Password*] \n" +
			"\n" +
			"Enter 'c username password' to create a new user account. \n" +
			"Enter 'r username password' to login to an existing user account. \n" +
			"Enter 'u username oldPassword newPassword' to change the password for an existing user account. \n" +
			"Enter 'd username password' to delete an existing user account. \n" +
			"Enter 'back' to return to the administrator menu. \n"
	};
	
	public static String getScreen(int screenNum) {
		return SCREEN_UPPER + screens[screenNum] + SCREEN_LOWER;
	}
	
	public static void updateScreen(int screenNumber, String information) {
		switch (screenNumber) {
		case ACCOUNT_SCREEN:
			screens[ACCOUNT_SCREEN] =
				String.format("Hello, %s! \n", information) +
				"Here is the list of available bank account options for you to choose from: \n" +
				String.format("%-40s", "1. View account balance") + String.format("%-40s", "5. Transfer funds between accounts") + "\n" +
				String.format("%-40s", "2. Make account deposit") + String.format("%-40s", "6. Create new account") + "\n" +
				String.format("%-40s", "3. Make account withdrawal") + String.format("%-40s", "7. Close existing account") + "\n" +
				String.format("%-40s", "4. View account transaction history") + String.format("%-40s", "8. Logout") + "\n";
			break;
		case VIEW_SCREEN:
			screens[VIEW_SCREEN] =
				"\n" +
				"\n" +
				information +
				"\n" +
				"Enter 'back' to return to the account menu." +
				"\n" +
				"\n";
			break;
		case DEPOSIT_SCREEN:
			screens[DEPOSIT_SCREEN] =
				"\n" +
				"Choose a bank account to deposit funds into and enter the amount of funds to deposit. \n" +
				"\n" +
				information +
				"\n" +
				"Enter 'back' to return to the account menu. \n" +
				"\n";
			break;
		case WITHDRAWAL_SCREEN:
			screens[WITHDRAWAL_SCREEN] =
				"\n" +
				"Choose a bank account to withdraw funds from and enter the amount of funds to withdraw. \n" +
				"\n" +
				information +
				"\n" +
				"Enter 'back' to return to the account menu. \n" +
				"\n";
			break;
		case HISTORY_SCREEN:
			screens[HISTORY_SCREEN] =
				"\n" +
				"Choose a bank account you would like to view the transaction history of. \n" +
				"\n" +
				information +
				"\n" +
				"Enter 'back' to return to the account menu. \n" +
				"\n";
			break;
		case TRANSFER_SCREEN:
			screens[TRANSFER_SCREEN] =
				"\n" +
				"Choose a bank account to withdraw funds from, a bank account to deposit to, and enter an amount to transfer. \n" +
				"\n" +
				information +
				"\n" +
				"Enter 'back' to return to the account menu. \n" +
				"\n";
			break;
		case CLOSING_SCREEN:
			screens[CLOSING_SCREEN] =
				"Choose the bank account you would like to close. \n" +
				"NOTICE: THE SELECTED BANK ACCOUNT MUST BE EMPTY FOR CLOSING TO SUCCEED. \n" +
				"\n" +
				information +
				"\n" +
				"Enter 'back' to return to the account menu. \n" +
				"\n";
			break;
		case USER_SCREEN:
			screens[USER_SCREEN] =
				"\n" +
				information +
				"\n" +
				"Enter 'c username password' to create a new user account. \n" +
				"Enter 'r username password' to login to an existing user account. \n" +
				"Enter 'u username oldPassword newPassword' to change the password for an existing user account. \n" +
				"Enter 'd username password' to delete an existing user account. \n" +
				"Enter 'back' to return to the administrator menu. \n";
			break;
		}
	}
}
