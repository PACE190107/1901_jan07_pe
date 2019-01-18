package com.jdbcbank.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jdbcbank.models.Account;
import com.jdbcbank.models.AccountType;
import com.jdbcbank.models.Transaction;
import com.jdbcbank.models.User;
import com.jdbcbank.services.AccountServices;
import com.jdbcbank.services.TransactionServices;
import com.jdbcbank.services.UserServices;
import com.jdbcbank.util.BankErrors;
import com.jdbcbank.util.ConnectionManager;

public class BankTests {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@BeforeClass
	public static void setupConnection() {
		ConnectionManager.setJDBCConnection("BankAdmin", "bankadmin");
	}

	@AfterClass
	public static void closeConnection() throws SQLException {
		ConnectionManager.getJDBCConnection().close();
	}
	
	@Before
	public void removeExistingData() throws SQLException {
		List<User> users = UserServices.readUsers();
		for (User user : users) {
			List<Account> accounts = AccountServices.readAccounts(user.getUserID());
			for (Account account : accounts)
				if (account.getBalance() > 0.0)
					AccountServices.updateAccount(account.getAccountID(), account.getBalance(), false);
			AccountServices.deleteAccounts(user.getUserID());
			UserServices.deleteUser(user.getUsername(), user.getPassword());
		}
	}
	
	@After
	public void removeRemainingData() throws SQLException {
		List<User> users = UserServices.readUsers();
		for (User user : users) {
			List<Account> accounts = AccountServices.readAccounts(user.getUserID());
			for (Account account : accounts) 
				if (account.getBalance() > 0.0)
					AccountServices.updateAccount(account.getAccountID(), account.getBalance(), false);
			AccountServices.deleteAccounts(user.getUserID());
			UserServices.deleteUser(user.getUsername(), user.getPassword());
		}
	}
	
	/*****************************************************************************************************
	 * TESTING USER SERVICES
	 *****************************************************************************************************/
	// Testing createUser
	@Test
	public void testCreateUser_Normal() throws SQLException {
		assertTrue(UserServices.createUser("testman", "12345"));
	}
	@Test
	public void testCreateUser_Duplicate() throws SQLException {
		expectedException.expect(BankErrors.ExistingUsernamePasswordException.class);
		UserServices.createUser("twinsies", "12345");
		UserServices.createUser("twinsies", "54321");
	}
	
	// Testing readUsers
	@Test
	public void testReadUsers() throws SQLException {
		assertTrue(UserServices.readUsers().size() == 0);
		UserServices.createUser("testman", "12345");
		UserServices.createUser("horrible", "abcde");
		UserServices.createUser("generic", "nothing");
		assertTrue(UserServices.readUsers().size() == 3);
	}
	
	// Testing readUser
	@Test
	public void testReadUser_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		User controlUser = new User();
		controlUser.setUsername("testman");
		controlUser.setPassword("12345");
		User returnedUser = UserServices.readUser("testman", "12345");
		assertTrue(controlUser.getUsername().equals(returnedUser.getUsername()));
		assertTrue(controlUser.getPassword().equals(returnedUser.getPassword()));
	}
	@Test
	public void testReadUser_Nonexistant() throws SQLException {
		expectedException.expect(BankErrors.InvalidUsernamePasswordException.class);
		UserServices.readUser("failed", "read");
	}
	@Test
	public void testReadUser_WrongPassword() throws SQLException {
		expectedException.expect(BankErrors.InvalidUsernamePasswordException.class);
		UserServices.createUser("myself", "right_password");
		UserServices.readUser("myself", "wrong_password");
	}
	
	// Testing updateUser
	@Test
	public void testUpdateUser_Normal() throws SQLException {
		UserServices.createUser("myself", "old_password");
		assertTrue(UserServices.updateUser("myself", "old_password", "new_password"));
		assertNotNull(UserServices.readUser("myself", "new_password"));
	}
	@Test
	public void testUpdateUser_WrongPassword() throws SQLException {
		expectedException.expect(BankErrors.InvalidUsernamePasswordException.class);
		UserServices.createUser("myself", "wrong_password");
		UserServices.updateUser("myself", "wrong_password", "right_password");
		UserServices.readUser("myself", "wrong_password");
	}
	@Test
	public void testUpdateUser_Nonexistant() throws SQLException {
		expectedException.expect(BankErrors.InvalidUsernamePasswordException.class);
		UserServices.updateUser("update", "shall", "fail");
	}
	
	// Testing deleteUser
	@Test
	public void testDeleteUser_Normal() throws SQLException {
		UserServices.createUser("dispose_of", "me");
		assertTrue(UserServices.deleteUser("dispose_of", "me"));
	}
	@Test
	public void testDeleteUser_Nonexistant() throws SQLException {
		expectedException.expect(BankErrors.InvalidUsernamePasswordException.class);
		UserServices.deleteUser("DO_NOT", "EXIST");
	}

	/*****************************************************************************************************
	 * TESTING ACCOUNT SERVICES
	 *****************************************************************************************************/
	// Testing createAccount
	@Test
	public void testCreateAccount_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		assertTrue(AccountServices.createAccount(userID, "checking", 100.0));
	}
	@Test
	public void testCreateAccount_InvalidType() throws SQLException {
		expectedException.expect(BankErrors.InvalidTypeException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "nothing", 100.0);
	}
	@Test
	public void testCreateAccount_NoDeposit() throws SQLException {
		expectedException.expect(BankErrors.InvalidAmountException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 0.0);
	}
	
	// Testing readAccounts
	@Test
	public void testReadAccounts() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		assertTrue(AccountServices.readAccounts(userID).size() == 0);
		AccountServices.createAccount(userID, "checking", 100.0);
		AccountServices.createAccount(userID, "savings", 200.0);
		AccountServices.createAccount(userID, "checking", 300.0);
		assertTrue(AccountServices.readAccounts(userID).size() == 3);
	}
	
	// Testing readAccount
	@Test
	public void testReadAccount_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(account.getType().toString().equals(AccountType.valueOf("checking").toString()));
		assertTrue(account.getBalance() == 100.0);
	}
	@Test
	public void testReadAccount_Nonexistant() throws SQLException {
		expectedException.expect(BankErrors.InvalidAccountIDException.class);
		AccountServices.readAccount(00000000);
	}
	
	// Testing updateAccount
	@Test
	public void testUpdateAccount_Deposit_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(AccountServices.updateAccount(account.getAccountID(), 100.0, true));
		account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(account.getBalance() == 200.0);
	}
	@Test
	public void testUpdateAccount_Deposit_ZeroAmount() throws SQLException {
		expectedException.expect(BankErrors.InvalidAmountException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		AccountServices.updateAccount(account.getAccountID(), 0.0, true);
	}
	@Test
	public void testUpdateAccount_Deposit_NegativeAmount() throws SQLException {
		expectedException.expect(BankErrors.InvalidAmountException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		AccountServices.updateAccount(account.getAccountID(), -100.0, true);
	}
	@Test
	public void testUpdateAccount_Withdraw_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(AccountServices.updateAccount(account.getAccountID(), 50.0, false));
		account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(account.getBalance() == 50.0);
	}
	@Test
	public void testUpdateAccount_Withdraw_Overdraft() throws SQLException {
		expectedException.expect(BankErrors.OverdraftException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		AccountServices.updateAccount(account.getAccountID(), 200.0, false);
	}
	
	// Testing deleteAccounts
	@Test
	public void testDeleteAccounts_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		assertTrue(AccountServices.readAccounts(userID).size() == 0);
		AccountServices.createAccount(userID, "checking", 100.0);
		AccountServices.createAccount(userID, "savings", 200.0);
		AccountServices.createAccount(userID, "checking", 300.0);
		List<Account> accounts = AccountServices.readAccounts(userID);
		for (Account account : accounts)
			AccountServices.updateAccount(account.getAccountID(), account.getBalance(), false);
		assertTrue(AccountServices.deleteAccounts(userID));
		assertTrue(AccountServices.readAccounts(userID).size() == 0);
	}
	@Test
	public void testDeleteAccounts_NonemptyAccount() throws SQLException {
		expectedException.expect(BankErrors.NonEmptyAccountException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		AccountServices.createAccount(userID, "savings", 200.0);
		AccountServices.createAccount(userID, "checking", 300.0);
		AccountServices.deleteAccounts(userID);
	}
	
	// Testing deleteAccount
	@Test
	public void testDeleteAccount_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		AccountServices.updateAccount(account.getAccountID(), account.getBalance(), false);
		assertTrue(AccountServices.deleteAccount(account.getAccountID()));
	}
	@Test
	public void testDeleteAccount_Nonempty() throws SQLException {
		expectedException.expect(BankErrors.NonEmptyAccountException.class);
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		AccountServices.deleteAccount(account.getAccountID());
	}
	@Test
	public void testDeleteAccount_Nonexistant() throws SQLException {
		expectedException.expect(BankErrors.InvalidAccountIDException.class);
		AccountServices.deleteAccount(00000000);
	}

	/*****************************************************************************************************
	 * TESTING TRANSACTION SERVICES
	 *****************************************************************************************************/
	// Testing createTransaction
	@Test
	public void testCreateTransaction_Deposit_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(TransactionServices.createTransaction(account.getAccountID(), "deposit", 200.0, 300.0));
	}
	@Test
	public void testCreateTransaction_Deposit_NonexistantAccount() throws SQLException {
		expectedException.expect(BankErrors.InvalidAccountIDException.class);
		assertTrue(TransactionServices.createTransaction(00000000, "deposit", 200.0, 300.0));
	}
	@Test
	public void testCreateTransaction_Withdraw_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(TransactionServices.createTransaction(account.getAccountID(), "withdraw", 200.0, 300.0));
	}
	@Test
	public void testCreateTransaction_Withdraw_NonexistantAccount() throws SQLException {
		expectedException.expect(BankErrors.InvalidAccountIDException.class);
		assertTrue(TransactionServices.createTransaction(00000000, "withdraw", 200.0, 300.0));
	}
	
	// Testing readTransactions
	@Test
	public void testReadTransactions_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		Map<Timestamp, Transaction> transactions = TransactionServices.readTransactions(account.getAccountID());
		assertTrue(transactions.get(transactions.keySet().toArray()[0]).getAmount() == 100.0);
	}
	@Test
	public void testReadTransactions_NonexistantAccount() throws SQLException {
		expectedException.expect(BankErrors.InvalidAccountIDException.class);
		TransactionServices.readTransactions(00000000);
	}
	
	// Test deleteTransactions
	@Test
	public void testDeleteTransactions_Normal() throws SQLException {
		UserServices.createUser("testman", "12345");
		int userID = UserServices.readUser("testman", "12345").getUserID();
		AccountServices.createAccount(userID, "checking", 100.0);
		Account account = AccountServices.readAccount(AccountServices.readAccounts(userID).get(0).getAccountID());
		assertTrue(TransactionServices.deleteTransactions(account.getAccountID()));
	}
	@Test
	public void testDeleteTransactions_NonexistantAccount() throws SQLException {
		expectedException.expect(BankErrors.InvalidAccountIDException.class);
		assertTrue(TransactionServices.deleteTransactions(00000000));
	}
}
