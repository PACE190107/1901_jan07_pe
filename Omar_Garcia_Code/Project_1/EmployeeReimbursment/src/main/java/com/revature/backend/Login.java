package com.revature.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.services.EmployeeService;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		BufferedReader read = req.getReader();
		StringBuffer object = new StringBuffer();
		int c;
		while ((c = read.read()) > -1) {
			object.append((char) c);
		}
		PrintWriter writer = rep.getWriter();
		JsonParser jsonParser = new JsonParser();
		JsonObject objectFromString = jsonParser.parse(object.toString()).getAsJsonObject();
		if (objectFromString.has("username")) {
			String username = objectFromString.get("username").getAsString();
			if (objectFromString.has("password")) {
				String password = objectFromString.get("password").getAsString();

				try {
					if(EmployeeService.getEmployeeService().login(username, password) != null) {
					rep.setStatus(200);
					writer.append("all is good");
					} else {
						rep.setStatus(400);
						writer.append("USER NOT FOUND");
					}

				} catch (SQLException e) {
					rep.setStatus(400);
					writer.append("SQL Exception");
				}

			} else {
				rep.setStatus(400);
				writer.append("Missing password");
			}

		} else {
			rep.setStatus(400);
			writer.append("Missing username");
		}

	}
}
