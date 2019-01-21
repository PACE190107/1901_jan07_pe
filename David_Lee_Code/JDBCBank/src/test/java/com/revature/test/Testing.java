package com.revature.test;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;

import junit.framework.TestCase;


public class Testing extends TestCase {
	

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	User existing_user = new User("Test2","User2","UserName2","Password2");
	Account test_account = new Account();
	
	@Test
	public void F_addAccount() {
		existing_user.setUser_id(UserService.getUserService().getID(existing_user));
		test_account.setAccountBalance(30.0);
		test_account.setAccountType("Checking");
		assertTrue(UserService.getUserService().createAccount(existing_user, test_account));
	}	
	@Test
	public void addanotherAccount() {
		existing_user.setUser_id(UserService.getUserService().getID(existing_user));
		test_account.setAccountBalance(30.0);
		test_account.setAccountType("Checking");
		assertTrue(UserService.getUserService().createAccount(existing_user, test_account));
	}
	
	@Test
	public void createExistUser() {
		User existing_user = new User("Test2","User2","UserName2","Password2");
		existing_user.setUser_id(UserService.getUserService().getID(existing_user));
		assertFalse(UserService.getUserService().createUser(existing_user));
	}
	
	@Test
	public void checkPass() {
		User existing_user = new User("Test2","User2","UserName2","Password2");
		existing_user.setUser_id(UserService.getUserService().getID(existing_user));
		assertTrue(UserService.getUserService().confirmPass(existing_user));
	}
	
	@Test
	public void checkInvalidPass() {
		existing_user.setUser_id(UserService.getUserService().getID(existing_user));
		existing_user.setPassword("WRONG");
		assertFalse(UserService.getUserService().confirmPass(existing_user));
	}
	
}