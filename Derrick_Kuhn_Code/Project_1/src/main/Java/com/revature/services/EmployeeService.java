package com.revature.services;

import com.revature.dao.SinisterSpongeImpl;
import com.revature.model.Employee;
import com.revature.model.Request;
import org.apache.log4j.Logger;

import java.util.List;

public class EmployeeService {
    final static Logger log = Logger.getLogger(EmployeeService.class);

    private static EmployeeService service = new EmployeeService();

    private static SinisterSpongeImpl dao = SinisterSpongeImpl.getInstance();

    private EmployeeService(){}

    public static EmployeeService getInstance(){
        return service;
    }

    public boolean login(Employee emp){
        return dao.login(emp);
    }

    public Employee registerEmployee(Employee emp){
        return dao.registerNewEmployee(emp);
    }

    public List<Request> getEmployeeRequests(Employee emp){
        return dao.getEmployeeRequests(emp);
    }

    public List<Request> getEmployeePendingRequests(Employee emp){
        return dao.getEmployeePendingRequests(emp);
    }

    public List<Request> getEmployeeResolvedRequests(Employee emp){
        return dao.getEmployeeResolvedRequests(emp);
    }

    public Employee getProfileInformation(Employee emp){
        return dao.getProfileInformation(emp);
    }

    public Employee getProfileInformationUname(Employee emp){
        return dao.getProfileInformationUname(emp);
    }

    public boolean updateProfileInformation(Employee emp){
        return dao.updateProfileInformation(emp);
    }

    public boolean createNewRequest(Request req, Employee emp){
        return dao.createNewRequest(req, emp);
    }
}
