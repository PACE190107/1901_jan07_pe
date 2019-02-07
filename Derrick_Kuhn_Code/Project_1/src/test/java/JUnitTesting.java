import com.revature.model.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;
import com.revature.services.SessionImpl;
import com.revature.services.SessionService;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitTesting {
    private static EmployeeService employeeService = EmployeeService.getInstance();

    private static ManagerService managerService = ManagerService.getInstance();

    private static SessionService sessionService = SessionImpl.getInstance();

    @Test
    public void verifyEmployeeCount(){
        //System.out.println(managerService.getAllEmployees());
        assertEquals(6, managerService.getAllEmployees().size());
    }

    @Test
    public void verifyRequestCount(){
        //System.out.println(managerService.getAllRequests());
        assertEquals(8, managerService.getAllRequests().size());
    }

    @Test
    public void verifyEmployee(){
        Employee expected = new Employee((long) 0, "320DAE32A746E91585495047336DDA77",
        "manager", "Marcus", "Anager", "test@test.com", true);
        assertEquals(expected, managerService.getProfileInformation(expected));
    }

    @Test
    public void registerNewEmployee(){
        Employee newGuy = new Employee("password", "uniqueUser", "Jay", "Unit", "Junit@junit.com");
        assertEquals(new Employee((long) 6, "6D9A0BB9D7349E3454984144F7B49AD3", "uniqueUser", "Jay", "Unit", "Junit@junit.com", false), employeeService.registerEmployee(newGuy));
    }

    @Test
    public void registerExistingEmployee(){
        Employee newGuy = new Employee("password", "manager", "Jay", "Unit", "Junit@junit.com");
        assertEquals(null, employeeService.registerEmployee(newGuy));
    }

    @Test
    public void pendingRequestCount(){
        assertEquals(3, managerService.getAllPendingRequests().size());
    }

    @Test
    public void resolvedRequestCount(){
        assertEquals(5, managerService.getAllResolvedRequests().size());
    }
}
