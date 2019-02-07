package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class FrontController  extends DefaultServlet {

	
	private static final long serialVersionUID = 1L;
	
private RequestDispatcher reqD = new RequestDispatcher();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getRequestURI().substring(req.getContextPath().length())
				.startsWith("/static/")) {
			System.out.println("INSIDE DOGET IF");
			super.doGet(req, resp);
		} else {
			System.out.println("INSIDE DOGET ELSE");
			reqD.process(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("INSIDE DOPOST");
		doGet(req,resp);
	}
	
	

}
