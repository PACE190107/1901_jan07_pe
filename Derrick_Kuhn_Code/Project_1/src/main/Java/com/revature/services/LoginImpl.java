package com.revature.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginImpl implements LoginService {
    private static LoginService loginService = new LoginImpl();

    private LoginImpl(){}

    public static LoginService getLoginService(){return loginService;}

    final static Logger log = Logger.getLogger(LoginImpl.class);

    private static EmployeeService employeeService = EmployeeService.getInstance();

    private static ManagerService managerService = ManagerService.getInstance();

    private static SessionService sessionService = SessionImpl.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee currentUser = sessionService.retreiveInstanceEmployee(req, resp);

        //currentUser = new Employee((long) 0, true);

        log.info("Receive method: " + req.getMethod());
        if(req.getMethod().equals("POST")){
            if(currentUser != null){
                log.info("Current User session active.");
                if(currentUser.isManager()){
                    resp.sendRedirect("/static/managerhome.html");
                } else{
                    resp.sendRedirect("/static/employeehome.html");
                }
            } else{
                Employee input = null;
                if(req.getHeader("Content-Type").equals("application/json")){
                    try{
                        input = mapper.readValue(req.getReader(), Employee.class);
                        System.out.println("Jackson Object");
                        System.out.println(input);
                        final String username = input.getUsername();
                        final String password = input.getPassword();
                        input = new Employee(username, password);
                        System.out.println(input);
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
                /*Employee input = null;
                log.info(req.getReader());
                input = mapper.readValue(req.getReader(), Employee.class);*/
                //Employee input = new Employee(req.getParameter("username"), req.getParameter("password"));
                if(employeeService.login(input)){
                    input = employeeService.getProfileInformation(input);
                    HttpSession session = req.getSession();
                    session.setAttribute("SESSION_ID", input.getE_id());
                    session.setAttribute("MANAGER", input.isManager());
                    log.info("Login successful for: " + input.getUsername());
                    if(input.isManager()){
                        System.out.println("Manager logged in");
                        return true;
                    } else{
                        System.out.println("Employee logged in");
                        return true;
                    }
                    /*if(input.isManager()){
                        return "Manager login successful";
                        //resp.sendRedirect("managerHome");
                    } else{
                        return "Employee login successful";
                        //resp.sendRedirect("employeeHome");
                    }*/
                } else {
                    log.info("Login failed for: " + input.getUsername());
                    return "Login failed for username " +input.getUsername();
                    //resp.sendRedirect("home");
                }
            }
        } else if(req.getMethod().equals("GET")){
            System.out.println("Processing GET method");
            HttpSession session = req.getSession();
            session.invalidate();
            session = req.getSession(false);
            System.out.println("Logout attempt.  Session = "+ session);
            return true;
            /*req.getSession().invalidate();
            resp.sendRedirect("home");*/
        } else {
            currentUser = sessionService.retreiveInstanceEmployee(req, resp);
            if(currentUser.isManager()){
                resp.sendRedirect("/static/managerhome.html");
            } else{
                resp.sendRedirect("/static/employeehome.html");
            }

            return false;
        }
        return null;
    }
}
