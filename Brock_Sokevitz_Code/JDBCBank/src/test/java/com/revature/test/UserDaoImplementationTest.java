package com.revature.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.main.mainDriver;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;

public class UserDaoImplementationTest {
	
	private static User user = new User("bob","burger", 0);
	
	private static Account account = new Account(1, "checking", 5);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mainDriver.setupDefaultConnection();
		UserService.getUserService().registerUser(user);
		AccountService.getAccountService().insertAccount(account);
	}
	
	@AfterClass
	public static void DeleteUser() {
		User tempUser = UserService.getUserService().getUserDetails(user.getUserID());		
		UserService.getUserService().deleteAllUsers();
		AccountService.getAccountService().deleteAllAccounts(1);
	}
	
	@Test
	public void testGetUser() {
		User testUser = UserService.getUserService().getUserDetails(user.getUsername());
		System.out.println(testUser.getUsername()+ ", "+ user.getUsername());
		Assert.assertTrue(user.getUsername().equals(testUser.getUsername()));
	}
	
	@Test
	public void testCheckPassword() {
		Assert.assertTrue(UserService.getUserService().checkPassword(user.getUsername(), user.getPassword()));
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
	public void testEditUserDetials() {
		User testUser = new User("bobbo", "burgero", 0);
		Assert.assertTrue(UserService.getUserService().editUserDetails(user.getUsername(), user.getPassword(), testUser));
	}
	
	@Test
	public void testUpdateBalance() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account testAccount: testList) {
		Assert.assertTrue(AccountService.getAccountService().updateBalance(testAccount.getAccountID(), 2));
		Assert.assertTrue(AccountService.getAccountService().updateBalance(testAccount.getAccountID(), -2));
		}
	}

	@Test
	public void testGetAccount() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account testAccount: testList) {
			Assert.assertTrue(AccountService.getAccountService().getAccount(testAccount.getAccountID()).getUserID() == 1);
		}
	}

	@Test
	public void testGetAllAccounts() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		System.out.println("list size:"+ testList.size());
		Assert.assertTrue(testList.size()==1);
	}

	@Test
	public void testAccountExists() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account account:testList) {
			Assert.assertTrue(AccountService.getAccountService().exists(account.getAccountID(), 1));
		}
	}

	@Test
	public void testGetBalance() {
		List<Account> testList = AccountService.getAccountService().getAllAccounts(1);
		for(Account account:testList) {
			Assert.assertTrue(AccountService.getAccountService().getBalance(account.getAccountID()) == 5);
		}
	}
}
