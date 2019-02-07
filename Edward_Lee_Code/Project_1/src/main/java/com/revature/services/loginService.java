package com.revature.services;

import com.revature.model.Employee;

public interface loginService {
	public Employee attemptLogin(String username, String password);
}
