package com.ers.services;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.EmployeeDAO;
import com.ers.dao.ReimbursementRequestDAO;
import com.ers.models.Employee;
import com.ers.models.ReimbursementRequest;
import com.ers.util.ConnectionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EmployeeService {
	private EmployeeService() {
	}

	private static ObjectMapper mapper = new ObjectMapper();

	public static void getReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String outJson = mapper.writeValueAsString(ReimbursementRequestDAO.getDAO()
				.readRequests(((Employee) req.getSession().getAttribute("user")).geteID()).toArray());
		resp.setContentType("application/json");
		resp.getWriter().write(outJson);
	}

	public static void makeRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String inJson = req.getReader().readLine();
		ReimbursementRequest request = mapper.readValue(inJson, ReimbursementRequest.class);
		ReimbursementRequestDAO.getDAO().createRequest(
				((Employee) req.getSession().getAttribute("user")).geteID(), request.getRrDescription(),
				request.getRrAmount());
	}

	public static void getUsername(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/plain");
		resp.getWriter().write(((Employee) req.getSession().getAttribute("user")).geteUsername());
	}

	public static void getSettings(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Employee employee = (Employee) req.getSession().getAttribute("user");
		String outJson = mapper.writeValueAsString(employee);
		resp.setContentType("application/json");
		resp.getWriter().write(outJson);
	}

	public static void updateSettings(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		ConnectionManager.setJDBCConnection("Admin01", "s9d5j1q8");
		String inJson = req.getReader().readLine();
		ObjectNode node = (ObjectNode) mapper.readTree(inJson);
		EmployeeDAO.getDAO().updateEmployee(((Employee) req.getSession().getAttribute("user")).geteID(),
				node.get("credential").asText(), node.get("newValue").asText());
		switch (node.get("credential").asText()) {
		case "username":
			((Employee) req.getSession().getAttribute("user")).seteUsername(node.get("newValue").asText());
			break;
		case "password":
			((Employee) req.getSession().getAttribute("user"))
					.setePassword(getMd5(node.get("newValue").asText() + "SALT").toUpperCase());
			break;
		case "firstName":
			((Employee) req.getSession().getAttribute("user")).seteFirstName(node.get("newValue").asText());
			break;
		case "lastName":
			((Employee) req.getSession().getAttribute("user")).seteLastName(node.get("newValue").asText());
			break;
		case "email":
			((Employee) req.getSession().getAttribute("user")).seteEmail(node.get("newValue").asText());
			break;
		case "confirmation":
			((Employee) req.getSession().getAttribute("user"))
					.setePassword(getMd5(node.get("newValue").asText() + "SALT").toUpperCase());
			((Employee) req.getSession().getAttribute("user")).setConfirmed(true);
			req.getRequestDispatcher("/webfiles/employee/employee.html").forward(req, resp);
			break;
		default:
			break;
		}
		ConnectionManager.setJDBCConnection(((Employee) req.getSession().getAttribute("user")).geteUsername(),
				((Employee) req.getSession().getAttribute("user")).getePassword());
	}

	private static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			StringBuilder hashtext = new StringBuilder(no.toString(16));
			while (hashtext.length() < 32) {
				hashtext.insert(0, "0");
			}
			return "PW" + hashtext.toString().substring(0, 28);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
