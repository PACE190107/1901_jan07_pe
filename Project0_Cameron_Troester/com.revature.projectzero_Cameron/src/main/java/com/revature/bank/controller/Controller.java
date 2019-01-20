package com.revature.bank.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.bank.dao.*;
import com.revature.bank.view.*;

public class Controller {

	private static Set<String> userNameSet = new HashSet<>();
	final static Logger log = Logger.getLogger(Controller.class);
	private static Scanner console = new Scanner(System.in);

	private static void loadUsers() {
		UserDaoImp userDI = new UserDaoImp();
		for (User u : userDI.getUsers()) {
			userNameSet.add(u.getUserName());
		}
	}

	private Controller() {
		super();
	}

	public static Account createAccount(String uName, String fName, String lName, String psWord, double bal) {

		@SuppressWarnings("resource")
		Scanner console = new Scanner(System.in);
		loadUsers();
		int setSize = userNameSet.size();
		boolean isNameAvailable = false;
		userNameSet.add(uName);

		while (!isNameAvailable) {
			if (userNameSet.size() == setSize) {
				System.out.println("That user name has already been taken. Please enter a different user name.");
				uName = console.nextLine();
				userNameSet.add(uName);
			}
			if (userNameSet.size() != setSize) {
				isNameAvailable = true;
			}
		}
		Account newAcc = new Account(uName, bal);
		User newUser = new User(uName, fName, lName, psWord);

		UserDAO userDI = new UserDaoImp();
		AccountDAO accDI = new AccountDaoImp();
		int accCreated = 0;
		int userCreated = userDI.createUser(newUser);
		if (userCreated > 0) {
			accCreated = accDI.createAccount(newAcc);
		}
		if (accCreated > 0) {
			System.out.println("New account created: " + newAcc.toString());
		} else {
			System.out.println("Account failed to be created.");
			return null;
		}
		return newAcc;
	}

	public static Account loadAccount(String uName) {
		AccountDAO accDI = new AccountDaoImp();
		return accDI.getAccountByUser(uName);
	}

	public static boolean withdrawal(double withdrawal, Account account) {

		if (withdrawal > account.getBalance()) {
			System.out.println("Withdrawal failed. You requested an amount greater than your account balance. Your balance: "
					+ account.getBalance() + " Amount requested: " + withdrawal);
			return false;
		}else if(withdrawal < 0) {
			System.out.println("Withdrawal failed. You requested a negative number to withdrawal. Your balance: "
					+ account.getBalance() + " Amount requested: " + withdrawal);
			return false;
		}
		account.setBalance(account.getBalance() - withdrawal);
		AccountDAO accDI = new AccountDaoImp();
		int accUpdated = accDI.updateAccount(account);
		if (accUpdated > 0) {
			return true;
		}
		log.info("withdrawal failed.");
		return false;
	}

	public static boolean deposit(double deposit, Account account) {
		
		if(deposit < 0) {
			System.out.println("Deposit failed; You requested a negative number to deposit. Your balance: "
					+ account.getBalance() + " Amount requested: " + deposit);
			return false;
		}
		account.setBalance(account.getBalance() + deposit);
		AccountDAO accDI = new AccountDaoImp();
		int accUpdated = accDI.updateAccount(account);
		if (accUpdated > 0) {
			return true;
		}
		return true;
	}

	public static boolean logIn(String userName) throws InterruptedException, IOException {
		loadUsers();
		Account acc = null;
		while (userName == null) {
			System.out.println("No user name entered. Please enter a user name.");
			userName = console.nextLine();
		}
		if (userNameSet.contains(userName)) {
			acc = validate(userName);
			if (acc != null) {
				System.out.println("logged in successfully as: " + acc.toString());
				return true;
			}
		} else {
			while (!userNameSet.contains(userName)) {
				System.out.println("User name not found. Please enter your user name again."
						+ " If you do not have an account,\n then type \"create\" to create a new account.");
				userName = console.nextLine();
				if (userName.equalsIgnoreCase("create")) {
					return View.createView();
				}
				if (userNameSet.contains(userName)) {
					acc = validate(userName);
					if (acc != null) {
						System.out.println("logged in successfully as: " + acc.toString());
						return true;
					}
				}
			}
		}
		return false;
	}

	public static Account validate(String userName) {
		UserDAO user = new UserDaoImp();
		Account acc = null;
		String pswd = null;
		
		System.out.println("Enter your password.");
		pswd = console.nextLine();
		if(user.getUserByName(userName).getPsWord().equals(pswd)) {
			acc = loadAccount(userName);
		}
		return acc;
	}
	
	public static int deleteUser(String userName) {
		
		UserDAO userDI = new UserDaoImp();
		return userDI.deletUserByName(userName);
	}
}