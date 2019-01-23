package com.revature.functions;
import java.sql.SQLException;

import com.revature.user.AuthenticatedUser;

public interface JDBCFunctions {
	
	public void viewMenu(AuthenticatedUser user) throws SQLException;
	public boolean logIn(String username, String password) throws SQLException;
	public void viewAccounts(AuthenticatedUser user) throws  SQLException;
	public void createAccount(AuthenticatedUser user) throws  SQLException;
	public void deleteAccount(AuthenticatedUser user) throws  SQLException;
	public void makeDeposit(AuthenticatedUser user) throws  SQLException;
	public void makeWithdrawl(AuthenticatedUser user) throws  SQLException;
	public void pressAnyKeyToContinue();
	public void firstChoice() throws SQLException;
	public void createUserAccount() throws SQLException;
	public boolean testSuper(AuthenticatedUser user);
	public void superMenu(AuthenticatedUser user) throws SQLException;
	public void superViewUser(AuthenticatedUser user) throws SQLException;
	public void superUpdate(AuthenticatedUser user) throws SQLException;
	public void superChangeUser(AuthenticatedUser user, int userId) throws SQLException;
	public void superChangePassword(AuthenticatedUser user, int userId) throws SQLException;
	public void superDeleteUser(AuthenticatedUser user) throws SQLException;
}
