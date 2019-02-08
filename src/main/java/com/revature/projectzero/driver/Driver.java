package com.revature.projectzero.driver;

import java.util.Scanner;

import com.revature.projectzero.dao.UserDaoImplementation;
import com.revature.projectzero.models.RegUser;

public class Driver {
	UserDaoImplementation udi = new UserDaoImplementation();

	public void DriverMainMenu() {
		int input = -1;
		System.out.println(
				"Welcome to the Bank of Revature!" + 
				//art from http://www.glassgiant.com/ascii/
				"\r\n" + 
				"                                              ...   .I.  ..I..   .I     ...     \r\n" + 
				"                                            ...I..  .7.  ..I..  .I:     7..     \r\n" + 
				"                                       ..I.....I..  II.  .7?....II.  ..II..  ...\r\n" + 
				"                                   ......I:. ..I....I,.  ,I.  .,7.....II   . .7:\r\n" + 
				"                                  ..7....II....I....I.. .7~.  .7.....II.  ..:I..\r\n" + 
				"                             .......I~.......       .  . ... .7I.  .77.. ..I7...\r\n" + 
				"                              .I... ......  .       .   . .   . ...7I.....II. ..\r\n" + 
				"                             ......                              ..I..  .7I..   \r\n" + 
				"                        ..??..                                        ..7..     \r\n" + 
				"                         .....                                       ......     \r\n" + 
				"        .        ..         ..    .      .                     .                \r\n" + 
				"OOOZOO? .OZOOOOO OO.  ..O.   OO ..OOOOOOOZ. Z     OO  OOOOOOO  ?OOOOOO.         \r\n" + 
				"O.   .O..O..    ..OO  .OZ. .O.OO. .  OO.    O     OO  O..   O= ?O .   .         \r\n" + 
				"OOOOOZ+ .OOOOOO.  .O. =Z.  OO. Z:    OO.    O     OO  OOOOOZZ. ?OOOOO.          \r\n" + 
				"O. .Z$  .O..    .   O.O...+OOOOOZ..  OO.   .O..   OZ  O  .8Z.  ?O.    .         \r\n" + 
				"O    OO  OOOOOOO.   OO....Z.. ...Z.  OZ.   ..ZOOOO7. .Z.   :O  ?OOOOOO.         \r\n" +
				"\n1)Log in as a registered user\n2)Log in as a new user\n3)Log in as an administrator\n0)Quit");
		while (input == -1) {
			input = Driver.getInt(1, 3);
			if (input == 1) {
				RegUser reguser = new RegUser();
				reguser = udi.getRegUser();
				DriverRegUserMenu(reguser);
				input = -1;
			}
			if (input == 2) {
				RegUser reguser = new RegUser();
				reguser = udi.getNewUser();
				System.out.println("Account successfully created.");
				DriverRegUserMenu(reguser);
				input = -1;
			}
			if (input == 3) {
				RegUser superUser = new RegUser();
				boolean superUserCheck = udi.getSuperUser();
				if (superUserCheck == true) {
					System.out.println("Login successful.");
					SuperUserMenu(superUser);
				} else {
					System.out.println("Login unsuccessful.  Username/password don't match.");
				}
				input = -1;
			}
			if (input == 0) {
				System.out.println("Thank you for banking with Revature.");
				break;
			}
		}
		return;
	}

	private void SuperUserMenu(RegUser superUser) {
		int input = -1;
		while (input != 0) {
			System.out.println(
					"Admin Menu\n1)View all users\n2)Create a new user\n3)Delete a user\n4)Delete all users\n0)Logout");
			input = Driver.getInt(1, 4);
			if (input == 1) {
				input = udi.getAllUsers(superUser);
			}
			if (input == 2) {
				input = udi.createNewAccount(superUser);
			}
			if (input == 3) {
				input = udi.superUserdeleteAccount(superUser);
			}
			if (input == 4) {
				input = udi.superUserdeleteAll(superUser);
			}
		}
		DriverMainMenu();
		return;
	}

	public void DriverRegUserMenu(RegUser user) {
		int input = -1;
		while (input != 0) {
			System.out.println(
					"Main Menu\n1)View existing account balances\n2)Create an account\n3)Delete an account\n0)Logout");
			input = Driver.getInt(1, 3);
			if (input == 1) {
				input = udi.getAccounts(user);
			}
			if (input == 2) {
				udi.createNewAccount(user);
			}
			if (input == 3) {
				udi.deleteAccount(user);
			}
		}
		DriverMainMenu();
		return;
	}

	@SuppressWarnings("resource")
	public static int getInt(int low, int high) {
		Scanner scanner = new Scanner(System.in);
		int input = -1;
		boolean invalidInput = true;
		while (invalidInput) {
			System.out.print("Please enter a choice (" + low + "-" + high + "): ");
			try {
				input = Integer.parseInt(scanner.nextLine());
				if (input >= low && input <= high) {
					invalidInput = false;
				} else if (input == 0) {
					invalidInput = false;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid entry.  Please try again.");
			}
		}
		return input;
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.DriverMainMenu();
	}
}
