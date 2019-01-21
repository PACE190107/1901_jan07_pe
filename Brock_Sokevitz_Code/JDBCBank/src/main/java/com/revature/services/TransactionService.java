package com.revature.services;

import java.util.List;

import com.revature.dao.TransactionDaoImplementation;
import com.revature.models.Transaction;

public class TransactionService {
	
	private static TransactionService transactionService;

	private TransactionService() {

	}

	public static TransactionService getTransactionService() {
		if (transactionService == null)
			transactionService = new TransactionService();
		return transactionService;
	}
	
	public boolean insertTransaction(Transaction transaction) {
		return TransactionDaoImplementation.getTransactionDao().insertTransaction(transaction);
		}
	
	public List<Transaction> getAllTransactions(int userID){
		return TransactionDaoImplementation.getTransactionDao().getAllTransactions(userID);
		}
	
	public boolean deleteAllTransactions(int userID) {
		// TODO Auto-generated method stub
		return TransactionDaoImplementation.getTransactionDao().deleteAllUserTransactions(userID);
	}
	
	public boolean deleteAllAccountTransactions(int accountID) {
		// TODO Auto-generated method stub
		return TransactionDaoImplementation.getTransactionDao().deleteAllAccountTransactions(accountID);
	}
}
