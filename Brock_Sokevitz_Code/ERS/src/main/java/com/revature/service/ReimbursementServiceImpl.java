package com.revature.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDaoImplementation;
import com.revature.model.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	final static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	private static final ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object process(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		List<Reimbursement> reimbursementList = null;
		String[] path = req.getRequestURI().split("/");
		for (int i = 0; i < path.length; i++)
			log.info(path[i]);

		if (path.length == 4) {
			reimbursementList = reimbursementService.getAllPendingReimbursements();
		} else if (req.getRequestURI().contains("resolved")) {
			log.info("we got here");
			reimbursementList = reimbursementService.getAllResolvedReimbursements();
		} else if (req.getRequestURI().contains("users")) {
			log.info(path[path.length - 1]);
			reimbursementList = reimbursementService.getAllReimbursements(path[path.length - 1]);
		} else if (req.getRequestURI().contains("image")) {
			log.info("we got here");
			log.info(path[path.length - 1]);
	          File file = new File("C:\\apache-tomcat-9.0.14\\webapps\\data\\"+path[path.length - 1]+".png");

	          byte[] imageBytes = getBytesFromFile(file);
	          ServletOutputStream sos = resp.getOutputStream();
	          resp.setContentType("image/gif");
	          sos.write(imageBytes);
		} else {
			reimbursementList = reimbursementService
					.getAllReimbursements(String.valueOf(req.getSession().getAttribute("username")));
		}

		req.getSession().setAttribute("username", req.getSession().getAttribute("username"));

		return reimbursementList;

	}

	@Override
	public List<Reimbursement> getAllReimbursements(String username) {
		return ReimbursementDaoImplementation.getReimbursementDao().getAllReimbursements(username);
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() {
		// TODO Auto-generated method stub
		return ReimbursementDaoImplementation.getReimbursementDao().getAllPendingReimbursements();
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursements() {
		// TODO Auto-generated method stub
		return ReimbursementDaoImplementation.getReimbursementDao().getAllResolvedReimbursements();
	}

	@Override
	public boolean insertReimbursement(String username, double amount) {
		return ReimbursementDaoImplementation.getReimbursementDao().insertReimbursement(username, amount);
	}

	@Override
	public boolean deleteReimbursement(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReimbursement(int reimbursementId, String status, String manager) {
		return ReimbursementDaoImplementation.getReimbursementDao().updateReimbursement(reimbursementId, status,
				manager);
	}

	@Override
	public void updateUsernameReimbursements(String newUsername, String oldUsername) {
		ReimbursementDaoImplementation.getReimbursementDao().updateUsernameReimbursement(newUsername, oldUsername);

	}

	@Override
	public Reimbursement getReimbursement(int parseInt) {
		// TODO Auto-generated method stub
		return ReimbursementDaoImplementation.getReimbursementDao().getReimbursement(parseInt);
	}

	@Override
	public int getMostRecentReimbursement() {
		// TODO Auto-generated method stub
		return ReimbursementDaoImplementation.getReimbursementDao().getMostRecentReimbursement();
	}

	@Override
	public byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];
		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		// Close the input stream and return bytes
		is.close();
		return bytes;
	}// end getBytesFromFile

	@Override
	public void addImage(int id) {
		// TODO Auto-generated method stub
		ReimbursementDaoImplementation.getReimbursementDao().addImage(id);
	}

}
