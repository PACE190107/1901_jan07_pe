package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	//endpoint:  jdbc:oracle:thin:@rds1901.cugpj0vbdsnx.us-east-1.rds.amazonaws.com:1521:ORCL
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
		System.out.println("Hello World");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
		
	}
}
