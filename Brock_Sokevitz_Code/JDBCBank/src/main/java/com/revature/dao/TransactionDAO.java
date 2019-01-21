package com.revature.dao;

import java.util.List;
import com.revature.models.Transaction;

public interface TransactionDAO {

	public boolean insertTransaction(Transaction transaction);
	public List<Transaction> getAllTransactions(int userID);	
	public boolean deleteAllUserTransactions(int userID);
	boolean deleteAllAccountTransactions(int userID);
}
