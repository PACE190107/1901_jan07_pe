package com.revature.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.AccountDAOImplementation;
import com.revature.dao.UserDAOImplementation;
import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;

public class JDBCBankTest {

	@BeforeClass
	public static void insertValidUser() {
		User dne = new User(-1, "testUser", "testUser", false);
		assertTrue(UserDAOImplementation.getUserDAO().insertUser(dne));
	}
	
	@BeforeClass
	public static void insertInvalidUser() {
		User exists = new User(-1, "admin", "admin", false);
		assertFalse(UserDAOImplementation.getUserDAO().insertUser(exists));
	}
	
	@BeforeClass
	public static void addValidCredentials() {
		User exists = new User(-1, "testUser", "testUser", false);
		assertTrue(UserDAOImplementation.getUserDAO().createCredentials(exists));
	}
	
	@BeforeClass
	public static void insertValidAccount() {
		Account dne = new Account(-1, 0, "adminAccount", AccountType.CHECKINGS, 0);
		assertTrue(AccountDAOImplementation.getAccountDAO().insertAccount(dne));
	}
	
	//@Test (SQLIntegrity Exception)
	
	@Test
	public void checkForExistingUser() {
		assertNotNull(UserDAOImplementation.getUserDAO().getUser("admin"));
	}
	
	@Test
	public void checkForNonExistingUser() {
		assertNull(UserDAOImplementation.getUserDAO().getUser(""));
	}
	
	@Test
	public void checkGetAllUsers() {
		assertNotNull(UserDAOImplementation.getUserDAO().getAllUsers());
	}
	
	@Test
	public void updateValidUser() {
		User exists = UserDAOImplementation.getUserDAO().getUser("testUser");
		assertTrue(UserDAOImplementation.getUserDAO().updateUser(exists));
	}
	
	@Test
	public void updateInvalidUser() {
		User dne = new User(-1, "", "", false);
		assertFalse(UserDAOImplementation.getUserDAO().updateUser(dne));
	}
	
	@Test
	public void updateValidAccount() {
		ArrayList<Account> accounts = AccountDAOImplementation.getAccountDAO().getAllAccounts(0);
		Account toUpdate = accounts.get(0);
		toUpdate.setType(AccountType.MORTGAGE);
		assertTrue(AccountDAOImplementation.getAccountDAO().updateAccount(toUpdate));
	}
	
	@AfterClass
	public static void deleteValidUser() {
		assertTrue(UserDAOImplementation.getUserDAO().deleteUser("testUser"));
	}
	
	@AfterClass
	public static void deleteInvalidUser() {
		assertFalse(UserDAOImplementation.getUserDAO().deleteUser(""));
	}
	
	@AfterClass
	public static void removeValidCredentials() {
		User exists = new User(-1, "testUser", "newPass", false);
		assertTrue(UserDAOImplementation.getUserDAO().deleteCredentials(exists));
	}
	
	@AfterClass
	public static void deleteValidAccount() {
		ArrayList<Account> accounts = AccountDAOImplementation.getAccountDAO().getAllAccounts(0);
		Account toDelete = accounts.get(0);
		assertTrue(AccountDAOImplementation.getAccountDAO().deleteAccount(toDelete.getId()));
	}
	
}
