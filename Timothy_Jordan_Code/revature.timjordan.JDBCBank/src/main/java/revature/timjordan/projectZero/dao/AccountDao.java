package revature.timjordan.projectZero.dao;

import java.util.List;

import revature.timjordan.projectZero.models.Account;
import revature.timjordan.projectZero.models.User;

public interface AccountDao {
	public boolean addChecking(User currentUser);
	public boolean addSavings(User currrntUser);
	public List<Account> getAccounts(User currentUser, String type, int number);
	public boolean withdraw(Account tempAccount, double amount);
	public boolean deposit(Account tempAccount, double amount);
	public boolean hasSavings(User currentUser);
	public boolean removeSavings(Account currentAccount);

}
