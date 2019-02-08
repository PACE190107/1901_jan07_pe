package com.revature.tjordan.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println(httpRequest.getMethod() + "request going to" + httpRequest.getRequestURI() + "from origin" + httpRequest.getRemoteHost());
		
		
		
		
		
		//In order for us to accept requests from other domains, we need to add two request headers
		//First being, 'Access-Control-Allow_origin' with the value being the domain
		
		
		httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		//domain of server or sending request
		
		httpResponse.addHeader("Access-Control-Allow-Methods",  "GET, PUT, POST, DELETE, OPTIONS");
		
		
		
		
		//The most important part
		
		chain.doFilter(httpRequest, httpResponse);
		
	}

}
