package rev.project0.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rev.project0.models.Account;
import rev.project0.util.JDBCConnectionUtil;

public class AccountDaoImpl implements AccountDao {

	private static AccountDaoImpl accDaoImpl;
	
	//Singleton Implementation
	private AccountDaoImpl() {
		
	}
	
	public static AccountDaoImpl getAccountDao() {
		if(accDaoImpl == null) {
			accDaoImpl = new AccountDaoImpl();
		}
		return accDaoImpl;
	}
	
	@Override
	public Account getAccountByAccountId(int accountId) {
		
		ResultSet results = null;
		Account account = null;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from bank_account where account_id = " + accountId;
			PreparedStatement ps = conn.prepareStatement(sql);
			results = ps.executeQuery();
			
			if(results.next()) {
				String accTypeQ = results.getString("account_type");
				int accIdQ = results.getInt("account_id");
				int userIdQ = results.getInt("user_id");
				int balanceQ = results.getInt("balance");
				account = new Account(accTypeQ, accIdQ, userIdQ, balanceQ);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return account;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		ResultSet results = null;
		List<Account> accounts = new ArrayList<Account>();
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from bank_account where user_id = " + userId;
			PreparedStatement ps = conn.prepareStatement(sql);
			results = ps.executeQuery();
			
			while(results.next()) {
				
				String accTypeQ = results.getString("account_type");
				int accIdQ = results.getInt("account_id");
				int userIdQ = results.getInt("user_id");
				int balanceQ = results.getInt("balance");
				Account account = new Account(accTypeQ, accIdQ, userIdQ, balanceQ);
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return accounts;
	}

	@Override
	public boolean createAccount(String accountType, int userId) {
		boolean accountCreated = true;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call insert_account(?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, 0);
			ps.setInt(2, userId);
			ps.setString(3, accountType);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			accountCreated = false;
		}
		
		return accountCreated;
	}

	@Override
	public boolean deleteAccount(int accountId) {
		boolean accountDeleted = false;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "delete from bank_account where account_id = " + accountId;
			PreparedStatement ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() == 1) {
				accountDeleted = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return accountDeleted;
	}

}
