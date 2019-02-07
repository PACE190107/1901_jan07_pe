package com.revautre.dao;

import com.revature.models.User;

public interface UserDao {
	public User goodLogin(String email, String password);
	public User managerLogin(String email, String password);
}
