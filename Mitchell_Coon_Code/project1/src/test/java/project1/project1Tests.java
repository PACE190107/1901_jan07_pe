package project1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.delegate.HomeDelegate;

public class project1Tests {

	//private static final UserConsole userConsole = new HomeDelegate();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * isValidNumber tests
	 ******************************************************************/
	@Test
	public void testAValidNumber() {
		assertTrue(new HomeDelegate().isValidNumber("100"));
	}

	@Test
	public void testANumberWithDecimal() {
		assertTrue(new HomeDelegate().isValidNumber("100.00"));
	}
	
	@Test
	public void testANumberWithTooManyDigits() {
		assertFalse(new HomeDelegate().isValidNumber("100.111111"));
	}
	
	@Test
	public void testANumberWithTooManyDecimals() {
		assertFalse(new HomeDelegate().isValidNumber("100.00.00"));
	}

	@Test
	public void testANegativeNumber() {
		assertFalse(new HomeDelegate().isValidNumber("-100"));
	}
	

	@Test
	public void testAWord() {
		assertFalse(new HomeDelegate().isValidNumber("ThisIsNotANumber"));
	}
	
	@Test
	public void testAnEmptyNumber() {
		assertFalse(new HomeDelegate().isValidNumber(""));
	}
	
	@Test
	public void testAVeryBigNumber() {
		assertFalse(new HomeDelegate().isValidNumber(Double.toString(Double.MAX_VALUE) + 1));
	}
	
	/*******************************************************************
	 * verifyName tests
	 ******************************************************************/
	
	@Test
	public void testGoodName() {
		assertTrue(new HomeDelegate().verifyName("Mitch"));
	}

	@Test
	public void testVeryLongName() {
		assertFalse(new HomeDelegate().verifyName("Miiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiitch"));
	}
	
	@Test
	public void testNameWithNumbers() {
		assertFalse(new HomeDelegate().verifyName("Mitch2"));
	}
	

	@Test
	public void testNameWithSpecialCharacters() {
		assertFalse(new HomeDelegate().verifyName("Xx_Mitch_the_Destroyer_of_Worlds_xX"));
	}
	

	@Test
	public void testEmptyName() {
		assertFalse(new HomeDelegate().verifyName(""));
	}
	
	@Test
	public void testSpaceName() {
		assertFalse(new HomeDelegate().verifyName(" "));
	}
	
	
	/*******************************************************************
	 * verifyUsernameOrPassword tests
	 ******************************************************************/
	
	@Test
	public void testGoodUserName() {
		assertTrue(new HomeDelegate().verifyUsernameOrPassword("username"));
	}
	
	@Test
	public void testUserNameWithUnderscore() {
		assertTrue(new HomeDelegate().verifyUsernameOrPassword("user_name"));
	}
	
	@Test
	public void testUserNameWithNumbers() {
		assertTrue(new HomeDelegate().verifyUsernameOrPassword("username12345"));
	}
	
	@Test
	public void testUserNameWithOtherSpecialCharacters() {
		assertFalse(new HomeDelegate().verifyUsernameOrPassword("user_name!@#$%^&*()/\\<>-+="));
	}
	
	@Test
	public void testVeryLongUserName() {
		assertFalse(new HomeDelegate().verifyUsernameOrPassword("usernaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaame"));
	}
	
	@Test
	public void testEmptyUserName() {
		assertFalse(new HomeDelegate().verifyUsernameOrPassword(""));
	}
	
	@Test
	public void testSpaceUserName() {
		assertFalse(new HomeDelegate().verifyUsernameOrPassword(" "));
	}

	/*******************************************************************
	 * isInteger tests
	 ******************************************************************/
	
	@Test
	public void testAValidInteger() {
		assertTrue(new HomeDelegate().isPositiveInteger("1"));
	}
	
	@Test
	public void testADouble() {
		assertFalse(new HomeDelegate().isPositiveInteger("1.0"));
	}
	
	@Test
	public void testATextBasedInteger() {
		assertFalse(new HomeDelegate().isPositiveInteger("one"));
	}
	
	@Test
	public void testAnEmptyInteger() {
		assertFalse(new HomeDelegate().isPositiveInteger(""));
	}

	@Test
	public void testANegativeInteger() {
		assertFalse(new HomeDelegate().isPositiveInteger("-1"));
	}
	
	@Test
	public void testAVeryBigInteger() {
		assertFalse(new HomeDelegate().isPositiveInteger("100000000000000000000000000"));
	}
	
}