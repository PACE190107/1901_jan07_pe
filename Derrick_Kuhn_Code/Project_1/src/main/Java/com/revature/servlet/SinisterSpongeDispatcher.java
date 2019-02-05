package com.revature.servlet;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;
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

    private static EmployeeService employeeService = EmployeeService.getInstance();

    private static ManagerService managerService = ManagerService.getInstance();

    private Employee currentEmployee;
    private Request currentRequest;
    private List<Request> currentRequests;
    private boolean employeeManager;

    private SinisterSpongeDispatcher(){}

    public static SinisterSpongeDispatcher getDispatcher(){
        return instance;
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uri = req.getRequestURI().substring(req.getContextPath().length()+1);
        while(uri.indexOf("/")>0){
            uri = uri.substring(0, uri.indexOf("/"));
        }
        switch(uri){
            case "Project_1_war": RequestDispatcher rd = req.getRequestDispatcher("/static/home.html");
                rd.include(req, resp); break;
            case "Project_1": req.getRequestDispatcher("home.html").forward(req, resp); break;
            case "login": System.out.println("Not implemented"); break;
            case "logout": System.out.println("Not implemented"); break;
            case "requests": System.out.println("Not implemented"); break;
            case "user_info": System.out.println("Not implemented"); break;
            default: System.out.println("Not implemented");

        }
        System.out.println("Dispacter received request: " + req.getRequestURI());
    }
}
