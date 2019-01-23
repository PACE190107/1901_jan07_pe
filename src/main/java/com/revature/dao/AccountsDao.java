package com.revature.dao;

import java.util.List;

import com.revature.models.Accounts;

public interface AccountsDao {

	public boolean insertAccounts(Accounts accounts);
	public boolean insertAccountsProcedure(Accounts accounts);
	public Accounts getAccounts();
	public List<Accounts> getAllAccounts();
	public boolean updateAccountsProcedure(Accounts accounts);
	public boolean deleteAccountsProcedure(Accounts accounts);
}