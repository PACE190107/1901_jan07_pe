package revature.timjordan.projectZero.base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import revature.timjordan.projectZero.models.Account;
import revature.timjordan.projectZero.models.User;
import revature.timjordan.projectZero.services.UserService;

/**
 * @author Timothy Jordan
 *
 */

public class AppDriver {

	/**
	 * Function to start the application.
	 */
	static void init() {
		// Ask for the Username
		System.out.println("~~~~~~~~~~~~~~~~~~~Welcome to the JDBC Banking App!~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Scanner scan = new Scanner(System.in);
		String userName = "";
		String password = "";
		String command = "";
		boolean isAdmin = false;
		
		System.out.println("1. Admin Login");
		System.out.println("2. User Login");
		System.out.println("3. Exit");
		command = scan.next();
		switch(command) {
		case "1":
			System.out.println("Enter Admind Username");
			userName = scan.next();
			System.out.println("Enter Admind Password");
			password = scan.next();
			isAdmin = isSuper(userName,password);
			if(isAdmin) {
				
				superUserPanel(userName, password);
			} else {
				System.out.println("Invalid Login");
				init();
			}
			break;
		case "2":
			// check if the username is registered/in database
			// if true then user name is in the database.
			System.out.println("Please Enter Your UserName");
			userName = scan.next();
			if (checkUser(userName)) {
				// Prompt for password input.
	System.out.println("------------------------------------------------------------------------------------");
				System.out.println("Welcome " + userName + " to JDBC Bank!");
				System.out.println("Please Enter Your Password");
	System.out.println("------------------------------------------------------------------------------------");
				password = scan.next();
				// call to check password method
				System.out.println("Checking password..................");
				if(checkPassword(userName, password)) {
	System.out.println("------------------------------------------------------------------------------------");
					System.out.println("Login Successful!");
					//Determine super user/regularUser
					
					
					userPanel(userName, password);
					
				
					
					
					
					
					
					
//					boolean isSuper = false;
//					isSuper = isSuper(userName);
//					if(isSuper) {
//						//Call to superUserPanel
//						//System.out.println("Super User Panel");
//						superUserPanel(userName, password);
//					} else {
//						//call to userPanel
//						//System.out.println("Reg User Panel");
//						userPanel(userName, password);
//					}
				} else {
	System.out.println("------------------------------------------------------------------------------------");
					System.out.println("Oops!, That Password does not match. Please try again.");
					//return to login start
					init();
				}
				
				

			} else {
				// Username is not in the database.
				// Prompt for login retry or create new user.
				System.out.println("Oops! We Couldn't Find That Username");
				System.out.println("1: Re-enter username.");
				System.out.println("2: Create a new user.");
				command = scan.next();
				switch (command) {
				case "1":
					init();
					break;
				case "2":
					if(createNewUser(userName)) {
						init();
					} else {
						System.out.println("Oops!, Failed To Create Account!");
					}
					break;
				default:
					// Throw custom exception here?
					System.out.println("~~Invalid Input~~");
					init();
					break;
				}
			}
			break;
		case "3":
			System.out.println("Program Closed");
			break;
			default:
				System.out.println("Invalid Command");
				init();
				break;
		}
		
		
	

	}

	/**
	 * @param userName
	 * @return True if user is in the database. False if not registered.
	 */
	static boolean checkUser(String userName) {
		boolean isUser = UserService.getUserService().isUser(new User(userName));
		// Return true if user is registered
		if (isUser) {
			return true;
		} else {
			return false;
		}

	}

	
//	static void readWriteBytes() throws  IOException, FileNotFoundException{
//		BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\properties.txt"));
//		
//		String x;
//		x = bf.readLine();
//		System.out.println(x);
//		
//	}
	
	/**
	 * @param userName
	 * @param password
	 * @return Returns true if the password is valid. False otherwise
	 */
	static boolean checkPassword(String userName, String password) {
		//System.out.println("Username: " + userName);
		//System.out.println("Password: " + password);
		//true if the password matches in the database
		boolean validPass = UserService.getUserService().isPassword(new User(userName, password));
		if(validPass) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * @param userName
	 * @param password
	 * Used to handle superUser interface and options.
	 */
	static void superUserPanel(String userName, String password) {
		Scanner scan = new Scanner(System.in);
		String command = "";
		List<User> allUsers = null;
		System.out.println("Welcome " + userName + " to the admin panel.");
		System.out.println("1. View all users");
		System.out.println("2. Add a user");
		System.out.println("3. Remove a user");
		System.out.println("4. Modifiy a user");
		System.out.println("5. Log out");
		command = scan.next();
		switch(command) {
		case "1":
			System.out.println("Getting Users...................");
			allUsers = UserService.getUserService().getAllUserDetails();
			for(User x: allUsers) {
				System.out.println("---------------------------------------------------------------------");
				System.out.println("ID: " + x.getId());
				System.out.println("Username: " + x.getUserName());
				System.out.println("Firstname: " + x.getFirstName());
				System.out.println("Lastname: " + x.getLastName());
				System.out.println("---------------------------------------------------------------------");
			}
			System.out.println("1. Another Transaction");
			System.out.println("2. Log Out");
			command = scan.next();
			switch(command) {
			case "1":
				superUserPanel(userName, password);
				break;
			case "2":
				System.out.println("Logging Out");
				init();
				break;
				default:
					superUserPanel(userName, password);
					break;
			}
			
			break;
		case "2":
			System.out.println("Add a user");
			if(addUser()) {
				System.out.println("User Was Added");
				System.out.println("1. Another transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					superUserPanel(userName, password);
					break;
				case "2":
					System.out.println("Logging Out");
					init();
					break;
					default:
						superUserPanel(userName, password);
						break;
				}
			} else {
				System.out.println("Failed To Add User");
				System.out.println("1. Another transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					superUserPanel(userName, password);
					break;
				case "2":
					System.out.println("Logging Out");
					init();
					break;
					default:
						superUserPanel(userName, password);
						break;
				}
				
			}
			break;
		case "3":
			System.out.println("Remove A User");
			if(removeUser()) {
				System.out.println("User Was Removed Along With Associated Accounts");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					superUserPanel(userName, password);
					break;
				case "2":
					System.out.println("Logging Out");
					init();
					break;
				default:
					System.out.println("Invalid Command");
					superUserPanel(userName, password);
					break;
				}
				
			} else {
				System.out.println("Failed To Remove User");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					superUserPanel(userName, password);
					break;
				case "2":
					System.out.println("Logging Out");
					init();
					break;
				default:
					System.out.println("Invalid Command");
					superUserPanel(userName, password);
					break;
				}
			}
			break;
		case "4":
			System.out.println("Modifiy a user");
			if(modifyUser()) {
				System.out.println("Account Updated");
				System.out.println("1 Another Transaction");
				System.out.println("2 Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					superUserPanel(userName, password);
					break;
				case "2":
					System.out.println("Loggint Out");
					init();
					break;
					default:
						System.out.println("Invalid Command");
						superUserPanel(userName, password);
						break;
				}
				
			} else {
				System.out.println("Update Unsuccessful");
				System.out.println("1 Another Transaction");
				System.out.println("2 Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					superUserPanel(userName, password);
					break;
				case "2":
					System.out.println("Loggint Out");
					init();
					break;
					default:
						System.out.println("Invalid Command");
						superUserPanel(userName, password);
						break;
				}
			}
			
			
			
			
			
			break;
		case "5":
			System.out.println("Logging Out");
			init();
			break;
		default:
			System.out.println("Invalid Command");
			init();
			break;
		}
	}
	
	
	/**
	 * @param userName
	 * @param password
	 * Used to handle user panel operations.
	 */
	static void userPanel(String userName, String password) {
		//veriables
		Scanner scan = new Scanner(System.in);
		String command = "";
		
		//setup user from database
		User currentUser = UserService.getUserService().getUser(userName);
//		System.out.println("UserID: " + currentUser.getId());
//		System.out.println("Username: " + currentUser.getUserName());
//		System.out.println("Password " + currentUser.getPassword());
//		System.out.println("Firstname: " + currentUser.getFirstName());
//		System.out.println("Lastname: " + currentUser.getLastName());
System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Welcome " + userName + " To The User Panel.");
		System.out.println("What Would You Like To Do?");
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Inquiry");
		System.out.println("4. Account Manager");
		System.out.println("5. Log out");
		command = scan.next();
		switch(command) {
		case "1":
			System.out.println("Processing Withdraw............................");
	System.out.println("------------------------------------------------------------------------------------");
			if(withdraw(currentUser)) {
				System.out.println("~~~~~~~~~~Successful Transaction~~~~~~~~~~");
				System.out.println("1. Another transaction");
				System.out.println("2. Log out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
					default:
						userPanel(userName, password);
						break;
				}
			} else {
				System.out.println("~~~~~~~~~~~~Unsuccessful Transaction~~~~~~~~~~~~");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
					default:
						System.out.println("~~Invalid Command~~");
						userPanel(userName, password);
				}
				
				
			}
			break;
		case "2":
			System.out.println("Processing Deposit.................................");
			if(deposit(currentUser)) {
				System.out.println("~~~~~~~~~~~~~Successful Deposit~~~~~~~~~~~~~~~~");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
					default:
						System.out.println("~~Invalid Command~~");
						userPanel(userName, password);
				}
			} else {
				System.out.println("~~~~~~~~~~~~Unsuccessful Deposit~~~~~~~~~~~~~~");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
					default:
						System.out.println("~~Invalid Command~~");
						userPanel(userName, password);
				}
				
			}
			
			
			
			
			
			break;
		case "3":
			System.out.println("Processing Inquiry...............");
			
			//check if successful inquiry was completed
			if(inquiry(currentUser)) {
				System.out.println("~~~~~~~~~~~Successful Inquiry~~~~~~~~~~~");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("Logging Out");
					init();
					break;
				}
			} else {
				System.out.println("~~~~~~~~~~~~~~Failed Inquiry~~~~~~~~~~~~~~");
				System.out.println("1. Another Transaction");
				System.out.println("2. Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
				}
			}
			
			
			
			
			
			break;
		case "4":
			System.out.println("Moving to Account Manager......................");
			if(accountManager(currentUser)) {
				//System.out.println("Account was removed or created");
				System.out.println("~~~~~~~~~~Account Transaction Successful~~~~~~~~");
				System.out.println("1 Another Transaction");
				System.out.println("2 Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
					default:
						userPanel(userName, password);
						break;
				
				}
				
			} else {
				System.out.println("~~~~~~~~~~~Unsuccessful Account Transaction~~~~~~~~~~~~");
				System.out.println("1 Another Transaction");
				System.out.println("2 Log Out");
				command = scan.next();
				switch(command) {
				case "1":
					userPanel(userName, password);
					break;
				case "2":
					System.out.println("~~Logging Out~~");
					init();
					break;
					default:
						userPanel(userName, password);
						break;
				
				}
			}
			
			
			
			
			break;
		case "5":
			System.out.println("~~Logging Out~~");
			init();
			break;
			
		default: 
			System.out.println("~~Invalid Command~~");
			userPanel(userName, password);
			break;
			
		}
		
	}
	
	
	static boolean modifyUser() {
		User tempUser = null;
		String newValue = "";
		String columnName = "";
		boolean wasSuccess = false;
		boolean wasChanged = false;
		Scanner scan = new Scanner(System.in);
		String command = "";
		List<User> allUsers= null;
		//Show a list of users with user ID
		allUsers = getAllUsers();
		//System.out.println(allUsers);
		for(User x: allUsers) {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("ID: " + x.getId());
			System.out.println("Username: " + x.getUserName());
			System.out.println("Firstname: " + x.getFirstName());
			System.out.println("Lastname: " + x.getLastName());
			System.out.println("---------------------------------------------------------------------");
		}
		
		System.out.println("Enter the ID of the user to modify.");
		int user_ID = 0;
		if(scan.hasNextInt()) {
			user_ID = scan.nextInt();
			//check if id is in set of accounts
			//call to usermodify
			System.out.println("1. Update first name");
			System.out.println("2 Update last name");
			System.out.println("3 Update username");
			command = scan.next();
			switch(command) {
			case "1":
				columnName = "FIRST_NAME";
				System.out.println("Enter new first name value.");
				newValue = scan.next();
				wasChanged = UserService.getUserService().modifyUser(columnName, newValue, user_ID);
				if(wasChanged) {
					System.out.println("Account was changed.");
					wasSuccess = true;
				} else {
					System.out.println("Account couldnt be changed");
					wasSuccess = false;
				}
				break;
			case "2":
				columnName = "LAST_NAME";
				System.out.println("Enter new last name value");
				newValue = scan.next();
				wasChanged = UserService.getUserService().modifyUser(columnName, newValue, user_ID);
				if(wasChanged) {
					System.out.println("Account was changed.");
					wasSuccess = true;
				} else {
					System.out.println("Account couldnt be changed");
					wasSuccess = false;
				}
				break;
			case "3":
				columnName = "USER_NAME";
				System.out.println("Enter the new username");
				newValue = scan.next();
				wasChanged = UserService.getUserService().modifyUser(columnName, newValue, user_ID);
				if(wasChanged) {
					System.out.println("Account was changed.");
					wasSuccess = true;
				} else {
					System.out.println("Account couldnt be changed");
					wasSuccess = false;
				}
				break;
			default:
				System.out.println("Invalid Command");
				wasSuccess = false;
				
			}
			
			
		} else {
			System.out.println("Invalid Input");
			wasSuccess = false;
		}
		
		
		
		
		//Show List of users
		
		
		
		if(wasSuccess) {
			return true;
		} else {
			return false;
		}
		
		
		
	}
	
	static boolean removeUser() {
		String userName = "";
		User tempUser = null;
		boolean wasSuccess = false;
		boolean wasRemoved = false;
		Scanner scan = new Scanner(System.in);
		String command = "";
		List<User> allUsers= null;
		//Show a list of users with user ID
		allUsers = getAllUsers();
		//System.out.println(allUsers);
		for(User x: allUsers) {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("ID: " + x.getId());
			System.out.println("Username: " + x.getUserName());
			System.out.println("Firstname: " + x.getFirstName());
			System.out.println("Lastname: " + x.getLastName());
			System.out.println("---------------------------------------------------------------------");
		}
		
		System.out.println("Enter The User ID of the User you want to remove");
		int user_ID = 0;
		if(scan.hasNextInt()) {
			user_ID = scan.nextInt();
			//System.out.println("userID: " + user_ID);
			wasRemoved = UserService.getUserService().deleteUser(new User(user_ID));
			//System.out.println("wasRemoved" + wasRemoved);
			if(wasRemoved) {
				wasSuccess = true;
			} else {
				wasSuccess = false;
			}
			
			
		} else {
			System.out.println("Invalid Input");
			wasSuccess = false;
		}
		
		
		if(wasSuccess) {
			return true;
		} else {
			return false;
		}
		
	}
	
	static List<User> getAllUsers() {
		
		List<User> allUsers = UserService.getUserService().getAllUserDetails();
		
		
		return allUsers;
		
		
	}
	
	
	static boolean addUser() {
		String userName = "";
		String password = "";
		String firstName = "";
		String lastName = "";
		User tempUser =  null;
		boolean wasSuccess = false;
		boolean wasAdded = false;
		Scanner scan = new Scanner(System.in);
		String command = "";
		System.out.println("Enter Username of User");
		userName = scan.next();
		//Check if already a user
		if(checkUser(userName)) {
			System.out.println("Already A Registered User");
			wasSuccess = false;
		} else {
			System.out.println("Enter The First Name Of User");
			firstName = scan.next();
			System.out.println("Enter The Last Name Of User");
			lastName = scan.next();
			System.out.println("Enter Password For User");
			password = scan.next();
			tempUser = new User(firstName, lastName, userName, password);
			wasAdded = UserService.getUserService().addUser(tempUser);
			if(wasAdded) {
				wasSuccess = true;
			} else {
				wasSuccess = false;
			}
			
		}
		
		if(wasSuccess) {
			return true;
		} else {
			return false;
		}
		
		
		
	}
	
	/**
	 * @param currentUser
	 * @return Withdraw from checking or savings account.
	 */
	static boolean withdraw(User currentUser) {
		Scanner scan = new Scanner(System.in);
		String command = "";
		boolean wasSuccess = false;
		Account tempAccount = null;
		String accountType = "";
		System.out.println("Which account would you like to withdraw from?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		System.out.println("3. Log out");
		command = scan.next();
		if(command.equals("1")) {
			accountType = "Checking";
		} else if(command.equals("2")){
			accountType = "Savings";
		}
		switch(command) {
		case "1":
			System.out.println("Retrieving Checking Account Information.....................");
			 tempAccount = getAccountInfo(currentUser, accountType);
			 System.out.println("------------------------------------------------------------------------------------");			 
			 System.out.println(tempAccount.getAccountType() + " Account");
			 System.out.println("Balance: $" + tempAccount.getAccountBalance());
			 System.out.println("------------------------------------------------------------------------------------");			 
			 
			 
			 System.out.println("Enter Withdraw Amount:");
			 System.out.println("$");
			 
			 double mdouble = 0;
			 if(scan.hasNextDouble()) {
				 mdouble = scan.nextDouble();
				 if(mdouble < 0) {
					 System.out.println("Cannot Withdraw A Negative Amount");
					 wasSuccess = false;
				 } else if(handleWithdraw(tempAccount, mdouble)){
					 wasSuccess = true;
				 } else {
					 wasSuccess = false;
				 }
//				 if(handleWithdraw(tempAccount, mdouble)) {
//					 wasSuccess = true;
//				 } else {
//					 wasSuccess = false;
//				 }
			 } else {
				 System.out.println("Invalid Input");
				 wasSuccess = false;
			 }
			 
			 
			 
			 
			
			break;
		case "2":
			if(hasSavings(currentUser.getId())) {
				System.out.println("Retrieving Savings Account Informartion......................");
				tempAccount = getAccountInfo(currentUser, accountType);
System.out.println("------------------------------------------------------------------------------------");				 
				System.out.println(tempAccount.getAccountType() + " Account");
				 System.out.println("Balance: $" + tempAccount.getAccountBalance());
				 System.out.println("------------------------------------------------------------------------------------");				 
	System.out.println("Enter Withdraw Amount");
				 
				 double mSdouble = 0;
				 if(scan.hasNextDouble()) {
					 mSdouble = scan.nextDouble();
					 if(mSdouble < 0) {
						 System.out.println("Cannot Withdraw A Negative Amount");
						 wasSuccess = false;
					 } else if(handleWithdraw(tempAccount, mSdouble)) {
						 wasSuccess = true;
					 } else {
						 wasSuccess = false;
					 }
					 
					 
//					 if(handleWithdraw(tempAccount, mSdouble)) {
//							wasSuccess = true; 
//						 } else {
//							 wasSuccess = false;
//						 }
				 } else {
					 System.out.println("~~Invalid Input~~");
					 wasSuccess = false;
				 }
				 
				
				
			} else {
				System.out.println("~~No Savings Account~~");
				
			}
			//Check if user has a savings account.
			
			
			break;
		case "3":
			//call logout confirm
			System.out.println("~~Logging out~~");
			init();
			break;
		default:
			System.out.println("~~Invalid Command~~");
			wasSuccess = false;
			//userPanel(currentUser.getUserName(), currentUser.getPassword());
			break;
		}
		if(wasSuccess) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	static boolean accountManager(User currentUser) {
		System.out.println("Welcome to the Account Manager");
		Scanner scan = new Scanner(System.in);
		String command = "";
		boolean wasSuccess = false;
		String userName = currentUser.getUserName();
		String password = currentUser.getPassword();
		Account tempAccount = null;
		int user_ID = currentUser.getId();
		List<Account> results = UserService.getUserService().getAccounts(new User(user_ID), "", 2);
		System.out.println("Please review your accounts.");
		for(Account x : results) {
			System.out.println(x.getAccountType() + " Balance: " + "$" + x.getAccountBalance());
		}
		
		System.out.println("1. Add a Savings Account");
		System.out.println("2. Remove a Savings Account");
		System.out.println("3. Log out");
		command = scan.next();
		switch(command) {
		case "1":
			//check if user has a savings
			//if so cant add another
			if(hasSavings(user_ID)) {
				System.out.println("You already have an existing savings account.");
				wasSuccess = false;
			}
			//if not add a savings account
			//set return value to true if account is created
			else {
				boolean temp = UserService.getUserService().addSavings(currentUser);
				if(temp) {
					wasSuccess = true;
				}  else {
					wasSuccess = false;
				}
			}
			break;
		case "2":
			//check if user has a savings account
			//if so check if the balance is 0
			//cant delete if more then 0
			if(hasSavings(user_ID)) {
				//get account info
				tempAccount = getAccountInfo(currentUser, "Savings");
				if(tempAccount.getAccountBalance() != 0) {
					System.out.println("Please empty your account before deleting.");
					wasSuccess = false;
				} else {
					//call remove account
					boolean myTemp = UserService.getUserService().removeSavings(tempAccount);
					if(myTemp) {
						System.out.println("Account was removed");
						wasSuccess = true;
					} else {
						wasSuccess = false;
					}
				}
			}
			//if no savings account cant remove
			//set return value to true if account is removed
			else {
				System.out.println("You do not have an existing savings account.");
				
			}
			break;
		case "3":
			System.out.println("Logging out");
			init();
			break;
			default:
				accountManager(currentUser);
				break;
		}
		
		
		
		
		
		
		
		
		
		if(wasSuccess) {
			return true;
		} else {
			return false;
		}
		
		
		
	}
	
	static boolean inquiry(User currentUser) {
		Scanner scan = new Scanner(System.in);
		String command = "";
		boolean wasSuccess = false;
		String userName = currentUser.getUserName();
		String password = currentUser.getPassword();
		String accountType = "";
		int user_ID = currentUser.getId();
		Account tempAccount = null;
		
		System.out.println("Inquiry:");
		System.out.println("Which account would you like to check the balance of?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		System.out.println("3. Log out");
		command = scan.next();
		if(command.equals("1")) {
			accountType = "Checking";
		} else if(command.equals("2")) {
			accountType = "Savings"; 
		}
		switch(command) {
		case "1":
			System.out.println("Retrieving Checking Account Information.");
			tempAccount = getAccountInfo(currentUser, accountType);
			System.out.println(tempAccount.getAccountType() + " Balance:"); 
			System.out.println("$" + tempAccount.getAccountBalance());
			wasSuccess = true;
			break;
		case "2":
			System.out.println("Retrieving Savings Account Information.");
			//check if user has a savings account
			if(hasSavings(user_ID)) {
				tempAccount = getAccountInfo(currentUser, accountType);
				System.out.println(tempAccount.getAccountType() + " Balance:");
				System.out.println("$" + tempAccount.getAccountBalance());
				wasSuccess = true;
			} else {
				System.out.println("Savings account does not exists.");
				wasSuccess = false;
			}
			break;
		case "3":
			System.out.println("Logging out");
			init();
			break;
			default:
				System.out.println("Invalid Command");
				wasSuccess = false;
		}
		if(wasSuccess) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * @param user_ID
	 * @return True if user has a savings account.
	 */
	static boolean hasSavings(int user_ID) {
		
		boolean hasSavings = UserService.getUserService().hasSavings(new User(user_ID));
		
		
		return hasSavings;
	}
/**
 * @param currentUser
 * @return Returns true or false depending on transaction success or failure.
 */
static boolean deposit(User currentUser) {
	String userName = currentUser.getUserName();
	String password = currentUser.getPassword();
	Scanner scan = new Scanner(System.in);
	String command = "";
	boolean wasSuccess = false;
	Account tempAccount = null;
	String accountType = "";
System.out.println("------------------------------------------------------------------------------------");	
	System.out.println("Which account would you like to make a deposit to?");
	System.out.println("1. Checking");
	System.out.println("2. Savings");
	
	System.out.println("3. Log out");
	command = scan.next();
	if(command.equals("1")) {
		accountType = "Checking";
	} else if (command.equals("2")){
		accountType = "Savings";
	}
	switch(command) {
	case "1":
		System.out.println("Retrieving checking account information..............");
		tempAccount = getAccountInfo(currentUser, accountType);
		 System.out.println("------------------------------------------------------------------------------------");
		 System.out.println("Checking Account");
		 System.out.println("Balance: $" + tempAccount.getAccountBalance());
		 System.out.println("------------------------------------------------------------------------------------");	 
		 
		 
		 System.out.println("Enter the amount you would like to deposit.");
		 
		 double mdouble = 0;
		 if(scan.hasNextDouble()) {
			 mdouble = scan.nextDouble();
			 if(mdouble < 0 ) {
				 System.out.println("Cannot Deposite A Negative Amount");
				 wasSuccess = false;
			 } else if(handleDeposit(tempAccount, mdouble)) {
				 wasSuccess = true;
			 } else {
				 wasSuccess = false;
			 }
			 
			 
			 
//			 if(handleDeposit(tempAccount, mdouble)) {
//					wasSuccess = true; 
//				 } else {
//					 wasSuccess = false;
//				 }
		 } else {
			 System.out.println("Invalid Input");
			 wasSuccess = false;
		 }
		 
		 
		 
		 
		 
		 
		
		break;
	case "2":
		System.out.println("Retriving savings account information.");
		
		//check if user has a savings account
		if(hasSavings(currentUser.getId())) {
			
			
			tempAccount = getAccountInfo(currentUser, accountType);
System.out.println("------------------------------------------------------------------------------------");		 
			System.out.println(tempAccount.getAccountType() + " Account");
			 System.out.println("Balance: $" + tempAccount.getAccountBalance());
			 System.out.println("------------------------------------------------------------------------------------");			 
System.out.println("Enter the amount you would like to deposite.");
			 
			 double mSdouble = 0;
			 if(scan.hasNextDouble()) {
				 mSdouble = scan.nextDouble();
				 
				 if(mSdouble < 0) {
					 System.out.println("Cannot Deposit A Negative Amount");
					 wasSuccess = false;
				 } else if(handleDeposit(tempAccount, mSdouble)) {
					 wasSuccess = true;
				 } else {
					 wasSuccess = false;
				 }
				 
				 
//				 if(handleDeposit(tempAccount, mSdouble)) {
//						wasSuccess = true; 
//					 } else {
//						 wasSuccess = false;
//					 }
			 } else {
				 System.out.println("Invalid Input");
				 wasSuccess = false;
			 }
			 
			 
			
			
			
			
		} else {
			System.out.println("No existing savings account.");
			wasSuccess = false;
		}
		break;
	
	case "3":
		System.out.println("Logging out");
		init();
		break;
		
		default:
			System.out.println("Invalid Command");
			//userPanel(currentUser.getUserName(), currentUser.getPassword());
			break;
			
	}
	
	
	
	if(wasSuccess) {
		return true;
	} else {
		return false;
	}
	
	
}
	
	
	
	/**
	 * @param currentUser
	 * @param accountType
	 * @return returns instance of an account.
	 */
	static Account getAccountInfo(User currentUser, String accountType) {
		Account tempAccount = null;
		List<Account> results = null;
		int user_ID = currentUser.getId();
		//retrieve and process checking account information
		results = UserService.getUserService().getAccounts(new User(user_ID), accountType, 1);
		for(Account x : results) {
			tempAccount = x;
			
		}
//		System.out.println(tempAccount.getUserID());
//		System.out.println(tempAccount.getAccountID());
//		System.out.println(tempAccount.getAccountType());
//		System.out.println(tempAccount.getAccountBalance());
		
		
		
		return tempAccount;
	}
	
	
	
	/**
	 * @return True if logout was confirmed, will logout.
	 * False if no or invalid command will cancel
	 */
	static boolean confirmLogOut() {
		Scanner scan = new Scanner(System.in);
		String command = "";
		System.out.println("Are you sure you want to logout?");
		System.out.println("Yes to confirm.");
		System.out.println("No to cancel.");
		command = scan.next();
		String lowercase = command.toLowerCase();
		
		if(lowercase.equals("yes")) {
			return true;
		} else if(lowercase.equals("no")) {
			return false;
		} else {
			System.out.println("Invalid command");
			return false;
		}
		
		
	}
	
	
	/**
	 * @param currentAccount
	 * @param amount
	 * @return Determine if successful withdraw
	 */
	static boolean handleWithdraw(Account currentAccount, double amount) {
		boolean didWithdraw = UserService.getUserService().withdraw(currentAccount, amount);
		return didWithdraw;
	}
	
	
	
	/**
	 * @param currentAccount
	 * @param amount
	 * @return Determine if successful deposite
	 */
	static boolean handleDeposit(Account currentAccount, double amount) {
		boolean didDeposit = UserService.getUserService().deposit(currentAccount, amount);
		
		return didDeposit;
	}
	
	
	
	
	/**
	 * @param userName
	 * @return Returns true if user is a super user. False otherwise.
	 */
	static boolean isSuper(String userName, String password) {
		//System.out.println(userName);
		//System.out.println(password);
		String superUserName = "";
		String superUserPassword = "";
		//System.out.println(userName);
		try (BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\superUser.properties"));) {
			
			superUserName = bf.readLine();
			superUserPassword = bf.readLine();
			//System.out.println(superUserName + "from file");
			//System.out.println(superUserPassword + "fromfile");
			
			
		} catch(IOException e) {
			System.out.println(" File not found.");
		} 
		if(userName.equals(superUserName) && password.equals(superUserPassword) ) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
	
	
	/**
	 * @param userName
	 * @return true if a new user was created, false if a new user couldn't be
	 *         created.
	 */
	static boolean createNewUser(String userName) {
		Scanner scan = new Scanner(System.in);
		String firstName,lastName,password =  "";
		boolean wasCreated = false;
		//Gather information for new user creation.
		System.out.println("Welcome " + userName + "let's setup your account.");
		System.out.println("Please enter you first name.");
		firstName = scan.next();
		System.out.println("Please Enter you last name.");
		lastName = scan.next();
		System.out.println("Please enter your desired password.");
		password = scan.next();
		System.out.println("Thank you, for entering your information.");
		//Attempt to create a new user
		wasCreated = UserService.getUserService().addUser(new User(firstName, lastName, userName, password));
		if(wasCreated) {
			//Add default checking account for the new user  		
			checkingAccount(userName);
			System.out.println("Thank you for creating your account.");
			return true;
			
			
		} else {
			System.out.println("Failed to create account.");
			init();
			return false;
		}
		
		
		
	}
	
	
	
	/**
	 * @param userName
	 * @return creates a new base checking account for a new user. Returns true if successful and false otherwise.
	 */
	static boolean checkingAccount(String userName) {
		boolean wasCreated = UserService.getUserService().addChecking(new User(userName));
		if (wasCreated) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// start the app
		init();
	}

}
