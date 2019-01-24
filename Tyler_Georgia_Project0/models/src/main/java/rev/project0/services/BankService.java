package rev.project0.services;

import java.util.ArrayList;
import java.util.List;

import rev.project0.dao.AccountDaoImpl;
import rev.project0.dao.TransactionDaoImpl;
import rev.project0.dao.UserDaoImpl;
import rev.project0.models.Account;
import rev.project0.models.Transaction;
import rev.project0.models.User;

public class BankService {
	private static BankService bankService;
	
	private BankService() {
	}
	
	public static BankService getBankService() {
		if(bankService == null) {
			bankService = new BankService();
		}
		return bankService;
	}
	
	public boolean insertUser(User newUser) {
		
		UserDaoImpl userDAOImpl = UserDaoImpl.getUserDao();
		boolean userInserted = userDAOImpl.createUser(newUser);
		
		return userInserted;
		
	}
	
	public boolean checkForUserByUsername(String username) {
		UserDaoImpl userDaoImpl = UserDaoImpl.getUserDao();
		return userDaoImpl.checkForUserByUsername(username);
	}
	
	public User getUser(String username, String password) {
		UserDaoImpl userDAOImpl = UserDaoImpl.getUserDao();
		User theUser = userDAOImpl.getUser(username, password);
		return theUser;
	}
	
	public List<User> getAllUsers(){
		List<User> users = null;
		UserDaoImpl userDAOImpl = UserDaoImpl.getUserDao();
		users = userDAOImpl.getAllUsers();
		return users;
	}
	
	public boolean deleteUser(int userId) {
		UserDaoImpl userDAOImpl = UserDaoImpl.getUserDao();
		 
		return userDAOImpl.deleteUser(userId);
	}
	
	public boolean updatePassword(String password, int userId) {
		UserDaoImpl userDAOImpl = UserDaoImpl.getUserDao();
		 
		return userDAOImpl.updatePassword(password, userId);
	}
	
	public boolean updateUsername(String username, int userId) {
		UserDaoImpl userDAOImpl = UserDaoImpl.getUserDao();
		 
		return userDAOImpl.updateUsername(username, userId);
	}
	
	public Account getAccountByAccountId(int accountId) {
		
		AccountDaoImpl accountDaoImpl = AccountDaoImpl.getAccountDao();
		
		return accountDaoImpl.getAccountByAccountId(accountId);
	}
	
	public List<Account> getAccountsByUserId(int userId) {
		AccountDaoImpl accountDaoImpl = AccountDaoImpl.getAccountDao();
		return accountDaoImpl.getAccountsByUserId(userId);
	}
	
	public boolean createAccount(String accountType, int userId) {
		AccountDaoImpl accountDaoImpl = AccountDaoImpl.getAccountDao();
		return accountDaoImpl.createAccount(accountType, userId);
	}
	
	public boolean deleteAccount(int accountId) {
		AccountDaoImpl accountDaoImpl = AccountDaoImpl.getAccountDao();
		return accountDaoImpl.deleteAccount(accountId);
	}
	
	public List<Transaction> getAllTransactionsByAccountId(int accountId){
		
		TransactionDaoImpl transactionDaoImpl = TransactionDaoImpl.getTransactionDaoImpl();
		return transactionDaoImpl.getAllTransactionsByAccountId(accountId);
	}
	
	public boolean insertAndExecuteTransaction(Transaction transaction) {
		
		TransactionDaoImpl transactionDaoImpl = TransactionDaoImpl.getTransactionDaoImpl();
		
		String type = transaction.getTransactionType();
		int value = transaction.getValue();
		int fkAccountId = transaction.getAccountId();
		
		return transactionDaoImpl.insertTransaction(type, value, fkAccountId);
	}
	
	
	
}
