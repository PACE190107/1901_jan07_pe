package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static EmployeeDaoImpl empDao;
	final static Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	
	public static EmployeeDaoImpl getEmpDao() {
		if (empDao == null) {
			empDao = new EmployeeDaoImpl();
		}
		return empDao;
	}

	@Override
	public Employee viewSingleEmployee(int e_id) {
		Employee test = new Employee();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "Select * FROM EMPLOYEE WHERE e_id = " + e_id;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				test = new Employee(
						rs.getInt("e_id"), 
						rs.getString("e_username"), 
						rs.getString("e_password"),
						rs.getString("e_firstName"),
						rs.getString("e_lastName"),
						rs.getString("e_email"),
						rs.getInt("e_isManager")
						);
			}			
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("viewSinleEmploye failed");
		}
		return test;
	}

	@Override
	public boolean changeUsername(String e_username, int e_id) {
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "call updateUsername(?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, e_username);
			cs.setInt(2, e_id);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("changeUsername failed");
			return false;
		}	
		return true;
	}

	@Override
	public boolean changePassword(String e_password, int e_id) {
		Connection conn = null;
		conn = cu.getConnection();	
		String sql = "call updatePassword(?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, e_password);
			cs.setInt(2, e_id);
			cs.executeUpdate();
		} catch (SQLException e) {
			log.error("changePassword failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean changeEmail(String e_email, int e_id) {
		Connection conn = null;
		conn = cu.getConnection();
		
		String sql = "call updateEmail(?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, e_email);
			cs.setInt(2, e_id);
			cs.executeUpdate();
		} catch (SQLException e) {
			log.error("changeEmail failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Employee> viewAllEmployees() {
		Employee singleEmployee = new Employee();
		List<Employee> employeeList = new ArrayList();
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "Select * FROM EMPLOYEE";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				singleEmployee = new Employee(
						rs.getInt("e_id"), 
						rs.getString("e_username"), 
						rs.getString("e_password"),
						rs.getString("e_firstName"),
						rs.getString("e_lastName"),
						rs.getString("e_email"),
						rs.getInt("e_isManager")
						);
				employeeList.add(singleEmployee);
			}			
		}catch (SQLException e) {
			log.error("viewAllEmployees failed");
			e.printStackTrace();
		}
		return employeeList;
	}
	
	public Employee attemptAuthentication(String username, String password) {
		Employee employee = null;
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "Select * from EMPLOYEE WHERE e_username = ? AND e_password = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2, getHashedPassword(password));
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				employee = new Employee(
						rs.getInt("e_id"), 
						rs.getString("e_username"), 
						rs.getString("e_password"),
						rs.getString("e_firstName"),
						rs.getString("e_lastName"),
						rs.getString("e_email"),
						rs.getInt("e_isManager")
						);
			}	
		} catch (SQLException e) {
			log.error("attemptAuthentication failed");
			e.printStackTrace();
		}
		return employee;
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();

	}
	
	private String getHashedPassword(String password) {
		// invoke a Stored Procedure to perform the one-way hash of your password
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "{? = call GET_EMPLOYEE_HASH(?)}";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, password);
			cs.execute();
			return cs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("getHashedPassword failed");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	

	

}
