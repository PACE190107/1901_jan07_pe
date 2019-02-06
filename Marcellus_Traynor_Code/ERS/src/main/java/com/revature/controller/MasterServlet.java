package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4610864359249189402L;
	
	// This is the object responsible for marshalling/unmarshalling POJOS to JSON
	private final ObjectMapper mapper = new ObjectMapper();
	final static Logger log = Logger.getLogger(MasterServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append(mapper.writeValueAsString(MasterDispatcher.process(request, response)));
		
		if(response.getHeader("text/html") != null)
		{
			response.setContentType("text/html");
		}
		else
		{
			response.setContentType("application/json");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}