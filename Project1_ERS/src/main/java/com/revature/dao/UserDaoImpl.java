package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl userDao = null;
	
	private UserDaoImpl() {
		
	}
	
	//DAO getter
	public static UserDaoImpl getUserDao() {
		if(userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}
	
	
	public boolean createEmployee(Employee employee) {

		boolean executed = false;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "call insert_user(?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, employee.getFirstName());
			cs.setString(2, employee.getLastName());
			cs.setString(3, employee.getUsername() );
			cs.setString(4, employee.getPassword());
			
			switch(employee.getEmployeeType()) {
				case MANAGER: cs.setInt(5, 1);
				case EMPLOYEE: cs.setInt(5, 1);
			}
			
			cs.setString(6, employee.getEmail());
			if(cs.execute()) {
				executed = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return executed;
	}

	public Employee getUserById(int id) {

		Employee employee = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_user where user_id = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				employee = new Employee();	
				employee.setEmployeeId(id);
				employee.setUsername(rs.getString(2));
				employee.setFirstName(rs.getString(4));
				employee.setLastName(rs.getString(5));
				switch(rs.getInt(6)) {
				case 1:
					employee.setEmployeeType(EmployeeType.MANAGER);
					break;
				case 2: 
					employee.setEmployeeType(EmployeeType.EMPLOYEE);
					break;
				default: System.out.println("Error in switch statement.");
				}
				employee.setEmail(rs.getString(7));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
// tested
	public Employee getUserByUsernamePassword(String username, String password) {
		Employee employee = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_user where username = '" + username+"' AND password = '"+ password + "'";
			PreparedStatement ps = conn.prepareCall(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				employee = new Employee();
				employee.setEmployeeId(rs.getInt(1));
				employee.setUsername(rs.getString(2));
				employee.setFirstName(rs.getString(4));
				employee.setLastName(rs.getString(5));
				switch(rs.getInt(6)) {
				case 1:
					employee.setEmployeeType(EmployeeType.MANAGER);
					break;
				case 2: 
					employee.setEmployeeType(EmployeeType.EMPLOYEE);
					break;
				default: System.out.println("Error in switch statement.");
				}
				employee.setEmail(rs.getString(7));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
//
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from ers_user where user_type = 2";
			PreparedStatement ps = conn.prepareCall(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Employee employee = new Employee();	
				employee.setEmployeeId(rs.getInt(1));
				employee.setUsername(rs.getString(2));
				employee.setFirstName(rs.getString(4));
				employee.setLastName(rs.getString(5));
				employee.setEmployeeType(EmployeeType.EMPLOYEE);
				employee.setEmail(rs.getString(7));
				employees.add(employee);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public boolean updateFirstName(String newFirstName, int uId) {
		boolean executed = false;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update ers_user set first_name = '"+newFirstName+"' where user_id = " + uId;
			PreparedStatement ps = conn.prepareCall(sql);
			if( ps.executeUpdate() == 1) {
				executed = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return executed;
	}
	
	public boolean updateLastName(String newLastName, int uId) {
		boolean executed = false;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update ers_user set last_name = '"+newLastName+"' where user_id = " + uId;
			PreparedStatement ps = conn.prepareCall(sql);
			if( ps.executeUpdate() == 1) {
				executed = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return executed;
	}
	
	public boolean updateEmail(String newEmail, int uId) {
		boolean executed = false;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update ers_user set email = '"+newEmail+"' where user_id = " + uId;
			PreparedStatement ps = conn.prepareCall(sql);
			if( ps.executeUpdate() == 1) {
				executed = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return executed;
	}
	
}
