package com.revature.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	
	private static ReimbursementService rebService;
	private final ObjectMapper mapper = new ObjectMapper();
	
	public static ReimbursementService getRebService() {
		if (rebService == null) {
			rebService = new ReimbursementService();
		}
		return rebService;
	}
	
	public Object process(HttpServletRequest request, HttpServletResponse response) {

		if (request.getMethod().equals("GET")) {
			String[] path = request.getRequestURI().split("/");
			 if (path.length == 5 && request.getRequestURI().contains("AllApproved")) {
					//Manager can view resolved requests
					//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/AllApproved
					return viewResolved();
				}
			 else if (path.length == 5 && request.getRequestURI().contains("Approved")) {
				//User can view resolved requests
				//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Approved
				HttpSession session = request.getSession();
				int e_id = (int) session.getAttribute("e_id");
				return viewResolved(e_id);
			}
			else if (path.length == 5 && request.getRequestURI().contains("AllPending")) {
				//Manager can view Pending requests
				//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/AllPending
				return viewPending();
			}else if (path.length == 5 && request.getRequestURI().contains("Pending")) {
				//User can view Pending requests
				//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Pending
				HttpSession session = request.getSession();
				int e_id = (int) session.getAttribute("e_id");
				return viewPending(e_id);
			}
			else
				return null;
		}
		if (request.getMethod().equals("POST")) {
			if (request.getHeader("Content-Type").equals("application/json")) {
				try {
					//An Employee can submit a request
					Reimbursement newReb = mapper.readValue(request.getReader(), Reimbursement.class);
					double amount = newReb.getR_amount();
					String description = newReb.getDescription();
					HttpSession session = request.getSession();
					int e_id = (int) session.getAttribute("e_id");
					return submitRequest(amount, description, e_id);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		if (request.getMethod().equals("PUT")) {
			if (request.getHeader("Content-Type").equals("application/json")) {
				if (request.getRequestURI().contains("Approval")) {
					try {
						//Manager can approve a request 
						//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Approval
						Reimbursement rebDecision = mapper.readValue(request.getReader(), Reimbursement.class);
						int reb_id = rebDecision.getR_id();
						HttpSession session = request.getSession();
						int resolver_id = (int) session.getAttribute("e_id");
						return approveRequest(reb_id, resolver_id);
					}catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (request.getRequestURI().contains("Deny")) {
					try {
						//Manager can deny a request 
						//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Deny
						Reimbursement rebDecision = mapper.readValue(request.getReader(), Reimbursement.class);
						int reb_id = rebDecision.getR_id();
						HttpSession session = request.getSession();
						int resolver_id = (int) session.getAttribute("e_id");
						return denyRequest(reb_id, resolver_id);
					}catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}if(request.getRequestURI().contains("Request")) {
					//view Requests from single employee
					//http://localhost:8080/ExpenseReimbursement/rest/reimbursement/Request
					try {
						Reimbursement newReb = mapper.readValue(request.getReader(), Reimbursement.class);
						int single = newReb.getE_id();
						return viewRequestsSingleEmployee(single);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		
		
		return null;
	}
	
	
	public boolean submitRequest(double amount, String description, int e_id) {
		return ReimbursementDaoImpl.getRebDao().submitRequest(amount, description, e_id);
	}
	
	public List<Reimbursement> viewPending(){
		return ReimbursementDaoImpl.getRebDao().viewPending();
	}
	
	public List<Reimbursement> viewPending(int e_id){
		return ReimbursementDaoImpl.getRebDao().viewPending(e_id);
	}
	
	public List<Reimbursement> viewResolved(){
		return ReimbursementDaoImpl.getRebDao().viewResolved();
	}
	public List<Reimbursement> viewResolved(int e_id){
		return ReimbursementDaoImpl.getRebDao().viewResolved(e_id);
	}
	
	public boolean approveRequest(int re_id, int e_id) {
		return ReimbursementDaoImpl.getRebDao().approveRequest(re_id, e_id);
	}
	
	public boolean denyRequest(int re_id, int e_id) {
		return ReimbursementDaoImpl.getRebDao().denyRequest(re_id, e_id);
	}
	
	public List<Reimbursement> viewRequestsSingleEmployee(int e_id){
		return ReimbursementDaoImpl.getRebDao().viewRequestsSingleEmployee(e_id);
	}
	
	
	
	
}
