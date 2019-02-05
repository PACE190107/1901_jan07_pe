package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReimbursementService 
{
	Object process(HttpServletRequest request, HttpServletResponse response);
}