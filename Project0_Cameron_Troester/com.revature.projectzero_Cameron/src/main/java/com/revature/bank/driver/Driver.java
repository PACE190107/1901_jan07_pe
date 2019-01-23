package com.revature.bank.driver;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.view.View;
import com.revature.model.Account;

public class Driver {
	private static Scanner console = new Scanner(System.in);
	final static Logger log = Logger.getLogger(Driver.class);

	/*
	 * input check spelling parsing issues Decimal issues implement view sort out
	 * why Ron can't log in how should database be connected? Persistence?
	 * 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {

		String input = null;
		boolean validInput = false;
		boolean loggedIn = false;
		boolean exit = false;
		Account currUser = null;

		while (!exit) {
			System.out.println("Welcome to the bank for low budget people");
			System.out.println("\n[1]To create a new account type 'create'. \n"
					+ "[2]To log in, type 'log in'. \n"
					+ "[3]To log off, type 'exit'.");
			input = console.nextLine();
			input = input.toLowerCase();
			if (input.equalsIgnoreCase("create") || input.equalsIgnoreCase("1")) {
				while (!validInput) {
					validInput = View.showView();
				}
			} else if (input.equalsIgnoreCase("log in") || input.equalsIgnoreCase("2")) {
				while (!loggedIn) {
					currUser = View.logView();
					if (currUser != null) {
						loggedIn = true;
					}
				}
				while (loggedIn) {
					loggedIn = View.loggedIn(currUser);
					if (!loggedIn) {
						//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
						System.out.println("Would you like to log back in? (yes/no)");
						input = console.nextLine();
						if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
							input = null;
							validInput = false;
							loggedIn = false;
							exit = false;
							currUser = null;
							//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

						}
						else{
							exit = true;
						}
						
					}
				}
			} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("3")) {
				
				exit = true;
				
			}
			else {
				System.out.println("Invalid input, please try again");
			}
		}
		System.out.println("Thanks for choose our bank, come back soon");

		console.close();
		
	}

}