package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImplementation implements EmployeeDAO {

	private static EmployeeDAOImplementation instance;
	static Logger logger = Logger.getLogger(EmployeeDAOImplementation.class);
	
	private EmployeeDAOImplementation() {}
	
	public static EmployeeDAOImplementation getEmployeeDAO() {
		if (instance == null) {
			instance = new EmployeeDAOImplementation();
		}
		return instance;
	}
	
	public Employee insertEmployee(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO EMPLOYEE values(NULL,?,?,?,?,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, employee.getUsername());
				ps.setString(2, hashPassword(employee.getUsername(), employee.getPassword()));
				ps.setString(3, employee.getEmail());
				ps.setString(4, employee.getFirstName());
				ps.setString(5, employee.getLastName());
				ps.setString(6, employee.getIsManager() ? "1" : "0");
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getEmployee(employee.getUsername());	// to get the triggered id
			}
		} catch (SQLException e) {
			logger.error("insertEmployee() exception due to: " + e.getMessage());
		}
		return null;
	}

	public Employee updateEmployee(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE EMPLOYEE SET "
					+ "E_USERNAME = ?, E_PASSWORD = ?, E_EMAIL = ?, E_FIRSTNAME = ?, "
					+ "E_LASTNAME = ?, E_IS_MANAGER = ? WHERE E_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, employee.getUsername());
				ps.setString(2, hashPassword(employee.getUsername(), employee.getPassword()));
				ps.setString(3, employee.getEmail());
				ps.setString(4, employee.getFirstName());
				ps.setString(5, employee.getLastName());
				ps.setString(6, employee.getIsManager() ? "1" : "0");
				ps.setInt(7, employee.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return employee;	
			}
		} catch (SQLException e) {
			logger.error("updateEmployee() exception due to: " + e.getMessage());
		}
		return null;
	}

	public Employee deleteEmployee(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM EMPLOYEE WHERE E_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, employee.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return employee;
			}
		} catch (SQLException e) {
			logger.error("deleteEmployee() exception due to: " + e.getMessage());
		}
		return null;
	}

	public Employee getEmployee(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE E_USERNAME = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, username);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Employee(
								rs.getInt("E_ID"),
								rs.getString("E_USERNAME"), 
								rs.getString("E_PASSWORD"),
								rs.getString("E_EMAIL"),
								rs.getString("E_FIRSTNAME"), 
								rs.getString("E_LASTNAME"),
								rs.getBoolean("E_IS_MANAGER")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error("getEmployee() exception due to: " + e.getMessage());
		}
		return null;
	}

	public ArrayList<Employee> getAllEmployees() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Employee> employees = new ArrayList<Employee>();
					while (rs.next()) {
						employees.add(new Employee(
								rs.getInt("E_ID"),
								rs.getString("E_USERNAME"), 
								rs.getString("E_PASSWORD"),
								rs.getString("E_EMAIL"),
								rs.getString("E_FIRSTNAME"), 
								rs.getString("E_LASTNAME"),
								rs.getBoolean("E_IS_MANAGER")
								));
					}
					return employees;
				}
			}
		} catch (SQLException e) {
			logger.error("getAllEmployees() exception due to: " + e.getMessage());
		}
		return null;
	}

	public boolean insertCredentials(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "CREATE USER " + username + " IDENTIFIED BY " + password;
			try (Statement stmt = connection.createStatement()) {
				stmt.execute(sql);
				return true;
			}
		} catch (SQLException e) {
			logger.error("insertCredentials() exception due to: " + e.getMessage());
		}
		return false;
	}

	public boolean updateCredentials(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "ALTER USER " + username + " IDENTIFIED BY " + password;
			try (Statement stmt = connection.createStatement()) {
				stmt.executeUpdate(sql);
				return true;	
			}
		} catch (SQLException e) {
			logger.error("insertCredentials() exception due to: " + e.getMessage());
		}
		return false;
	}

	public boolean deleteCredentials(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DROP USER " + employee.getUsername();
			try (Statement stmt = connection.createStatement()) {
				stmt.executeUpdate(sql);
				return true;	
			}
		} catch (SQLException e) {
			logger.error("deleteCredentials() exception due to: " + e.getMessage());
		}
		return false;
	}
	
	public boolean grantDBPermissions(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql;
			try (Statement stmt = connection.createStatement()) {
				if (employee.getIsManager()) {
					sql = "GRANT DBA TO " + employee.getUsername();	
					stmt.execute(sql);
				} else {
					sql = "GRANT CREATE SESSION TO " + employee.getUsername();
					stmt.execute(sql);
					sql = "GRANT SELECT, UPDATE ON EMPLOYEE TO " + employee.getUsername();
					stmt.execute(sql);
					sql = "GRANT SELECT, INSERT, UPDATE, DELETE ON REIMBURSEMENT TO " + employee.getUsername();
					stmt.execute(sql);
					sql = "GRANT SELECT ON REIMBURSEMENT_SEQ TO " + employee.getUsername();
					stmt.execute(sql);
				}
				return true;	
			}
		} catch (SQLException e) {
			logger.error("grantPermissions() exception due to: " + e.getMessage());
		}
		return false;
	}
	
	public boolean revokeDBPermissions(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql;
			try (Statement stmt = connection.createStatement()) {
				if (employee.getIsManager()) {
					sql = "REVOKE DBA FROM " + employee.getUsername();	
					stmt.execute(sql);
				} else {
					sql = "REVOKE CREATE SESSION FROM " + employee.getUsername();
					stmt.execute(sql);
					sql = "REVOKE SELECT, INSERT, UPDATE, DELETE ON REIMBURSEMENT FROM " + employee.getUsername();
					stmt.execute(sql);
					sql = "REVOKE SELECT ON REIMBURSEMENT_SEQ FROM " + employee.getUsername();
					stmt.execute(sql);
				}
				return true;
			}
		} catch (SQLException e) {
			logger.error("grantPermissions() exception due to: " + e.getMessage());
		}
		return false;
	}
	
	public String hashPassword(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT GET_EMPLOYEE_HASH(?, ?) FROM dual";
			try (CallableStatement cs = connection.prepareCall(sql)) {
				cs.setString(1, username);
				cs.setString(2, password);
				ResultSet rs = cs.executeQuery();
				if (rs.next()) {
					return rs.getString(1);	
				}
			}
		} catch (SQLException e) {
			logger.error("insertCredentials() exception due to: " + e.getMessage());
		}
		return null;
	}
}
