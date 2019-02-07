package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.services.EmployeeServiceImp;
import com.revature.utils.JDBCConnectionUtil;

public class EmployeeDaoImp implements EmployeeDao{
	private static EmployeeDaoImp empDao;
	public static EmployeeDaoImp getEmployeeDaoImp() {
		if (empDao==null) {
			empDao= new EmployeeDaoImp();
		}
		return empDao;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		ResultSet rs = null;
		Employee employee = null;
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from employee_table WHERE user_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, employeeId);
				rs = ps.executeQuery();
				if(rs.next()) {
					
					employee = new Employee(
							rs.getInt("user_id"),
							rs.getString("firstname"),
							rs.getString("lastname"),
							rs.getString("username"),
							rs.getString("passwrd"),
							rs.getString("email"),
							rs.getInt("ismanager")
							);
					/*
					 * 	employee = new Employee();
					employee.setUser_id(rs.getInt(1));
					employee.setFirstName(rs.getString(2));
					employee.setLastName(rs.getString(3));
					employee.setUserName(rs.getString(4));
					employee.setPassword(rs.getString(5));
					employee.setEmailAddress(rs.getString(6));
					employee.setIsManager(rs.getInt(7));
					 */
				
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "select * from employee_table";
			ResultSet rs = stmt.executeQuery(sql);
			List<Employee> allEmployees = new ArrayList<>();
			while (rs.next()) {
				allEmployees.add(new Employee(
						rs.getInt("user_id"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("username"),
						rs.getString("passwrd"),
						rs.getString("email"),
						rs.getInt("isManager"))
						);
			}
			return allEmployees;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public boolean updateEmployeeFName(int employeeId, String firstName) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call UPDATE_EMPLOYEE_FIRSTNAME (?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employeeId);
			cs.setString(2, firstName);
			if(cs.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeLName(int employeeId, String lastName) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call UPDATE_EMPLOYEE_LASTNAME (?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employeeId);
			cs.setString(2, lastName);
			if(cs.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return false;
	}

	@Override
	public boolean updateEmployeeUsername(int employeeId, String userName) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call UPDATE_EMPLOYEE_USERNAME (?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employeeId);
			cs.setString(2, userName);
			if(cs.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return false;
	}

	@Override
	public boolean updateEmployeePassword(int employeeId, String passw0rd) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call UPDATE_EMPLOYEE_PASSWORD (?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employeeId);
			cs.setString(2, passw0rd);
			if(cs.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return false;
	}

	@Override
	public boolean updateEmployeeEmail(int employeeId, String emailAddress) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call UPDATE_EMPLOYEE_EMAIL (?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, employeeId);
			cs.setString(2, emailAddress);
			if(cs.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		return false;
	}

	
}
