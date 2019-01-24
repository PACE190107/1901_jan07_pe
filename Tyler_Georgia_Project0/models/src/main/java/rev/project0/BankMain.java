package rev.project0;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rev.project0.models.Account;
import rev.project0.models.Transaction;
import rev.project0.models.User;
import rev.project0.services.BankService;

public class BankMain {
	private static BankService bankService = BankService.getBankService();
	private static int superUserId;
	private static Account currentAccount;
	private static User userAccount;
	private static Integer superUserUserChoice = null;
	private static Scanner userIn = new Scanner(System.in);
	
	static {
		User superUser = new User("SuperUser", "SuperPass");
		if(!bankService.checkForUserByUsername("SuperUser"))
			bankService.insertUser(superUser);
		
		superUser = bankService.getUser("SuperUser", "SuperPass");
		superUserId = superUser.getUserId();
	}
	
	public static void main(String[] args) {
		System.out.println("\n\tWelcome to TGA Banking\n");
		boolean exitProgram = false;
		while(!exitProgram) {
			createConsolePartition();
			//Prompt User to Login or Create a New Account
			
			System.out.println("Enter a Number to Choose the Corresponding Action:");
			System.out.println("\t 1: Login");
			System.out.println("\t 2: Register");   //Create New User 
			System.out.println("\t 3: Exit");
			System.out.print("Choice:");
			String userChoice = userIn.nextLine();
			userChoice = userChoice.trim();
			
			switch(userChoice) {
				case "1":
					createConsolePartition();
					System.out.println("Login Selected");
					handleLogin();
					break;
					
				case "2":
					createConsolePartition();
					System.out.println("Create New User Selected");
					handleUserCreation();
					break;
					
				case "3": 
					createConsolePartition();
					System.out.println("Closing Application");
					System.out.println("Have A Great Day!!");
					exitProgram = true;
					break;
				
				default: System.out.println("Invalid Choice - Try Again \n");
				
			}
		}
		
	}
	
	private static void createConsolePartition() {
		System.out.println("\n=========================================================================================\n");
	}
	private static void handleLogin() {
		//handle Login from main
		
		boolean keepTrying = true;
		boolean validUsername = false;
		String userUsername = null;
		while(keepTrying && !validUsername) {
			System.out.print("Username: ");
			userUsername = userIn.nextLine();
			validUsername = bankService.checkForUserByUsername(userUsername);
			if(!validUsername) {
				System.out.println("Invalid Username");
				System.out.println("Try Again?");
				System.out.println("\t 1: yes");
				System.out.println("\t 2: no");
				String userChoice = userIn.nextLine();
				userChoice = userChoice.trim();
				switch(userChoice) {
					case "1":
						System.out.println("NOTE: Username and Password are CASE SENSITIVE.");
						break;
						
					case "2":
						 keepTrying = false;
						break;
						
					default: System.out.println("Invalid Choice - Try Again");
				}
			}
		}
		int attemptCounter = 0;
		while(validUsername) {
			
			System.out.print("Password: ");
			String password = userIn.nextLine();
			userAccount = bankService.getUser(userUsername, password);
			if(userAccount == null) {
				System.out.println("Invalid Password - Try Again");
			}else {
				System.out.println("Logging In");
				if(userAccount.getUserId() == superUserId) {
					accessSuperUser();
					validUsername = false; //exits while loop - not actually invalid username
					//TODO: accessSuperUser
				}else {
					accessUser();
					validUsername = false; //exits while loop - not actually invalid username
				}
			}
			
			if(attemptCounter > 5) {
				System.out.println("No Attempts Remain. Exiting");
				validUsername = false;
			}
			attemptCounter++;
		}
		
	}
	
	private static void handleUserCreation() {
		
		//handle User Creation
		String usernameChoice = null;
		boolean validUsername = false;
		boolean keepTrying = true;
		while(!validUsername && keepTrying) {
			
			System.out.println("Enter a UserName:");
			usernameChoice = userIn.nextLine();
			validUsername = !bankService.checkForUserByUsername(usernameChoice);
	
			if(!validUsername) {
				System.out.println("Invalid UserName");
				boolean validTryAgainChoice = false;
				while(!validTryAgainChoice) {
					System.out.println("Try Again?");
					System.out.println("\t 1: yes");
					System.out.println("\t 2: no");
					String tryAgainChoice = userIn.nextLine();
					tryAgainChoice = tryAgainChoice.trim();
					switch(tryAgainChoice) {
					case "1":
						validTryAgainChoice = true;
						break;
					case "2": 
						validTryAgainChoice = true;
						keepTrying = false;
						System.out.println("Returning to main menu");
						break;
					default: 
						System.out.println("Invalid Choice - Try Again");
					}
				}
				
				
			}
		}
		
		if(keepTrying && validUsername) {
			boolean validPassword = false;
			
			while(!validPassword) {
				System.out.println("Enter a Password with at least 5 characters:");
				String password = userIn.nextLine();
				
				if(password.length() >= 5) {
					validPassword = true;
					User newUser = new User(usernameChoice, password);
					bankService.insertUser(newUser);
					System.out.println("User Account Created. \nNOTE: Username and Password are case sensitive!\n");
					
				}else {
					System.out.println("Invalid Entry - Try Again");
				}
			}
		}
		
		//No Return 
	}
	
	
	private static void accessSuperUser() {
		createConsolePartition();
		System.out.println("Welcome "+ userAccount.getUserName());
		System.out.println("What would you like to do today?");
		boolean keepGoing = true;
		while(keepGoing) {
			
			System.out.println("Enter a Number to Choose the Corresponding Action:");
			System.out.println("\t 1: View All Users");
			System.out.println("\t 2: Update a User");
			System.out.println("\t 3: Delete a User");
			System.out.println("\t 4: Create a User");
			System.out.println("\t 5: LogOut");
			
			String userChoice = userIn.nextLine();
			userChoice = userChoice.trim();
			
			switch(userChoice) {
			case "1":
				createConsolePartition();
				System.out.println("Viewing Users");
				viewAllUsers();
				break;

			case "2":
				createConsolePartition();
				System.out.println("Updating a User");
				updateUser();
				break;
				
			case "3":
				createConsolePartition();
				System.out.println("Deleting a User");
				selectUserFromSuperUser();
				if(superUserUserChoice != null) {
					bankService.deleteUser(superUserUserChoice);
					System.out.println("User Deleted");
					superUserUserChoice = null;
				}
				
				break;
			case "4":
				createConsolePartition();
				System.out.println("Creating a User");
				superUserCreateUser();
				break;
				
			case "5":
				System.out.println("Logging Out");
				userAccount = null;
				keepGoing = false;
				break;
				
			default: System.out.println("Invalid Choice - Try Again");
			}
		}
	}
	
	private static void superUserCreateUser() {
		//handle User Creation
				String usernameChoice = null;
				boolean validUsername = false;
				boolean keepTrying = true;
				while(!validUsername && keepTrying) {
					
					System.out.println("Enter a UserName:");
					usernameChoice = userIn.nextLine();
					validUsername = !bankService.checkForUserByUsername(usernameChoice);
			
					if(!validUsername) {
						System.out.println("UserName Already Exists");
						boolean validTryAgainChoice = false;
						while(!validTryAgainChoice) {
							System.out.println("Try Again?");
							System.out.println("\t 1: yes");
							System.out.println("\t 2: no");
							String tryAgainChoice = userIn.nextLine();
							tryAgainChoice = tryAgainChoice.trim();
							switch(tryAgainChoice) {
							case "1":
								validTryAgainChoice = true;
								break;
							case "2": 
								validTryAgainChoice = true;
								keepTrying = false;
								System.out.println("Returning to main menu");
								break;
							default: 
								System.out.println("Invalid Choice - Try Again");
							}
						}
						
						
					}
				}
				
				if(keepTrying && validUsername) {
					boolean validPassword = false;
					
					while(!validPassword) {
						System.out.println("Enter a Password with at least 5 characters:");
						String password = userIn.nextLine();
						
						if(password.length() >= 5) {
							validPassword = true;
							User newUser = new User(usernameChoice, password);
							bankService.insertUser(newUser);
							System.out.println("User Account Created. \nNOTE: Username and Password are case sensitive!\n");
							
						}else {
							System.out.println("Invalid Entry - Try Again");
						}
					}
				}
				
				//No Return 
	}
	
	private static void viewAllUsers() {
		List<User> users = bankService.getAllUsers();
		System.out.println("===================== ALL USERS ====================");
		System.out.println("User Id \t\t Username\n");
		for(User user : users) {
			System.out.println("    " + user.getUserId() + "  \t\t   " + user.getUserName());
		}
	}
	private static void selectUserFromSuperUser() {
		List<User> users = bankService.getAllUsers();
		System.out.println("Specify a Valid User Id or a non-letter to prompt exit");
		boolean keepGoing = true;
		while(keepGoing) {
			String userChoice = null;
			System.out.print("User Id:");
			userChoice = userIn.nextLine();
			int accChoice = -1;
			try {
				accChoice = Integer.parseInt(userChoice);
				for(User currUser : users) {
					if(currUser.getUserId() == accChoice) {
						superUserUserChoice = currUser.getUserId();
						keepGoing = false;
						System.out.println("User " + accChoice + " Selected");
					}
				}
			}catch(NumberFormatException e) {
				
			}
			
			if(keepGoing) {
				System.out.println("Invalid Choice - Try Again?");
				System.out.println("\t 1: yes");
				System.out.println("\t 2: no");
				
				String tryAgainChoice = userIn.nextLine();
				tryAgainChoice = tryAgainChoice.trim();
				
				switch(tryAgainChoice) {
					case "1":
						break;
					case "2": 
						keepGoing = false;
						System.out.println("No Longer Selecting Account");
						currentAccount = null;
						break;
					default: 
						System.out.println("Invalid Choice - Try Again");
				}
				
			}
		}
		//No Return
	}
	
	
	private static void updateUser() {
		boolean keepGoing = true;
		while(keepGoing) {
		System.out.println("Would you like to:");
		System.out.println("\t1: Change Username");
		System.out.println("\t2: Change Password");
		System.out.println("\t3: Go Back");
		String userChoice = userIn.nextLine();
		
			switch(userChoice) {
				case "1":
					createConsolePartition();
					selectUserFromSuperUser();
					if(superUserUserChoice != null) {
						updateUsername();
						superUserUserChoice = null;
					}
					keepGoing = false;
					break;
					
				case "2":
					createConsolePartition();
					selectUserFromSuperUser();
					if(superUserUserChoice != null) {
						updatePassword();
						superUserUserChoice = null;
					}
					keepGoing = false;
					break;
					
				case "3":
					System.out.println("No Update Made");
					keepGoing = false;
					break;
					
				default: System.out.println("Invalid Choice - Try Again");
			}
		}
	}
	
	private static void updateUsername() {
		String usernameChoice = null;
		boolean validUsername = false;
		boolean keepTrying = true;
		while(!validUsername && keepTrying) {
			System.out.println("Enter a UserName:");
			usernameChoice = userIn.nextLine();
			validUsername = !bankService.checkForUserByUsername(usernameChoice);
			System.out.println(validUsername);
			if(!validUsername) {
				System.out.println("Invalid UserName");
				boolean validTryAgainChoice = false;
				while(!validTryAgainChoice) {
					System.out.println("Try Again?");
					System.out.println("\t 1: yes");
					System.out.println("\t 2: no");
					String tryAgainChoice = userIn.nextLine();
					tryAgainChoice = tryAgainChoice.trim();
					switch(tryAgainChoice) {
					case "1":
						validTryAgainChoice = true;
						break;
					case "2": 
						validTryAgainChoice = true;
						keepTrying = false;
						System.out.println("Returning to main menu");
						break;
					default: 
						System.out.println("Invalid Choice - Try Again");
					}
				}
				
				
			}
		}
		
		if(keepTrying && validUsername) {
			bankService.updateUsername(usernameChoice, superUserUserChoice);
		}
	}
	
	private static void updatePassword() {
		boolean validPassword = false;
		int attemptCounter = 0;
		while(!validPassword) {
			System.out.println("Enter a Password with at least 5 characters:");
			String password = userIn.nextLine();
			
			if(password.length() >= 5) {
				validPassword = true;
				bankService.updatePassword(password, superUserUserChoice);
				System.out.println("User Password Updated. \nNOTE: Username and Password are case sensitive!\n");
				
			}else {
				System.out.println("Invalid Entry - Try Again");
				attemptCounter++;
			}
			if(attemptCounter > 5) {
				validPassword = true; //Not actually valid
				System.out.println("No More Attempts Remaining - Try Again Later");
			}
		}
	}
	//==========Super^============
	
	private static void accessUser() {
		
		System.out.println("Welcome "+ userAccount.getUserName());
		System.out.println("What would you like to do today?");
		boolean keepGoing = true;
		while(keepGoing) {
			
			System.out.println("Enter a Number to Choose the Corresponding Action:");
			System.out.println("\t 1: View Accounts");
			System.out.println("\t 2: Access Account By Account Id");
			System.out.println("\t 3: Create A New Account");
			System.out.println("\t 4: LogOut");
			
			String userChoice = userIn.nextLine();
			userChoice = userChoice.trim();
			
			switch(userChoice) {
			case "1":
				createConsolePartition();
				System.out.println("Viewing Accounts");
				viewUserAccounts();
				break;
			
			case "2":
				createConsolePartition();
				System.out.println("Access An Account");
				selectAccount();
				if(currentAccount != null) {
					accessAccount();
				}
				break;
				
			case "3":
				createConsolePartition();
				System.out.println("Create An Account");
				createAccount();
				break;
				
			case "4":
				System.out.println("Logging Out");
				userAccount = null;
				keepGoing = false;
				break;
				
			default: System.out.println("Invalid Choice - Try Again");
			}
		}
		
	}
	
	private static void viewUserAccounts() {
		//Load User Account List
		List<Account> accounts = bankService.getAccountsByUserId(userAccount.getUserId());
		userAccount.setAccounts(accounts); 
		System.out.println("All User Accounts Displayed Below");
		System.out.println("Account Id \t\t Account Balance \t\t Account Type");
		for(Account account : accounts) {
			System.out.println("   " + account.getAccountId() +"\t\t\t\t " + account.getBalance()
								+ "\t\t\t\t" + account.getAccountType() + "\n");
		}
		System.out.println("\n\n");
	}
	
	private static void createAccount() {
		//create a new Account
		boolean keepGoing = true;
		String accountType = null;
		while(keepGoing) {
			System.out.println("Select An Account Type:");
			System.out.println("\t 1: Savings");
			System.out.println("\t 2: Checking");
			System.out.println("\t 3: Exit");
			
			String userChoice = userIn.nextLine();
			switch(userChoice) {
				case "1":
					accountType = "SAVING";
					keepGoing = false;
					break;
					
				case "2":
					accountType = "CHECKING";
					keepGoing = false;
					break;
					
				case "3":
					System.out.println("No Account Created - Exiting");
					keepGoing = false;
					break;
			
				default: System.out.println("Invalid Choice - Try Again");
			}
		
		}
		if(accountType != null) {
			bankService.createAccount(accountType, userAccount.getUserId());
			System.out.println("New Account Created");
		}
	}
	
	private static void accessAccount() {

		boolean keepGoing = true;
		while(keepGoing) {
			System.out.println("\nAccount Information:");
			System.out.println("\t Id: " + currentAccount.getAccountId());
			System.out.println("\t Type: " + currentAccount.getAccountType());
			System.out.println("\t Balance: " + currentAccount.getBalance());
			System.out.println("\nWhat would you like to do with this Account?");
			System.out.println("\t 1: Deposit");
			System.out.println("\t 2: Withdraw");
			System.out.println("\t 3: Delete");
			System.out.println("\t 4: View Transaction History");
			System.out.println("\t 5: Exit");
			String userChoice = userIn.nextLine();
			userChoice = userChoice.trim();
			
			switch(userChoice) {
			case "1":
				depositInAccount();
				break;
				
			case "2":
				createConsolePartition();
				withdrawFromAccount();
				break;
				
			case "3":
				createConsolePartition();
				deleteAccount();
				if(currentAccount == null)
					keepGoing = false;
				break;
			
			case "4": 
				createConsolePartition();
				viewTransactionHistory();
				break;
				
			case "5":
				System.out.println("Exiting Account View");
				keepGoing = false;
				break;
				
			default: System.out.println("Invalid Choice - Try Again");
			}
			
			
			
		}
	}
	
	private static void selectAccount() {
		List<Account> accounts = bankService.getAccountsByUserId(userAccount.getUserId());
		userAccount.setAccounts(accounts);
		System.out.println("Specify a Valid Account Id");
		boolean keepGoing = true;
		while(keepGoing) {
			String userChoice = null;
			System.out.print("Account Id:");
			userChoice = userIn.nextLine();
			int accChoice = -1;
			try {
				accChoice = Integer.parseInt(userChoice);
				for(Account currAccount : accounts) {
					if(currAccount.getAccountId() == accChoice) {
						currentAccount = currAccount;
						keepGoing = false;
						System.out.println("Account " + accChoice + " Selected");
					}
				}
			}catch(NumberFormatException e) {
				
			}
			
			if(keepGoing) {
				System.out.println("Invalid Choice - Try Again?");
				System.out.println("\t 1: yes");
				System.out.println("\t 2: no");
				
				String tryAgainChoice = userIn.nextLine();
				tryAgainChoice = tryAgainChoice.trim();
				
				switch(tryAgainChoice) {
					case "1":
						break;
					case "2": 
						keepGoing = false;
						System.out.println("No Longer Selecting Account");
						currentAccount = null;
						break;
					default: 
						System.out.println("Invalid Choice - Try Again");
				}
				
			}
		}
		//No Return
	}
	
	private static void deleteAccount() {
		if(currentAccount.getBalance() == 0) {
			bankService.deleteAccount(currentAccount.getAccountId());
			System.out.println("Account Deleted");
			currentAccount = null;
		}else {
			System.out.println("Cannot delete an account unless it is empty");
			System.out.println("Account Balance: " + currentAccount.getBalance());
		}
	}
	
	private static void withdrawFromAccount() {
		System.out.println("How much would you like to withdraw?");
		String userInput = userIn.nextLine();
		int withdrawalAmount = 0;
		try {
			withdrawalAmount = 0 - Integer.parseInt(userInput);
			if(currentAccount.getBalance() + withdrawalAmount >= 0) {
				Transaction transaction = new Transaction("Withdrawal", withdrawalAmount , currentAccount.getAccountId());
				bankService.insertAndExecuteTransaction(transaction);
				currentAccount.setBalance(currentAccount.getBalance() + withdrawalAmount);
			}
			else {
				System.out.println("Insufficient Funds");
				System.out.println("Current Balance: " +currentAccount.getBalance());
			}
		}catch(NumberFormatException e) {
			System.out.println("Invalid Amount");
		}
		
	}
	
	private static void depositInAccount() {
		System.out.println("How much would you like to deposit?");
		String userInput = userIn.nextLine();
		int depositAmount = 0;
		try {
			depositAmount = Integer.parseInt(userInput);
			Transaction transaction = new Transaction("Deposit", depositAmount , currentAccount.getAccountId());
			bankService.insertAndExecuteTransaction(transaction);
			currentAccount.setBalance(currentAccount.getBalance() + depositAmount);
		}catch(NumberFormatException e) {
			System.out.println("Invalid Amount");
		}
	}
	
	private static void viewTransactionHistory() {
		List<Transaction> transactions = bankService.getAllTransactionsByAccountId(currentAccount.getAccountId());
		currentAccount.setTransaction(transactions);
		System.out.println("Transaction History:");
		System.out.println("Transaction Id \t\t Transaction Amount \t\t Transaction Type");
		for(Transaction currTransaction : transactions) {
			System.out.println("\t" + currTransaction.getTransactionId() + "\t\t\t" 
								+ currTransaction.getValue() + "\t\t\t\t" + currTransaction.getTransactionType());
		}
		System.out.print("\n\n");
	}
	
}
