package com.revature.BankDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.BankingExceptions.IncorrectUsernamePassword;
import com.revature.ProjectZeroBank.projectZeroController;
import com.revature.model.AccountInterface;
import com.revature.model.BankAccount;
import com.revature.model.adminUser;
import com.revature.model.regularUser;
import com.revature.model.userAbstract;
import com.revature.utilities.DAOUtilities;

import oracle.jdbc.OracleTypes;

public class BankDAOImpl implements BankDAO {
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;

	/*------------------------------------------------------------------------------------------------*/
	private static BankDAOImpl bankDAO;
	final static Logger log = LogManager.getLogger(BankDAOImpl.class);
	private BankDAOImpl() {
	}
	
	public static BankDAOImpl getBankDao() {
		if (bankDAO == null) {
			bankDAO = new BankDAOImpl();
		}
		return bankDAO;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	public double accountWithdrawl(int accountNumber, double withdrawAmount) {
		try {
			String sql = "CALL WITHDRAW_FROM(?,?)";
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, accountNumber);
			stmt.setDouble(2, withdrawAmount);
			stmt.executeUpdate();
		}catch(Exception e) {
			log.error(e.getStackTrace());
			System.out.println(e.getStackTrace());
		}
		finally {
		this.closeResources();
		}
		return 0;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	public double accountDeposit(int accountNumber, double depositAmount) {
		try {
			String sql = "CALL DEPOSIT_CASH(?,?)";
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, accountNumber);
			stmt.setDouble(2, depositAmount);
			stmt.executeUpdate();
		}catch(Exception e) {
			log.error(e.toString());
			System.out.println(e.toString());
		}
		finally {
		this.closeResources();
		}
		return 0;
	}

	/*------------------------------------------------------------------------------------------------*/
	public userAbstract logIn(StringBuffer username, StringBuffer password) {
		// TODO Auto-generated method stub
		userAbstract user = null;
		try (Connection conn = DAOUtilities.getConnection()) {
			String sql = "CALL LOG_IN(?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1,  username.toString());
			ps.setString(2, password.toString());
			ps.registerOutParameter(3, OracleTypes.CURSOR);
			if(ps.executeUpdate() == 0) {
				ResultSet rs =(ResultSet) ps.getObject(3);
				if(!rs.next()) {
					throw new IncorrectUsernamePassword();
				}
				if(rs.getString(7).equals("Y")) {
					return user = new adminUser(new StringBuffer(rs.getString(4)), new StringBuffer(rs.getString(2)), new StringBuffer(rs.getString(3)), rs.getInt(1), getAccounts(rs.getInt(1)));
				}else {
					return user = new regularUser(new StringBuffer(rs.getString(4)), new StringBuffer(rs.getString(2)), new StringBuffer(rs.getString(3)), rs.getInt(1), getAccounts(rs.getInt(1)));
				}
			}
			return user;
		}
		catch(IncorrectUsernamePassword iup) {
			log.info("Log in failed as: " + username + ", " + password);
			System.out.println("Invalid username and password combo");
		}catch(SQLException sqle) {
			//logger
			System.out.println(sqle.toString());
		}
		catch(Exception e) {
			//logger
			System.out.println(e.toString());
		}
		finally {
			this.closeResources();
		}
		return user;
	}

	/*------------------------------------------------------------------------------------------------*/
	public userAbstract createUser(StringBuffer username, StringBuffer password, StringBuffer firstName, StringBuffer lastName,
			StringBuffer email) {
		userAbstract newUser =  null;
		try (Connection conn = DAOUtilities.getConnection()) {
			String sql = "CALL NEW_CUSTOMER(?,?,?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps = conn.prepareCall(sql);
			ps.setString(1, username.toString());
			ps.setString(2, password.toString());
			ps.setString(3, firstName.toString());
			ps.setString(4, lastName.toString());
			ps.setString(5, email.toString());
			ps.registerOutParameter(6, OracleTypes.CURSOR);
			if(ps.executeUpdate() == 0) { //need to check this
				ResultSet rs =(ResultSet) ps.getObject(6);
				int user_id = 0;
				StringBuffer user = null;
				StringBuffer fname= null;
				StringBuffer lname= null;
				while(rs.next()) {
					user_id = rs.getInt(1);
					user = new StringBuffer(rs.getString(4));
					fname = new StringBuffer(rs.getString(2));
					lname = new StringBuffer(rs.getString(3));
					
				}
				rs.close();
				List<BankAccount> ba = getAccounts(user_id);
				newUser = new regularUser(user, fname,
						lname, user_id, ba);
			} else {
				throw new SQLException();
			}
		}catch(Exception e){
			//log the error
			System.out.println(e.toString());
			newUser = null;
		}
		finally {
		this.closeResources();
		}
		return newUser;
	}

	/*------------------------------------------------------------------------------------------------*/
	public BankAccount newAccount(String Username, String accountName) {
		BankAccount ba = null;
		try( Connection conn = DAOUtilities.getConnection()){
			String sql ="CALL NEW_ACCOUNT(?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, Username.toString());
			ps.setString(2, accountName.toString());
			ps.registerOutParameter(3, OracleTypes.CURSOR);
			if(ps.executeUpdate() == 0) {
				ResultSet rs = (ResultSet) ps.getObject(3);
				rs.next();
				ba = new BankAccount(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getFloat(4));
			}
			return ba;
		}catch(Exception e){
			//log
		}
		finally {
			this.closeResources();			
		}
		return ba;
	}

	/*------------------------------------------------------------------------------------------------*/
	public int newAccountUser(StringBuffer currentUserID, StringBuffer addedUserName, StringBuffer accountName) {
		//for adding an additional user to an account
		this.closeResources();
		return 0;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	public List<String> getAllUsers(){
		List<String> user = new ArrayList<>();
		try {
			String sql = "SELECT USER_NAME FROM BANKING_CUSTOMER";
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(sql);
			ResultSet rs;
			rs =stmt.executeQuery();
			while(rs.next()) {
				System.out.println(user);
				user.add(rs.getString(1));
			}
		}catch(Exception e) {
			log.error(e.toString());
			System.out.println(e.toString());
		}
		finally {
		this.closeResources();
		}
		return user;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	@Override
	public userAbstract getUser(String username) {
//		haven't needed this yes
		return null;
	}
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<BankAccount> getAccounts(int userid) {
		List<BankAccount> accounts = new ArrayList<>();
		try (Connection conn = DAOUtilities.getConnection()) {
			String sql = "CALL GET_USER_ACCOUNTS(?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, userid);
			ps.registerOutParameter(2, OracleTypes.CURSOR);
			ps.execute();
			ResultSet rs = (ResultSet) ps.getObject(2);
			while(rs.next()) {
				accounts.add(new BankAccount(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getFloat(4)));
			}
		}catch(Exception e) {
			//make a log
		}finally {
			closeResources();
		}
		return accounts;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	public boolean deleteAccount(int accountID) {
		String sql = "CALL DELETE_ACCOUNT(?)";
		try{
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, accountID);
			stmt.execute();
			return true;
		}catch(Exception e) {
			log.error(e.getStackTrace());
		}
		finally {
			closeResources();
		}
		return false;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	public void deleteUser(String deletedUser) {
		String sql = "CALL DELETE_BY_USERNAME(?)";
		try{
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, deletedUser);
			stmt.execute();
		}catch(Exception e) {
			log.error(e.getStackTrace());
		}
		finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/
	public void updatePassword(String username, String newPassword) {
		String sql = "CALL CHANGE_PASSWORD(?, ?)";
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(2, username);
			stmt.setString(1,newPassword);
			stmt.executeUpdate();
		}catch(Exception e){
			log.error( e.toString());
		}finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
//switch to logger
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
//switch to logger
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
