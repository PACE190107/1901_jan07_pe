package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.utils.DBConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SinisterSpongeImpl implements SinisterSpongeDao {

    final static Logger log = Logger.getLogger(SinisterSpongeImpl.class);

    private static SinisterSpongeImpl instance = new SinisterSpongeImpl();

    private static DBConnectionUtil dbs = DBConnectionUtil.getInstance();

    public static SinisterSpongeImpl getInstance(){
        return instance;
    }

    private  SinisterSpongeImpl(){}

    @Override
    public boolean login(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            CallableStatement call = conn.prepareCall("{ ? = call GET_PASS_HASH(?,?) }");
            call.registerOutParameter(1, Types.VARCHAR);
            call.setString(2, emp.getUsername());
            call.setString(3, emp.getPassword());
            call.execute();
            if(getPassword(emp) == call.getString(1)) return true;
            else return false;
        } catch (SQLException e){
            log.error("SQL Error in DAO Login.", e);
            return false;
        }
    }



    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public List<Request> getEmployeeRequests(Employee emp) {
        return null;
    }

    @Override
    public List<Request> getEmployeePendingRequests(Employee emp) {
        return null;
    }

    @Override
    public List<Request> getEmployeeResolvedRequests(Employee emp) {
        return null;
    }

    @Override
    public List<Request> getAllRequests() {
        return null;
    }

    @Override
    public List<Request> getAllPendingRequests() {
        return null;
    }

    @Override
    public List<Request> getAllResolvedRequests() {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getProfileInformation(Employee emp) {
        return null;
    }

    @Override
    public Employee updateProfileInformation(Employee emp) {
        return null;
    }

    @Override
    public boolean registerNewEmployee(Employee emp) {
        return false;
    }

    @Override
    public boolean createNewRequest(Request req, Employee emp) {
        return false;
    }

    @Override
    public boolean updateRequest(Request req) {
        return false;
    }

    @Override
    public String getPassword(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT E_PASS FROM EMPLOYEE WHERE E_UNAME = ?");
            prep.setString(1, emp.getUsername());
            ResultSet result = prep.executeQuery();
            while(result.next()){
                return result.getString("E_PASS");
            }
            throw new SQLException();
        } catch (SQLException e){
            log.error("Error retreiving password in DAO getPassword()", e);
            return "";
        }
    }
}
