package com.revature.model;

import java.util.List;

public class regularUser extends userAbstract {

	public regularUser(StringBuffer username, StringBuffer firstName, StringBuffer lastName, int userId,
			List<BankAccount> bankAccounts) {
		super(username, firstName, lastName, userId, bankAccounts);
	}

	@Override
	public StringBuffer getUsername() {
		// TODO Auto-generated method stub
		return super.getUsername();
	}

	@Override
	public void setUsername(StringBuffer username) {
		// TODO Auto-generated method stub
		super.setUsername(username);
	}

	@Override
	public StringBuffer getFirstName() {
		// TODO Auto-generated method stub
		return super.getFirstName();
	}

	@Override
	public void setFirstName(StringBuffer firstName) {
		// TODO Auto-generated method stub
		super.setFirstName(firstName);
	}

	@Override
	public StringBuffer getLastName() {
		// TODO Auto-generated method stub
		return super.getLastName();
	}

	@Override
	public void setLastName(StringBuffer lastName) {
		// TODO Auto-generated method stub
		super.setLastName(lastName);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}

