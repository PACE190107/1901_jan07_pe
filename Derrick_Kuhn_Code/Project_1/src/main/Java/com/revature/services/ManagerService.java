package com.revature.services;

import com.revature.dao.SinisterSpongeImpl;
import com.revature.model.Employee;
import com.revature.model.Request;
import org.apache.log4j.Logger;

import java.util.List;

public class ManagerService {
    final static Logger log = Logger.getLogger(ManagerService.class);

    private static ManagerService service = new ManagerService();

    private static SinisterSpongeImpl dao = SinisterSpongeImpl.getInstance();

    private ManagerService(){}

    public static ManagerService getInstance(){
        return service;
    }

    public boolean login(Employee emp){
        return dao.login(emp);
    }

    public boolean logout(){
        return dao.logout();
    }

    public List<Request> getEmployeeRequests(Employee emp){
        return dao.getEmployeeRequests(emp);
    }

    public List<Request> getAllRequests(){
        return dao.getAllRequests();
    }

    public List<Request> getAllPendingRequests() {
        return dao.getAllPendingRequests();
    }

    public List<Request> getAllResolvedRequests(){
        return dao.getAllResolvedRequests();
    }

    public List<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }

    public Employee getProfileInformation(Employee emp){
        return dao.getProfileInformation(emp);
    }

    public Employee updateProfileInformation(Employee emp){
        return dao.updateProfileInformation(emp);
    }

    public boolean updateRequest(Request req){
        return dao.updateRequest(req);
    }
}

