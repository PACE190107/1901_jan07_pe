package revature.timjordan.projectZero;

import static org.junit.Assert.*;

import org.junit.Test;

import revature.timjordan.projectZero.models.User;
import revature.timjordan.projectZero.services.UserService;

public class UserDaoTest {

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsUser() {
		assertTrue(UserService.getUserService().isUser(new User("twjordan")));
		assertFalse(UserService.getUserService().isUser(new User("asdasda")));
	}

	@Test
	public void testIsPassword() {
		assertTrue(UserService.getUserService().isPassword(new User("twjordan", "tim93")));
		assertFalse(UserService.getUserService().isPassword(new User("twjordan", "assa")));
	}

	

}
