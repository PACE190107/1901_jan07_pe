package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.Exceptions.*;
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
			System.out.println("Deposit");
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

	public void withdraw(int amount, int account, int user) { // throws OverDraftException
		System.out.println("Withdraw");
		String sql = "UPDATE ACCOUNT SET A_AMOUNT = (SELECT A_AMOUNT FROM ACCOUNT WHERE A_ID = ? AND A_UID = ?) - ? WHERE A_ID = ? AND A_UID = ?";

	}

	public void checkUsername(String username) throws UsernameAlreadyExistException {
		if (username.equals("Username")) {
			throw new UsernameAlreadyExistException();
		}
	}

	@Override
	public void getAccounts(int userID) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from ACCOUNT WHERE A_UID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet results = ps.executeQuery();
			System.out.println("ID TYPE AMOUNT");
			while (results.next()) {
				System.out.println(results.getInt(1) + " " + results.getString(2) + " " + results.getInt(3));
			}
		}
	}

}
