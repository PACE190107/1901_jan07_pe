package com.revature.eval;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.BankDAO.BankDAO;
import com.revature.BankDAO.BankDAOImpl;
import com.revature.BankingExceptions.IncorrectUsernamePassword;
import com.revature.ProjectZeroBank.projectZeroController;
import com.revature.model.BankAccount;
import com.revature.model.adminUser;

public class SQLTests {
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void signOn() {
		adminUser same = new adminUser(new StringBuffer("BJhorseman"),new StringBuffer( "Bojack"), new StringBuffer("Horseman"),1,null);
		assertEquals(same ,BankDAOImpl.getBankDao().logIn(new StringBuffer("BJhorseman"), new StringBuffer("secretariet")));
	}
	
	@Test
	public void signOnFail() {
		expectedException.expect(IncorrectUsernamePassword.class);
		BankDAOImpl.getBankDao().getBankDao().logIn(new StringBuffer("BJhresman"), new StringBuffer("secretariet")); 
	}
}
