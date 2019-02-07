package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.exceptions.LoginFailureException;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.utilities.DAOUtilities;

import oracle.jdbc.OracleTypes;

public class EmployeeDAOImpl implements EmployeeDAO{
	Connection connection = null;	// Our connection to the database
	private static EmployeeDAOImpl employeeDao;
	
	final static Logger log = LogManager.getLogger(EmployeeDAOImpl.class);
	private EmployeeDAOImpl() {
	}
	
	public static EmployeeDAOImpl getEmployeeDao() {
		if (employeeDao == null) {
			employeeDao = new EmployeeDAOImpl();
		}
		return employeeDao;
	}
	//--------------------------------------------------------------------------
	public Employee updateEmployee(int ID, String fName, String lName,  String email) {
		Employee emp = null;
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "CALL EDIT_EMPLOYEE(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, ID);
			cs.setString(2,fName);
			cs.setString(3,lName);
			cs.setString(4, email);
			cs.registerOutParameter(5, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(5);
			while(rs.next()) {
				if (rs.getInt(6)==1) {
					emp = new Manager();
					emp.setId(rs.getInt(1));
					emp.setFirstName(rs.getString(2));
					emp.setLastName(rs.getString(3));
					emp.setUserName(rs.getString(4));
					emp.setEmail(rs.getString(5));
				}else {
					emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				}
			}
 		}catch(Exception e){
			log.error(e.getMessage());
		}
		return emp;
	}
	//--------------------------------------------------------------------------
	public Employee newEmployee(String fName, String lName, String uname, String pass, String email) {
//FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, RETURNED_USER OUT
		Employee emp = new Employee();
		try (Connection conn = DAOUtilities.getConnection()) {
			String sql = "CALL NEW_MANAGER(?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, fName);
			cs.setString(2, lName);
			cs.setString(3, uname);
			cs.setString(4, pass);
			cs.setString(5, email);
			cs.registerOutParameter(6, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(6);
			while(rs.next()) {
				emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			return emp;
		}catch(Exception e){
			log.error("New Employee Error");
			log.error(e.getLocalizedMessage());
		}finally {
			this.closeResources();
		}
		return null;
	}

	//--------------------------------------------------------------------------
	public List<Employee> getAllEmployees() {
		List<Employee> Employees = new ArrayList<>();
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, USER_NAME, EMAIL, MANAGER FROM EMPLOYEE";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(6) == 1) {
					Employees.add(new Manager(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5)));
				}else {
					Employees.add(new Employee(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5)));
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}finally {
			this.closeResources();
		}
		return Employees;
	}

	//--------------------------------------------------------------------------
	public Employee LogIn(String username, String password) {
		Employee employee = null;
		try(Connection conn = DAOUtilities.getConnection()){
			String sql = "CALL LOGIN_CHECK(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			System.out.println("before login execute");
			cs.execute();
			System.out.println("After login Execute");
			ResultSet rs = (ResultSet) cs.getObject(3);
			System.out.println("After rs made");
			if( rs != null) {
				while(rs.next()) {
					System.out.println("Top of Login while");
					if(rs.getInt(6) == 1) {
						System.out.println("Manager");
						employee = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
					}else {
					//regular employee
						System.out.println("Employee");
						employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				}
				}
			}
			System.out.println("after while");
		}catch(Exception e) {
			System.out.println("Error is caused in while");
			log.error(e.toString());
		}
		finally{
			this.closeResources();
		}
		try {
			if(employee == null) {
				throw new LoginFailureException();
			}			
		}catch(Exception e) {
		}
		return employee;
	}

	//--------------------------------------------------------------------------
	private void closeResources() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
//switch to logger
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
