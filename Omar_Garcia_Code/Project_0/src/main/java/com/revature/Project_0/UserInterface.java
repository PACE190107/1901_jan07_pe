package com.revature.Project_0;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.Exceptions.UsernameAlreadyExistException;
import com.revature.dao.BankDao;
import com.revature.dao.BankImplementation;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;
import com.revature.services.*;

public class UserInterface {
	static Scanner in = new Scanner(System.in);
	final static Logger log =Logger.getLogger(UserDaoImplementation.class);
	
	private static User currentUser;
	private static BankService bank;

	public static void main(String[] args) {
	start();
	}

	public static void start() {
		//login();
		register();
	}

	public static void login() {
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
				if(currentUser.getFirstName() == null) {
					System.out.println("Username/Password combination not found\nPlease try again");
				}
				else {
					bank = BankService.getBankService(currentUser);
					login = true;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} while (!login);

		options();

	}

	public static void register() {
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
							if(currentUser.getFirstName() == null) {
								System.out.println("Username/Password combination not found\nPlease try again");
							}
							else {
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

	public static void options() {
		boolean signout = false;
		do {
			System.out.println("Please select an option " + "\n1.) Deposite" + "\n2.) Withdraw" + "\n3.) Exit");
			int option = in.nextInt();
			if (option == 1) {
				//bank.deposite(100);
			} else if (option == 2) {
				//bank.withdraw(100);
			} else if (option == 3) {
				signout = true;
			}
		} while (!signout);
	}
}
