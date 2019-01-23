package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Exceptions.AccountNotFoundException;
import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.EmptyAccountException;
import com.revature.Exceptions.OverDraftException;
import com.revature.Exceptions.UsernameAlreadyExistException;
import com.revature.Exceptions.WithdrawException;
import com.revature.util.JDBCConnectionUtil;

public class BankImplementation implements BankDao {
	private static BankImplementation bank;

	private BankImplementation() {

	}

	public static BankImplementation getBankImplementation() {
		if (bank == null) {
			bank = new BankImplementation();
		}
		return bank;
	}

	public void deposite(int amount, int account, int user) throws SQLException, DepositFailedException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "UPDATE ACCOUNT SET A_AMOUNT = (SELECT A_AMOUNT FROM ACCOUNT WHERE A_ID = ? AND A_UID = ?) + ? WHERE A_ID = ? AND A_UID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account);
			ps.setInt(2, user);
			ps.setInt(3, amount);
			ps.setInt(4, account);
			ps.setInt(5, user);
			if (!(ps.executeUpdate() > 0)) {
				throw new DepositFailedException();
			}
		}

	}

	public void withdraw(int amount, int account, int user)
			throws SQLException, OverDraftException, AccountNotFoundException, WithdrawException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT A_AMOUNT FROM ACCOUNT WHERE A_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				int accountAmount = result.getInt(1);
				if (accountAmount >= amount) {
					sql = "UPDATE ACCOUNT SET A_AMOUNT = (SELECT A_AMOUNT FROM ACCOUNT WHERE A_ID = ? AND A_UID = ?) - ? WHERE A_ID = ? AND A_UID = ?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, account);
					ps.setInt(2, user);
					ps.setInt(3, amount);
					ps.setInt(4, account);
					ps.setInt(5, user);
					if (!(ps.executeUpdate() > 0)) {
						throw new WithdrawException();
					}
				} else {
					throw new OverDraftException();
				}
			} else {
				throw new AccountNotFoundException();
			}
		}
	}

	public void checkUsername(String username) throws UsernameAlreadyExistException {
		if (username.equals("Username")) {
			throw new UsernameAlreadyExistException();
		}
	}

	public List<Integer> getAccounts(int userID, int command) throws SQLException, EmptyAccountException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "";
			if (command == 0) {
				sql = "select * from ACCOUNT WHERE A_UID = ?";
			} else if (command == 1) {
				sql = "select * from ACCOUNT WHERE A_UID = ? AND A_AMOUNT = 0";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet results = ps.executeQuery();
			List<Integer> account = new ArrayList<Integer>();
			System.out.printf("\n%-10.10s %-10.10s %-10.10s%n\n","ID", "TYPE" ,"BALANCE");
			while (results.next()) {
				System.out.printf("%-10.10s %-10.10s %-10.10s%n\n",results.getInt(1),results.getString(2), ""+results.getInt(3));
				account.add(results.getInt(1));
			}
			if (account.isEmpty()) {
				throw new EmptyAccountException();
			}
			return account;
		}
	}

	public void createAccount(String type, int amount, int id) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "CALL INSERT_ACCOUNT(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, type);
			cs.setInt(2, amount);
			cs.setInt(3, id);
			cs.execute();
		}
	}

	public void deleteAccount(int accountID, int userID) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "DELETE FROM ACCOUNT WHERE A_ID = ? AND A_UID = ? AND A_AMOUNT = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountID);
			ps.setInt(2, userID);
			ps.execute();
		}
	}

}
