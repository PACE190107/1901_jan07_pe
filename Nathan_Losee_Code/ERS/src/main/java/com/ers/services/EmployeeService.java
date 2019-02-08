package com.ers.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.Base64;

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
	
	public static void saveReceipt(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		try {
            // Gets absolute path to root directory of web app.
            String appPath = System.getProperty("user.home");
            appPath = appPath.replace('\\', '/');
 
            // The directory to save uploaded file
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + "Receipts";
            } else {
                fullSavePath = appPath + "/Receipts";
            }
 
            // Creates the save directory if it does not exists
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
 
            // Part list (multi files).
            for (Part part : req.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + "/" + fileName;
                    part.write(filePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static void loadReceipt(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
        String appPath = System.getProperty("user.home");
        appPath = appPath.replace('\\', '/');

        // The directory to save uploaded file
        String fullLoadPath = null;
        if (appPath.endsWith("/")) {
        	fullLoadPath = appPath + "Receipts";
        } else {
        	fullLoadPath = appPath + "/Receipts";
        }
        
        File f = new File(fullLoadPath + "/" + req.getParameter("reqID") + ".png");
		byte image[] = new byte[(int)f.length()];
		
		FileInputStream fis = new FileInputStream(fullLoadPath + "/" + req.getParameter("reqID") + ".png");  
		fis.read(image);
		image = Base64.encodeBase64(image);
        
		resp.setHeader("Content-Type", "image/jpeg");
		for (byte bt : image)
			resp.getWriter().write(bt);
		
		fis.close();
	}
	private static String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

	public static void getUsername(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/plain");
		resp.getWriter().write(((Employee) req.getSession().getAttribute("user")).geteUsername());
	}
	public static void getEmail(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/plain");
		resp.getWriter().write(((Employee) req.getSession().getAttribute("user")).geteEmail());
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
			if (!((Employee) req.getSession().getAttribute("user")).isConfirmed())
				sendConfirmationEmail(node.get("newValue").asText(),
						((Employee) req.getSession().getAttribute("user")).geteUsername(),
						req.getServerName() + ":" + req.getServerPort());
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
	private static void sendConfirmationEmail(String email, String username, String host) {
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
	        		+ "<a href=\"http://" + host + "/ERS?temp=" + username + "/\">Click here to reset your password</a>"
	         		+ "</div>", "utf-8", "html");
	         
	 		Transport transport = session.getTransport("smtp");
	 		transport.connect("smtp.live.com", 587, "sand-storm15@hotmail.com", "s9D5J!Q*");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
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
