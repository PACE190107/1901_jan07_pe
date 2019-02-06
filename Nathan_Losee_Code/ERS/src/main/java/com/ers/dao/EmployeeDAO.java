package com.ers.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Employee;
import com.ers.util.ConnectionManager;
import com.ers.util.ERSExceptions;

public class EmployeeDAO implements EmployeeDAOInterface {
	private static EmployeeDAO singleton = new EmployeeDAO();
	private EmployeeDAO () { }
	public static EmployeeDAO getDAO() {
		return singleton;
	}
	
	private PreparedStatement readEmployeeStmnt;
	private PreparedStatement readEmployeeIDStmnt;
	public void resetStmnts() {
		try {
			String sql = "SELECT * FROM employees WHERE e_username = ? AND e_password = ?";
			readEmployeeStmnt = ConnectionManager.getJDBCConnection().prepareCall(sql);
			sql = "SELECT * FROM employees WHERE e_id = ?";
			readEmployeeIDStmnt = ConnectionManager.getJDBCConnection().prepareCall(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean createEmployee( String username, String password, String firstName,
			String lastName, String email, boolean isManager) {
		String sql = "CALL insert_employee(?,?,?,?,?,?,?)";
		try (CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall(sql)) {
			stmnt.setString(1, username);
			stmnt.setString(2, password);
			stmnt.setString(3, firstName);
			stmnt.setString(4, lastName);
			stmnt.setString(5, email);
			stmnt.setString(6, (isManager ? "t" : "f"));
			stmnt.registerOutParameter(7, Types.INTEGER);
		
			stmnt.executeUpdate();
			return true;
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new ERSExceptions.ExistingEmployeeException();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}

	@Override
	public List<Employee> readEmployees() {
		String sql = "SELECT * FROM Employees";
		try (ResultSet foundEmployees = ConnectionManager.getJDBCConnection().createStatement().
				executeQuery(sql)) {
			List<Employee> employees = new ArrayList<>();
			
			while (foundEmployees.next()) {
				Employee employee = new Employee();

				employee.seteID(foundEmployees.getInt(1));
				employee.seteUsername(foundEmployees.getString(2));
				employee.setePassword(foundEmployees.getString(3));
				employee.seteFirstName(foundEmployees.getString(4));
				employee.seteLastName(foundEmployees.getString(5));
				employee.seteEmail(foundEmployees.getString(6));
				employee.setManager(foundEmployees.getString(7).equals("t"));
				employee.setConfirmed(foundEmployees.getString(8).equals("t"));
				
				employees.add(employee);
			}
			
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	@Override
	public Employee readEmployeeByID(int eID) {
		try {
			readEmployeeIDStmnt.setInt(1, eID);
			try (ResultSet foundEmployees = readEmployeeIDStmnt.executeQuery()) {
				if (foundEmployees.next()) {
					Employee employee = new Employee();
	
					employee.seteID(foundEmployees.getInt(1));
					employee.seteUsername(foundEmployees.getString(2));
					employee.setePassword(foundEmployees.getString(3));
					employee.seteFirstName(foundEmployees.getString(4));
					employee.seteLastName(foundEmployees.getString(5));
					employee.seteEmail(foundEmployees.getString(6));
					employee.setManager(foundEmployees.getString(7).equals("t"));
					employee.setConfirmed(foundEmployees.getString(8).equals("t"));
					
					return employee;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Employee readEmployee(String username, String password) {
		try {
			readEmployeeStmnt.setString(1, username);
			readEmployeeStmnt.setString(2, getMd5(password + "SALT").toUpperCase());
			try (ResultSet foundEmployees = readEmployeeStmnt.executeQuery()) {
				if (foundEmployees.next()) {
					Employee employee = new Employee();
	
					employee.seteID(foundEmployees.getInt(1));
					employee.seteUsername(foundEmployees.getString(2));
					employee.setePassword(foundEmployees.getString(3));
					employee.seteFirstName(foundEmployees.getString(4));
					employee.seteLastName(foundEmployees.getString(5));
					employee.seteEmail(foundEmployees.getString(6));
					employee.setManager(foundEmployees.getString(7).equals("t"));
					employee.setConfirmed(foundEmployees.getString(8).equals("t"));
					
					return employee;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static String getMd5(String input) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);	
            StringBuilder hashtext = new StringBuilder(no.toString(16)); 
            while (hashtext.length() < 32) { 
                hashtext.insert(0, "0");
            } 
            return "PW" + hashtext.toString().substring(0, 28); 
        } catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }

	@Override
	public boolean updateEmployee(int eID, String credential, String newValue) throws SQLException {
		String sql = "CALL update_employee(?,?,?,?)";
		try (CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall(sql)) {
			stmnt.setInt(1, eID);
			stmnt.setString(2, credential);
			stmnt.setString(3, newValue);
			stmnt.registerOutParameter(4, Types.INTEGER);
			stmnt.executeUpdate();
			if (stmnt.getInt(4) <= 0)
				throw new ERSExceptions.InvalidUsernamePasswordException();
			if (stmnt.getInt(4) == 2)
				throw new ERSExceptions.ExistingUsernameException();
			return true;
		} catch (SQLSyntaxErrorException e) {
			throw new ERSExceptions.InvalidUsernamePasswordException();
		}
	}

	@Override
	public boolean deleteEmployee(int eID) throws SQLException {
		String sql = "CALL delete_user(?,?)";
		try (CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall(sql)) {
			stmnt.setInt(1, eID);
			stmnt.registerOutParameter(2, Types.INTEGER);
			
			stmnt.executeUpdate();
			return true;
		} catch (SQLSyntaxErrorException e) {
			throw new ERSExceptions.InvalidUsernamePasswordException();
		}
	}
}
