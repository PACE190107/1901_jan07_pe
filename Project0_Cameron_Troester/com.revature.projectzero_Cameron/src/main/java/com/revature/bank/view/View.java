package com.revature.bank.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.controller.Controller;
import com.revature.model.Account;

public class View {

	private static Scanner console = new Scanner(System.in);
	final static Logger log = Logger.getLogger(View.class);
	private static DecimalFormat number = new DecimalFormat(".##");

	private View() {
		super();
	}

	
	public static boolean showView() throws InterruptedException, IOException {
		ArrayList<String> accountCreate = new ArrayList<>();
		String input = null;
		//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Enter your prefered user name");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z0-9]*$")) {
			System.out.println("Invalid input. Please enter a valid user name");
			input = console.nextLine();
		}
		accountCreate.add(input);

		System.out.println("Enter your first name.");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z]*$")) {
			System.out.println("Invalid input. Please enter a valid first name");
			input = console.nextLine();
		}
		accountCreate.add(input);

		System.out.println("Enter your last name.");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z]*$")) {
			log.info("Invalid input. Please enter a valid last name");
			input = console.nextLine();
		}
		accountCreate.add(input);
		System.out.println(
				"Create your desired password");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z0-9]*$")) {
			System.out.println("Invalid input. Please enter a valid password");
			input = console.nextLine();
		}
		accountCreate.add(input);

		System.out.println("What will be your initial deposit?"
				+ "\nIt must be greater than 5 dollars.");
		if (!input.matches("^\\d+\\.\\d{0,2}$")) {
			input = input + ".00";
		}
		while (!input.matches("^\\d+\\.\\d{0,2}$") || (Double.parseDouble(input) <= 5)) {
			
			input = console.nextLine();
			if (!input.matches("^\\d+\\.\\d{0,2}$")) {
				input = input + ".00";
			}
			
			if(!input.matches("^\\d+\\.\\d{0,2}$") || Double.parseDouble(input) <= 5) {
					System.out.println("Invalid input, please try again.");
			}
		}
		accountCreate.add(input);
	//	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

		Account newAcc = Controller.createAccount(accountCreate.get(0), accountCreate.get(1), accountCreate.get(2),
				accountCreate.get(3), Double.parseDouble(accountCreate.get(4)));
		if (newAcc != null) {
			return true;
		}
		return false;
	}

	public static Account logView() throws InterruptedException, IOException {

		Account acc;
		System.out.println("Enter your User name for log in.");
		String enteredU = console.nextLine();
		if (Controller.logIn(enteredU)) {
			acc = Controller.loadAccount(enteredU);
			return acc;
		}
		return null;

	}

	public static boolean loggedIn(Account acc) throws InterruptedException, IOException {
		String input = null;
		boolean withdrawal = false;

		System.out.println(
				"\n[1]Type \"deposit\" to make a deposit. "
				+ "\n[2]Type \"withdrawal\" to make a withdrawal. "
				+ "\n[3]Type \"exit\" to log out.");
		input = console.nextLine();
		if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("3")) {
			return false;
		} else if (input.equalsIgnoreCase("withdrawal") || input.equalsIgnoreCase("2")) {
			System.out.println("Enter a numeric value greater than 0.01 to withdrawal");
			input = console.nextLine();
			if (!input.matches("^\\d+\\.\\d{0,2}$")) {
				input = input + ".00";
			}
			while (!input.matches("^\\d+\\.\\d{0,2}$") && (Double.parseDouble(input) <= 0.01)) {
				System.out.println("Invalid input. Please enter a valid number to withdrawal.");
				input = console.nextLine();
			}
			withdrawal = Controller.withdrawal(Double.parseDouble(input), acc);
			if (withdrawal) {
			//	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("Withdrawal successful. New Balance: $" + number.format(acc.getBalance()));
				return true;
			} else {
			//	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("Withdrawal failed. Current Balance: $" + number.format(acc.getBalance()) + " amount requested: "
						+ input);
				return true;
			}
		} else if (input.equalsIgnoreCase("deposit") || input.equalsIgnoreCase("1")) {
			System.out.println("Enter a numeric value greater than 0.01 to deposit");
			input = console.nextLine();
			if (!input.matches("^\\d+\\.\\d{0,2}$")) {
				input = input + ".00";
			}
			while (!input.matches("^\\d+\\.\\d{0,2}$") && (Double.parseDouble(input) <= 0.01)) {
				System.out.println("Invalid input. Please enter a valid number to deposit.");
				input = console.nextLine();
			}

			if (Controller.deposit(Double.parseDouble(input), acc)) {
				//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("Deposit successful. New Balance: $" + number.format(acc.getBalance()));
				return true;
			} else {
			//	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("Deposit failed. Current Balance: $" + number.format(acc.getBalance()) + " amount requested: "
						+ input);
				return true;
			}
		} else {
			log.info("Invalid request. try again");
			return true;
		}
	}
}