package com.revature.Project_0;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.Exceptions.*;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;
import com.revature.services.*;

public class UserInterface {
	static Scanner in = new Scanner(System.in);
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	private static String transactions = "";

	private static BankService bank;

	public static void main(String[] args) {
		try {
			start();
		} catch (SQLException e) {
			log.error("SQL ERROR");
		}
	}

	public static void start() throws SQLException {
		boolean running = true;
		do {
			// in.nextInt();
			login();
			// register();
			running = false;
		} while (running);
	}

	public static void login() throws SQLException {
		String username;
		String password;
		boolean login = false;

		do {
			System.out.println("Please input your username");
			username = in.nextLine();
			System.out.println("Pleae input your password");
			password = in.nextLine();
			try {
				User currentUser = UserService.getUserService().login(username, password);
				if (currentUser.getFirstName() == null) {
					System.out.println("Username/Password combination not found\nPlease try again");
				} else {
					bank = BankService.getBankService(currentUser);
					login = true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} while (!login);

		options();

	}

	public static void register() throws SQLException {
		String first, last, username, password, confirmation;
		boolean login = false;
		boolean uniquename = true;

		System.out.println("Please input your first name");
		first = in.nextLine();
		System.out.println("Please input your last name");
		last = in.nextLine();

		do {
			System.out.println("Please input your username");
			username = in.nextLine();

			if (uniquename) {
				do {
					System.out.println("Pleae input your password");
					password = in.nextLine();
					System.out.println("Pleae confirm your password");
					confirmation = in.nextLine();
					if (password.equals(confirmation)) {
						try {
							User currentUser = UserService.getUserService().register(first, last, username, password);
							if (currentUser.getFirstName() == null) {
								System.out.println("Username/Password combination not found\nPlease try again");
							} else {
								bank = BankService.getBankService(currentUser);
								login = true;
							}
						} catch (SQLException e) {

							e.printStackTrace();
						}
					} else {
						System.out.println("Passwords don't match, please try again");
					}
				} while (!password.equals(confirmation));
			} else {
				System.out.println("Username " + username + " is already taken!");
			}
		} while (!login);

		options();
	}

	public static void options() throws SQLException {
		boolean signout = false;
		do {
			System.out.println("\nPlease select an option " + "\n1.) Deposite        4.) Create Account"
					+ "\n2.) Withdraw        5.) Delete Account" + "\n3.) View accounts   6.) View Transactions"
					+ "\n7.)Exit");
			int option = in.nextInt();
			if (option == 1) {
				deposit();
			} else if (option == 2) {
				withdraw();
			} else if (option == 3) {
				try {
					bank.getAccounts();
				} catch (EmptyAccountException e) {
					log.warn("User currently has no accounts");
				}
			} else if (option == 4) {
				createAccount();
			} else if (option == 5) {
				deleteAccount();
			} else if (option == 6) {
				System.out.println(transactions);
			} else if (option == 7) {
				signout = true;
			}
		} while (!signout);
	}

	private static void deleteAccount() {

	}

	private static void createAccount() {
		System.out.println(
				"What kind of acount would you like to create\n" + "1.) Checkings\n" + "2.) Savings\n" + "3.) Go Back");
		int response = in.nextInt();
		if (response == 1) {
			System.out.print("How much would you like to deposite\n$");
			int amount = in.nextInt();
			try {
				bank.createAccount("Checkings", amount);
			} catch (SQLException e) {
				log.error("SQL error while creating account");
			}
		} else if (response == 2) {
			System.out.print("How much would you like to deposite\n$");
			int amount = in.nextInt();
			try {
				bank.createAccount("Savings", amount);
			} catch (SQLException e) {
				log.error("SQL error while creating account");
			}
		} else if (response == 3) {
		} else {
			log.warn("Invalid response");
		}

	}

	public static void deposit() throws SQLException {
		boolean done = false;
		do {
			try {
				bank.getAccounts();
				System.out.println("Which account would like to deposit into?\n0.) Exit\n");
				int account = in.nextInt();
				if (account == 0) {
					done = true;
				} else
					try {
						if (bank.isAnAccount(account)) {
							System.out.println("How much would you like to deposit!\n )0.) Exit\n");
							int amount = in.nextInt();
							if (amount > 0) {
								try {
									bank.deposit(amount, account);
									done = true;
									transactions += "Deposited " + amount + " into account " + account;
								} catch (DepositFailedException e) {
									done = false;
								}
							} else {
								done = true;
							}
						}
					} catch (AccountNotFoundException e) {
						log.warn("Account not found");
					}
			} catch (EmptyAccountException e1) {
				done = true;
			}
		} while (!done);
	}

	public static void withdraw() throws SQLException {
		boolean done = false;
		do {
			try {
				bank.getAccounts();
				System.out.println("Which account would like to withdraw from?\n0.) Exit\n");
				int account = in.nextInt();
				if (account == 0) {
					done = true;
				} else
					try {
						if (bank.isAnAccount(account)) {
							do {
								System.out.println("How much would you like to withdraw?\n0.) Exit\n");
								int amount = in.nextInt();
								if (amount > 0) {
									try {
										bank.withdraw(amount, account);
										done = true;
										transactions += "Withdrew " + amount + " from account " + account;
									} catch (OverDraftException e) {
										System.out.println("over draft");
										done = false;
									} catch (AccountNotFoundException e) {
										log.warn("Account not found");
									} catch (WithDrawException e) {
										log.error("An error occured withdrawing");
									}
								} else {
									done = true;
								}
							} while (!done);
						}
					} catch (AccountNotFoundException e) {
						log.warn("Account not found");
					}
			} catch (EmptyAccountException e) {
				done = true;
				log.error("No accounts where found for this user");
			}
		} while (!done);
	}
}
