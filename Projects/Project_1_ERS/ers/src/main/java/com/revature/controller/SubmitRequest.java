package com.revature.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.database.RequestDao;

/**
 * Servlet implementation class SubmitRequest
 */
public class SubmitRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SubmitRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double amount = Double.parseDouble(request.getParameter("amount").toString());
		String cat = request.getParameter("category");
		String comments = request.getParameter("comments");
		
		boolean go = new RequestDao().makerequest(Integer.parseInt(request.getSession().getAttribute("id").toString()), amount, cat, comments);
		response.sendRedirect("http://localhost:8080/ers");
//		System.out.println(request.getParameter("category"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
