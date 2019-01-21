package com.revature.BankDAO;

import java.util.List;
import com.revature.model.AccountInterface;
import com.revature.model.BankAccount;
import com.revature.model.userAbstract;

/**
 * Interface for our Data Access Object to handle database queries related to Book Tags.
 */
public interface BankDAO {
	
	public double accountWithdrawl(int accountNumber, double withdrawlAmount);
	public double accountDeposit(int accountNumber, double depositAmout);
	//accountTransfer(int withdrawlAccount, int depositAccount, double transferAmount will make if I have time
	public userAbstract logIn(StringBuffer username, StringBuffer password);
	
	public userAbstract getUser(String username);
	public List<String> getAllUsers();
	public userAbstract createUser(StringBuffer username, StringBuffer password, StringBuffer firstName, StringBuffer lastName, StringBuffer email);	//create a new bank account for an existing user
	public List<BankAccount> getAccounts(int userid);
	public BankAccount newAccount(String userId, String accountName);
	//add a user to an existing bank account
	public int newAccountUser(StringBuffer currentUserID, StringBuffer addedUserName, StringBuffer accountName );
}
