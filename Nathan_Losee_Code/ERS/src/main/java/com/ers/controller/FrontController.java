package com.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.ers.util.ConnectionManager;

public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 3479236907455377769L;
	
	@Override
	public void init() throws ServletException {
		ConnectionManager.setJDBCConnection("Admin01", "s9d5j1q8");
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith(".html")) {
			super.doGet(req, resp);
		} else {
			try {
				RequestHelper.processPOST(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("Host = " + req.getServerName());
	    System.out.println("Port = " + req.getServerPort());
		if (req.getRequestURI().endsWith(".html") ||
			req.getRequestURI().endsWith(".js") ||
			req.getRequestURI().endsWith(".css") ||
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
			try {
				RequestHelper.processGET(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith(".html")) {
			super.doGet(req, resp);
		} else {
			try {
				RequestHelper.processPUT(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod() + " " + req.getRequestURI());
		
		throw new RuntimeException("Method not recognized.");
	}
	
	@Override
	public void destroy() {
		ConnectionManager.closeJDBCConnection();
		super.destroy();
	}
}
