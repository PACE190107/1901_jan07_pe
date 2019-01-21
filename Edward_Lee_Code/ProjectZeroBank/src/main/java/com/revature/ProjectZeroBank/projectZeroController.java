package com.revature.ProjectZeroBank;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.BankingExceptions.OverdraftException;
import com.revature.services.UserServices;
import com.revature.model.BankAccount;
import com.revature.model.regularUser;
import com.revature.model.userAbstract;

public class projectZeroController {
	Scanner scan;
	final Logger logger = LogManager.getLogger(projectZeroController.class); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		projectZeroController pz = new projectZeroController();
		pz.runBankApplication();
	}
	public void runBankApplication() {
		scan = new Scanner(System.in);
		String input = "";
		
//		logger.error("Error #1");
		while(!input.equals("exit")) {
			System.out.print("Welcome to the JDBC bank\nIf you are a new user type in y\nif you are a returning customer type in your username\nType 'exit' to leave: ");		
			input = scan.next();
			if(input.equals("y")) { //NEW_USER 
				userAbstract user = createUser();
				//create user
				transaction(user);
			}else if(!input.equals("exit")){ //USER LOG_IN
				System.out.print("What is your password: ");
				String password = scan.next();
				userAbstract user = UserServices.getCustomerService().attemptLogIn(input, password);
				if( user != null) {
					transaction(user);
				}else {
					//log
					//throw error
				}

			}
		}
		//check for open connections and readers
	}
	private userAbstract createUser() {
		userAbstract user;
		System.out.print("What is your first name: ");
		String fname = scan.next();
		System.out.print("What is your last name: ");
		String lname = scan.next();
		System.out.print("What is your email: ");
		String email = scan.next();
		System.out.print("What would you like your username to be: ");
		String username = scan.next();
		System.out.print("What would you like your password to be: ");
		String password = scan.next();
		return user = UserServices.getCustomerService().createUser(username, password, fname, lname, email);
	}
	public void transaction(userAbstract loggedInUser) {
		int input = 0;
		System.out.println(loggedInUser.isAdmin());
		if (loggedInUser.isAdmin()) {
			while(input != 4) {
				//run through user abilities like get accounts deposit withdraw delete
				System.out.println("Welcome " + loggedInUser.getFirstName());
				System.out.println("1. View account status");
				System.out.println("2. Edit User");
				System.out.println("3. Admin tasks");
				System.out.println("4. Log out");
				System.out.print("What would you like to do: ");
				input = scan.nextInt();
				switch(input) {
				case 1 : manageAccounts(loggedInUser); break;
				case 2 : editUser(loggedInUser); break;
				case 3 : admin();break;
				case 4 : break;
				default: System.out.println("invalid input please try a number that is in range"); break;
				}
				}
		}else {
			while(input != 3) {
				//run through user abilities like get accounts deposit withdraw delete
				System.out.println("Welcome " + loggedInUser.getFirstName());
				System.out.println("1. View account status");
				System.out.println("2. Edit User");
				System.out.println("3. Log out");
				System.out.print("What would you like to do: ");
				input = scan.nextInt();
				switch(input) {
				case 1 : manageAccounts(loggedInUser); break;
				case 2 : editUser(loggedInUser); break;
				case 3 : break;
				default: System.out.println("invalid input please try a number that is in range"); break;
				}
			}
		}
	}
	private void admin() {
		//C R U D
		int input;
		System.out.println("1. Create User");
		System.out.println("2. View All Users");
		System.out.println("3. Edit User");
		System.out.println("4. Delete User");
		input = scan.nextInt();
		switch(input) {
		case 1: createUser(); break;
		case 2: viewUsers();break;
		case 3: editOtherUser();break;
		case 4: deleteUser();break;
		}
	}
	private void deleteUser() {
		String deletedUser;
		System.out.print("WHOM would you like to delete: ");
		deletedUser = scan.next();
		UserServices.getCustomerService().deleteUser(deletedUser);
	}
	public void manageAccounts(userAbstract currentUser) {
		System.out.println(currentUser.getClass());
		int i = 0;
		List<BankAccount> accounts =  UserServices.getCustomerService().getUserAccounts(currentUser.getUserID());
		for(BankAccount b : accounts) {
			System.out.println(i++ + ": " +b);
		}
		System.out.println(i+": Make a new one");
		System.out.print("Which account do you want to use: ");
		int choice = scan.nextInt();
		if(choice < accounts.size() && choice>= 0) {
			System.out.println("1. Withdraw Cash");
			System.out.println("2. Deposit Cash");
			System.out.println("3. Delete Account");
			System.out.println("4. Cancel");
			System.out.print("What do you want to do: ");
			int option = scan.nextInt();
			switch(option) {
			case 1:	try{
				withdrawCash(accounts.get(choice));
			}catch(OverdraftException oe){
				System.out.println("You don't have enough money to do that");
				logger.info(currentUser.getUsername() +"Overdraft Stopped");
			}catch(Exception e) {
				logger.error(e.toString());
			} break;
			case 2: depositCash(accounts.get(choice)); break;
			case 3: deleteAccount(accounts.get(choice)); break;
			case 4: break;
			default: System.out.println("Try Again");
			}
		}else if(choice == accounts.size()){
			System.out.println("What do you want to call it: ");
			String accountName = scan.next();
			currentUser.addBankAccount(UserServices.getCustomerService().CreateAccount(currentUser.getUsername().toString(), accountName));
		}
	}
	public void withdrawCash(BankAccount account) throws OverdraftException {	//throws exception
		System.out.print("How much cash would you like to withdraw: ");
		Double quantity = scan.nextDouble();
		if( quantity > account.getBalance()) {
			throw new OverdraftException();
		}else {
			UserServices.getCustomerService().withdrawCash(account.getAccountID(), quantity);
			account.withdrawl(quantity);
		}
	}
	public void depositCash(BankAccount account) { //throws exception
		System.out.print("How much cash would you like to deposit: ");
		Double quantity = scan.nextDouble();
		account.deposit(quantity);
		UserServices.getCustomerService().depositCash(account.getAccountID(), quantity);
	}
	public void deleteAccount(BankAccount account) { //throws exception
		if(account.getBalance() == 0.0) {
			UserServices.getCustomerService().deleteAccount(account.getAccountID());
		}else {
			System.out.println("You can only delete an account if it has a 0 balance");
		}
	}
	public void editUser(userAbstract currentUser) {
		System.out.println("What do you want you new password to be: ");
		String s = scan.next();
		UserServices.getCustomerService().updateUserPassword(s, currentUser.getUsername().toString());
	}
	public void editOtherUser() {
		System.out.print("Which user will this affect: ");
		String username = scan.next();
		System.out.print("What is their new password: ");
		String password = scan.next();
		UserServices.getCustomerService().updateUserPassword(password, username );
	}
	public void viewUsers() {
		System.out.println("Before Get");
		List<String> users = UserServices.getCustomerService().getAllUsers();
		System.out.println("AfterGet");
		int i = 0;
		for(String s: users) {
			System.out.println(i++ +": " + s);
		}
		
	}

}
