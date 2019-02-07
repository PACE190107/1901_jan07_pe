package com.revature.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {
    Object process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
