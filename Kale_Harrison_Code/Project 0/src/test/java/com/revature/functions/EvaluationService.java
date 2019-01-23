package com.revature.functions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;


import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.user.*;


public class EvaluationService {
	
	private static final JDBCFunctionsImplementation evaluationService = new JDBCFunctionsImplementation();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testInvalidSelection() throws SQLException {
		assertEquals(false, evaluationService.logIn("IncorrectUser", "BadPassword"));
	}
	
	@Test
	public void testValidUser() throws SQLException {
		assertEquals(true, evaluationService.logIn("Odin", "thor"));
	}
	

	@Test
	public void testValidSuper() throws SQLException{
		AuthenticatedUser user = new AuthenticatedUser("super", "man");
		assertEquals(true, evaluationService.testSuper(user));
	}
	
	@Test
	public void testInvalidSuper() throws SQLException{
		AuthenticatedUser user = new AuthenticatedUser("Odin", "thor");
		assertEquals(false, evaluationService.testSuper(user));
	}
	

	

}
