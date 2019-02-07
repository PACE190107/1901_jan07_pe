package com.revature.services;

import com.revature.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SessionService {
    public Employee retreiveInstanceEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
