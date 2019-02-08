package com.revature.tjordan.controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;





public class FrontController extends DefaultServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Dispatcher dispatch = new Dispatcher();
	private final ObjectMapper mapper = new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println(req.getRequestURI().substring(req.getContextPath().length()));
		
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Cache-Control","no-store");
        resp.setHeader("Pragma","no-cache");
        resp.setDateHeader ("Expires", 0);
		if((req.getRequestURI().equals("/Project1ERS/")) || (req.getRequestURI().contains("/static")) || req.getRequestURI().contains("index")) {
			System.out.println("Inside of dogetif in front controller");
			super.doGet(req, resp);
		} else {
			System.out.println("Inside of doget else in front controller");
			dispatch.process(req, resp);
//			resp.setContentType("application/json");
//			resp.getWriter().append(mapper.writeValueAsString(dispatch.process(req, resp)));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inside of doPost inside of front controller");
		doGet(req,resp);
	}
	
	

}
