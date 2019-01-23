package com.revature.ServiceTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.exceptions.LoginFailedException;
import com.revature.exceptions.OutstandingBalanceException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.ATMService;

public class ATMServiceTest {

	private static final ATMService atmTest = ATMService.getATMService();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/*
	 * Test verifyLogin User
	 */
	@Test
	public void testVerifyLogin() throws LoginFailedException {
		assertEquals(new User(2, "junit", "junit", 0), atmTest.verifyLogin(new User("junit", "junit")));
	}
	
	@Test
	public void testFailedLogin() throws LoginFailedException {
		expectedException.expect(LoginFailedException.class);
		atmTest.verifyLogin(new User("admin", "fail"));
	}
	/*
	 * Test registerNewUser boolean
	 */
	@Test
	public void testRegisterNewUser() {
		assertTrue(atmTest.registerNewUser(new User("junitTest", "junitTest")));
	}
	
	@Test
	public void testRegisterExistingUser() {
		assertFalse(atmTest.registerNewUser(new User("admin", "admin")));
	}
	
	/*
	 * Test withdraw boolean
	 */
	@Test
	public void testOverdraft() throws OverdraftException {
		expectedException.expect(OverdraftException.class);
		atmTest.withdraw(new Account(1, 1000, 2, "junit" ), 1001);
	}
	
	@Test
	public void testWithdraw() throws OverdraftException{
		assertTrue(atmTest.withdraw(new Account(1, 1000, 2, "junit"), 1000));
	}
	
	/*
	 * Test deposit boolean
	 */
	@Test
	public void testDeposit() {
		assertTrue(atmTest.deposit(new Account(1, 0, 2, "junit"), 1000));
	}
	
	/*
	 * Test createAccountRequest boolean
	 */
	@Test
	public void createAccountRequest() {
		assertTrue(atmTest.createAccountRequest("junitTest", new User(2, "junit", "junit", 0)));
	}
	
	/*
	 * Test deleteAccountRequest boolean
	 */
	@Test
	public void deleteAccountRequest() throws OutstandingBalanceException {
		assertTrue(atmTest.deleteAccountRequest(new Account(5, 0, 2, "junitTest")));
	}
	
	@Test
	public void deleteAccountOutstandingBalance() throws OutstandingBalanceException {
		expectedException.expect(OutstandingBalanceException.class);
		atmTest.deleteAccountRequest(new Account(5, 10, 2, "junitTest"));
	}
}
