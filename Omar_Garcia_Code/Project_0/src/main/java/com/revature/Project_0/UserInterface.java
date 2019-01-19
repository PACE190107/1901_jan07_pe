package com.revature.Project_0;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.UsernameAlreadyExistException;
import com.revature.dao.BankDao;
import com.revature.dao.BankImplementation;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;
import com.revature.services.*;

public class UserInterface {
	static Scanner in = new Scanner(System.in);
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);

	private static BankService bank;

	public static void main(String[] args) {
		try {
			start();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void start() throws SQLException {
		boolean running = true;
		do {
			// in.nextInt();
			login();
			// register();
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
			System.out.println("Please select an option " + "\n1.) Deposite" + "\n2.) Withdraw" + "\n3.) Exit");
			int option = in.nextInt();
			if (option == 1) {
				deposit();
			} else if (option == 2) {
				withdraw();
			} else if (option == 3) {
				signout = true;
			}
		} while (!signout);
	}

	public static void deposit() throws SQLException {
		boolean failed = true;
		do {
			bank.getAccounts();
			System.out.println("Which account would like to deposit into?\n0.) go back\n");
			int account = in.nextInt();
			if (account == 0) {
				failed = false;
			} else {
				try {
					bank.deposit(100, account);
					failed = false;
				} catch (DepositFailedException e) {
					failed = true;
					e.printStackTrace();
				}
			}
		} while (failed);
	}

	public static void withdraw() throws SQLException {
		boolean failed = true;
		do {
			bank.getAccounts();
			System.out.println("Which account would like to withdraw from?\n0.) go back\n");
			int account = in.nextInt();
			if (account == 0) {
				failed = false;
			} else {
				try {
					bank.withdraw(100, account);
					failed = false;
				} catch (DepositFailedException e) {
					failed = true;
					e.printStackTrace();
				}
			}
		} while (failed);
	}
}
