package com.revature.Project_0;

import java.util.Scanner;

import com.revature.Exceptions.UsernameAlreadyExistException;

public class UserInterface {
	static Scanner in = new Scanner(System.in);

	private static Bank bank;

	public static void main(String[] args) {
		bank = new MyBank();
		start();
	}

	public static void start() {
		// login();
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
			login = bank.login(username, password);
		} while (!login);

		options();

	}

	public static void register() {
		String username, password, confirmation;
		boolean login = false;
		boolean uniquename = false;

		do {
			System.out.println("Please input your username");
			username = in.nextLine();
			try {
				bank.checkUsername(username);
				uniquename = true;
			} catch (UsernameAlreadyExistException e) {
				//e.printStackTrace();
				uniquename = false;
			}
			if (!uniquename) {
				do {
					System.out.println("Pleae input your password");
					password = in.nextLine();
					System.out.println("Pleae confirm your password");
					confirmation = in.nextLine();
					if (password.equals(confirmation)) {
						login = bank.register(username, password);
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
				bank.deposite(100);
			} else if (option == 2) {
				bank.withdraw(100);
			} else if (option == 3) {
				signout = true;
			}
		} while (!signout);
	}
}
