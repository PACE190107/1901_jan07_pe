package project0;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.UserConsole;

public class project0Tests {

	//private static final UserConsole userConsole = new UserConsole();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * isValidNumber tests
	 ******************************************************************/
	@Test
	public void testAValidNumber() {
		assertTrue(new UserConsole().isValidNumber("100"));
	}

	@Test
	public void testANumberWithDecimal() {
		assertTrue(new UserConsole().isValidNumber("100.00"));
	}
	
	@Test
	public void testANumberWithTooManyDigits() {
		assertFalse(new UserConsole().isValidNumber("100.111111"));
	}
	
	@Test
	public void testANumberWithTooManyDecimals() {
		assertFalse(new UserConsole().isValidNumber("100.00.00"));
	}

	@Test
	public void testANegativeNumber() {
		assertFalse(new UserConsole().isValidNumber("-100"));
	}
	

	@Test
	public void testAWord() {
		assertFalse(new UserConsole().isValidNumber("ThisIsNotANumber"));
	}
	
	@Test
	public void testAnEmptyNumber() {
		assertFalse(new UserConsole().isValidNumber(""));
	}
	
	@Test
	public void testAVeryBigNumber() {
		assertFalse(new UserConsole().isValidNumber(Double.toString(Double.MAX_VALUE) + 1));
	}
	
	/*******************************************************************
	 * verifyName tests
	 ******************************************************************/
	
	@Test
	public void testGoodName() {
		assertTrue(new UserConsole().verifyName("Mitch"));
	}

	@Test
	public void testVeryLongName() {
		assertFalse(new UserConsole().verifyName("Miiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiitch"));
	}
	
	@Test
	public void testNameWithNumbers() {
		assertFalse(new UserConsole().verifyName("Mitch2"));
	}
	

	@Test
	public void testNameWithSpecialCharacters() {
		assertFalse(new UserConsole().verifyName("Xx_Mitch_the_Destroyer_of_Worlds_xX"));
	}
	

	@Test
	public void testEmptyName() {
		assertFalse(new UserConsole().verifyName(""));
	}
	
	@Test
	public void testSpaceName() {
		assertFalse(new UserConsole().verifyName(" "));
	}
	
	
	/*******************************************************************
	 * verifyUsernameOrPassword tests
	 ******************************************************************/
	
	@Test
	public void testGoodUserName() {
		assertTrue(new UserConsole().verifyUsernameOrPassword("username"));
	}
	
	@Test
	public void testUserNameWithUnderscore() {
		assertTrue(new UserConsole().verifyUsernameOrPassword("user_name"));
	}
	
	@Test
	public void testUserNameWithNumbers() {
		assertTrue(new UserConsole().verifyUsernameOrPassword("username12345"));
	}
	
	@Test
	public void testUserNameWithOtherSpecialCharacters() {
		assertFalse(new UserConsole().verifyUsernameOrPassword("user_name!@#$%^&*()/\\<>-+="));
	}
	
	@Test
	public void testVeryLongUserName() {
		assertFalse(new UserConsole().verifyUsernameOrPassword("usernaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaame"));
	}
	
	@Test
	public void testEmptyUserName() {
		assertFalse(new UserConsole().verifyUsernameOrPassword(""));
	}
	
	@Test
	public void testSpaceUserName() {
		assertFalse(new UserConsole().verifyUsernameOrPassword(" "));
	}

	/*******************************************************************
	 * isInteger tests
	 ******************************************************************/
	
	@Test
	public void testAValidInteger() {
		assertTrue(new UserConsole().isPositiveInteger("1"));
	}
	
	@Test
	public void testADouble() {
		assertFalse(new UserConsole().isPositiveInteger("1.0"));
	}
	
	@Test
	public void testATextBasedInteger() {
		assertFalse(new UserConsole().isPositiveInteger("one"));
	}
	
	@Test
	public void testAnEmptyInteger() {
		assertFalse(new UserConsole().isPositiveInteger(""));
	}

	@Test
	public void testANegativeInteger() {
		assertFalse(new UserConsole().isPositiveInteger("-1"));
	}
	
	@Test
	public void testAVeryBigInteger() {
		assertFalse(new UserConsole().isPositiveInteger("100000000000000000000000000"));
	}
	
}