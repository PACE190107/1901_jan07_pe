package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;

import org.apache.log4j.Logger;
public class ERSService {
	final static Logger log = Logger.getLogger(ERSService.class);
	
	private static ERSService ersService = null;
	
	private ERSService() {}
	
	public static ERSService getERSService() {
		if(ersService == null) {
			ersService = new ERSService();
		}
		return ersService;
	}
	
	
	//tested
	public boolean createEmployee(Employee employee) {
		log.info("method createEmployee() in ERSService");
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		
		return userDao.createEmployee(employee);
		
	}
	
	//tested
	public Employee getUserById(int id) {
		log.info("method getUserById() in ERSService");
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		
		return userDao.getUserById(id);
	}
	
	//tested
	public Employee getUserByUsernamePassword(String username, String password) {
		log.info("method getUserByUsernamePassword() in ERSService");
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		return userDao.getUserByUsernamePassword(username, password);
	}
	
	//tested
	public List<Employee> getAllEmployees(){
		log.info("method getAllEmployeed() in ERSService");
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		return userDao.getAllEmployees();
	}
	
	//Tested
	public boolean createReimbursement(Reimbursement reimbursement) {
		log.info("method createReimbursement() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.createReimbursement(reimbursement);
	}
	
	//tested
	public Reimbursement getReimbursementById(int rId) {
		log.info("method getReimbursementById() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.getReimbursementById(rId);
	}
	
	//tested
	public List<Reimbursement> getAllReimbursements(){
		log.info("method getAllReimbursements() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.getAllReimbursements();
		
	}
	
	//tested
	public List<Reimbursement> getPendingReimbursements(){
		log.info("method getPendingReimbursements() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.getPendingReimbursements();
	}
	
	//tested
	public List<Reimbursement> getResolvedReimbursements(){
		log.info("method getResolvedReimbursements() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.getResolvedReimbursements();
		
	}
	
	//tested
	public List<Reimbursement> getReimbursementsByEmployeeId(int id){
		log.info("method getReimbursementsByEmployeeId() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.getReimbursementsByEmployeeId(id);
	}
	
	//tested
	public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id){
		log.info("getResolvedReimbursementsByEmployeeId() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		return reimbursementDao.getResolvedReimbursementsByEmployeeId(id);
	}
	
	//tested
	public boolean resolveReimbursement(ReimbursementStatus newStatus, int rId, int managerId) {
		
		log.info("method resolveReimbursement() in ERSService");
		ReimbursementDaoImpl reimbursementDao = ReimbursementDaoImpl.getReimbursementDao();
		
		return reimbursementDao.resolveReimbursement(newStatus, rId, managerId);
	}
	
	public boolean updateFirstName(String newFirstName, int uId) {
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		return userDao.updateFirstName(newFirstName, uId);
		
	}
	
	public boolean updateLastName(String newLastName, int uId) {
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		return userDao.updateLastName(newLastName, uId);
	}
	
	public boolean updateEmail(String newEmail, int uId) {
		UserDaoImpl userDao = UserDaoImpl.getUserDao();
		return userDao.updateEmail(newEmail, uId);
	}
}
