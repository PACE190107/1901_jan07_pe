package com.revature.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImplementation;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class LoginServiceImpl implements LoginService {

	private static final String senderEmail = "erstestemail7@gmail.com";// change with your sender email
	private static final String senderPassword = "p4ssw0rd.";// change with your sender password

	final static Logger log = Logger.getLogger(LoginServiceImpl.class);
	private static final ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	private static final LoginService loginService = new LoginServiceImpl();

	@Override
	public Object process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String username = req.getParameter("username");
		final String password = req.getParameter("password");
		final String email = req.getParameter("email");
		final String filePath = req.getServletContext().getInitParameter("file-upload");
		log.info(filePath);
		boolean isMultipart=ServletFileUpload.isMultipartContent(req);
		User attempting = null;
		boolean successfulEdit = true;
		boolean success = true;
		
		String uri = req.getRequestURI();

		String pageName = uri.substring(uri.lastIndexOf("/")+1);
		
		log.info(pageName);
		if (req.getRequestURI().contains("userinfo")) {

			attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			req.getSession().setAttribute("username", req.getSession().getAttribute("username"));

		} else if (req.getRequestURI().contains("users")) {
			req.getSession().setAttribute("username", req.getSession().getAttribute("username"));
			return loginService.getAllUsers();
		} else {
			
			log.info("button"+req.getParameter("button"));
			
			if(isMultipart) {
				req.getRequestDispatcher("home.jsp").forward(req, resp);
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));

				int id = reimbursementService.getMostRecentReimbursement();
				loginService.uploadFile(filePath, req, id);
				reimbursementService.addImage(id);
				
			}else if (req.getParameter("button").equals("login")) {
				attempting = loginService.attemptAuthentication(username, password);
			}else if (req.getParameter("button").equals("logout")) {
				req.getSession().invalidate();
				req.logout();
				req.getSession().setAttribute("username", "invalid");
				resp.sendRedirect(req.getContextPath());
				return "/index?faces-redirect=true";
			} else if (req.getParameter("button").equals("registerSubmit")) {
				attempting = loginService.insertUser(username, email, password);
			} else if (req.getParameter("button").equals("submit reimbursement")) {
				
				final double amount = Double.parseDouble(req.getParameter("amount"));		
						attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
						reimbursementService.insertReimbursement(attempting.getUsername(), amount);
				
			} else if (req.getParameter("button").equals("approve")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
				reimbursementService.updateReimbursement(Integer.parseInt(req.getParameter("selectId")), "approved",
						String.valueOf(req.getSession().getAttribute("username")));

				Reimbursement reimbursement = reimbursementService
						.getReimbursement(Integer.parseInt(req.getParameter("selectId")));
				String body = reimbursement.getUsername() + "Your reimbursement in the amount of " + reimbursement.getAmount()
				+ " has been " + reimbursement.getStatus() + " by " + attempting.getUsername();
				loginService.sendEmail(loginService.getUser(reimbursement.getUsername()), attempting, reimbursement, body);

			} else if (req.getParameter("button").equals("decline")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
				reimbursementService.updateReimbursement(Integer.parseInt(req.getParameter("selectId")), "declined",
						String.valueOf(req.getSession().getAttribute("username")));

				Reimbursement reimbursement = reimbursementService
						.getReimbursement(Integer.parseInt(req.getParameter("selectId")));
				String body = reimbursement.getUsername() + "Your reimbursement in the amount of " + reimbursement.getAmount()
				+ " has been " + reimbursement.getStatus() + " by " + attempting.getUsername();
				loginService.sendEmail(loginService.getUser(reimbursement.getUsername()), attempting, reimbursement, body);

			} else if (req.getParameter("button").equals("Home")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			} else if (req.getParameter("button").equals("Profile")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			} else if (req.getParameter("button").equals("edit profile")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			} else if (req.getParameter("button").equals("submit edit")) {

				attempting = loginService
						.attemptAuthentication(String.valueOf(req.getSession().getAttribute("username")), req.getParameter("password"));

				if(!username.equals(req.getSession().getAttribute("username")) && loginService.userExists(username)) {
					req.getSession().setAttribute("info", "username or email is taken");
					successfulEdit = false;
				}if (attempting == null) {
					req.getSession().setAttribute("info", "invalid password");
					successfulEdit = false;
					attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
				} else {

					User updatedUser = null;
					
					if(req.getParameter("new_password") != null && req.getParameter("new_password").length()>0) {
						updatedUser = new User(req.getParameter("username"), req.getParameter("email"), req.getParameter("new_password"),
								attempting.getSuperuser());
					}else {
						updatedUser = new User(req.getParameter("username"), req.getParameter("email"), req.getParameter("password"),
								attempting.getSuperuser());
					}
					
					log.info(updatedUser);
					
					attempting = loginService.updateUser(updatedUser,
							String.valueOf(req.getSession().getAttribute("username")));

					if (attempting == null) {
						req.getSession().setAttribute("info", "username or email already exists");
						successfulEdit = false;
						attempting = loginService.getUser(updatedUser.getUsername());
					} else {						
						reimbursementService.updateUsernameReimbursements(username,
								String.valueOf(req.getSession().getAttribute("username")));
						attempting = loginService.getUser(updatedUser.getUsername());
					}

				}
			} else if (req.getParameter("button").equals("resolved")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			} else if (req.getParameter("button").equals("users")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			} else if (req.getParameter("button").equals("view reimbursements")) {
				req.getSession().setAttribute("info", username);
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			}else if(req.getParameter("button").equals("cancel")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			}else if(req.getParameter("button").equals("register user")) {
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
			}else if(req.getParameter("button").equals("register submit")) {
				User user = loginService.insertUser(username, email, password);
				attempting = loginService.getUser(String.valueOf(req.getSession().getAttribute("username")));
				if(user != null) {
				String body = "Username: " + user.getUsername()+ "     Password: " + user.getPassword();
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setUsername("new user");
				loginService.sendEmail(user, attempting, reimbursement, body);
				}else {
					success = false;
				}
			}

			log.info("user: " + attempting);

			if (attempting != null) {
				req.getSession().setAttribute("username", attempting.getUsername());

				if(isMultipart) {
				}else if (req.getParameter("button").equals("Profile")) {
					req.getRequestDispatcher("userinfo.jsp").forward(req, resp);
				} else if (req.getParameter("button").equals("edit profile")) {
					req.getRequestDispatcher("editinfo.jsp").forward(req, resp);
				} else if (req.getParameter("button").equals("submit edit") && !successfulEdit) {
					req.getRequestDispatcher("incorrecteditinfo.jsp").forward(req, resp);
				} else if (req.getParameter("button").equals("resolved")) {
					req.getRequestDispatcher("superhomeresolved.jsp").forward(req, resp);
				} else if (req.getParameter("button").equals("users")) {
					req.getRequestDispatcher("superusers.jsp").forward(req, resp);
				} else if (req.getParameter("button").equals("view reimbursements")) {
					req.getRequestDispatcher("superhomeuser.jsp").forward(req, resp);
				} else if(req.getParameter("button").equals("submit reimbursement")){
					req.getRequestDispatcher("uploadreceipt.jsp").forward(req, resp);
				}else if(req.getParameter("button").equals("register user")) {
					req.getSession().setAttribute("info", "none");
					req.getRequestDispatcher("superregisteruser.jsp").forward(req, resp);
				}else if(req.getParameter("button").equals("register submit")) {
					if(success) {
					req.getRequestDispatcher("superhome.jsp").forward(req, resp);
					}else {
						req.getSession().setAttribute("info", "username or email already exists.");
						req.getRequestDispatcher("superregisteruser.jsp").forward(req, resp);
					}
				}else {
					if (attempting.getSuperuser() == 0) {
						req.getRequestDispatcher("home.jsp").forward(req, resp);

					} else {
						req.getRequestDispatcher("superhome.jsp").forward(req, resp);
					}
				}
			} else {
				if (req.getParameter("button").equals("register")) {
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				} else if (req.getParameter("button").equals("logout") || req.getParameter("button").equals("exit")
						|| req.getParameter("button").equals("registerSubmit")) {
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				} else {
					log.info(req.getParameter("button"));
					resp.setStatus(400);
					req.getRequestDispatcher("incorrectformindex.jsp").forward(req, resp);
				}
			}
		}
		return attempting;
	}

	@Override
	public User attemptAuthentication(String username, String password) {
		log.info("username: " + username);
		log.info("password: "+ password);
		if (UserDaoImplementation.getUserDao().verifyPassword(username, password))
			return UserDaoImplementation.getUserDao().getUser(username);
		return null;
	}

	@Override
	public User insertUser(String username, String email, String password) {
		User user = new User(username, email, password, 0);
		if (UserDaoImplementation.getUserDao().insertUser(user)) {
			return user;
		}
		return null;
	}

	@Override
	public User getUser(String username) {
		User user = UserDaoImplementation.getUserDao().getUser(username);
		return user;
	}

	@Override
	public User updateUser(User updatedUser, String oldUsername) {
		// TODO Auto-generated method stub
		return UserDaoImplementation.getUserDao().editUser(updatedUser, oldUsername);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return UserDaoImplementation.getUserDao().getAllUsers();
	}

	@Override
	public boolean sendEmail(User employee, User manager, Reimbursement reimbursement, String body) {
		String title = "Reimbursement" + reimbursement.getReimbursement_id();
		
		if(reimbursement.getUsername().equals("new user")) {
			title = "new user";
		}
		
		String to = employee.getEmail();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// create message using session
			MimeMessage message = new MimeMessage(session);
			message.setContent(body, "text/html; charset=utf-8");
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(title);
			// sending message
			Transport.send(message);
			return true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		} catch(NullPointerException e) {
			log.error(e.getMessage());
		}
		return false;
	}

	@Override
	public void uploadFile(String filePath, HttpServletRequest req, int id) {
		final int maxFileSize = 50 * 1024;
		final int maxMemSize = 4 * 1024;
		File file;

		log.info(System.getProperty("user.home"));
		filePath = System.getProperty("user.home") + filePath;
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);

		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(req);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = "" + id + ".png";
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);
				}
			}
		} catch (Exception ex) {
			log.error(ex);
		}
		log.info("we got here");
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return UserDaoImplementation.getUserDao().userExists(username);
	}
}
