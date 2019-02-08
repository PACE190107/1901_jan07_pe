package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

import org.junit.Test;

public class TestERS 
{
	private static final EmployeeDao employeeDao = new EmployeeDaoImpl();
	private static final ReimbursementDao reimburseDao = new ReimbursementDaoImpl();
	
	public void createEmployee()
	{
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "INSERT INTO MTREVATURE.Employee_Info( first_name, last_name, job_description, user_name, user_password)"
					+ "values(?,?,?,?,?)";
			
			try 
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "Test");
				ps.setString(2, "Case");
				ps.setString(3, "Employee");
				ps.setString(4, "tester");
				ps.setString(5, "qwerty");
				ps.executeUpdate();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testEmployeeLogin()
	{
		createEmployee();
		String username = "tester";
		String password = "qwerty";
		assertEquals("Employee", employeeDao.employeeLogin(username, password));
		deleteEmployee();
	}
	
	@Test
	public void testSingleEmployeeInfo()
	{
		createEmployee();
		List<Employee> employee = employeeDao.getEmployeeInfo("tester");
		assertTrue(employee.size() == 1);
		deleteEmployee();
	}
	
	@Test
	public void testAllEmployeeInfo()
	{
		createEmployee();
		List<Employee> employee = employeeDao.getAllEmployeeInfo();
		assertTrue(employee.size() == 8);
		deleteEmployee();
	}
	
	@Test
	public void testAllReimbursements()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.manAllReimbursements();
		assertEquals(3, reim.size());
		deleteEmployee();
	}
	
	@Test
	public void testSingleEmployeeReim()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.manEmployeeReimbursements("tester");
		assertTrue(reim.size() == 0);
		deleteEmployee();
	}
	
	@Test
	public void testResolvedReimbursements()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.manResolvedReimbursements();
		assertEquals(1, reim.size());
		deleteEmployee();
	}
	
	@Test
	public void testPendingReimbursements()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.manPendingReimbursements();
		assertEquals(2, reim.size());
		deleteEmployee();
	}
	
	@Test
	public void testEmployeeAllReim()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.empEmployeeReimbursements("tester");
		assertEquals(0, reim.size());
		deleteEmployee();
	}
	
	@Test
	public void testEmployeeResolvedReim()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.empResolvedReimbursements("emp3");
		assertEquals(1, reim.size());
		deleteEmployee();
	}
	
	@Test
	public void testEmployeePendingReim()
	{
		createEmployee();
		List<Reimbursement> reim = reimburseDao.empPendingReimbursements("emp3");
		assertEquals(0, reim.size());
		deleteEmployee();
	}
	
	public void deleteEmployee()
	{
		try(Connection conn = ConnectionUtil.getConnection())
		{
			String sql = "DELETE FROM MTREVATURE.Employee_Info WHERE user_name = ?";
			
			try 
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "tester");
				ps.executeUpdate();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
}