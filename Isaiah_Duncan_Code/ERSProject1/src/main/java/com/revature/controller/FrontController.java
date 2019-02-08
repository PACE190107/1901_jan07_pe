package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class FrontController extends DefaultServlet {
	
	private static final long serialVersionUID = 3479236907455377769L;
	final static Logger Log = Logger.getLogger(ConnectionUtil.class);
	private RequestHelper rh = new RequestHelper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getRequestURI().equals("/ERSProject1/") || req.getRequestURI().contains("/static")) {
			Log.info("doGet method accessed.");
			super.doGet(req, resp);
		} else {
			Log.info("doGet else block triggered in Front Controller");
			rh.process(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Log.info("doPost method accessed.");
		doGet(req,resp);
	}
}
