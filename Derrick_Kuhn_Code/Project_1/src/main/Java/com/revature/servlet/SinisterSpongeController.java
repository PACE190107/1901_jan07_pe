package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.SessionImpl;
import com.revature.services.SessionService;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SinisterSpongeController extends DefaultServlet {

    final static Logger log = Logger.getLogger(SinisterSpongeController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private SinisterSpongeDispatcher dispatcher = SinisterSpongeDispatcher.getDispatcher();

    private static SessionService sessionService = SessionImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Received Request on: " + req.getRequestURI());
        if(req.getRequestURI().substring(req.getContextPath().length())
                .startsWith("/static/")) {
            if(req.getRequestURI().contains("home")){
                if(sessionService.retreiveInstanceEmployee(req, resp) != null){
                    if(sessionService.retreiveInstanceEmployee(req, resp).isManager()){
                        if(req.getRequestURI().contains("managerhome.html")){
                            super.doGet(req, resp);
                        } else {
                            req.getRequestDispatcher("managerhome.html").forward(req,resp);
                        }
                    } else {
                        if(req.getRequestURI().contains("employeehome.html")){
                            super.doGet(req, resp);
                        } else {
                            req.getRequestDispatcher("employeehome.html").forward(req,resp);
                        }
                    }
                } else {
                    req.getRequestDispatcher("login.html").forward(req, resp);
                }
            }
            if(sessionService.retreiveInstanceEmployee(req, resp) != null){
                req.getRequestDispatcher("home.html");
            } else {
                if(req.getRequestURI().contains("login.html")){
                    super.doGet(req, resp);
                } else {
                    req.getRequestDispatcher("login.html").forward(req,resp);
                }
            }

        } else {
            resp.setContentType("application/json");
            resp.getWriter().append(mapper.writeValueAsString(dispatcher.process(req, resp)));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
