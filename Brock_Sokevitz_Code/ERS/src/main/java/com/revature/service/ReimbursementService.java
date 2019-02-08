package com.revature.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.model.Reimbursement;

public interface ReimbursementService {
	
	Object process(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException;

	List<Reimbursement> getAllReimbursements(String username);
	
	List<Reimbursement> getAllPendingReimbursements();
	
	boolean insertReimbursement(String username, double amount);
	
	boolean deleteReimbursement(int id);

	boolean updateReimbursement(int parseInt, String parameter, String manager);

	List<Reimbursement> getAllResolvedReimbursements();

	void updateUsernameReimbursements(String newUsername, String oldUsername);

	Reimbursement getReimbursement(int parseInt);

	int getMostRecentReimbursement();

	void addImage(int id);

	byte[] getBytesFromFile(File file) throws IOException;
}
