package com.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.ers.util.ConnectionManager;

@WebServlet("/ERS")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 3479236907455377769L;
	private static final Logger log = Logger.getLogger(FrontController.class);
	
	@Override
	public void init() throws ServletException {
		ConnectionManager.setJDBCConnection("Admin01", "s9d5j1q8");
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info(req.getMethod() + " " + req.getRequestURI());

		try {
			if (req.getRequestURI().endsWith(".html")) {
				super.doGet(req, resp);
			} else {
					RequestHelper.processPOST(req, resp);
			}
		} catch (Exception e) {
			exceptionCatcher(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info(req.getMethod() + " " + req.getRequestURI());

		try {
			if (req.getRequestURI().endsWith(".html") ||
				req.getRequestURI().endsWith(".js") ||
				req.getRequestURI().endsWith(".css") ||
				req.getRequestURI().endsWith(".png") ||
				req.getRequestURI().endsWith(".jpg") ||
				req.getRequestURI().equals("/ERS/")) {
				if (req.getParameter("temp") != null) {
					req.getSession().invalidate();
					req.getSession().setAttribute("temp", new String(req.getParameter("temp")));
					resp.sendRedirect("/ERS/");
				}
				resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				resp.setHeader("Pragma", "no-cache");
				resp.setHeader("Expires", "0");
				super.doGet(req, resp);
			} else {
				RequestHelper.processGET(req, resp);
			}
		} catch (Exception e) {
			exceptionCatcher(e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info(req.getMethod() + " " + req.getRequestURI());

		try {
			if (req.getRequestURI().endsWith(".html")) {
				super.doGet(req, resp);
			} else {
					RequestHelper.processPUT(req, resp);
			}
		} catch (Exception e) {
			exceptionCatcher(e);
		}
	}
	
	@Override
	public void destroy() {
		ConnectionManager.closeJDBCConnection();
		super.destroy();
	}
	
	private void exceptionCatcher(Exception e) {
		for (StackTraceElement ele : e.getStackTrace())
			log.error(ele.toString());
	}
}
