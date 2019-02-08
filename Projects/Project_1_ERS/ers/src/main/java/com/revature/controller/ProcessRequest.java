package com.revature.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.database.RequestDao;

/**
 * Servlet implementation class ProcessRequest
 */
public class ProcessRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int req = Integer.parseInt(request.getParameter("request").toLowerCase());
		String dec = request.getParameter("group1").toString();
		int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
		
		if(dec.equalsIgnoreCase("approve"))
			{
				boolean go = new RequestDao().approveRequest(id, req);
			}
		else if(dec.equalsIgnoreCase("deny"))
			{
				boolean go = new RequestDao().denyRequest(id, req);
			}
		response.sendRedirect("ad.jsp");
		System.out.println(dec);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
