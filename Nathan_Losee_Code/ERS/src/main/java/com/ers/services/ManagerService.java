package com.ers.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.EmployeeDAO;
import com.ers.dao.ReimbursementRequestDAO;
import com.ers.models.Employee;
import com.ers.models.ReimbursementRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ManagerService {
	private ManagerService() { }
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void getEmployees(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		if (req.getSession().getAttribute("user") != null
				&& ((Employee) req.getSession().getAttribute("user")).isManager()) {
			String outJson = mapper.writeValueAsString(EmployeeDAO.getDAO().readEmployees().toArray());
			resp.setContentType("application/json");
			resp.getWriter().write(outJson);
		}
	}
	public static void getEmployee(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		if (req.getSession().getAttribute("user") != null
				&& ((Employee) req.getSession().getAttribute("user")).isManager()) {
			String inJson = req.getReader().readLine();
			ObjectNode node = (ObjectNode)mapper.readTree(inJson);
			String outJson = mapper.writeValueAsString(EmployeeDAO.getDAO().readEmployeeByID(node.get("eID").asInt()));
			resp.setContentType("application/json");
			resp.getWriter().write(outJson);
		}
	}

	public static void registerEmployee(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String inJson = req.getReader().readLine();
		Employee registration = mapper.readValue(inJson, Employee.class);
		if (EmployeeDAO.getDAO().readEmployees().stream()
				.anyMatch(employee -> employee.geteUsername().equals(registration.geteUsername()))) {
			return;
		}
		EmployeeDAO.getDAO().createEmployee(
				registration.geteUsername(), registration.geteUsername(),
				registration.geteFirstName(), registration.geteLastName(),
				registration.geteEmail(), false);
		sendConfirmationEmail(registration.geteEmail(), registration.geteUsername());
	}
	private static void sendConfirmationEmail(String email, String username) {
	      String origin = "sand-storm15@hotmail.com";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.transport.protocol", "smtp");
	      properties.setProperty("mail.host", "smtp.live.com");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(origin));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	         message.setSubject("Reimbursement Request Update");
	         message.setText("<div>"
	        		+ "<p>Your username and password are:</p><br>"
	        		+ "<p>Username: " + username + "</p>"
	        		+ "<p>Password: " + username + "</p><br><br>"
	        		+ "<a href=\"http://localhost:8080/ERS?temp=" + username + "/\">Click here to reset your password</a>"
	         		+ "</div>", "utf-8", "html");
	         
	 		Transport transport = session.getTransport("smtp");
	 		transport.connect("smtp.live.com", 587, "sand-storm15@hotmail.com", "s9D5J!Q*");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

	public static void getReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
			List<ReimbursementRequest> requests = new ArrayList<>();
			for (Employee employee : EmployeeDAO.getDAO().readEmployees())
				if (!employee.isManager())
						requests.addAll(ReimbursementRequestDAO.getDAO().readRequests(employee.geteID()));
			String outJson = mapper.writeValueAsString(requests.toArray());
			resp.setContentType("application/json");
			resp.getWriter().write(outJson);
	}
	public static void getReimbursementsSingle(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
			String inJson = req.getReader().readLine();
			ObjectNode node = (ObjectNode)mapper.readTree(inJson);
			String outJson = mapper.writeValueAsString(ReimbursementRequestDAO.getDAO()
					.readRequests(node.get("eID").asInt()).toArray());
			resp.setContentType("application/json");
			resp.getWriter().write(outJson);
	}
	
	public static void stampRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String inJson = req.getReader().readLine();
		ObjectNode node = (ObjectNode)mapper.readTree(inJson);
		ReimbursementRequestDAO.getDAO().updateRequest(
				node.get("request").asInt(),
				((Employee) req.getSession().getAttribute("user")).geteID(),
				node.get("isApproved").asBoolean());
		
		ReimbursementRequest request = ReimbursementRequestDAO.getDAO().readRequest(
				node.get("request").asInt());
		sendApprovalEmail(EmployeeDAO.getDAO().readEmployeeByID(request.geteID()).geteEmail(), request);
	}
	private static void sendApprovalEmail(String email, ReimbursementRequest request) {
	      String origin = "sand-storm15@hotmail.com";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.transport.protocol", "smtp");
	      properties.setProperty("mail.host", "smtp.live.com");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(origin));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	         message.setSubject("Reimbursement Request Update");
	         message.setText("Your reimbursement request for " + request.getRrDescription() + " was "
	        		 + (request.isApproved() ? "approved." : "denied."));
	         
	 		Transport transport = session.getTransport("smtp");
	 		transport.connect("smtp.live.com", 587, "sand-storm15@hotmail.com", "s9D5J!Q*");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
