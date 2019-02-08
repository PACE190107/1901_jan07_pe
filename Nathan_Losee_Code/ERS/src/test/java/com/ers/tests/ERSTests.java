package com.ers.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.CallableStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ers.dao.EmployeeDAO;
import com.ers.dao.ReimbursementRequestDAO;
import com.ers.models.Employee;
import com.ers.models.ReimbursementRequest;
import com.ers.util.ConnectionManager;
import com.ers.util.ERSExceptions;

public class ERSTests {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionManager.setJDBCConnection("Admin01", "s9d5j1q8");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ConnectionManager.getJDBCConnection().close();
	}

	@Before
	public void setUp() throws Exception {
		String sql = "CALL delete_employees()";
		CallableStatement cs = ConnectionManager.getJDBCConnection().prepareCall(sql);
		cs.execute();
	}

	@After
	public void tearDown() throws Exception {
		String sql = "CALL delete_employees()";
		CallableStatement cs = ConnectionManager.getJDBCConnection().prepareCall(sql);
		cs.execute();
	}

	/*****************************************************************************************************
	 * TESTING EMPLOYEE DAO
	 *****************************************************************************************************/
	// Testing createEmployee
	@Test
	public void testCreateEmployee_Normal() {
		assertTrue(EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "b", "a@b.c", true));
		Employee testWith = EmployeeDAO.getDAO().readEmployee("manager", "mpass");
		testWith.seteID(0);
		testWith.setePassword(null);
		
		Employee testAgainst = new Employee();
		testAgainst.seteUsername("manager");
		testAgainst.seteFirstName("a");
		testAgainst.seteLastName("b");
		testAgainst.seteEmail("a@b.c");
		testAgainst.setManager(true);
		
		assertEquals(testWith, testAgainst);
	}
	@Test
	public void testCreateEmployee_Duplicate() throws SQLException {
		expectedException.expect(ERSExceptions.ExistingUsernameException.class);
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "b", "a@b.c", true);
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "b", "a@b.c", true);
	}
	
	// Testing readEmployees
	@Test
	public void testReadEmployees() throws SQLException {
		assertEquals(0, EmployeeDAO.getDAO().readEmployees().size());
		EmployeeDAO.getDAO().createEmployee("master", "mpass", "a", "b", "a@b.c", true);
		EmployeeDAO.getDAO().createEmployee("servant", "mpass", "a", "b", "a@b.c", false);
		EmployeeDAO.getDAO().createEmployee("dobby", "mpass", "a", "b", "a@b.c", false);
		assertEquals(3, EmployeeDAO.getDAO().readEmployees().size());
	}
	
	// Testing readEmployee
	@Test
	public void testReadEmployee_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "b", "a@b.c", true);
		Employee testWith = EmployeeDAO.getDAO().readEmployee("manager", "mpass");
		testWith.seteID(0);
		testWith.setePassword(null);
		
		Employee testAgainst = new Employee();
		testAgainst.seteUsername("manager");
		testAgainst.seteFirstName("a");
		testAgainst.seteLastName("b");
		testAgainst.seteEmail("a@b.c");
		testAgainst.setManager(true);
		
		assertEquals(testWith, testAgainst);
	}
	@Test
	public void testReadEmployee_Nonexistant() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidUsernamePasswordException.class);
		EmployeeDAO.getDAO().readEmployee("manager", "mpass");
	}
	@Test
	public void testReadEmployee_WrongPassword() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidUsernamePasswordException.class);
		EmployeeDAO.getDAO().createEmployee("manager", "good", "a", "b", "a@b.c", true);
		EmployeeDAO.getDAO().readEmployee("manager", "bad");
	}
	
	// Testing readEmployee using ID
	@Test
	public void testReadEmployeeByID_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "b", "a@b.c", true);
		Employee testWith = EmployeeDAO.getDAO().readEmployee("manager", "mpass");
		int id = testWith.geteID();
		
		Employee testAgainst = EmployeeDAO.getDAO().readEmployee(id);
		
		assertEquals(testWith, testAgainst);
	}
	@Test
	public void testReadEmployeeByID_Nonexistant() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidEIDException.class);
		EmployeeDAO.getDAO().readEmployee(99999999);
	}
	
	// Testing updateEmployee
	@Test
	public void testUpdateEmployee_Username_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		assertTrue(EmployeeDAO.getDAO().updateEmployee(id, "username", "newmanager"));
		assertNotNull(EmployeeDAO.getDAO().readEmployee("newmanager", "mpass"));
	}
	@Test
	public void testUpdateEmployee_Username_Duplicate() throws SQLException {
		expectedException.expect(ERSExceptions.ExistingUsernameException.class);
		EmployeeDAO.getDAO().createEmployee("manager1", "mpass", "a", "b", "a@b.c", true);
		EmployeeDAO.getDAO().createEmployee("manager2", "mpass", "a", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager1", "mpass").geteID();
		EmployeeDAO.getDAO().updateEmployee(id, "username", "manager2");
	}
	@Test
	public void testUpdateEmployee_Password_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "oldpass", "a", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "oldpass").geteID();
		assertTrue(EmployeeDAO.getDAO().updateEmployee(id, "password", "newpass"));
		assertNotNull(EmployeeDAO.getDAO().readEmployee("manager", "newpass"));
	}
	@Test
	public void testUpdateEmployee_FirstName_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		assertTrue(EmployeeDAO.getDAO().updateEmployee(id, "firstName", "newFirstName"));
		Employee emp = EmployeeDAO.getDAO().readEmployee("manager", "mpass");
		assertEquals("newFirstName", emp.geteFirstName());
	}
	@Test
	public void testUpdateEmployee_LastName_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "a", "oldLastName", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		assertTrue(EmployeeDAO.getDAO().updateEmployee(id, "lastName", "newLastName"));
		Employee emp = EmployeeDAO.getDAO().readEmployee("manager", "mpass");
		assertEquals("newLastName", emp.geteLastName());
	}
	@Test
	public void testUpdateEmployee_Nonexistant() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidEIDException.class);
		EmployeeDAO.getDAO().updateEmployee(00000000, "lastName", "newLastName");
	}
	
	/*****************************************************************************************************
	 * TESTING REIMBURSEMENT REQUEST DAO
	 *****************************************************************************************************/
	// Testing createRequest
	@Test
	public void testCreateRequest_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		assertTrue(ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0));
	}
	@Test
	public void testCreateRequest_InvalidEID() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidEIDException.class);
		ReimbursementRequestDAO.getDAO().createRequest(00000000, "new request", 100.0);
	}
	
	// Testing readRequests
	@Test
	public void testReadRequests_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		assertEquals(0, ReimbursementRequestDAO.getDAO().readRequests(id).size());
		ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0);
		ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0);
		ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0);
		assertEquals(3, ReimbursementRequestDAO.getDAO().readRequests(id).size());
	}
	
	// Testing readRequest
	@Test
	public void testReadRequest_Normal() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		assertTrue(ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0));
		ReimbursementRequest testWith = ReimbursementRequestDAO.getDAO().readRequest(
				ReimbursementRequestDAO.getDAO().readRequests(id).get(0).getRrID());
		testWith.setRrID(0);
		
		ReimbursementRequest testAgainst = new ReimbursementRequest();
		testAgainst.seteID(id);
		testAgainst.setRrDescription("new request");
		testAgainst.setRrAmount(100.0);

		assertEquals(testWith, testAgainst);
	}
	@Test
	public void testReadRequest_Nonexistant() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidRRIDException.class);
		ReimbursementRequestDAO.getDAO().readRequest(00000000);
	}
	
	// Testing updateRequest
	@Test
	public void testUpdateRequest_Normal_Approved() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0);
		int rrid = ReimbursementRequestDAO.getDAO().readRequests(id).get(0).getRrID();
		assertTrue(ReimbursementRequestDAO.getDAO().updateRequest(rrid, id, true));
		assertTrue(ReimbursementRequestDAO.getDAO().readRequests(id).get(0).isApproved());
	}
	@Test
	public void testUpdateRequest_Normal_Denied() throws SQLException {
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0);
		int rrid = ReimbursementRequestDAO.getDAO().readRequests(id).get(0).getRrID();
		assertTrue(ReimbursementRequestDAO.getDAO().updateRequest(rrid, id, false));
		assertFalse(ReimbursementRequestDAO.getDAO().readRequests(id).get(0).isApproved());
	}
	@Test
	public void testUpdateRequest_NonexistantRRID() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidRRIDException.class);
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		ReimbursementRequestDAO.getDAO().updateRequest(00000000, id, true);
	}
	@Test
	public void testUpdateRequest_NonexistantEID() throws SQLException {
		expectedException.expect(ERSExceptions.InvalidRRIDException.class);
		EmployeeDAO.getDAO().createEmployee("manager", "mpass", "oldFirstName", "b", "a@b.c", true);
		int id = EmployeeDAO.getDAO().readEmployee("manager", "mpass").geteID();
		ReimbursementRequestDAO.getDAO().createRequest(id, "new request", 100.0);
		int rrid = ReimbursementRequestDAO.getDAO().readRequests(id).get(0).getRrID();
		ReimbursementRequestDAO.getDAO().updateRequest(rrid, 00000000, true);
	}
}
