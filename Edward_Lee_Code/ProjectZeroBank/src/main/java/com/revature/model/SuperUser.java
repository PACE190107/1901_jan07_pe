package com.revature.model;

import java.util.List;

public interface SuperUser {
	// new user
	public void createUser();
	
	// view user
	public void viewUser(String username);
	
	// view all users
	public List<userAbstract> viewAllUsers();
	
	// update user 
		//new password
	public void changeUserPassword(String username, String newPassword);
		//new username
	public void changeUsername(String oldUsername, String newUsername);
		//new firstname
	public void updateFirstName(String newFirstName);
		//new lastname
	public void updateLastName(String newLastName);
		//new email
	public void updateEmail(String newEmail);
	// delete user
	public void deleteUSer(String username);

}
