package com.revature.servlet;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.services.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SinisterSpongeDispatcher {

    final static Logger log = Logger.getLogger(SinisterSpongeDispatcher.class);

    private static SinisterSpongeDispatcher instance = new SinisterSpongeDispatcher();

    private static EmployeeServ employeeService = EmployeeImpl.getInstance();

    private static RequestService requestService = RequestImpl.getInstance();

    private static LoginService loginService = LoginImpl.getLoginService();

    private Employee currentEmployee;
    private Request currentRequest;
    private List<Request> currentRequests;
    private boolean employeeManager;

    private SinisterSpongeDispatcher(){}

    public static SinisterSpongeDispatcher getDispatcher(){
        return instance;
    }

    public Object process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getRequestURI().contains("request")){
            System.out.println("Send Request to requestService");
            return requestService.process(req, resp);}
        else if (req.getRequestURI().contains("employee")) {
            System.out.println("Send Request to employeeServ");
            return employeeService.process(req, resp);
        } else if (req.getRequestURI().contains("login")){
            System.out.println("Send request to loginService");
            return loginService.process(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/static/login.html");
            dispatcher.forward(req, resp);
        };
        return null;

        //if (request.getRequestURI().contains("users"))
            //return userService.process(request, response);
        //else
        //System.out.println("Dispacter received request: " + req.getRequestURI());
        //return null;
    }
}
