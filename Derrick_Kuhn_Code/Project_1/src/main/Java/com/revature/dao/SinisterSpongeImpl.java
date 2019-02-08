package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.model.Status;
import com.revature.utils.DBConnectionUtil;
import com.revature.utils.DateConversion;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinisterSpongeImpl implements SinisterSpongeDao {

    final static Logger log = Logger.getLogger(SinisterSpongeImpl.class);

    private static SinisterSpongeImpl instance = new SinisterSpongeImpl();

    private static DBConnectionUtil dbs = DBConnectionUtil.getInstance();

    private static DateConversion dateConversion = DateConversion.getInstance();

    public static SinisterSpongeImpl getInstance(){
        return instance;
    }

    private  SinisterSpongeImpl(){}

    @Override
    public boolean login(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            CallableStatement call = conn.prepareCall("{ ? = call GET_PASS_HASH(?) }");
            call.registerOutParameter(1, Types.VARCHAR);
            call.setString(2, emp.getPassword());
            call.execute();
            if(getPassword(emp).equals(call.getString(1))) return true;
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
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM REQUEST WHERE E_ID = ?");
            prep.setLong(1, emp.getE_id());
            return createRequestList(prep.executeQuery());
        } catch (SQLException e){
            log.error("Error in DAO getEmployeeRequests", e);
            return null;
        }
    }

    @Override
    public List<Request> getEmployeePendingRequests(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM REQUEST WHERE E_ID = ? AND R_STATUS = ?");
            prep.setLong(1, emp.getE_id());
            prep.setString(2, "PENDING");
            ResultSet result = prep.executeQuery();
            ArrayList<Request> requests = new ArrayList<>();
            while(result.next()){
                requests.add(new Request(result.getLong("R_ID"), result.getLong("E_ID"), result.getString("R_SUBJECT"),
                        result.getString("R_DESCRIPTION"), result.getDouble("R_AMOUNT"),
                        result.getLong("R_APPROVER"), "PENDING"));
            }
            return requests;
        } catch (SQLException e){
            log.error("Error in DAO getEmployeePendingRequests", e);
            return null;
        }
    }

    @Override
    public List<Request> getEmployeeResolvedRequests(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM REQUEST WHERE E_ID = ? AND R_STATUS != ?");
            prep.setLong(1, emp.getE_id());
            prep.setString(2, "PENDING");
            return createRequestList(prep.executeQuery());
        } catch (SQLException e){
            log.error("Error in DAO getEmployeePendingRequests", e);
            return null;
        }
    }

    @Override
    public List<Request> getAllRequests() {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM REQUEST");
            return createRequestList(prep.executeQuery());
        } catch (SQLException e){
            log.error("Error in DAO getAllRequests", e);
            return null;
        }
    }

    @Override
    public List<Request> getAllPendingRequests() {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM REQUEST WHERE R_STATUS = ?");
            prep.setString(1, "PENDING");
            return createRequestList(prep.executeQuery());
        } catch (SQLException e){
            log.error("Error in DAO getAllPendingRequests", e);
            return null;
        }
    }

    @Override
    public List<Request> getAllResolvedRequests() {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM REQUEST WHERE R_STATUS != ?");
            prep.setString(1, "PENDING");
            return createRequestList(prep.executeQuery());
        } catch (SQLException e){
            log.error("Error in DAO getAllResolvedRequests", e);
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM EMPLOYEE");
            ResultSet result = prep.executeQuery();
            ArrayList<Employee> employees = new ArrayList<>();
            while(result.next()){
                employees.add(new Employee(result.getLong("E_ID"), result.getString("E_PASS"),
                        result.getString("E_UNAME"), result.getString("E_FNAME"), result.getString("E_LNAME"),
                        result.getString("E_EMAIL"), isManager(result.getInt("E_MANAGER"))));
            }
            return employees;
        } catch (SQLException e){
            log.error("Error in DAO getAllEmployees", e);
            return null;
        }
    }

    @Override
    public Employee getProfileInformation(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE E_ID = ?");
            prep.setLong(1, emp.getE_id());
            ResultSet result = prep.executeQuery();
            Employee employee = null;
            while(result.next()){
                employee = new Employee(result.getLong("E_ID"), result.getString("E_PASS"),
                        result.getString("E_UNAME"), result.getString("E_FNAME"), result.getString("E_LNAME"),
                        result.getString("E_EMAIL"), isManager(result.getInt("E_MANAGER")));
            }
            System.out.println(employee);
            return employee;
        } catch (SQLException e){
            log.error("Error in DAO getProfileInformation", e);
            return null;
        }
    }

    @Override
    public Employee getProfileInformationUname(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE E_UNAME = ?");
            prep.setString(1, emp.getUsername());
            ResultSet result = prep.executeQuery();
            Employee employee = null;
            while(result.next()){
                employee = new Employee(result.getLong("E_ID"), result.getString("E_PASS"),
                        result.getString("E_UNAME"), result.getString("E_FNAME"), result.getString("E_LNAME"),
                        result.getString("E_EMAIL"), isManager(result.getInt("E_MANAGER")));
            }
            System.out.println(employee);
            return employee;
        } catch (SQLException e){
            log.error("Error in DAO getProfileInformation", e);
            return null;
        }
    }

    @Override
    public boolean updateProfileInformation(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareStatement("UPDATE EMPLOYEE SET E_UNAME = ?, E_FNAME = ?, E_LNAME = ?," +
                    " E_EMAIL = ? WHERE E_ID = ?");
            prep.setString(1, emp.getUsername());
            prep.setString(2, emp.getFirst_name());
            prep.setString(3, emp.getLast_name());
            prep.setString(4, emp.getEmail());
            prep.setLong(5, emp.getE_id());
            prep.executeUpdate();
            commitQuery(conn.createStatement());
            return true;
        } catch (SQLException e){
            log.error("Error in DAO updateProfileInformation", e);
            return false;
        }
    }

    @Override
    public Employee registerNewEmployee(Employee emp) {
        try(Connection conn = dbs.getConnection()){
            if(checkIfEmployeeExists(emp)){ return null; }
            //email, username, password, fname, lname
            CallableStatement call = conn.prepareCall("call REGISTER_EMPLOYEE(?,?,?,?,?)");
            call.setString(1, emp.getEmail());
            call.setString(2, emp.getUsername());
            call.setString(3, emp.getPassword());
            call.setString(4, emp.getFirst_name());
            call.setString(5, emp.getLast_name());
            call.executeUpdate();
            commitQuery(conn.createStatement());
            return getProfileInformation(emp);
        } catch (SQLException e){
            log.error("Error in DAO registerNewEmployee", e);
            return null;
        }
    }

    @Override
    public boolean createNewRequest(Request req, Employee emp) {
        try(Connection conn = dbs.getConnection()){
            //(E_ID, R_SUBJECT, R_DESCRIPTION, R_AMOUNT, R_STATUS
            CallableStatement call = conn.prepareCall("call CREATE_REQUEST(?,?,?,?,?)");
            call.setLong(1, emp.getE_id());
            call.setString(2, req.getsubject());
            call.setString(3, req.getDescription());
            call.setDouble(4, req.getAmount());
            call.setString(5, req.getStatus());
            call.executeUpdate();
            commitQuery(conn.createStatement());
            return true;
        } catch (SQLException e){
            log.error("Error in DAO createNewRequest", e);
            return false;
        }
    }

    @Override
    public boolean updateRequest(Request req, Employee emp) {
        try(Connection conn = dbs.getConnection()){
            PreparedStatement prep = conn.prepareCall("UPDATE REQUEST SET R_STATUS = ?, R_APPROVER = ? WHERE R_ID = ?");
            prep.setString(1, req.getStatus());
            prep.setLong(2, emp.getE_id());
            prep.setLong(3, req.getR_id());
            prep.executeUpdate();
            commitQuery(conn.createStatement());
            return true;
        } catch (SQLException e){
            log.error("Error in DAO updateRequest", e);
            return false;
        }
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

    public boolean checkIfEmployeeExists(Employee emp){
        if(getProfileInformation(emp) == null) return false;
        else return true;
    }

    public Status setEnum(String input){
        switch(input){
            case "APPROVED": return Status.APPROVED;
            case "DENIED": return Status.DENIED;
            default: return Status.PENDING;
        }
    }

    public String convertEnum(Status status){
        switch(status){
            case APPROVED: return "APPROVED";
            case DENIED: return "DENIED";
            default: return "PENDING";
        }
    }

    public boolean isManager(int input){
        if(input == 0) return false;
        else return true;
    }

    public List<Request> createRequestList(ResultSet result) throws SQLException{
        ArrayList<Request> requests = new ArrayList<>();
        while(result.next()){
            requests.add(new Request(result.getLong("R_ID"), result.getLong("E_ID"), result.getString("R_SUBJECT"),
                    result.getString("R_DESCRIPTION"), result.getDouble("R_AMOUNT"),
                    result.getLong("R_APPROVER"), result.getString("R_STATUS")));
        }
        return requests;
    }

    public void commitQuery(Statement stmt) throws SQLException{
        stmt.execute("commit");
    }
}
