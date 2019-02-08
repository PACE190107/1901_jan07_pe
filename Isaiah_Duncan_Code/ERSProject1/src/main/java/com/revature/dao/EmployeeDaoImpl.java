package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import com.revature.exceptions.UsernameExistsException;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.util.ConnectionUtil;
import com.revature.util.GoogleMail;

import oracle.jdbc.proxy.annotation.Pre;

public class EmployeeDaoImpl implements EmployeeDao {

	final static Logger Log = Logger.getLogger(ConnectionUtil.class);
	
	@Override
	public List<Request> getEmployeeResolved(Employee emp) {
		
		List<Request> employeeResolvedRequests = new ArrayList<>();
		//String sql = "SELECT * FROM requests WHERE"
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean updateEmployee(int employeeId, String username, String firstname, String lastname,
			String email) {
		
		String sql = "UPDATE employees SET username = ?, firstname  = ?, lastname = ?, email = ?" +
		" WHERE employee_id = ?";
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username); ps.setString(2, firstname); 
			ps.setString(3, lastname); ps.setString(4, email); ps.setInt(5, employeeId);
			if(ps.executeUpdate() == 1) {
				return true;
			} else {
				throw new SQLException("SQL Exception: UpdateEmployee method failed!");
			}
			
		} catch (SQLException e) {
			
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateLogin(String currentUsername, String username, String password) {
		
		String sql = "UPDATE login SET username = ?, password  = ? WHERE username = ?";
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username); ps.setString(2, password); ps.setString(3, currentUsername);  
			if(ps.executeUpdate() == 1) {
				return true;
			} else {
				throw new SQLException("SQL Exception: UpdateLogin method failed!");
			}
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	//mangager function to get all employees
	@Override
	public List<Employee> getAllEmployees() {
		//create empty array list to store results
		List<Employee> allEmployees = new ArrayList<>();
		
		String sql = "SELECT * FROM employees ORDER BY employee_id ASC";
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet results = ps.executeQuery();
			while(results.next()) {
				allEmployees.add(new Employee(results.getInt("employee_id"), results.getString("firstname"),
						results.getString("lastname"), results.getString("username"), results.getString("email"),
						results.getString("is_manager")));
			}
			return allEmployees;
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	//manager function to get all pending requests
	@Override
	public List<Request> getAllPendingRequests() {

		//create empty array list to store results
		List<Request> allPendingRequests = new ArrayList<>();
		
		String sql = "SELECT * FROM requests WHERE status = 'PENDING' ORDER BY request_id ASC";
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet results = ps.executeQuery();
			while(results.next()) {
				allPendingRequests.add(new Request(results.getInt("request_id"),results.getInt("employee_id"), results.getString("type"),
						results.getString("description"), results.getDouble("amount"), results.getString("status"),
						results.getString("date_submitted")));
			}
			return allPendingRequests;
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Request> getAllResolvedRequests() {

		//create empty array list to store results
		List<Request> allResolvedRequests = new ArrayList<>();
		
		String sql = "SELECT * FROM requests WHERE status IN('APPROVED', 'DECLINED') ORDER BY request_id DESC";
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet results = ps.executeQuery();
			while(results.next()) {
				allResolvedRequests.add(new Request(results.getInt("request_id"),results.getInt("employee_id"), results.getString("type"),
						results.getString("description"), results.getDouble("amount"), results.getString("status"),
						results.getString("date_submitted"), results.getString("date_resolved"), results.getInt("resolved_by_id")));
			}
			return allResolvedRequests;
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Request> getAnEmployeesRequests() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//manager function to resolve an employee request
	@Override
	public boolean resolveEmployeeRequest(int requestId, String status, int managerId) {

		int selectEmpId = 0;
		String sendEmail = null;
		String emailMessage = null;
		
		if(status.equals("APPROVED")) {
			emailMessage = "Hello,\n\n We are pleased to inform you that your expense reimbursement request with the ID of " +requestId+ " has been " + status + "." +
		"\n\n Regards,\n Good Bank - Staff";
		} else if (status.equals("DECLINED")) {
			emailMessage = "Hello,\n\n We're sorry to inform you that your expense reimbursement request with the ID of " +requestId+ " has been " + status + "." +
					"\n\n Regards,\n Good Bank - Staff";
		}
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT employee_id FROM requests WHERE request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			ResultSet results = ps.executeQuery();
			if(results.next()) {
				selectEmpId = results.getInt("employee_id");
			}
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT email FROM employees WHERE employee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, selectEmpId);
			ResultSet results = ps.executeQuery();
			if(results.next()) {
				sendEmail = results.getString("email");
			}
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		String timestamp = dtf.format(localDate); //11/16/2016
		
		
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE requests SET status = ?, resolved_by_id = ?, date_resolved = ? WHERE request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, managerId);
			ps.setString(3, timestamp);
			ps.setInt(4, requestId);
			
			if(ps.executeUpdate() == 1) {
				try {
					GoogleMail.Send("iduncan.revature", "iamking23!", sendEmail, "iduncan.revature@gmail.com", "Good Bank - Expense Reimbursement Resolution", emailMessage);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				return true;
			}
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	//manager method to create a new employee record
	@Override
	public boolean createEmployee(Employee emp) {
		System.out.println("createEmployee method triggered!");
		boolean employeeExists = false;
		
		try (Connection conn = ConnectionUtil.getConnection()){
			System.out.println("createEmployee first try-catch triggered");
			String sql = "SELECT username FROM employees WHERE username = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getUsername());
			ResultSet results = ps.executeQuery();
			
			if(results.next()) {
				employeeExists = true;
				try {
					throw new UsernameExistsException();
				} catch (UsernameExistsException e) {
					Log.info(e.getMessage());
					e.printStackTrace();
				}
			} 
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		if(!employeeExists) {
			
			try (Connection conn = ConnectionUtil.getConnection()){
				Log.info("createEmployee second try-catch triggered!");
				String sql2 = "INSERT INTO employees(firstname, lastname, username, email, is_manager) " +
						"VALUES(?,?,?,?,?)";
				
				PreparedStatement ps = conn.prepareStatement(sql2);
				ps.setString(1, emp.getFirstname());
				ps.setString(2, emp.getLastname());
				ps.setString(3, emp.getUsername());
				ps.setString(4, emp.getEmail());
				ps.setString(5, emp.getIsManager());
				
				if(ps.executeUpdate() == 1) {
					return true;
				} else {
					throw new SQLException("SQL Exception: createEmployee method failed!");
				}
				
			} catch (SQLException e) {
				Log.info(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		return false;
	}

	@Override
	public List<Request> getAllRequests() {

		//create empty array list to store results
		List<Request> allRequests = new ArrayList<>();
		
		String sql = "SELECT * FROM requests ORDER BY request_id DESC";
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet results = ps.executeQuery();
			while(results.next()) {
				allRequests.add(new Request(results.getInt("request_id"),results.getInt("employee_id"), results.getString("type"),
						results.getString("description"), results.getDouble("amount"), results.getString("status"),
						results.getString("date_submitted"), results.getString("date_resolved"), results.getInt("resolved_by_id")));
			}
			return allRequests;
			
		} catch (SQLException e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

}
