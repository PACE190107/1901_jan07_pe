import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.EmployeeDAOImplementation;
import com.revature.dao.ReimbursementDAOImplementation;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ERSTests {

	private static Employee e = new Employee(-1, "testUserr", "testUser", "empty", "empty", "empty", false);
	private static Reimbursement r = new Reimbursement(-1, 0, -1, ReimbursementType.BUSINESS, ReimbursementStatus.PENDING, 10, "date");
	
	@BeforeClass
	public static void initialize() {
		e = EmployeeDAOImplementation.getEmployeeDAO().insertEmployee(e);
		ReimbursementDAOImplementation.getReimbursementDAO().insertReimbursement(r);
		r = (Reimbursement)(ReimbursementDAOImplementation.getReimbursementDAO().getAllReimbursements(
																					r.getRequesterId(), r.getStatus()).toArray()[0]);
	}
	
	@Test
	public void checkForExistingEmployee() {
		assertNotNull(EmployeeDAOImplementation.getEmployeeDAO().getEmployee(e.getUsername()));
	}
	
	@Test
	public void checkForNonExisting() {
		assertNull(EmployeeDAOImplementation.getEmployeeDAO().getEmployee(""));
	}
	
	@Test
	public void checkForExistingReimbursement() {
		assertNotNull(ReimbursementDAOImplementation.getReimbursementDAO().getReimbursement(r.getId()));
	}
	
	@Test
	public void checkForNonExistingReimbursement() {
		assertNull(ReimbursementDAOImplementation.getReimbursementDAO().getReimbursement(-1));
	}
	
	@Test
	public void checkGetAllUsers() {
		assertNotNull(EmployeeDAOImplementation.getEmployeeDAO().getAllEmployees());
	}
	
	@Test
	public void checkGetAllPendingReimbursements() {
		assertNotNull(ReimbursementDAOImplementation.getReimbursementDAO().getAllReimbursements(ReimbursementStatus.PENDING));
	}
	
	@Test
	public void checkGetAllPendingReimbursementsForEmployee() {
		assertNotNull(ReimbursementDAOImplementation.getReimbursementDAO().getAllReimbursements(e.getId(), ReimbursementStatus.PENDING));
	}
	
	@Test
	public void updateValidEmployee() {
		Employee updated = new Employee(e.getId(), e.getUsername(), "newPass", "newEmail", "newFName", "newLName", false);
		assertNotNull(EmployeeDAOImplementation.getEmployeeDAO().updateEmployee(updated));
	}
	
	@Test
	public void updateInvalidEmployee() {
		Employee nonexistent = new Employee(-1, "", "", "", "", "", false);
		assertNull(EmployeeDAOImplementation.getEmployeeDAO().updateEmployee(nonexistent));
	}
	
	@Test
	public void updateValidReimbursement() {
		Reimbursement updated = 
				new Reimbursement(r.getId(), r.getRequesterId(), r.getAuthorizerId(),
								  r.getType(), ReimbursementStatus.DENIED, r.getAmount(), r.getDate());
		assertNotNull(ReimbursementDAOImplementation.getReimbursementDAO().updateReimbursement(updated));
	}
	
	@Test
	public void updateInvalidReimbursement() {
		Reimbursement nonexistent = new Reimbursement(-2, 0, -1, ReimbursementType.BUSINESS, ReimbursementStatus.DENIED, 10, "date");
		assertNull(ReimbursementDAOImplementation.getReimbursementDAO().updateReimbursement(nonexistent));
	}
	
	@AfterClass
	public static void deinitialize() {
		ReimbursementDAOImplementation.getReimbursementDAO().deleteReimbursement(r.getId());
		EmployeeDAOImplementation.getEmployeeDAO().deleteEmployee(e);
	}
}
