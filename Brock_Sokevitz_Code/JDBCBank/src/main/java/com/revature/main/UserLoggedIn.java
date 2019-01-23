package com.revature.main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.InvalidAccountDeletionException;
import com.revature.exceptions.InvalidAccountIDException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.NegativeDepositException;
import com.revature.exceptions.NegativeWithdrawException;
import com.revature.exceptions.NoAccountsException;
import com.revature.exceptions.NotEnoughFundsException;
import com.revature.exceptions.UnsupportedAccountTypeException;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.TransactionService;
import com.revature.utils.JDBCConnectUtil;

public class UserLoggedIn {
	
	private final static String[] ALLACCOUNTTYPES = {"checking", "savings"};
	private static DecimalFormat df = new DecimalFormat("#0.00"); 
	
	public static void login(User user){
		boolean loggedIn = true;
		
		System.out.println("Would you like to view your accounts and balances, create an account, delete an account, deposit, withdraw, view transaction history, or logout?");
		System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		while(loggedIn) {
			try {				
				if(input.toLowerCase().equals("view")){
					viewAccounts(user);
					System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
					input = sc.nextLine();					
				}else if(input.toLowerCase().equals("create")){
					createAccount(user);
					System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
					input = sc.nextLine();					
				}else if(input.toLowerCase().equals("delete")){
					viewAccounts(user);
					deleteAccount(user);
					System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("deposit")){
					viewAccounts(user);
					verifyDepositAccount(user);
					System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("withdraw")){
					viewAccounts(user);
					verifyWithdraw(user);
					System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("history")){
					viewTransactionHistory(user.getUserID());
					System.out.println(" Please enter view, create, delete, deposit, withdraw, history, or logout.");
					input = sc.nextLine();
				}else if(input.toLowerCase().equals("logout")){
					System.out.println("Logging out.");
					loggedIn = false;
					try {
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new InvalidInputException();
				}
				
			}catch(InvalidInputException e){
				System.out.println(e.getMessage() + " Please enter view, create, delete, deposit, withdraw, history, or logout.");
				input = sc.nextLine();
			}
		}
	}

	static void viewAccounts(User user) {
		List<Account> accountList = AccountService.getAccountService().getAllAccounts(user.getUserID());
		
		try {
			if(accountList.isEmpty())
				throw new NoAccountsException();
			
			System.out.println("Here's a list of your accounts:");
		}catch(NoAccountsException nao) {
			
		}
		if(accountList.isEmpty()) {
			System.out.println("You haven't created any accounts yet.");
		}else {
		for(Account account: accountList) {
			System.out.println(account.getAccountID()+", "+account.getAccountType()+": $"+ df.format(account.getBalance()));
		}
		}
	}
	
	private static void createAccount(User user) {
		boolean typeChosen = false;
		
		System.out.println("Please enter checking or savings to make an account.");
		Scanner sc = new Scanner(System.in);
		String accountType = sc.nextLine();
		
		while(!typeChosen && !accountType.equalsIgnoreCase("exit")) {
			try {
				if(!Arrays.asList(ALLACCOUNTTYPES).contains(accountType)) {
					throw new UnsupportedAccountTypeException();
				}else {
					AccountService.getAccountService().insertAccount(new Account(user.getUserID(), accountType, 0.00));
					System.out.println("A " + accountType + " account has been created with a balance of 0.00.");
					System.out.println("Account created.");
					return;
				}
					
			}catch(UnsupportedAccountTypeException u) {
				System.out.println(u.getMessage() + "Please enter an account type or exit.");
				accountType = sc.nextLine();
			}
		}
		return;
	}
	
	private static boolean deleteAccount(User user) {
		boolean accountDeleted = false;
		
		System.out.println("Please enter an account ID to delete.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		while(!accountDeleted && !input.equalsIgnoreCase("exit")) {
			try {
				if(!AccountService.getAccountService().exists(Integer.parseInt(input), user.getUserID()) || AccountService.getAccountService().getBalance(Integer.parseInt(input)) > 0.00) {
					
					throw new InvalidAccountDeletionException();
					
				}else {
					accountDeleted = TransactionService.getTransactionService().deleteAllAccountTransactions(Integer.parseInt(input));
					accountDeleted = AccountService.getAccountService().deleteAccount(Integer.parseInt(input));
					System.out.println("Account deleted.");
					return accountDeleted;
					
				}
					
			}catch(InvalidAccountDeletionException u) {
				System.out.println(u.getMessage() + " Please enter another Account ID");
				input = sc.nextLine();
			}catch(NumberFormatException nf) {
				System.out.println(" Please enter a valid number.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println(" Please enter a valid number.");
				input = sc.nextLine();
			}
		}
		return false;
	}

	private static void verifyDepositAccount(User user) {
		boolean depositMade = false;
		
		System.out.println("Please enter an account ID to deposit into.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while(!depositMade && !input.equalsIgnoreCase("exit")) {
			try {
				if(!AccountService.getAccountService().exists(Integer.parseInt(input), user.getUserID())) {
						
					throw new InvalidAccountIDException();
				}else {
					depositMade = deposit(Integer.parseInt(input), user.getUserID());
					System.out.println("Deposit made.");
					return;
				}
					
			}catch(InvalidAccountIDException id) {
				System.out.println(id.getMessage() + "Please enter another Account ID");
				input = sc.nextLine();
			}catch(NumberFormatException nf) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}
		}
	}


	private static boolean deposit(int accountID, int userID) {
		boolean depositMade = false;
		
		System.out.println("Please enter an amount to deposit.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while(!depositMade && !input.equalsIgnoreCase("exit")) {
			try {
					if(Double.parseDouble(input)<=0)
						throw new NegativeDepositException();
					
					double currentBalance = AccountService.getAccountService().getBalance(accountID);
					AccountService.getAccountService().updateBalance(accountID, Double.parseDouble(input)+currentBalance);
					depositMade = TransactionService.getTransactionService().insertTransaction(new Transaction(accountID, userID, Double.parseDouble(input)));					
					
			}catch(NumberFormatException nf) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			} catch (NegativeDepositException nde) {
				// TODO Auto-generated catch block
				System.out.println(nde.getMessage());
				input = sc.nextLine();
			}
		}
		return false;
	}
	
	private static void verifyWithdraw(User user) {
		boolean withdrawMade = false;
		
		System.out.println("Please enter an account ID to withdraw from.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while(!withdrawMade && !input.equalsIgnoreCase("exit")) {
			try {
				if(!AccountService.getAccountService().exists(Integer.parseInt(input), user.getUserID())) {
						
					throw new InvalidAccountIDException();
				}else {
					withdrawMade = withdraw(Integer.parseInt(input), user.getUserID());
					System.out.println("Withdraw made.");
					return;
				}
					
			}catch(InvalidAccountIDException id) {
				System.out.println(id.getMessage() + "Please enter another Account ID");
				input = sc.nextLine();
			}catch(NumberFormatException nf) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}
		}
	}
	
	private static boolean withdraw(int accountID, int userID) {
		
		boolean depositMade = false;
		
		System.out.println("Please enter an amount to withdraw.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while(!depositMade && !input.equalsIgnoreCase("exit")) {
			try {
					double currentBalance = AccountService.getAccountService().getBalance(accountID);
					if(Double.parseDouble(input)<=0){
						throw new NegativeWithdrawException();
					}else if(currentBalance - Double.parseDouble(input)>=0) {
					AccountService.getAccountService().updateBalance(accountID, currentBalance-Double.parseDouble(input));
					return TransactionService.getTransactionService().insertTransaction(new Transaction(accountID, userID, -Double.parseDouble(input)));			
					}else {
						throw new NotEnoughFundsException();
					}
					
			}catch(NumberFormatException nf) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}catch(NullPointerException np) {
				System.out.println("Please enter a valid number.");
				input = sc.nextLine();
			}catch(NotEnoughFundsException broke) {
				System.out.println(broke.getMessage()+" Please check your balance and enter a valid number.");
				input = sc.nextLine();
			} catch (NegativeWithdrawException nwe) {
				// TODO Auto-generated catch block
				System.out.println(nwe.getMessage());
				input = sc.nextLine();
			}
		}
		return false;
	}
	
	private static void viewTransactionHistory(int userID) {
		System.out.println("This is your transaction history accross all your accounts.");
		System.out.println("Transaction ID, Account ID: transaction amount");
		List<Transaction> transactionList = TransactionService.getTransactionService().getAllTransactions(userID);
		for(Transaction transaction: transactionList) {
			System.out.println(transaction.getTransactionID()+", "+transaction.getAccountID()+": $"+ df.format(transaction.getAmount()));
		}
	}
}
