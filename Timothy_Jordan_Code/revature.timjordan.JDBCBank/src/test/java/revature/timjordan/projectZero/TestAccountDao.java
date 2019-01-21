package revature.timjordan.projectZero;

import static org.junit.Assert.*;

import org.junit.Test;

import revature.timjordan.projectZero.models.User;
import revature.timjordan.projectZero.services.UserService;

public class TestAccountDao {

	@Test
	public void testHasSavings() {
		assertTrue(UserService.getUserService().hasSavings(new User("twjordan")));
		
	}

}
