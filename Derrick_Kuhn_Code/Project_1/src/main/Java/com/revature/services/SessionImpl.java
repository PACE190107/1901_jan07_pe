package com.revature.services;

import com.revature.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionImpl implements SessionService{

    private static SessionService instance = new SessionImpl();

    private SessionImpl(){}

    public static SessionService getInstance(){return instance;}

    @Override
    public Employee retreiveInstanceEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            HttpSession session = req.getSession();

            Long session_id = (Long)session.getAttribute("SESSION_ID");
            Boolean isManager = (Boolean)session.getAttribute("MANAGER");

            Employee currentUser;
            if(session_id != null)currentUser = new Employee(session_id, isManager);
            else currentUser = null;
            return currentUser;
        } catch (NullPointerException e){
            return null;
        }
    }
}
