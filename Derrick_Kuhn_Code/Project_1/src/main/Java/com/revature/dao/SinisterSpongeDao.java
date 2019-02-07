package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Request;

import java.util.List;

public interface SinisterSpongeDao {
    public boolean login(Employee emp);
    public boolean logout();
    public List<Request> getEmployeeRequests(Employee emp);
    public List<Request> getEmployeePendingRequests(Employee emp);
    public List<Request> getEmployeeResolvedRequests(Employee emp);
    public List<Request> getAllRequests();
    public List<Request> getAllPendingRequests();
    public List<Request> getAllResolvedRequests();
    public List<Employee> getAllEmployees();
    public Employee getProfileInformation(Employee emp);
    public Employee updateProfileInformation(Employee emp);
    public Employee registerNewEmployee(Employee emp);
    public boolean createNewRequest(Request req, Employee emp);
    public boolean updateRequest(Request req, Employee emp);
    public String getPassword(Employee emp);
}
