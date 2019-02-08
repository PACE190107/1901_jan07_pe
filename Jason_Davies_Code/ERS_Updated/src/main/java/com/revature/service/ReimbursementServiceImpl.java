package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImplementation;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	private final ReimbursementDAO dao = ReimbursementDAOImplementation.getReimbursementDAO();
	
	@Override
	public Reimbursement insertReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ReimbursementType type = ReimbursementType.valueOf(request.getParameter("typeSelect").toString());
		try {
			float amount = Float.parseFloat(request.getParameter("amount"));
			int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
			logger.info("insertReimbursement() - succeeded");
			return dao.insertReimbursement(new Reimbursement(-1, id, -1, type, ReimbursementStatus.PENDING, amount, new Date().toString()));		
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public Reimbursement updateReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Reimbursement r = dao.getReimbursement(Integer.parseInt(request.getParameter("id").toString()));
			if (r != null) {
				r.setAuthorizerId(Integer.parseInt(request.getSession().getAttribute("id").toString()));
				r.setStatus(ReimbursementStatus.valueOf(request.getParameter("btn").toString()));
				return dao.updateReimbursement(r);
			}
		} catch (NumberFormatException e) {}
		return null;
	}
	
	@Override
	public ArrayList<Reimbursement> getAllReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArrayList<Reimbursement> reimbursements = dao.getAllReimbursements(ReimbursementStatus.PENDING);
		reimbursements.addAll(dao.getAllReimbursements(ReimbursementStatus.APPROVED));
		reimbursements.addAll(dao.getAllReimbursements(ReimbursementStatus.DENIED));
		return reimbursements;
	}
	
	@Override
	public ArrayList<Reimbursement> getAllReimbursements(int requesterId, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArrayList<Reimbursement> reimbursements = dao.getAllReimbursements(requesterId, ReimbursementStatus.PENDING);
		reimbursements.addAll(dao.getAllReimbursements(requesterId, ReimbursementStatus.APPROVED));
		reimbursements.addAll(dao.getAllReimbursements(requesterId, ReimbursementStatus.DENIED));
		request.getSession().setAttribute("r_id", -1);
		return reimbursements;
	}

}
