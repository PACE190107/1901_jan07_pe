package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exceptions.LoginException;
import com.revature.exceptions.RegisterException;
//import com.revature.dao.UserDaoImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.JDBCConnectUtil;

public class GoodBank {

	public static void main(String[] args) {
		
		//beginning of the app
		runGoodBank();
        
	}
	
	final static Logger Log = Logger.getLogger(JDBCConnectUtil.class);
	
	//declare input variables
	static Scanner sc = new Scanner(System.in);
    static String usernameInput = null; 
    static String passwordInput = null; 
    static String firstnameInput = null; 
    static String lastnameInput = null;
    static String loginUsername = null;
    static String loginPassword = null;
    static String loginAdminUsername = null;
    static String loginAdminPassword = null;
    static String createUsernameInput = null; 
    static String createPasswordInput = null; 
    static String createFirstnameInput = null; 
    static String createLastnameInput = null;
    
    //establish empty object to store all user values in
    //private GoodBank userSession = null;
    
 
	
    //method that calls the start of the bank application
	static void runGoodBank() {
		
		System.out.println("\nHi! Welcome to Good Bank, home of the Good Bank, what would you like to do?\n");
        
        //move user to guest menu of application
		guestMenu();
	

	}
	
	//the guest menu to users who are not logged in
	static void guestMenu() {
		
		System.out.println("Enter the number associated with the task you'd like to do: "
        		+ "\n\n1 - Login \n2 - Register\n");
		
		String guestMenuInput = sc.nextLine();
		
		//if anything other than 1,2, or 3 give error message and return back to the
		switch (guestMenuInput) {
		case "1":
		case "2":
		case "2319":
			switch (guestMenuInput){
	        case "1":
	        	System.out.println("\nPlease enter your login credentials:\n");
	        	System.out.println("Enter your username: ");
	        	loginUsername = sc.nextLine();
	        	System.out.println("Enter your password: ");
	        	loginPassword = sc.nextLine();
	        	
	        	boolean checkLogin = UserService.getUserService().loginCheck(new User(loginUsername,loginPassword));
	        	
	        	if(checkLogin) {
	        		loggedInMenu();
	        	}else {
	        		try {
						throw new LoginException("The entered username and password was incorrect. Please try a different combination.\n");
					} catch (LoginException e) {
						//Log.error("Error with RegisterException");
					}
// >>>>     		
	        	}
	        	guestMenu();
	        	sc.close();
	        	break;
	        case "2":
	        	System.out.println("\nPlease fill out the fields below to register:\n");
	        	System.out.println("Enter your username: ");
	        	usernameInput = sc.nextLine();
	        	
	        	boolean checkRegister = UserService.getUserService().registerCheck(new User(usernameInput));
	        	
	        	if(!checkRegister) {
	        		
	        		System.out.println("\nThat username is available! Let's continue with the registration process!\n");
	        		System.out.println("Enter your password: ");
		        	passwordInput = sc.nextLine();
		        	System.out.println("Enter your first name: ");
		        	firstnameInput = sc.nextLine();
	        		System.out.println("Enter your last name: ");
		        	lastnameInput = sc.nextLine();
		        	
		        	boolean wasRegistered = UserService.getUserService().registerUserProcedure(new User(firstnameInput, lastnameInput, usernameInput, passwordInput));
		        	//if user was successfully registered, add a new account into the account table
		        	if(wasRegistered) {
		        		UserService.getUserService().addNewChecking(new User(usernameInput));
		        	} else {
		        		try {
							throw new RegisterException("Registratiopn attempt failed.");
						} catch (RegisterException e) {
							Log.error("Error with RegisterException");
						}
		        	}
		        	runGoodBank();

	        	}else {
	        		System.out.println("\nUh Oh! That username is NOT available! Try using a different usernmae.");
	        		runGoodBank();

	        	}
	        	break;
	        	
	        //admin login menu	
	        case "2319":
	        	System.out.println("Redirecting you to the admin login panel...\n");
	        	adminLoginMenu();
	        	break;
	    	default:
	    		System.out.println("\nUH OH!: You entered an invalid menu input. Please try again.\n");
	    		runGoodBank();
	    		break;
	        }//switch end
			break;
		default:
			System.out.println("\nERROR: Uh oh! You've entered an invalid menu input. Be sure that are entering a 1, 2, or 3!\n\n"
					+ "-------------------------------------------------------------------------------------------");
			runGoodBank();
			break;
		}//switch end
		
		
		
	}
	
	//the menu for users who have successfully logged in
	static void loggedInMenu() {
		
		int sessUserId = UserService.getUserService().getUserId(new User(loginUsername));;
	    //String sessUsername;
	    //String sessPassword;
	    //String sessFirstName;
	    //String sessLastName;
	    //List<Account> sessAccounts;
	    
	    //get and store User id in session user id
	    //UserService.getUserService().getUserId(new User(loginUsername));
	    
	    //get and display all account balances
		List<Account> allUserAccounts = UserService.getUserService().displayUserAccounts(new User(loginUsername));
		//sessAccounts = allUserAccounts;
		System.out.println(">>>>>>>>>>>>>>>>>> Good Bank Account Details <<<<<<<<<<<<<<<<<<\n");
		for(Account x : allUserAccounts) {
			System.out.println("Account Type: " + x.getAccountType() + " - Account #: " + x.getAccountNum() + " - Balance: " + x.getAccountBal() + " USD"); 
		}
		
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");

		System.out.println("Hello, " + loginUsername + "! What would you like to do?\n");
		System.out.println("Enter the number of the menu option you'd like to complete.");
		System.out.println("\n1 - Withdraw \n2 - Deposit\n"
				+ "3 - Create Checking Account \n4 - Create Savings Account\n"
				+ "5 - Delete An Account\n6 - Logout");
		

		String loginMenuInput = sc.nextLine();
		
		switch (loginMenuInput) {

		//withdraw handling
		case "1":
			//create an arraylist to store all of user's account numbers
			//in order to validate that their entered account number is good
			List<String> storedAcctNumbers = new ArrayList<String>();
			
			System.out.println("Displaying all of your accounts...\nPlease enter the account number of the account you wish to withdraw from:\n");
			
			//display all of user's accounts before asking for specific account number
			for(Account x : allUserAccounts) {
				storedAcctNumbers.add(x.getAccountNum());
				System.out.println("Account Type: " + x.getAccountType() + " - Account #: " + x.getAccountNum() + " - Balance: " + x.getAccountBal() + " USD"); 
			}
			
			System.out.println("\nPlease enter the Account Number you wish to withdraw from: ");
			//get user input for selected account number
			String accountNumInput = sc.nextLine();
			//if the user has entered a correct account number, allow them to withdraw a specific amount
			if(storedAcctNumbers.contains(accountNumInput)) {
				
				System.out.println("How much do you want to withdraw from this account?: ");
				//get user input of the double type
				Double accountWithdrawInput = sc.nextDouble();
				sc.nextLine(); //skip to next line so scanner doesn't register the Double input as current input
				
				boolean isWithdrawn = UserService.getUserService().withdrawFromAccount(new Account(sessUserId,accountNumInput,accountWithdrawInput)); 
				
				//on success, print success and return back to main menu
				//on fail, print fail and return back to main menu
				if(isWithdrawn) {
					System.out.println("Returning to main menu...\n");
					loggedInMenu();
					break;
				} else {
					System.out.println("UH OH!: The amount you're trying to withdraw is more than what you have available.\n");
					//System.out.println("Returning to main menu...");
					loggedInMenu();
					break;
				}
			} else {	
				System.out.println("UH OH!: You've entered an incorrect account number. Returning to main menu...\n");
				loggedInMenu();	
			}
			break;
		
		//deposit handling
		case "2":
			//create an arraylist to store all of user's account numbers
			//in order to validate that their entered account number is good
			List<String> storedAcctNumbers2 = new ArrayList<String>();
			
			System.out.println("Displaying all of your accounts...\n");
			
			//display all of user's accounts before asking for specific account number
			for(Account x : allUserAccounts) {
				storedAcctNumbers2.add(x.getAccountNum());
				System.out.println("Account Type: " + x.getAccountType() + " - Account #: " + x.getAccountNum() + " - Balance: " + x.getAccountBal() + " USD"); 
			}
			
			System.out.println("\nPlease enter the account number you wish to deposit to: ");
			//get user input for selected account number
			String accountNumInput2 = sc.nextLine();
			//if the user has entered a correct account number, allow them to withdraw a specific amount
			if(storedAcctNumbers2.contains(accountNumInput2)) {
				
				System.out.println("How much do you want to deposit to this account?: ");
				//get user input of the double type
				Double accountDepositInput = sc.nextDouble();
				sc.nextLine(); //skip to next line so scanner doesn't register the Double input as current input
				
				boolean isDeposited = UserService.getUserService().depositFromAccount(new Account(sessUserId,accountNumInput2,accountDepositInput)); 
				
				//on success, print success and return back to main menu
				//on fail, print fail and return back to main menu
				if(isDeposited) {
					System.out.println("Returning to main menu...\n");
					loggedInMenu();
					break;
				} else {
					System.out.println("UH OH!: The amount you're trying to withdraw is more than what you have available.\n");
					//System.out.println("Returning to main menu...");
					loggedInMenu();
					break;
				}
				
				
			} else {
				
				System.out.println("UH OH!: You've entered an incorrect account number. Returning to main menu...\n");
				loggedInMenu();	
				
			}
			break;
			
		//create a checking account
		case "3":
			
			System.out.println("Are you sure you want to open a new checking account?\nEnter \"1\" for YES or \"2\" for NO.");
			String newCheckingVerifyInput = sc.nextLine();
			
			switch (newCheckingVerifyInput) {
			case "1":
				boolean addChecking = UserService.getUserService().addCheckingAcct(new Account(sessUserId));
	        	
	        	if(addChecking) {
	        		System.out.println("You have successfully added a new checking account!");
	        		loggedInMenu();
	        		break;
	        	}
				break;
			case "2":
				System.out.println("Not a problem. No account will be created. Returning to main menu...");
				loggedInMenu();
				break;
			default:
				System.out.println("You've entered an invalid input. Please make sure you are only entering a '1' or '2'.");
				loggedInMenu();
				break;
			}//switch end        	
			break;
		//create a savings account	
		case "4":
			
			System.out.println("Are you sure you want to open a new savings account?\nEnter \"1\" for YES or \"2\" for NO.");
			String newSavingsVerifyInput = sc.nextLine();
			
			switch (newSavingsVerifyInput) {
			case "1":
				boolean addSavings = UserService.getUserService().addSavingsAcct(new Account(sessUserId));
	        	
	        	if(addSavings) {
	        		System.out.println("You have successfully added a new savings account!");
	        		loggedInMenu();
	        		break;
	        	}
				break;
			case "2":
				System.out.println("Not a problem. No account will be created. Returning to main menu...");
				loggedInMenu();
				break;
			default:
				System.out.println("You've entered an invalid input. Please make sure you are only entering a '1' or '2'.");
				loggedInMenu();
				break;
			}//switch end
			break;
		
		//Delete an account if at zero	
		case "5":
			System.out.println("NOTICE: It should be known that you may remove an account with us only if it has a balance of ZERO US dollars.\n");
			
			List<String> storedAcctNumbers3 = new ArrayList<String>();

			//display all of user's accounts before asking for specific account number
			for(Account x : allUserAccounts) {
				storedAcctNumbers3.add(x.getAccountNum());
				System.out.println("Account Type: " + x.getAccountType() + " - Account #: " + x.getAccountNum() + " - Balance: " + x.getAccountBal() + " USD"); 
			}
			
			System.out.println("\nPlease enter the account number you wish to close: ");
			//get user input for selected account number to close
			String removeAccountNumInput = sc.nextLine();
			
			System.out.println("Are you absolutely sure that you want to REMOVE this account?\nEnter \"1\" for YES or \"2\" for NO.");
			String removeAccountVerifyInput = sc.nextLine();
			
			//verify if user wants to delete account
			switch (removeAccountVerifyInput) {
			//YES option
			case "1":
				
				//if entered account number is correct, process action
				if(storedAcctNumbers3.contains(removeAccountNumInput)) {
					
					boolean accountIsRemoved = UserService.getUserService().removeAccount(new Account(sessUserId,removeAccountNumInput)); 
					
					//on success, print success and return back to main menu
					//on fail, print fail and return back to main menu
					if(accountIsRemoved) {
						System.out.println("You have successfully closed out your account.\nReturning to main menu...\n");
						loggedInMenu();
						break;
					} else {
						System.out.println("Returning to main menu...");
						loggedInMenu();
						break;
					}
				} else {	
					System.out.println("UH OH!: You've entered an incorrect account number. Returning to main menu...\n");
					loggedInMenu();	
				}
				break;
				
			//NO option
			case "2":
				System.out.println("Not a problem. We appreciate you business.\nReturning to the main menu...");
				break;
				
			default:
				System.out.println("You've entered an invalid input. Please be sure you are only entering a '1' or a '2' as your input.\nReturning to main menu...");
				loggedInMenu();
				break;
			}//switch ends
			
			break;
		
		//log user out	
		case "6":
			
			System.out.println("Are you sure you want to log out?\nEnter \"1\" for YES or \"2\" for NO.");
			String logoutVerifyInput = sc.nextLine();
			
			switch (logoutVerifyInput) {
			case "1":
				System.out.println("You have been successfully logged out.");
				sessUserId = 0;
				//sessUsername = null;
				//sessAccounts = null;
				allUserAccounts = null;
				runGoodBank();
				break;
			case "2":
				System.out.println("Returning to main menu...\n");
				loggedInMenu();
				break;
				
			default:
				System.out.println("You've entered an invalid input. Please be sure you are only entering a '1' or a '2' as your input.\nReturning to main menu...");
				loggedInMenu();
				break;
			}
			
			break;
			
		default:
			System.out.println("\nERROR: You entered an invalid menu input. Please try again. TESTING\n");
			loggedInMenu();
			break;
		}//switch end
		
	}//loggedInMenu end
	
	
	static BufferedReader bf = null;
	static String adminUserName = null;
	static String adminPassWord = null;
	static boolean checkAdmin = false;
	
	static void readWriteBytes() throws  IOException, FileNotFoundException{
		bf = new BufferedReader(new FileReader("src\\main\\resources\\admin.properties"));
		adminUserName = bf.readLine();
		adminPassWord = bf.readLine();
			
	}
	
	static void adminLoginMenu() {
		
		try {
			readWriteBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter the number associated with the task you'd like to do: "
        		+ "\n\n1 - Admin Login \n2 - Return to User Login\n");
		
		String adminMenuInput = sc.nextLine();
		
		//if anything other than 1,2, or 3 give error message and return back to the
		switch (adminMenuInput) {
		case "1":
		case "2":
			switch (adminMenuInput){
	        case "1":
	        	System.out.println("\nPlease enter your admin login credentials:\n");
	        	System.out.println("Enter your admin username: ");
	        	loginAdminUsername = sc.nextLine();
	        	System.out.println("Enter your admin password: ");
	        	loginAdminPassword = sc.nextLine();
	        	
	        	if(adminUserName.equals(loginAdminUsername) && adminPassWord.equals(loginAdminPassword)) {
	        		checkAdmin = true;
	        	}
	        	
	        	if(checkAdmin) {
	        		adminMainMenu();
	        	}else {
	        		adminLoginMenu();
	        	}
	        	sc.close();
	        	break;
	        	
	        case "2":
	        	System.out.println("\nReturning you to the user login menu. Please wait...\n");
	        	guestMenu();
	        	break;
	        	
	    	default:
	    		System.out.println("\nUH OH!: You entered an invalid menu input. Please try again.\n");
	    		adminLoginMenu();
	    		break;
	    		
	        }//switch end
			break;
		default:
			System.out.println("\nERROR: Uh oh! You've entered an invalid menu input. Be sure that are entering a 1, 2, or 3!\n\n"
					+ "-------------------------------------------------------------------------------------------");
			runGoodBank();
			break;
		}//switch end		
	}//adminLoginMenu end
	
	static void adminMainMenu() {
	
		//get and store all user accounts in an array
		List<User> allUsers = UserService.getUserService().getAllUserDetails();
		//to store all of the users IDs
		List<Integer> storeUserIds = new ArrayList<Integer>();
		//store all user ids in array
		for(User x : allUsers) {
			storeUserIds.add(x.getUserId()); 
		}
		
		System.out.println("Hello, " + loginAdminUsername + "! What would you like to do?\n");
		System.out.println("Enter the number of the menu option you'd like to complete.");
		System.out.println("\n1 - View All Users\n2 - Create A User\n3 - Edit A User\n"
				+ "4 - Delete A User \n5 - Logout");
		
		String adminMenuInput = sc.nextLine();
		
		switch (adminMenuInput) {
		
		//view all users
		case "1":
			
			//sessAccounts = allUserAccounts;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Good Bank All Users <<<<<<<<<<<<<<<<<<<<<<<\n");
			for(User x : allUsers) {
				System.out.println("USER ID: " + x.getUserId() + " - USERNAME: " + x.getUserName() + " - FIRST NAME: " + x.getFirstName() + " - LAST NAME: " + x.getLastName()); 
			}
			System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
			adminMainMenu();
			break;
		
		//create a user
		case "2":
			System.out.println("\nYou are now creating a new user. Please enter their credentials:\n");
        	System.out.println("Enter your username: ");
        	createUsernameInput = sc.nextLine();
        	
        	boolean checkRegister = UserService.getUserService().registerCheck(new User(createUsernameInput));
        	
        	if(!checkRegister) {
        		
        		System.out.println("Enter your password: ");
	        	createPasswordInput = sc.nextLine();
	        	System.out.println("Enter your first name: ");
	        	createFirstnameInput = sc.nextLine();
        		System.out.println("Enter your last name: ");
	        	createLastnameInput = sc.nextLine();
	        	
	        	boolean wasRegistered = UserService.getUserService().registerUserProcedure(new User(createFirstnameInput, createLastnameInput, createUsernameInput, createPasswordInput));
	        	//if user was successfully registered, add a new account into the account table
	        	if(wasRegistered) {
	        		UserService.getUserService().addNewChecking(new User(createUsernameInput));
	        	}
	        	adminMainMenu();

        	}else {
        		System.out.println("\nThat username is NOT available! Try using a different usernmae. \n");
        		adminMainMenu();

        	}
			break;
		
		//edit a user
		case "3":
			
			//sessAccounts = allUserAccounts;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Good Bank All Users <<<<<<<<<<<<<<<<<<<<<<<\n");
			for(User x : allUsers) {
				System.out.println("USER ID: " + x.getUserId() + " - USERNAME: " + x.getUserName() + " - FIRST NAME: " + x.getFirstName() + " - LAST NAME: " + x.getLastName()); 
			}
			System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
			
			String changeUsernameInput = null;
			String changeFirstnameInput = null;
			String changeLastnameInput = null;
			
			System.out.println("Please enter the User ID of the user you'd like to edit:\n");
			
			String editUserIdInput = sc.nextLine();
			int editUserIdInputToInt = Integer.parseInt(editUserIdInput);
			
			if(storeUserIds.contains(editUserIdInputToInt)) {
				
				System.out.println("What do you want to edit about this user?\n");
				System.out.println("1 - Edit Username\n2 - Edit First Name\n3 - Edit Last Name\n4 - Return to Main Menu\n");
				String editUserOptionInput = sc.nextLine();
				
				switch (editUserOptionInput) {
				//edit user name
				case "1":
					System.out.println("What do you want to change this user's username to?");
					changeUsernameInput = sc.nextLine();
					boolean changedUsername = UserService.getUserService().editChangeUsername(new User(editUserIdInputToInt,changeUsernameInput,changeFirstnameInput,changeLastnameInput));
					
					if(changedUsername) {
						adminMainMenu();
					} else {
						System.out.println("The attempt to change a user's username has failed.\n");
						adminMainMenu();
					}
					break;
					
				//edit first name
				case "2":
					System.out.println("What do you want to change this user's first name to?");
					changeFirstnameInput = sc.nextLine();
					boolean changedFirstname = UserService.getUserService().editChangeFirstname(new User(editUserIdInputToInt,changeUsernameInput,changeFirstnameInput,changeLastnameInput));
					
					if(changedFirstname) {
						adminMainMenu();
					} else {
						System.out.println("The attempt to change a user's username has failed.\n");
						adminMainMenu();
					}
					break;
				//edit last name	
				case "3":
					System.out.println("What do you want to change this user's last name to?");
					changeLastnameInput = sc.nextLine();
					boolean changedLastname = UserService.getUserService().editChangeLastname(new User(editUserIdInputToInt,changeUsernameInput,changeFirstnameInput,changeLastnameInput));
					
					if(changedLastname) {
						adminMainMenu();
					} else {
						System.out.println("The attempt to change a user's username has failed.\n");
						adminMainMenu();
					}
					break;
				//cancel edit, return to main menu
				case "4":
					System.out.println("Canceling edit user. Returning to admin menu...\n");
					adminMainMenu();
					break;
					
				default:
					System.out.println("Canceling edit user. Returning to admin menu...\n");
					adminMainMenu();
					break;
				}//switch end
				
			} else {
				System.out.println("Invalid menu input. Returning to admin menu...\n");
				adminMainMenu();
			}//if condition end
			break;
			
		//delete a user
		case "4":
			
			//sessAccounts = allUserAccounts;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Good Bank All Users <<<<<<<<<<<<<<<<<<<<<<<\n");
			for(User x : allUsers) {
				System.out.println("USER ID: " + x.getUserId() + " - USERNAME: " + x.getUserName() + " - FIRST NAME: " + x.getFirstName() + " - LAST NAME: " + x.getLastName()); 
			}
			System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
			
			System.out.println("Please enter the User ID of the user you'd like to delete:\n");
			String deleteUserIdInput = sc.nextLine();
			int deleteUserIdInputToInt = Integer.parseInt(deleteUserIdInput);
			
			System.out.println("\nAre you sure you want to remove this user?:\nEnter \"1\" for YES or \"2\" for NO.\n");
        	String verifyUserDeletionInput = sc.nextLine();
        	
        	switch (verifyUserDeletionInput) {
        	//yes
        	case "1":
        		
        		if(storeUserIds.contains(deleteUserIdInputToInt)) {
        			
        			UserService.getUserService().deleteUser(new User(deleteUserIdInputToInt));
        			adminMainMenu();
        			
        		} else {
        			System.out.println("The user ID you entered was invalid. Returning to main menu...");
        			adminMainMenu();
        		}
        		break;
        		
        	//no
        	case "2":
        		System.out.println("Returning to main menu...\n");
        		adminMainMenu();
        		break;
        	//invalid input	
    		default:
    			System.out.println("Invalid input. Returning to main menu...\n");
        		adminMainMenu();
    			break;
        	}
			break;
		//logout	
		case "5":
			System.out.println("You have successfully logged out...");
			runGoodBank();
			break;
			
		//invalid input	
		default:
			System.out.println("Invalid input. Returning to main menu...\n");
    		adminMainMenu();
			break;

		}//switch end
	}//adminMainMenu end

}
