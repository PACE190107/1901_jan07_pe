package com.revature.dao;

import java.util.List;

import com.revature.models.Admin;
import com.revature.models.User;

public interface AdminDao {
	
	public boolean adminCheckImpl(Admin admin);
	public boolean createAUser();
	public boolean updateAUser(User user);
	public boolean deleteAUser(User user);
	public List<User> viewAllUsers();
	
	

}
