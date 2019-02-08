package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Login;
import com.revature.beans.Reimbursement;
import com.revature.exceptions.BadNumberException;
import com.revature.exceptions.InputTooLongException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.InvalidNameException;
import com.revature.exceptions.InvalidSpecialNameException;
import com.revature.exceptions.NotBooleanException;
import com.revature.util.ConnectionUtil;

public class HomeDelegate {
	final static Logger log = Logger.getLogger(ConnectionUtil.class);
	private final ObjectMapper mapper = new ObjectMapper();
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	public void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		// Give a personalized response
		Login login = (Login) session.getAttribute("user");

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				if (login.isManager()){
					req.getRequestDispatcher("static/managerHome.html").forward(req,resp);
				}
				else {
				req.getRequestDispatcher("static/home.html").forward(req,resp);
				}
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//			PrintWriter pw = resp.getWriter();
			//			pw.write("<!DOCTYPE html><html><head>" + "<meta charset=\"ISO-8859-1\"><title>HelloWorld</title>"
			//					+ "</head><body>");
			//
			//			pw.write("<div><div style=\"background-color:" + "blue" + "\">" + "<h4>Hello "
			//					+ login.getUsername() + "</h4></div>" + "<form action=\"logout\" method=\"post\">"
			//					+ "<input type=\"submit\" value=\"Logout\"/>" + "</form></div>");
			//
			//			pw.write("</body></html>");
		}
	}
	public void submitRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		Login login = (Login) session.getAttribute("user");
		Connection conn = null;
		conn = cu.getConnection();
		String purpose = req.getParameter("Purpose");
		double amount = Double.parseDouble(req.getParameter("Amount"));

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				if (purpose.length() > 40) {
					throw new InputTooLongException();
				}
				if(!isValidNumber(Double.toString(amount))) {
					throw new BadNumberException();
				}
				String sql = "call insert_reimbursement(?,?,?,?)";
				CallableStatement ps = conn.prepareCall(sql);
				ps.setInt(1, login.getEmployee_id());
				ps.setString(2, purpose);
				ps.setDouble(3, amount);
				ps.registerOutParameter(4, Types.NUMERIC);
				ps.executeUpdate();
				if(ps.getInt(4)> 0) {
					PrintWriter pw = resp.getWriter();
					log.info("Submit successful");
				} else {
					throw new SQLException();
				}
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("Account addition failed");
			} catch (InputTooLongException e) {
				log.info(e.getMessage());
				log.info("Account addition failed");
			} catch (BadNumberException e) {
				log.info(e.getMessage());
				log.info("Account addition failed");
			}
			try {
				if (login.isManager()){
					req.getRequestDispatcher("static/managerHome.html").forward(req,resp);
				}
				else {
					req.getRequestDispatcher("static/home.html").forward(req,resp);
				}
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void viewRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				Statement stmt = conn.createStatement();
				String sql = "select * from Reimbursements where Employee_ID = " + login.getEmployee_id();
				ResultSet results = stmt.executeQuery(sql);
				List<Reimbursement> reimbursements = new ArrayList<>();
				while (results.next()) {
					reimbursements.add(new Reimbursement(
							results.getInt("reimbursement_id"), 
							results.getInt("employee_id"), 
							results.getString("purpose"),
							results.getDouble("amount"),
							results.getString("approved_status"),
							results.getString("approved_by"))
							);
				}
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(reimbursements));
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("Could not view requests");
			} 
		}
	}

	public void viewInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		Login login = (Login) session.getAttribute("user");

		if (login == null) {
			resp.sendRedirect("login");
		} else {

			resp.setContentType("application/json");
			resp.getWriter().append(mapper.writeValueAsString(login));

		}
	}
	public void updateInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		// Get our session information
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");

		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String uname = req.getParameter("username");
		String pass = req.getParameter("pass");

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				if (!verifyName(fname) || !verifyName(lname)) {
					throw new InvalidNameException();
				}
				if (!verifyUsernameOrPassword(uname) || !verifyUsernameOrPassword(pass)) {
					throw new InvalidSpecialNameException();
				}
				String sql = "update Employees set firstname = '" + fname
						+ "', lastname = '" + lname + "', username = '" + uname
						+ "', pass = get_User_Hash('" + uname + "', '" + pass
						+ "') where employee_id = " + login.getEmployee_id();
				PreparedStatement ps = conn.prepareStatement(sql);
				if(ps.executeUpdate() > 0) {
					log.info("Information updated");
					login.setFirstname(fname);
					login.setLastname(lname);
					login.setUsername(uname);
					login.setPassword(pass);
					session.setAttribute("user", login);
				} else {
					throw new SQLException();
				}
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("User update failed");
			} catch (InvalidNameException e) {
				log.info(e.getMessage());
				log.info("User update failed");
				
			} catch (InvalidSpecialNameException e) {
				log.info(e.getMessage());
				log.info("User update failed");
				
			}
		}
		try {
			if (login.isManager()){
				req.getRequestDispatcher("static/managerHome.html").forward(req,resp);
			}
			else {
				req.getRequestDispatcher("static/home.html").forward(req,resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void viewEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				Statement stmt = conn.createStatement();
				String sql = "select * from Employees";
				ResultSet results = stmt.executeQuery(sql);
				List<Login> reimbursements = new ArrayList<>();
				while (results.next()) {
					boolean isManager = results.getInt("isManager") == 1 ? true:false;
					reimbursements.add(new Login(
							results.getInt("employee_id"), 
							results.getString("firstname"), 
							results.getString("lastname"),
							results.getString("username"),
							results.getString("pass"),
							isManager)
							);
				}
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(reimbursements));
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("Could not view employees");
			} 
		}
	}
	
	public void registerEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		// Get our session information
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");

		String fname = req.getParameter("empfirstname");
		String lname = req.getParameter("emplastname");
		String uname = req.getParameter("empusername");
		String pass = req.getParameter("emppass");
		int isManager = Integer.parseInt(req.getParameter("empismanager"));

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				if (!verifyName(fname) || !verifyName(lname)) {
					throw new InvalidNameException();
				}
				if (!verifyUsernameOrPassword(uname) || !verifyUsernameOrPassword(pass)) {
					throw new InvalidSpecialNameException();
				}
				if(isManager != 0 && isManager != 1) {
					throw new NotBooleanException();
				}
				String sql = "call insert_employee(?,?,?,?,?,?)";
				CallableStatement ps = conn.prepareCall(sql);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, uname);
				ps.setString(4, pass);
				ps.setInt(5, isManager);
				ps.registerOutParameter(6, Types.NUMERIC);
				ps.executeUpdate();
				if(ps.getInt(4)> 0) {
					log.info("Employee Registered");
				} else {
					throw new SQLException();
				}
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("User registration failed");
			} catch (InvalidNameException e) {
				log.info(e.getMessage());
				log.info("User update failed");
			} catch (InvalidSpecialNameException e) {
				log.info(e.getMessage());
				log.info("User update failed");
			} catch (NotBooleanException e) {
				log.info(e.getMessage());
				log.info("User update failed");
			}
		}
		try {
			if (login.isManager()){
				req.getRequestDispatcher("static/managerHome.html").forward(req,resp);
			}
			else {
				req.getRequestDispatcher("static/home.html").forward(req,resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void viewAllRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				Statement stmt = conn.createStatement();
				String sql = "select * from Reimbursements";
				ResultSet results = stmt.executeQuery(sql);
				List<Reimbursement> reimbursements = new ArrayList<>();
				while (results.next()) {
					reimbursements.add(new Reimbursement(
							results.getInt("reimbursement_id"), 
							results.getInt("employee_id"), 
							results.getString("purpose"),
							results.getDouble("amount"),
							results.getString("approved_status"),
							results.getString("approved_by"))
							);
				}
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(reimbursements));
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("Could not view requests");
			} 
		}
	}
	
	
	public void filterRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");
		int filter = Integer.parseInt(req.getParameter("filter"));

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				Statement stmt = conn.createStatement();
				String sql = "select * from Reimbursements where Employee_ID = " + filter;
				ResultSet results = stmt.executeQuery(sql);
				List<Reimbursement> reimbursements = new ArrayList<>();
				while (results.next()) {
					reimbursements.add(new Reimbursement(
							results.getInt("reimbursement_id"), 
							results.getInt("employee_id"), 
							results.getString("purpose"),
							results.getDouble("amount"),
							results.getString("approved_status"),
							results.getString("approved_by"))
							);
				}
				resp.setContentType("application/json");
				resp.getWriter().append(mapper.writeValueAsString(reimbursements));
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("Could not view requests");
			} 
		}
	}
	
	public void approveDeny(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		Connection conn = null;
		conn = cu.getConnection();
		Login login = (Login) session.getAttribute("user");

		String id = req.getParameter("reimbursementSelector");
		String input = req.getParameter("approveDenySelector");
		String approveDeny = input.equals("Approve") ? "approved" : "denied";
		log.info(id);
		log.info(input);

		if (login == null) {
			resp.sendRedirect("login");
		} else {
			try {
				String sql = "update Reimbursements set approved_status = '" + approveDeny
						+ "', approved_by = '" + login.getUsername() + "' where reimbursement_id = " + id;
				PreparedStatement ps = conn.prepareStatement(sql);
				if(ps.executeUpdate() > 0) {
					log.info("Reimbursement processed");
				} else {
					throw new SQLException();
				}
			} catch (SQLException e) {
				log.info("SQL exception occurred");
				log.info(e.getMessage());
				log.info("Could not process reimbursement");
			}
		}
		try {
			if (login.isManager()){
				req.getRequestDispatcher("static/managerHome.html").forward(req,resp);
			}
			else {
				req.getRequestDispatcher("static/home.html").forward(req,resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isValidNumber(String string) {

		// If the string is empty, it is not a valid:

		if (string.length() == 0) {
			return false;
		}

		// Split the string at the decimal:

		String []splitString = string.split("\\.");

		// If there is more than one decimal, the number is invalid:

		if (splitString.length > 2) {
			return false;
		}

		// Check if every character is a digit; if so, the number is valid:

		for (String segment : splitString) {
			for (int i = 0; i < segment.length(); i++) {
				if (!(Character.isDigit(string.charAt(i)))) {
					return false;
				}
			}
			if (segment != splitString[0] && segment.length() > 2) {
				return false;
			}

		}

		try {
			if (Double.parseDouble(string) < Double.MAX_VALUE) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}

	public boolean verifyName(String input) {

		// If the input is too long or an empty string, it is not valid:

		if (input.length() > 40 || input.length() == 0) {
			return false;
		}

		// If all of the characters in the word are letters, it is a valid name

		for (int i = 0; i < input.length(); i++) {
			if (!(Character.isLetter(input.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyUsernameOrPassword(String input) {

		// If the input is too long or an empty string, it is not valid:

		if (input.length() > 40 || input.length() == 0) {
			return false;
		}

		// The input can contain letters, numbers, and underscores; anything else is invalid

		String alteredString = input.replaceAll("_","");

		for (int i = 0; i < alteredString.length(); i++) {
			if (!(Character.isLetter(alteredString.charAt(i)) || (Character.isDigit(alteredString.charAt(i))))) {
				return false;
			}
		}
		return true;
	}

	public boolean isPositiveInteger(String string) {
		
		// If the string is empty, it is not an integer, obviously:
		
		if (string.length() == 0) {
			return false;
		}
		
		// Check if every character is a digit; if so, the number is an integer:
		
	    for (int i = 0; i < string.length(); i++) {
	    	if (!(Character.isDigit(string.charAt(i)))) {
	    		return false;
	    	}
	    }
	    
	    // Only accept positive integers:
	    
	    try {
	    	if (Integer.parseInt(string) > 0) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    catch (Exception e) {
	    	return false;
	    }
	}
	
}
