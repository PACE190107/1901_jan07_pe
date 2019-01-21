package com.revature.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.dao.DaoImplementationNoInput;
import com.revature.exceptions.OverdraftProtection;
import com.revature.exceptions.ThrowingAwayMoney;
import com.revature.models.User;

public class TestJDBCBank 
{
	private static final DaoImplementationNoInput daoImplementation = new DaoImplementationNoInput();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	public User createNewUser()
	{
		User user = new User("Test", "Case", "TC123", "qwerty987"); 
		return user;
	}
	
	@Test
	public void testRegisterUser()
	{
		User user = createNewUser();
		assertTrue(daoImplementation.registerUser(user));
	}
	
	@Test
	public void testLogin()
	{
		User user = createNewUser();
		assertTrue(daoImplementation.login(user));
	}
	
	@Test
	public void testUpdatePassword()
	{
		User user = createNewUser();
		String newP = "new_password";
		assertTrue(daoImplementation.updatePassword(user, newP));
	}
	
	@Test
	public void testDeleteUser()
	{
		User user = createNewUser();
		assertTrue(daoImplementation.deleteUser(user));
	}
	
	@Test
	public void testNewChecking()
	{
		User user = createNewUser();
		int choice = 1;
		double money = 500.00;
		
		assertTrue(DaoImplementationNoInput.newAccount(user, choice, money));
	}
	
	@Test
	public void testNewSavings()
	{
		User user = createNewUser();
		int choice = 2;
		double money = 1579.72;
		
		assertTrue(DaoImplementationNoInput.newAccount(user, choice, money));
	}
	
	@Test
	public void testDeleteEmptyAccount()
	{
		double balance = 0.0;
		
		assertTrue(DaoImplementationNoInput.deleteAccount(balance));
	}
	
	@Test
	public void testThrowingAwayMoneyException()
	{
		double balance = 64.21;
		
		expectedException.expect(ThrowingAwayMoney.class);
		DaoImplementationNoInput.deleteAccount(balance);
	}
	
	@Test
	public void testWithdraw()
	{
		int choice = 1;
		double balance = 1000.0;
		double money = 826.30;
		
		assertTrue(DaoImplementationNoInput.withdrawDeposit(choice, money, balance));
	}
	
	@Test
	public void testOverdraftProtectionException()
	{
		int choice = 1;
		double balance = 1000.0;
		double money = 5000.0;
		
		expectedException.expect(OverdraftProtection.class);
		assertTrue(DaoImplementationNoInput.withdrawDeposit(choice, money, balance));
	}
	
	@Test
	public void testDeposit()
	{
		int choice = 2;
		double balance = 1000.0;
		double money = 1126.30;
		
		assertTrue(DaoImplementationNoInput.withdrawDeposit(choice, money, balance));
	}
}