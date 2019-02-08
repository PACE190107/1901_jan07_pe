package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static Logger log = Logger.getLogger(MasterServlet.class);
	// This is the object responsible for marshaling/unmarshaling POJOS to JSON
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("entering Method doGet in Class MasterServlet ");
		resp.setContentType("application/json");
		resp.getWriter().append(mapper.writeValueAsString(MasterDispatcher.process(req, resp)));

//		resp.getWriter().append(mapper.writeValueAsString(new String("TestString")));
//		resp.setStatus(200);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
	}
	
}
