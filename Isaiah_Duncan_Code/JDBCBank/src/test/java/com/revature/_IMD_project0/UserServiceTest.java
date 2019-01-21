package com.revature._IMD_project0;

import org.junit.Ignore;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;

import junit.framework.TestCase;

public class UserServiceTest extends TestCase {

	//fails after first run due to record being added to database
	@Ignore 
	@Test
	public void testInsertUser() {
		User testUser = new User("MyUsername","MyPassword","FirstName","LastName");
		assertTrue(UserService.getUserService().registerUser(testUser));
	}
	
	//must hard code specific user ID of actual record in the database
	@Ignore
	@Test
	public void testDeleteUserImpl() {
		User testUser = new User();
		assertTrue(UserService.getUserService().deleteUser(testUser));
	}

	@Test
	public void testCheckUser() {
		User testUser = new User("IDuncan","HelloWorld");
		User testUser2 = new User("TheBestOneEver","3234");
		assertTrue(UserService.getUserService().loginCheck(testUser));
		assertTrue(UserService.getUserService().loginCheck(testUser2));
	}
	
	@Test
	public void testIsUser() {
		User testUser = new User("IDuncan");
		assertTrue(UserService.getUserService().registerCheck(testUser));
	}

	@Ignore
	@Test
	public void testAddNewChecking() {
		User testUser = new User("IDuncan");
		assertTrue(UserService.getUserService().addNewChecking(testUser));
	}
	
	@Ignore
	@Test
	public void testAddCheckingAcctImpl() {
		Account testUser = new Account(198);
		assertTrue(UserService.getUserService().addCheckingAcct(testUser));
	}
	
	@Ignore
	@Test
	public void testAddSavingsAcctImpl() {
		Account testUser = new Account(198);
		assertTrue(UserService.getUserService().addSavingsAcct(testUser));
	} 
	
	@Test
	public void testDepositFromAccountImpl() {
		Account testUser = new Account(1185);
		assertTrue(UserService.getUserService().depositFromAccount(testUser));
	}
	
	@Test
	public void testWithdrawFromAccountImpl() {
		Account testUser = new Account(1185);
		assertTrue(UserService.getUserService().withdrawFromAccount(testUser));
	}
	
	
}
