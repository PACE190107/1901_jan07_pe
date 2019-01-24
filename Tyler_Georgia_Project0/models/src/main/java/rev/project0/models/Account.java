package rev.project0.models;

import java.util.List;

enum AccountType { SAVING, CHECKING, JOINT }
public class Account {
	
	private String accountType;
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	private int accountId;
	private int userId;
	private int balance; 
	List<Transaction> transaction = null;
	
	//for creating a new account
	public Account (String type, int userid) {
		this.userId = userId;
		this.balance = 0;
		this.accountType = type;
	}
	
	//for instantiating an already existing account
	public Account (String accountType, int accountId, int userId, int balance) {
		this.accountType = accountType;
		this.accountId = accountId;
		this.userId = userId;
		this.balance = balance;
	}
	
	//TODO: implement constructor for Account
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	
	
}
