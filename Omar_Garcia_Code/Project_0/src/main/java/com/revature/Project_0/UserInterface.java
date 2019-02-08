package com.revature.Project_0;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.Exceptions.AccountNotFoundException;
import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.EmptyAccountException;
import com.revature.Exceptions.EmptyUsersException;
import com.revature.Exceptions.OverDraftException;
import com.revature.Exceptions.UserNotFoundException;
import com.revature.Exceptions.WithdrawException;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;
import com.revature.services.BankService;
import com.revature.services.UserService;

public class UserInterface {
	static Scanner in = new Scanner(System.in);
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	private static String transactions = "";
	private static BankService bank;

	public static void main(String[] args) {
		try {
			start();
			in.close();
		} catch (SQLException e) {
			log.error("SQL ERROR");
		}
	}

	public static void start() throws SQLException {
		boolean running = true;
		do {
			System.out.println("Welcome to Your Bank!\nWhat would you like to do?\n1.) Login\n2.) Register\n3.) Exit");
			int selection = getUserInt();
			if (selection == 1) {
				login();
			} else if (selection == 2) {
				register();
			} else if (selection == 3) {
				running = false;
			}
		} while (running);
	}

	public static void login() throws SQLException {
		String username;
		String password;
		boolean login = false;

		do {
			System.out.println("Please input your username");
			username = in.nextLine();
			System.out.println("Please input your password");
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
		transactions = "";
		boolean signout = false;
		do {
			System.out.println("Welcome "+ bank.getCurrentUser().getFirstName() + " "+ bank.getCurrentUser().getLastName()+ ", \nPlease select an option " + "\n1.) Deposite        4.) Create Account"
					+ "\n2.) Withdraw        5.) Delete Account" + "\n3.) View accounts   6.) View Transactions"
					+ "\n7.) Log off");
			if (bank.superUser() == 1) {
				System.out.println("------------------------------------------\n" + "            Super User Options\n"
						+ "------------------------------------------\n"
						+ "8.) View users      10.) Update user\n9.) Create users    11.) Delete all users");
			}
			int option = getUserInt();
			if (option == 1) {
				deposit();
			} else if (option == 2) {
				withdraw();
			} else if (option == 3) {
				try {
					bank.getAccounts(0);
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
			} else if (option == 8) {
				try {
					System.out.printf("%-10.10s %-10.10s %-10.10s %-10.10s %-10.10s%n\n", "ID", "|FIRSTNAME",
							"|LASTNAME", "|USERNAME", "|SUPERUSER");
					UserService.getUserService().viewAllUsers();
				} catch (EmptyUsersException e) {
					System.out.println("No Users Found");
				}
			} else if (option == 9) {
				register();
				signout = true;
			} else if (option == 10) {
				updateUser();
			} else if (option == 11) {
				UserService.getUserService().deleteAllUsers(); // Delete all non super users
			}
		} while (!signout);
	}

	private static void updateUser() throws SQLException {
		try {
			UserService.getUserService().viewAllUsers();
			System.out.println("Which User Would you like to update");
			int selection = getUserInt();
			try {
				User user = UserService.getUserService().getUser(selection);
				boolean done = false;
				boolean newPassword = false;
				do {
					System.out.println("Change 1.)First name  2.)Last name" + "\n3.) Username   4.)Password "
							+ "\n5.) SuperUser  6.)Done");
					selection = getUserInt();
					if (selection == 1) {
						System.out.println("Please input new First name\nCurrent : " + user.getFirstName());
						user.setFirstName(in.nextLine());
					} else if (selection == 2) {
						System.out.println("Please input new Last name\nCurrent : " + user.getLastName());
						user.setLastName(in.nextLine());
					} else if (selection == 3) {
						System.out.println("Please input new Username\nCurrent : " + user.getUserName());
						user.setUserName(in.nextLine());
					} else if (selection == 4) {
						System.out.println("Please input new  password");
						user.setPassWord(in.nextLine());
						newPassword = true;
					} else if (selection == 5) {
						System.out.println("Make user  SuperUser?\n1.) Yes\n2.) No");
						user.setSuperuser(getUserInt());
						in.nextLine();
					} else if (selection == 6) {
						done = true;
					}
				} while (!done);
				System.out.println("Updating");
				UserService.getUserService().updateUser(user, newPassword);
				System.out.println("Done updating");
			} catch (UserNotFoundException e) {
				log.warn("User could not be found");
			}
		} catch (EmptyUsersException e1) {
			System.out.println("No User Found");
		}
	}

	private static void deleteAccount() throws SQLException {
		try {
			bank.getAccounts(1);
			System.out.println("Which account would you like to delete?");
			int account = getUserInt();
			bank.deleteAccount(account);
			// transactions += bank.getCurrentUser().getFirstName() + " " +
			// bank.getCurrentUser().getLastName() + " deleted an Account";
		} catch (EmptyAccountException e) {
			System.out.println("Account must have a balance of zero");
		}
	}

	private static void createAccount() {
		System.out.println(
				"What kind of acount would you like to create\n" + "1.) Checkings\n" + "2.) Savings\n" + "3.) Go Back");
		int response = getUserInt();
		if (response == 1) {
			System.out.print("How much would you like to deposite\n$");
			int amount = getUserInt();
			try {
				bank.createAccount("Checkings", amount);
				// transactions += bank.getCurrentUser().getFirstName() + " " +
				// bank.getCurrentUser().getLastName() + " Created an new Checkings Account";
			} catch (SQLException e) {
				log.error("SQL error while creating account");
			}
		} else if (response == 2) {
			System.out.print("How much would you like to deposite\n$");
			int amount = getUserInt();
			try {
				bank.createAccount("Savings", amount);
				// transactions += bank.getCurrentUser().getFirstName() + " " +
				// bank.getCurrentUser().getLastName() + " Created an new Savings Account";
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
				bank.getAccounts(0);
				System.out.println("\nWhich account would like to deposit into?\n0.) Exit\n");
				int account = getUserInt();
				if (account == 0) {
					done = true;
				} else
					try {
						if (bank.isAnAccount(account)) {
							System.out.println("How much would you like to deposit!\n0.) Exit\n");
							int amount = getUserInt();
							if (amount > 0) {
								try {
									bank.deposit(amount, account);
									done = true;
									transactions += "Deposited " + amount + " into account " + account + "\n";
									try {
										bank.getAccounts(0);
									} catch (EmptyAccountException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (DepositFailedException e) {
									done = false;
								}
							} else if (amount < 0) {
								log.warn("Deposits should be more than 0!");
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
				bank.getAccounts(0);
				System.out.println("\nWhich account would like to withdraw from?\n0.) Exit\n");
				int account = getUserInt();
				if (account == 0) {
					done = true;
				} else
					try {
						if (bank.isAnAccount(account)) {
							do {
								System.out.println("How much would you like to withdraw?\n0.) Exit\n");
								int amount = getUserInt();
								if (amount > 0) {
									try {
										bank.withdraw(amount, account);
										done = true;
										transactions += "Withdrew " + amount + " from account " + account + "\n";
										try {
											bank.getAccounts(0);
										} catch (EmptyAccountException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} catch (OverDraftException e) {
										System.out.println("over draft");
										done = false;
									} catch (AccountNotFoundException e) {
										log.warn("Account not found");
									} catch (WithdrawException e) {
										log.error("An error occured withdrawing");
									}
								} else if (amount < 0) {
									log.warn("Withdraw should be more than 0!");
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

	public static int getUserInt() {
		int selection = 0;
		boolean done = false;
		do {
			if (in.hasNextInt()) {
				selection = in.nextInt();
				in.nextLine();
				return selection;
			} else {
				System.out.println("Wrong Input type!");
				in.nextLine();
			}
		} while (!done);
		return selection;
	}
}