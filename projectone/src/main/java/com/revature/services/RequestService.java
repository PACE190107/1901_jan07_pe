package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestService {

	public Object process(HttpServletRequest req, HttpServletResponse resp);
	
}