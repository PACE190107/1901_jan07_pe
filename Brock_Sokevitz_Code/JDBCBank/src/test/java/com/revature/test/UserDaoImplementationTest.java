package com.revature.test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.main.mainDriver;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.TransactionService;
import com.revature.services.UserService;

public class UserDaoImplementationTest {
	
	private static User user = new User("bob","burger", 0);
	
	private static Account account = new Account(1, "checking", 5);
	
	public static Transaction transaction = new Transaction(0, 1, 5);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mainDriver.setupDefaultConnection();
		UserService.getUserService().registerUser(user);
		AccountService.getAccountService().insertAccount(account);
		transaction.setAccountID(AccountService.getAccountService().getAllAccounts(1).get(0).getAccountID());
		TransactionService.getTransactionService().insertTransaction(transaction);
	}
	
	@AfterClass
	public static void DeleteUser() {
		User tempUser = UserService.getUserService().getUserDetails(user.getUserID());
		TransactionService.getTransactionService().deleteAllTransactions(1);
		AccountService.getAccountService().deleteAccount(AccountService.getAccountService().getAllAccounts(1).get(0).getAccountID());
		UserService.getUserService().deleteAllUsers();;
	}
	
	@Test
	public void testGetUser() {
		User testUser = UserService.getUserService().getUserDetails(user.getUsername());
		Assert.assertTrue(user.getUsername().equals(testUser.getUsername()));
	}
	
	@Test
	public void testInsertInvalidUser() {

		Assert.assertFalse(UserService.getUserService().registerUser(user));
	}
	
	@Test
	public void testDeleteInvalidUser() {

		Assert.assertFalse(UserService.getUserService().registerUser(user));
	}
	
	@Test
	public void testCheckPassword() {
		Assert.assertTrue(UserService.getUserService().checkPassword(user.getUsername(), user.getPassword()));
	}
	
	@Test
	public void testCheckInvalidPassword() {
		Assert.assertFalse(UserService.getUserService().checkPassword(user.getUsername(), "wrong password"));
	}
	
	@Test
	public void testGetAllUsers() {
		List<User> testUsers = UserService.getUserService().getAllUserDetails();
		Assert.assertTrue(testUsers.size() >= 1);
	}
	
	@Test
	public void testCheckUsername() {
		Assert.assertTrue(UserService.getUserService().checkUsername(user.getUsername()));
	}
	
	@Test
	public void testCheckInvalidUsername() {
		Assert.assertFalse(UserService.getUserService().checkUsername(""));
	}
	
	@Test
	public void testEditUserDetials() {
		User testUser = new User("bobbo", "burgero", 0);
		Assert.assertTrue(UserService.getUserService().editUserDetails(user.getUsername(), user.getPassword(), testUser));
	}
	
	@Test
	public void testUpdateBalance() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account testAccount: testList) {
		Assert.assertTrue(AccountService.getAccountService().updateBalance(testAccount.getAccountID(), 0));
		}
	}	
	
	@Test
	public void testInvalidUpdateBalance() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(-1);
		for(Account testAccount: testList) {
		Assert.assertFalse(AccountService.getAccountService().updateBalance(testAccount.getAccountID(), 0));
		}
	}

	@Test
	public void testGetAllAccounts() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account testAccount: testList) {
			Assert.assertTrue(AccountService.getAccountService().getAccount(testAccount.getAccountID()).getUserID() == 1);
		}
	}

	@Test
	public void testGetAllUserAccount() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		Assert.assertEquals(1, testList.size());
	}

	@Test
	public void testGetAllInvalidUserAccounts() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(-1);
		Assert.assertTrue(testList.isEmpty());
	}
	
	@Test
	public void testAccountExists() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account account:testList) {
			Assert.assertTrue(AccountService.getAccountService().exists(account.getAccountID(), 1));
		}
	}
	
	@Test
	public void testAccountDoesntExist() {
			Assert.assertFalse(AccountService.getAccountService().exists(-1, 1));
	}

	@Test
	public void testGetBalance() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account account:testList) {
			Assert.assertEquals(5, (int)AccountService.getAccountService().getBalance(account.getAccountID()));
		}
	}
	
	@Test
	public void testAddInvalidTransaction() {
		Transaction invalidTransaction = new Transaction(-1, -1, 5);
		Assert.assertFalse(TransactionService.getTransactionService().insertTransaction(invalidTransaction));
	}
	
	@Test
	public void testGetAllTransactionsForValidUser() {
		List<Transaction> testList = TransactionService.getTransactionService().getAllTransactions(1);
		Assert.assertEquals(1, testList.size());
	}
	
	@Test
	public void testGetAllTransactionsForInvalidUser() {
		List<Transaction> testList = TransactionService.getTransactionService().getAllTransactions(0);
		Assert.assertTrue(testList.isEmpty());
	}
	
	@Test
	public void testDeleteAllTransactionsForInvalidUser() {
		Assert.assertFalse(TransactionService.getTransactionService().deleteAllTransactions(0));
	}
	
	@Test
	public void testDeleteAllTransactionsForInvalidAccount() {
		Assert.assertFalse(TransactionService.getTransactionService().deleteAllAccountTransactions(0));
	}
}
