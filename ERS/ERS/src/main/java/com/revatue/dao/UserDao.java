package com.revatue.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	public User goodLogin(String email, String password);
	//public User managerLogin(String email, String password);
	public List<User> viewEmployees();
	public User updateUser(int e_id, String firstName, String lastName, String email);
}
