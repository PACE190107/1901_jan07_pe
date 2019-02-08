package com.revature.service;

import com.revature.models.Employee;

public interface LoginService {

	Employee attemptAuthentication(String username, String password);
}