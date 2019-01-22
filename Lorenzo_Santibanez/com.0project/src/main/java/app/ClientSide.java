package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.proj0.dao.CustomerDaoImplementation;
import com.proj0.models.Customer;
import com.proj0.services.CustomerService;

public class ClientSide {
	final static Logger log = Logger.getLogger(CustomerDaoImplementation.class);
	
	public ClientSide() {
		
	}
	
	//Method is used to ask user if they would like to create a user or log in with username and password.
	public void createOrNot() {
		int response;
		String s = "0: Exit\n" + "1: Log In with username/password\n" + "2: Create new account\n";
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println(s);
		response = Integer.parseInt(reader.next());
		
		while(response != 0) {
			
			if(response == 1) {
				System.out.println("Log In");
				insertUserPass(reader);
			}else if(response == 2) {
				System.out.println("create new");
				createUser(reader);
			}else if(response > 2 || response < 0) {
				System.out.println("INCORRECT RESPONSE. TRY AGAIN.\n");
			}
			
			System.out.println(s);
			while(!(reader.hasNextLine())) {	
			}
			response = Integer.parseInt(reader.next());
			
		}
		reader.close();//closes scanner once it is done.
		
		
	}
	
	public void createUser(Scanner reader) {
		String username;
		String password;
		String firstName;
		String lastName;
		int startDeposit;
		boolean insert;
		
		
		System.out.println("Insert First name: \n");
		firstName = reader.next();
		
		System.out.println("Insert Last name: \n");
		lastName = reader.next();
		
		System.out.println("Insert username: \n");
		username = reader.next();
		
		System.out.println("Insert password: \n");
		password = reader.next();
		
		System.out.println("How much money would you like to start your new account with? \n");
		startDeposit = reader.nextInt();
		
		insert = CustomerService.getCustomerService().registerCustomerProcedure(new Customer(4, firstName, lastName, username, password, startDeposit));
		if(insert == true) {
			System.out.println("New User is created.");
		}else {
			System.out.println("Not created.");
		}
	}
	
	//Method asks to submit username and password.
	public void insertUserPass(Scanner reader) {
		String user;
		String pass;
		boolean superUser;
		
		
		System.out.println("Please insert Username:");
		user = reader.next();
		
		System.out.println("Please insert Password:");
		pass = reader.next();
		
		superUser = isSuperUser(user,pass);
		
		
		while(!(usernameMatchesPassword(user, pass)) && !(isSuperUser(user, pass)))
		{
			System.out.println("Wrong Username Or Password. Try Again.\n");
			System.out.println("Please insert Username:");
			user = reader.next();
			
			System.out.println("Please insert Password:");
			pass = reader.next();	
		
			}
		
		
		
		
		superUser = isSuperUser(user,pass);
		
		if(superUser == true) {
			superUserCredentials(reader, user);
		}else {
			nonSuperUserCredentials(user, pass, reader);
		}
		
		
	}
	
	//Method used to check if user is superUser.
	public boolean isSuperUser(String user, String pass) {
		
		Properties prop = new Properties();
		InputStream input = null;
		String superUsername;
		String superPass;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		superUsername = prop.getProperty("superUsername");
		//System.out.println(superUsername);
		superPass = prop.getProperty("superPass");
		//System.out.println(superPass);
		//check to see if userName and password provided matches the superUser on file. 
		if(user.equals(superUsername) && pass.equals(superPass)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void superUserCredentials(Scanner reader, String user) {
		System.out.println("superUserCredentials");
		String s = "What would you like to do Super User? Choose one of the numerical options: \n" + "0: View"
				+ " all accounts\n" + "1: Create a new user\n" + "2: Modify existing user\n"
				+ "3: DELETE ALL USERS\n" + "4: LOG OUT";
		
		int nextMove;
		
		System.out.println(s);
		nextMove = Integer.parseInt(reader.next());
		
		while(nextMove != 4) {
			if(nextMove == 0) {
				viewAllUsers();
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
				
			}else if(nextMove == 1) {
				createUser(reader);
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
				
			}else if(nextMove == 2) {
				modifyExistingUser(user, reader);
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
				
			}else if(nextMove == 3) {
				
			}else {
				System.out.println("Sorry wrong input. Try Again.\n");
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
			}
		}
		
		
		
	}
	
	public void modifyExistingUser(String user, Scanner reader) {
		System.out.println("Would you like to view all users before you continue?\n 0: No\n 1: Yes\n");
		int response = Integer.parseInt(reader.next());
		if(response == 1) {
			viewAllUsers();
		}

		String username;
		int next;
		System.out.println("Which user would you like to modify? Enter Username please.");
		username = reader.next();
		
		String s = "What would you like to modify? Choose a numerical Value provided:\n" +
		"0: CANCEL\n" +"1: Modify First Name:\n" + "2: Modify Last Name\n" + "3: Modify Username\n" +
				"4: Modify Password\n" + "5: Modify Account Balance\n";
		System.out.println(s);
		next = Integer.parseInt(reader.next());
		
		while(next != 0) {
			if(next == 1) {
				System.out.println("FIRSTNAME: What would you like to change it to?\n");
				String modifyFirst = reader.next();
				modify(username, "A_FIRSTNAME", modifyFirst);
				System.out.println(s);
				next = Integer.parseInt(reader.next());
				
			}else if(next == 2) {
				System.out.println("LASTNAME: What would you like to change it to?\n");
				String modifyFirst = reader.next();
				modify(username, "A_LASTNAME", modifyFirst);
				System.out.println(s);
				next = Integer.parseInt(reader.next());
				
			}else if(next == 3) {
				System.out.println("USERNAME: What would you like to change it to?\n");
				String modifyFirst = reader.next();
				modify(username, "A_USERNAME", modifyFirst);
				System.out.println(s);
				next = Integer.parseInt(reader.next());
				
			}else if(next == 4) {
				System.out.println("PASSWORD: What would you like to change it to?\n");
				String modifyFirst = reader.next();
				modify(username, "A_PASSWORD", modifyFirst);
				System.out.println(s);
				next = Integer.parseInt(reader.next());
				
			}else if(next == 5) {
				System.out.println("ACCOUNTBALANCE: What would you like to change it to?\n");
				String modifyFirst = reader.next();
				modify(username, "A_BALANCE", modifyFirst);
				System.out.println(s);
				next = Integer.parseInt(reader.next());
			}else {
				System.out.println("Incorrect response please Try Again. \n");
				System.out.println(s);
				next = Integer.parseInt(reader.next());
			}
			
		}
		
	}

	public void modify(String username, String action, String modifyTo) {
		Customer a = CustomerDaoImplementation.getCustDao().getCustomer(username);
		boolean successful;
		
		successful = CustomerDaoImplementation.getCustDao().modify(a.getId(), action, modifyTo);
		if(successful) {
			log.info("Modification Successfull!");
			
		}else {
			log.info("Modification Unsuccessfull!");
		}
		
	}

	public void viewAllUsers() {
		List<Customer> a = new ArrayList<Customer>();
		List<Customer> b = new ArrayList<Customer>();
		a = CustomerDaoImplementation.getCustDao().getAllCustomers();	
		
		for(int i = 0; i <= a.size()-1; i++) {
			Customer allData;
	
			allData = CustomerDaoImplementation.getCustDao().getBalance(a.get(i).getId());
			b.add(allData);
		}
		
		System.out.println("Here are all the users with all their info:\n");
		System.out.println("1 First Name: 2 Last Name: 3 Username: 4 Password: 5 Account Number: 6 Account Balance: \n");
		for(int i = 0; i <= b.size()-1; i++) {
			Customer bData = b.get(i);
			Customer aData = a.get(i);
			
			System.out.println("1 " + aData.getFirstName() + ": 2 " + aData.getLastName() + ": 3 " + aData.getUserName() + ": 4 " +
			aData.getPassword() + ": 5 " + bData.getBankAccountId() + ": 6 " + bData.getCurrentBalance());
		}
		
		System.out.println("\n");
	}

	public void nonSuperUserCredentials(String user, String pass, Scanner reader) {
		System.out.println("UserCredentials");
		String s = "What would you like to do? Choose one of the numerical options: \n" + "0: Deposit\n"
		+ "1: Withdraw\n" + "2: Delete Account (ONLY IF EMPTY)\n" + "3: View Account and Current Balance\n" + 
				"4: LOGOUT";
		
		int nextMove;
		int amount;
		boolean correct;
		System.out.println(s);
		nextMove = Integer.parseInt(reader.next());
		
		while(nextMove != 4) {
			
			if(nextMove == 0) {
				amount = 0;
				correct = false;
				while(correct == false) {
					System.out.println("How much would you like to deposit?");
					amount = Integer.parseInt(reader.next());
					if(amount > 0) {
						correct = true;
					}
				}
				deposit(amount, user);
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
				
			}else if(nextMove == 1){
				amount = 0;
				correct = false;
				while(correct == false) {
					System.out.println("How much would you like to withdraw?");
					amount = Integer.parseInt(reader.next());
					if(amount > 0) {
						correct = true;
					}
				}
				withdraw(amount, user, reader);
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
				
			}else if(nextMove == 2) {
				if(deleteUser(user)) {
					nextMove = 4;
				}else {
					System.out.println(s);
					nextMove = Integer.parseInt(reader.next());
					
				}
				
				
			}else if(nextMove == 3) {
				
				customerInfo(user);
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
				
			}else{
				System.out.println("INCORRECT RESPONSE. TRY AGAIN.\\n");	
				System.out.println(s);
				nextMove = Integer.parseInt(reader.next());
			}
			
			
		}
		
		
		
	}
	
	public boolean deleteUser(String user) {
		boolean successful;
		int id;
		Customer a = CustomerDaoImplementation.getCustDao().getCustomer(user);
		id = a.getId();
		Customer b = CustomerDaoImplementation.getCustDao().getBalance(id);
		
		if(b.getCurrentBalance() == 0) {
		
			successful = CustomerDaoImplementation.getCustDao().deleteUser(id);
		
			if(successful) {
				log.info("Delete Successfull!");
				return successful;
			}else {
				log.info("Delete Unsuccessfull!");
				return false;
			}
		}else {
			System.out.println("Account is not Empty. Balance: " + b.getCurrentBalance() + "\nWithdraw remaining amount in order to delete account.");
		}
		return false;
		
	}

	public void customerInfo(String user) {
		int id;
		Customer a = CustomerDaoImplementation.getCustDao().getCustomer(user);
		id = a.getId();
		Customer b = CustomerDaoImplementation.getCustDao().getBalance(id);
		
		System.out.println("First Name: " + a.getFirstName() + "\nLast Name: " + a.getLastName() + "\nAccount Number: " +
				b.getBankAccountId() + "\nCurrent Balance: " + b.getCurrentBalance());
		
	}

	public boolean usernameMatchesPassword(String username, String password) {
		try {
		Customer a = CustomerDaoImplementation.getCustDao().getCustomer(username);
		if(password.contentEquals(a.getPassword())) {
			return true;
		}else {
			return false;
		}
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean deposit(int amount, String username) {
		int id;
		int currentAmount;
		int nextAmount;
		boolean successful;
		
		Customer a = CustomerDaoImplementation.getCustDao().getCustomer(username);
		id = a.getId();
		Customer b = CustomerDaoImplementation.getCustDao().getBalance(id);
		currentAmount = b.getCurrentBalance();
		nextAmount = currentAmount + amount;
				
		successful = CustomerDaoImplementation.getCustDao().depositWithdraw(nextAmount, username, id);
		if(successful) {
			log.info("Deposit Successfull!");
		}else {
			log.info("Deposit Unsuccessfull!");
		}
		return false;
	}
	
	public boolean withdraw(int amount, String username, Scanner reader) {
		int id;
		int thisAmount = amount;
		int currentAmount;
		int nextAmount;
		boolean successful;
		
		Customer a = CustomerDaoImplementation.getCustDao().getCustomer(username);
		id = a.getId();
		Customer b = CustomerDaoImplementation.getCustDao().getBalance(id);
		currentAmount = b.getCurrentBalance();
		while(!(currentAmount >= thisAmount)) {
			System.out.println("You will overdraft, please try a smaller amount.");
			thisAmount = Integer.parseInt(reader.next());
		}
		nextAmount = currentAmount - thisAmount;
				
		successful = CustomerDaoImplementation.getCustDao().depositWithdraw(nextAmount, username, id);
		if(successful) {
			log.info("Withdraw Successfull!");
		}else {
			log.info("Withdraw Unsuccessfull!");
		}
		return false;
	}

}
