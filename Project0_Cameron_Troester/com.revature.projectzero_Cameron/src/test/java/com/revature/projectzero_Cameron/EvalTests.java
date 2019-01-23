package com.revature.projectzero_Cameron;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDaoImp;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDaoImp;
import com.revature.bank.util.ConnectionUtil;
import com.revature.model.Account;
import com.revature.model.User;

public class EvalTests {

	private static final UserDAO evaluationUserDao = new UserDaoImp();
	private static final AccountDAO evaluationAccDao = new AccountDaoImp();
	private static Connection con = null;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateUserP1() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		User user = new User("TestMe", "Test", "Me", "testpass");
		assertEquals(1, evaluationUserDao.createUser(user, con));
	}

	@Test
	public void testGetAccountByUser() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		Account acc = new Account("MoneyDolla", 100);
		User user = new User("MoneyDolla", "money", "dolla", "testpass");
		evaluationUserDao.createUser(user, con);
		evaluationAccDao.createAccount(new Account("MoneyDolla", 100), con);
		assertEquals(acc, evaluationAccDao.getAccountByUser("MoneyDolla", con));
	}

}