package com.revature.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SinisterSpongeDispatcher {

    final static Logger log = Logger.getLogger(SinisterSpongeDispatcher.class);

    private static SinisterSpongeDispatcher instance = new SinisterSpongeDispatcher();

    private SinisterSpongeDispatcher(){}

    public static SinisterSpongeDispatcher getDispatcher(){
        return instance;
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }
}
