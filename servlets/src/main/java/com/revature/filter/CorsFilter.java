package com.revature.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println(httpRequest.getMethod() + " request going to " + httpRequest.getRequestURI());
		
		// In order for us to accept requests from other domains, we need to add two request headers
		// First being, 'Access-Control-Allow-Origin' with the value being the domain you are requesting from
		httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		// The second being, 'Access-Control-Allow-Methods' with the HTTP Methods you grant access to
		httpResponse.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
		
		
		// The most important part
		chain.doFilter(httpRequest, httpResponse);
	}


}








