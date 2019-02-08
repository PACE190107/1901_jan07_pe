package com.revature.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Request;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class EmployeeImpl implements EmployeeServ{

    private static long session_ID;

    final static Logger log = Logger.getLogger(EmployeeService.class);

    private static EmployeeServ instance = new EmployeeImpl();

    private final ObjectMapper mapper = new ObjectMapper();

    private EmployeeImpl(){}

    public static EmployeeServ getInstance(){return instance;}

    private static EmployeeService employeeService = EmployeeService.getInstance();

    private static ManagerService managerService = ManagerService.getInstance();

    private static SessionService sessionService = SessionImpl.getInstance();

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee currentUser = sessionService.retreiveInstanceEmployee(req, resp);

        //currentUser = new Employee((long) 0, true);
        System.out.println("Received "+req.getMethod()+" on: "+ req.getRequestURI());
        if(currentUser==null){
            resp.sendRedirect("home.html");
            return "no session active";
        }

        if (req.getMethod().equals("GET")) {

            /*if(currentUser == null){
                req.getRequestDispatcher("static/login.html").forward(req,resp);
            }*/
            // GET ALL LOGIC
            if(req.getRequestURI().contains("profile")){
                return employeeService.getProfileInformation(currentUser);
            }
            String[] path = splitURI(req.getRequestURI());
            if (path.length == 4) {
                if(currentUser.isManager()){
                    // execute if the request is /Project1/ManagerHome/Employee
                    return managerService.getAllEmployees();
                } else {
                    // execute if the request is /Project1/EmployeeHome/Request
                    return "Must be logged in as a manager for request";
                }
            }
            // GET ONE LOGIC
            if (path.length == 5) {
                if(currentUser.isManager()){
                    // execute if request looks like /Project1/Manager*/Employee/E_ID or Resolved
                    return managerService.getEmployeeRequests(new Employee(Long.parseLong(path[4]), false));
                } else {
                    return "Must be logged in as a manager for request";
                }

            }
        }

        if (req.getMethod().equals("POST")) {

            /*if(currentUser == null){
                req.getRequestDispatcher("static/login.html").forward(req,resp);
            }*/
            // GET ALL LOGIC
            if(req.getHeader("Content-Type").equals("application/json")){
                Employee input = null;
                try{
                    input = mapper.readValue(req.getReader(), Employee.class);
                    final String username = input.getUsername();
                    final String fName = input.getFirst_name();
                    final String lName = input.getLast_name();
                    final String email = input.getEmail();
                    input = new Employee(currentUser.getE_id(), username, fName, lName, email);
                    return employeeService.updateProfileInformation(input);
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
            }
        }

        return null;
    }

    public String[] splitURI(String s){
        return s.split("/");
    }
}
