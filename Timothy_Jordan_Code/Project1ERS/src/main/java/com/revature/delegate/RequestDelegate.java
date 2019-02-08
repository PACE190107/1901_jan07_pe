package com.revature.delegate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reavature.tjordan.models.Employee;
import com.reavature.tjordan.models.ReimbursementReq;
import com.revature.dao.EmployeeDaoImplementation;
import com.revature.tjordan.Exceptions.FailedUpdateException;
import com.revature.tjordan.base.GoogleMail;
import com.revature.tjordan.base.SendEmail;
import com.revature.tjordan.services.EmployeeService;
import com.revature.tjordan.services.ReimbursementReqService;

public class RequestDelegate {
	//Create ObjectMapper to write JSON objects.
	private final ObjectMapper mapper = new ObjectMapper();
	
	//Create Logger for logging.
	final static Logger requestLogger = Logger.getLogger(RequestDelegate.class);

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws FailedUpdateException
	 */
	public void updateRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Get Values from the form and session
		HttpSession session = req.getSession();
		Employee currentManager = (Employee) session.getAttribute("currentEmployee");

		int managerID = currentManager.getEmp_ID();
		String reqID = req.getParameter("requestID");

		int requestId = Integer.parseInt(reqID);
		String status = req.getParameter("updateRadios");

		// Get Local Date
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate currentDate = LocalDate.now();
		String currentDateStr = dateFormat.format(currentDate);

		boolean updated = ReimbursementReqService.getReimbursementService().updateReq(requestId, managerID, status,
				currentDateStr);

		if (updated) {
			// get info of employee the request belongs too

			ReimbursementReq currentReq = ReimbursementReqService.getReimbursementService().getRequest(requestId);

			Employee mailEmployee = EmployeeService.getEmployeeService().getEmployee(currentReq.getEmp_ID());

			String currentEmail = mailEmployee.getEmp_email();
			String body = " Hello, " + mailEmployee.getEmp_firstName() + " your reimbursement request: \n\n"
					+ currentReq.getReq_type() + " for $" + currentReq.getReq_amount() + " has been "
					+ currentReq.getReq_status() + " on " + currentReq.getResolvedDate() + ".";

			try {
				GoogleMail.Send("tjrevature", "revature1901", currentEmail, "Request Submission", body);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.getRequestDispatcher("static/ManagerIndex.jsp").forward(req, resp);
		} else {
			requestLogger.error("Request Update Failed.");
			try {
				throw new FailedUpdateException("Update Failed");
			} catch (FailedUpdateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ReimbursementReq> allReq = ReimbursementReqService.getReimbursementService().getAll();
		resp.setContentType("application/json");
		resp.getWriter().append(mapper.writeValueAsString(allReq));
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getGResolved(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ReimbursementReq> allResolved = ReimbursementReqService.getReimbursementService().getResolvedGlobal();
		resp.setContentType("application/json");
		resp.getWriter().append(mapper.writeValueAsString(allResolved));
	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		// Get employee ID
		Employee currentEmployee = (Employee) session.getAttribute("currentEmployee");
		int emp_ID = currentEmployee.getEmp_ID();

		// Get Parameters from form
		String request_type = req.getParameter("type");
		double req_amount = Double.parseDouble(req.getParameter("amount"));
		// Get Local Date
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate currentDate = LocalDate.now();
		String currentDateStr = dateFormat.format(currentDate);
		// int emp_ID = 201;

		// Call addRequest Method
		List<ReimbursementReq> updatedPending = ReimbursementReqService.getReimbursementService().addRequest(emp_ID,
				currentDateStr, request_type, req_amount);

		// Try to send an email::::

		String currentEmail = currentEmployee.getEmp_email();
		String body = "Your " + request_type + " reimbursement request for " + req_amount
				+ " was submitted successfully.";

		try {
			GoogleMail.Send("tjrevature", "revature1901", currentEmail, "Request Submission", body);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.getRequestDispatcher("static/EmployeeIndex.jsp").forward(req, resp);

	}

}
