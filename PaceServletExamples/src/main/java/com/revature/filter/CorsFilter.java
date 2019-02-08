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
		
		System.out.println(httpRequest.getMethod() + " request going to " + 
		httpRequest.getRequestURI());
		
		//in order for us to accept requests from other domains, need to add two  request headers
		//first "access-control-allow-origin
		
		httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		//second "access-control-allow-methods" w/ http methods you're allowing
		httpResponse.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
		
		//most important part
		chain.doFilter(httpRequest, httpResponse);
	}
	
}
