package com.revature.model;

import java.util.List;

public class adminUser extends userAbstract implements SuperUser {

	public adminUser(StringBuffer username, StringBuffer firstName, StringBuffer lastName, int userId,
			List<BankAccount> bankAccounts) {
		super(username, firstName, lastName, userId, bankAccounts);
		this.setAdmin();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<userAbstract> viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeUsername(String oldUsername, String newUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFirstName(String newFirstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLastName(String newLastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmail(String newEmail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUSer(String username) {
		// TODO Auto-generated method stub
		
	}

}
