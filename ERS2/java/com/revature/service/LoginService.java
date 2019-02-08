package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

	Object attemptAuthentication(HttpServletRequest request, HttpServletResponse response);
}