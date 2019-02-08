package com.revature.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface LoginService {
	
	Object process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	User attemptAuthentication(String username, String password);

	User insertUser(String email, String username, String password);

	User getUser(String username);

	User updateUser(User updatedUser, String valueOf);

	List<User> getAllUsers();

	boolean sendEmail(User employee, User manager, Reimbursement reimbursement, String message);

	void uploadFile(String filePath, HttpServletRequest req, int id);

	boolean userExists(String username);
}
