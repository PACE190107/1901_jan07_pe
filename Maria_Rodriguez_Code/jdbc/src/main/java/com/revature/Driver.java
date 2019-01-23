package com.revature;

import com.revature.dao.CustomerDaoImplementation;
import com.revature.models.Customer;
import com.revature.services.CustomerService;

import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		initiate();
	
		
		
		
		
		
	}// End main method
	
	static void initiate() {
		double withdrawAmount = 0 ;
		double depositAmount = 0;
		int userInput = 0;
		int accountBalance = 0;
		double accountNumber =0;
		Scanner inputScanner = new Scanner(System.in);
		//User Input loop...
		while( userInput != 3 ) {
			
			System.out.println( "Clover Mint Bank" );
			System.out.println( "=====================" );
			System.out.println( "     1.  Create an Account" );
			System.out.println( "     2.  Login" );
			System.out.println( "     3.  Exit" ) ;
			System.out.print( "Selection: ");
			userInput = inputScanner.nextInt();
			/////works!
			if( userInput == 1 ) {
				System.out.println( "Create an account..." );
				
				//implement register new user
		
				registerCustomerProcedure();
				System.out.println("Open an account...");
				
				System.out.println("Please confirm your first name.");
				String firstName = inputScanner.next();
				System.out.println("Please confirm your last name.");
				String lastName = inputScanner.next();
				System.out.println("Register your account number.");
				if(inputScanner.hasNextDouble()) {
					 accountNumber = inputScanner.nextDouble();
				} else {
					System.out.println("Invalid Input");
				}
				
				System.out.println("Deposit funds.");
				if(inputScanner.hasNextInt()) {
				   accountBalance = inputScanner.nextInt();
				}else {
					System.out.println("Invalid Input");
				}
				
				
				 boolean didAdd = CustomerService.getCustomerService().createBankAccount(accountBalance, accountNumber, firstName, lastName);
				 System.out.println(didAdd);
			////works!	
				}else if( userInput == 2 ) {
				Scanner uNameScanner = new Scanner( System.in );
				
				userInput = 0;
				String userName = "";
				String passWord = "";
				//System.out.println( "Log in..." );
				
				System.out.print( "Enter your username: " );
				userName = inputScanner.next();	
				
			    //Get User Password
				System.out.print( "\nEnter your password: " );
				passWord = uNameScanner.nextLine();
				
				//Get User ID
				
				
				
				
				
				
				
				
				//Checks if user is in database...		
				if( CustomerService.getCustomerService().getCustomer( userName, passWord ) != null ) {
					//Successfully logged in
					System.out.println( "\nValid user.\n\n" );
					
					int loggedInSelection = 0;
					
				
					
					
					Customer checkSuper = CustomerService.getCustomerService().getCustomer(userName, passWord);
					System.out.println(checkSuper.getFirstName());
					System.out.println(checkSuper.getId());
					System.out.println(checkSuper.getSuperuser());
					System.out.println(checkSuper);
					 if( userName.equals("AJROD")) {
						 //System.out.println("is super");
						 if(passWord.equals("123")) {
							 //System.out.println("pass match");
							 //break while
							 
							 userInput = 3;
							 
							 
							 System.out.println("1. Update User");
							 System.out.println("2. Add User");
							 System.out.println("3. Remove User");
							 System.out.println("4. View all users");
							 
							 String command = inputScanner.next();
							 
							 switch (command) {
							 case "1":  
								 
								 List<Customer> showCustomers= CustomerService.getCustomerService().getAllCustomers();
							 for(Customer x: showCustomers) {
								 System.out.println(x);
							 }
								 
							 System.out.println("Update User by choosing User Id...");
							 String tempid ="";
							 String tempfirst ="";
							 String templast ="";
							 
							 tempid = inputScanner.next();
							 System.out.println(" Update First Name...");
							 tempfirst = inputScanner.next();
							 System.out.println("Update Last Name...");
							 templast = inputScanner.next();
							 
								
								
										//Customer uCustomer = CustomerService.getCustomerService().getCustomer(userName, passWord);
										 boolean  updatebool = CustomerService.getCustomerService().updateUser(tempid, tempfirst, templast);
								 		if(updatebool) {
								 			System.out.println("Update Successful");
								 		} else {
								 			System.out.println("Update failed");
								 		}
								 		
								 break;
							 case "2":
								 registerCustomerProcedure();
								 
								 
							 	break;
							 case "3":
								 List<Customer> allUsers= CustomerService.getCustomerService().getAllCustomers();
								 for(Customer x: allUsers) {
									 System.out.println(x);
								 }
								 
								 System.out.println("Enter username of the user you want to remove...");
								 String user = inputScanner.next();
								 boolean wasDeleted = CustomerService.getCustomerService().deleteUser(user);
								 if(wasDeleted) {
									 System.out.println("User was removed.");
									 initiate();
								 } else {
									 System.out.println("Couldn't Remove User");
									 initiate();
								 }
							 	break;
							 case "4":
								 
									 List<Customer> allCustomers= CustomerService.getCustomerService().getAllCustomers();
									 for(Customer x: allCustomers) {
										 System.out.println(x);
									 }
							 	break;
							 	default:
							 		initiate();
							 		break;
							 }
							 
						 }
					 } else {
						 
						 
						 Customer tempCustomer = null;
							String userId = "";
							
								System.out.println( "Welcome, Maria Rodriguez!" );
								System.out.println( "=========================" );
								System.out.println( "     1. View Balance" );
								System.out.println( "     2. Withdraw" );
								System.out.println( "     3. Deposit" );
								System.out.println( "     4. Delete Account" );
								System.out.println( "     5. Logout" );
								System.out.print( "Selection: ");
								loggedInSelection = inputScanner.nextInt();
							switch (loggedInSelection) {
							case 1: 
								tempCustomer = CustomerService.getCustomerService().getCustomer(userName, passWord);
								userId = tempCustomer.getId();
								//System.out.println(tempCustomer.getId());
								
								
								System.out.println(balanceCheck(tempCustomer.getId()));
								break;
							case 2: 
								Customer wCustomer = CustomerService.getCustomerService().getCustomer(userName, passWord);
								System.out.println("Enter the amount you want to withdraw.");
								if(inputScanner.hasNextDouble()) {
									
									withdrawAmount = inputScanner.nextDouble();
									
									
								} else {
									//initiate();
									System.out.println("Invalid input");
								}
								//System.out.println(userId);
								 boolean wasSuccess = withdraw(wCustomer.getId(), withdrawAmount);
								//System.out.println(balanceCheck(wCustomer.getId()));
								//System.out.println(returnBalance(wCustomer.getId()));
								double tempBalance = balanceCheck(wCustomer.getId());
								System.out.println( "New Balance: " + tempBalance);
								
								break;
							case 3: 
								Customer dCustomer = CustomerService.getCustomerService().getCustomer(userName, passWord);
								System.out.println("Enter the amount you want to deposit.");
								if(inputScanner.hasNextDouble()) {
									depositAmount = inputScanner.nextDouble();
								} else {
									System.out.println("Invalid input");
								}
								System.out.println(deposit(dCustomer.getId(), depositAmount  ));
								
								break;
							case 4:
								Customer eCustomer = CustomerService.getCustomerService().getCustomer(userName, passWord);
								 deleteAccount(eCustomer.getId());
								
								break;
							case 5: 
								System.out.println("You are securely being signed out.");initiate();
								break;
								
					
							}
							
							
//								if( loggedInSelection == 1 ) {
//									//selection 1
//									System.out.println("View your balance...\n");
//									getBalance();}
//										
//									};
//									
//								
//								}
//							
//							else if( loggedInSelection == 2 ) {
//									//selection 2
//									System.out.println( "Withdrawing funds...\n" );
//								
//								}else if( loggedInSelection == 3 ) {
//									//selection 3
//									System.out.println( "Depositing funds...\n" );
//								
//								}else if( loggedInSelection == 4 ) {
//									//selection 4
//									System.out.println( "Viewing transaction history...\n" );
//								
//								}else {
//									//literally any other input...
//									System.out.println("Invalid input.  Please try again.\n\n");
//								}
//											
//						} else {
//							System.out.println( "\nInvalid Credentials.\n\n" );
//						}
//						
//						
//					}else if( userInput == 3 ) {
//						//Exiting the banking system/app...
//						System.out.println( "Thank you for being a member of Clover Mint Bank!" );
//					}else {
//						//The user has given a menu selection that is not a valid choice...
//						System.out.println( "Invalid input.  Please make another selection..." );
//					}
					
				/*	
					switch(input) {
					case1:insert method name(); break;
					case2:
					case3:
					case4:
					}
					
					*/
					
					
				}
						 
						 
						 
						 
						 
			 }
					//if (checkSuper.getsuperUser().equals("y")) {
						//System.out.println("Super User");
					//}
					
					
					
					
					
					
					
					
					//Do logged in logic here...
					//Logged in Menu...
				
					
			
			
			
			
			
			
			
				}
			
		}
	}
	
		
	
 static boolean registerCustomerProcedure() {
	Customer cust;
	Scanner stmt = new Scanner (System.in);
	System.out.println("\nEnter First Name");
	String firstName = stmt.nextLine();
	System.out.println("\nEnter Last Name");
	String lastName = stmt.nextLine();
	System.out.println("\nEnter Username");
	String userName = stmt.nextLine();
	System.out.println("\nEnter Password");
	String passWord = stmt.nextLine();
	cust = new Customer (firstName, lastName, userName,passWord);
	return CustomerService.getCustomerService().registerCustomerProcedure(cust);
}

static  double balanceCheck( String user_id ) {
	
	double balance = CustomerService.getCustomerService().balanceCheck(user_id);
			//return CustomerService.getCustomerService().getBalance( user_id ) ;
	return balance;
}


static boolean withdraw ( String user_id, double withdrawAmount ) {
		
	return CustomerService.getCustomerService().withdraw(user_id, withdrawAmount);
}

static boolean deposit( String user_id, double depositAmount ) {
	
	return CustomerService.getCustomerService().deposit(user_id, depositAmount);
}

public boolean createBankAccount(int accountBalance, int accountNumber, String firstName, String lastName) {
	
	return CustomerService.getCustomerService().createBankAccount(accountBalance, accountNumber, firstName, lastName);
}
static boolean deleteAccount(String user_id) {
	return CustomerService.getCustomerService().deleteAccount(user_id);
}

public boolean updateUser(String user_id, String firstName, String lastName) {
	return CustomerService.getCustomerService().updateUser(user_id, firstName, lastName);
}
}


