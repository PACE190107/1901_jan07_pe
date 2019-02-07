package com.revature.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.model.Status;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Local;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class RequestImpl implements RequestService {

    private static long session_ID;

    final static Logger log = Logger.getLogger(EmployeeService.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private static EmployeeService employeeService = EmployeeService.getInstance();

    private static ManagerService managerService = ManagerService.getInstance();

    private static RequestService instance = new RequestImpl();

    private static SessionService sessionService = SessionImpl.getInstance();

    private RequestImpl(){}

    public static RequestService getInstance(){
        return instance;
    }
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee currentUser = sessionService.retreiveInstanceEmployee(req, resp);

        //currentUser = new Employee((long) 0, true);
        if(currentUser==null){
            resp.sendRedirect("login.html");
            return "no session active";
        }
        System.out.println("Method received: "+req.getMethod());
        if (req.getMethod().equals("GET")) {

            /*if(currentUser == null){
                req.getRequestDispatcher("static/login.html").forward(req,resp);
            }*/
            // GET ALL LOGIC
            String[] path = splitURI(req.getRequestURI());
            if (path.length == 4) {
                if(currentUser.isManager()){
                    // execute if the request is /Project1/ManagerHome/Request
                    return managerService.getAllRequests();
                } else {
                    // execute if the request is /Project1/EmployeeHome/Request
                    return employeeService.getEmployeeRequests(currentUser);
                }
            }
            // GET ONE LOGIC
            if (path.length == 5) {
                if(currentUser.isManager()){
                    System.out.println("Request for Manager");
                    System.out.println(path[4]);
                    // execute if request looks like /Project1/Manager*/Request/Pending or Resolved
                    if(path[4].equals("pending")){
                        return managerService.getAllPendingRequests();
                    } else if(path[4] == "resolved"){
                        return managerService.getAllResolvedRequests();
                    } else return "Cannot process request.";
                } else {
                    System.out.println("Request for Employee");
                    // execute if request looks like /Project1/Employee*/Request/Pending or Resolved
                    if(path[4] == "pending"){
                        return employeeService.getEmployeePendingRequests(currentUser);
                    } else if(path[4] == "resolved"){
                        return employeeService.getEmployeeResolvedRequests(currentUser);
                    } else return "Cannot process request.";
                }

            }
        }

        if (req.getMethod().equals("POST")) {
            // CREATE LOGIC
            if (req.getHeader("Content-Type").equals("application/json")) {
                Request input = null;
                if(req.getHeader("Content-Type").equals("application/json")){
                    try{
                        input = mapper.readValue(req.getReader(), Request.class);
                        final long e_id = currentUser.getE_id();
                        final String subject = input.getSubject();
                        final String description = input.getDescription();
                        final double amount = input.getAmount();
                        input = new Request(e_id, subject, description, amount);
                        return employeeService.createNewRequest(input, currentUser);
                    } catch (JsonParseException e){
                        log.error("JsonParse error in login", e);
                        return "Error in parse";
                    } catch (JsonMappingException e){
                        log.error("JsonMapping error in login", e);
                        return "Error in mapping";
                    } catch (IOException e){
                        log.error("IOException in login", e);
                        return "IO error";
                    }
                } else {
                    return "application/json request expected but not received";
                }
            }
            try {
                // 415 is an Unsupported Media Type
                resp.sendError(415, "Please create using application/json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (req.getMethod().equals("PUT")){
            if(currentUser.isManager()){
                if (req.getHeader("Content-Type").equals("application/json")) {
                    Request input = null;
                    try{
                        input = mapper.readValue(req.getReader(), Request.class);
                        final long r_id = input.getR_id();
                        final long approver_id = currentUser.getE_id();
                        final Status status = input.getStatus();
                        input = new Request(r_id, approver_id, status);

                        return managerService.updateRequest(input, currentUser);

                    } catch (JsonParseException e){
                        log.error("JsonParse error in login", e);
                        return "Error in parse";
                    } catch (JsonMappingException e){
                        log.error("JsonMapping error in login", e);
                        return "Error in mapping";
                    } catch (IOException e) {
                        log.error("IOException in login", e);
                        return "IO error";
                    }} else {
                    return "application/json request expected but not received";
                }
            } else {
                return "Must be a manager to approve requests";
            }

            }
            try {
                // 415 is an Unsupported Media Type
                resp.sendError(415, "Please create using application/json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    public String[] splitURI(String s){
        return s.split("/");
    }
}
