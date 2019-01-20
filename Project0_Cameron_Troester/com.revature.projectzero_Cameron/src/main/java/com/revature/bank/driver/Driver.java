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
			log.info("Welcome to the bank for low budget people");
			log.info("\n[1]To create a new account type 'create'. \n"
					+ "[2]To log in, type 'log in'. \n"
					+ "[3]To log off, type 'exit'.");
			input = console.nextLine();
			input = input.toLowerCase();
			if (input.equalsIgnoreCase("create") || input.equalsIgnoreCase("1")) {
				while (!validInput) {
					validInput = View.createView();
				}
			} else if (input.equalsIgnoreCase("log in") || input.equalsIgnoreCase("2")) {
				while (!loggedIn) {
					currUser = View.logInView();
					if (currUser != null) {
						loggedIn = true;
					}
				}
				while (loggedIn) {
					loggedIn = View.loggedInMethod(currUser);
					if (!loggedIn) {
						exit = true;
					}
				}
			} else if (input.equalsIgnoreCase("exit")) {
				exit = true;
			} else {
				log.info("Invalid input. Please try again.");
			}
		}
		log.info("logged out successfully");

		console.close();
		
	}

}