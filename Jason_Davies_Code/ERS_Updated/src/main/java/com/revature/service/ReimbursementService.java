package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;

public interface ReimbursementService {
	public Reimbursement insertReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	public Reimbursement updateReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	public ArrayList<Reimbursement> getAllReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	public ArrayList<Reimbursement> getAllReimbursements(int requesterId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
