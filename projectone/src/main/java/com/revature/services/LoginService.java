package com.revature.services;

import com.revature.models.Employee;

public interface LoginService {

	Employee attemptAuthentication(String username, String passw0rd);
}
