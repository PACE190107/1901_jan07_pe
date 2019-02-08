package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.User;

public interface LoginService {
	
	User attemptAuthentication(HttpServletRequest request, HttpServletResponse response);
}
