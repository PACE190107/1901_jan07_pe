package com.revature.test;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.ReimbursementDaoImplementation;
import com.revature.dao.UserDaoImplementation;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.LoginService;
import com.revature.service.LoginServiceImpl;
import com.revature.enums.Status;


public class UserDaoTest {

	
	private static User user = new User("bob","bob@gmail.com","burger", 0);	
	private static Reimbursement reimbursement = new Reimbursement(-9, "user", 77, Status.approved, null, 0);
	private static final LoginService loginService = new LoginServiceImpl();
	
	@BeforeClass
	public static void setup() {
		ReimbursementDaoImplementation.getReimbursementDao().insertReimbursement("super", 33);
	}
	
	@Test
	public void testGetUser() {
		User testUser = UserDaoImplementation.getUserDao().getUser("super");
		Assert.assertEquals("super", testUser.getUsername());
		Assert.assertEquals("fuksyr@gmail.com", testUser.getEmail());
		Assert.assertEquals(25, testUser.getSuperuser());
	}
	
	@Test
	public void testFailGetUser() {
		User testUser = UserDaoImplementation.getUserDao().getUser("bobby");
		Assert.assertEquals(new User(), testUser);
	}
	
	@Test
	public void testFailVerifyPassword() {
		Assert.assertTrue(UserDaoImplementation.getUserDao().verifyPassword("super", "superpass"));
	}	
	
	@Test
	public void testUserExists() {
		Assert.assertTrue(UserDaoImplementation.getUserDao().userExists("super"));
	}
	
	@Test
	public void testUserDoesntExist() {
		Assert.assertFalse(UserDaoImplementation.getUserDao().userExists("bobbo"));
	}
	
	@Test
	public void testGetAllUsers() {
		Assert.assertTrue(UserDaoImplementation.getUserDao().getAllUsers().size()>0);
	}
	
	@Test
	public void testInsertUser() {
		Assert.assertTrue(UserDaoImplementation.getUserDao().insertUser(user));
	}
	@Test
	public void testFailInsertUser() {
		Assert.assertFalse(UserDaoImplementation.getUserDao().insertUser(new User()));
	}
	
	@Test
	public void testFailDeleteUser() {
		Assert.assertFalse(UserDaoImplementation.getUserDao().deleteUser("kfjfkhsfbsh"));
	}
    
	@Test
	public void testFailinsertReimbursement() {
		Assert.assertFalse(ReimbursementDaoImplementation.getReimbursementDao().insertReimbursement(null, 0));
	}
	
	@Test
	public void testinsertReimbursement() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().insertReimbursement("super", 99));
	}
    
	@Test
	public void testGetReimbursement() {
		Assert.assertEquals(new Reimbursement(), ReimbursementDaoImplementation.getReimbursementDao().getReimbursement(-1));
		
	}
	
	@Test
	public void testGetAllReimbursement() {
		Assert.assertEquals(new ArrayList<>(), ReimbursementDaoImplementation.getReimbursementDao().getAllReimbursements("tksjkhdsd"));
	}
	
	@Test
	public void testUpdateDeclineReimbursement() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().updateReimbursement(ReimbursementDaoImplementation.getReimbursementDao().getMostRecentReimbursement(), "declined", "super"));
	}
	
	@Test
	public void testUpdateApproveReimbursement() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().updateReimbursement(ReimbursementDaoImplementation.getReimbursementDao().getMostRecentReimbursement(), "approved", "super"));
	}
	
	@Test
	public void testFailUpdateReimbursement() {
		Assert.assertFalse(ReimbursementDaoImplementation.getReimbursementDao().updateReimbursement(-31, "decline", "super"));
	}
	
	@Test
	public void testaddImageReimbursement() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().addImage(ReimbursementDaoImplementation.getReimbursementDao().getMostRecentReimbursement()));
	}
	
	@Test
	public void testMostRecentReimbursement() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().getMostRecentReimbursement()>0);
	}
	
	@Test
	public void testUpdateNameReimbursement() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().updateUsernameReimbursement("super", "super"));
	}
	
	@Test
	public void testSendEmail() {
		Assert.assertTrue(loginService.sendEmail(new User("user", "bsokevitz@gmail.com", "status", 0), new User("super", "Fuksyr@gmail.com", "status", 0), reimbursement, "this is just a test"));
	}
	
	@Test
	public void testSendFailEmail() {
		Assert.assertFalse(loginService.sendEmail(new User(), new User(), reimbursement, "this is just a test"));
	}
	
	@Test
	public void testGetAllUserReimbursements() {
		Assert.assertTrue(ReimbursementDaoImplementation.getReimbursementDao().getAllReimbursements("super").size()>=1);
	}
	
	@Test
	public void testFailGetAllUserReimbursements() {
		Assert.assertEquals(new ArrayList<>(), ReimbursementDaoImplementation.getReimbursementDao().getAllReimbursements("hdfjhbjf"));
	}
    
    @AfterClass
    public static void tearDownClass() {
        UserDaoImplementation.getUserDao().deleteUser(user.getUsername());
        ReimbursementDaoImplementation.getReimbursementDao().deleteReimbursement("super");
        
        }
}
