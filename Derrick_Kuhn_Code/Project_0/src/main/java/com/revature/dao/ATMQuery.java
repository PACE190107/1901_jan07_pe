package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.exceptions.LoginFailedException;
import com.revature.exceptions.OutstandingBalanceException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.DBConnectionUtil;


public class ATMQuery implements ATMQueryInterface {

	private static ATMQuery atmQuery;
	
	private ATMQuery() {
		super();
	}
	
	public static ATMQuery getATMQuery() {
		if(atmQuery == null) {
			atmQuery = new ATMQuery();
			return atmQuery;
		} else return atmQuery;
	}
	@Override
	public boolean registerNewUser(User u) {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "call REGISTER_USER(?,?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			if(cs.executeUpdate() == 0) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public User verifyLogin(User u) throws LoginFailedException {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "select * from BANK_USER where U_NAME = \'"+u.getUsername()+"\'";
			Statement stmt = connect.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			result.next();
			if(result.getString("U_PASSWORD").equals(u.getPassword())) {
				return new User(result.getInt("U_ID"), result.getString("U_NAME"), result.getString("U_PASSWORD"), result.getInt("U_SUPER"));
			} else throw new LoginFailedException();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new LoginFailedException();
		}
	}

	@Override
	public boolean withdraw(Account account, double withdraw) throws OverdraftException {
		try(Connection connect = DBConnectionUtil.getConnection()){
			if(withdraw < 0) return false;
			if(account.getBalance() < withdraw) {
				throw new OverdraftException();
			} else {
				account.setBalance(account.getBalance() - withdraw);
			}
			String sql = "update BANK_ACCOUNT set A_BALANCE = "+account.getBalance()+" where A_ID = "+account.getAccountNumber();
			Statement stmt = connect.createStatement();
			boolean returnVal = stmt.execute(sql);
			stmt.execute("commit");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deposit(Account account, double deposit) {
		try(Connection connect = DBConnectionUtil.getConnection()){
			if(deposit < 0) return false;
			account.setBalance(account.getBalance() + deposit);
			String sql = "update BANK_ACCOUNT set A_BALANCE = "+account.getBalance()+" where A_ID = "+account.getAccountNumber();
			Statement stmt = connect.createStatement();
			boolean returnVal = stmt.execute(sql);
			stmt.execute("commit");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean createAccountRequest(String account, User user) {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "call CREATE_ACCOUNT(?,?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, account);
			cs.setInt(2, user.getUserID());
			if(cs.executeUpdate() == 0) return true;
			else return false;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteAccountRequest(Account account) throws OutstandingBalanceException {
		if(account.getBalance() != 0) throw new OutstandingBalanceException();
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "delete BANK_ACCOUNT where A_ID = "+account.getAccountNumber();
			Statement stmt = connect.createStatement();
			boolean returnVal = stmt.execute(sql);
			stmt.execute("commit");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	@Override
	public ArrayList<User> listAllUsers() {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "select * from BANK_USER";
			Statement stmt = connect.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ArrayList<User> users = new ArrayList<User>();
			while(result.next()) {
				users.add(new User(result.getInt("U_ID"), result.getString("U_NAME"), result.getString("U_PASSWORD"), result.getInt("U_SUPER")));
			}
			return users;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch
			return null;
		}
	}

	@Override
	public ArrayList<Account> getUserAccounts(User u) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "select * from BANK_ACCOUNT where U_ID = \'" + u.getUserID() +"\'";
			Statement stmt = connect.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				accounts.add(new Account(result.getInt("A_ID"), result.getDouble("A_BALANCE"), result.getInt("U_ID"), result.getString("A_NAME")));
			}
		} catch (SQLException e) {
			
		}
		return accounts;
	}

	
	@Override
	public boolean createUser(User u) {
		try(Connection connect = DBConnectionUtil.getConnection()){
			// name, password, id, super
			String sql = "insert into BANK_USER values(?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getUserID());
			if(u.isSuperUser()) ps.setInt(4, 1);
			else ps.setInt(4, 0);
			if(ps.executeUpdate() > 0) {
				return true;
			} else return false;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean updateUser(User u) {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "update BANK_USER set  U_NAME = \'"+u.getUsername()+"\', U_PASSWORD = \'"+u.getPassword()+"\' where U_ID = "+u.getUserID();
			Statement stmt = connect.createStatement();
			stmt.execute(sql);
			stmt.execute("commit");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteUser(User u) {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "delete BANK_USER where U_ID = "+u.getUserID();
			Statement stmt = connect.createStatement();
			stmt.execute(sql);
			stmt.execute("commit");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteAllUsers() {
		try(Connection connect = DBConnectionUtil.getConnection()){
			String sql = "delete BANK_USER where U_ID != 1";
			Statement stmt = connect.createStatement();
			stmt.execute(sql);
			stmt.execute("commit");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
