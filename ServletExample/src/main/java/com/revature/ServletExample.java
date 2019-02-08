package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletExample extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException{
		System.out.println("Hello World");
	}
}
