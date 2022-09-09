
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> ps;
	String validPs[] = {
			"Th!s1sValid",
			"s0issTh!s",
			"@ndThis?808",
			"SuchAStr0ngP@ssword"
	};
	String p1 = "TryThis1:)";
	String p2 = "C@mp1";
	@Before
	public void setUp() throws Exception {
		String arr[] = {
				"Password1$",
				"password2",
				"oddpassword?1A",
				"doosthiswork3!!",
				"aaandThis1?",
				"goodBurgers32",
				"332211aaABBb%%"
		};
		
		
		
		ps = new ArrayList<>();
		ps.addAll(Arrays.asList(arr));
	}

	@After
	public void tearDown() throws Exception {
		ps = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidLength(p1));
			
			assertTrue(PasswordCheckerUtility.isValidLength(p2));
			assertTrue("No exception thrown", false);
		}
		catch (LengthException e){
			assertTrue("Successfully threw a LengthException", true);
		}
		catch (Exception e) {
			assertTrue("Another exception was thrown", false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(p1));
			
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("wh4t??"));
			assertTrue("No exception thrown", false);
		}
		catch (NoUpperAlphaException e){
			assertTrue("Successfully threw a LengthException", true);
		}
		catch (Exception e) {
			assertTrue("Another exception was thrown", false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(p1));
			
			assertTrue(PasswordCheckerUtility.hasLowerAlpha("WH4T??"));
			assertTrue("No exception thrown", false);
		}
		catch (NoLowerAlphaException e){
			assertTrue("Successfully threw a LengthException", true);
		}
		catch (Exception e) {
			assertTrue("Another exception was thrown", false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			boolean pwd = PasswordCheckerUtility.isWeakPassword(p2);
			
			assertEquals(PasswordCheckerUtility.isWeakPassword("Str0ngNotWeak!"), false);
			
			assertTrue(PasswordCheckerUtility.isWeakPassword("Str0ng!"));
			assertTrue("No exception thrown", false);
		}
		catch (WeakPasswordException e){
			assertTrue("Successfully threw a LengthException", true);
		}
		catch (Exception e) {
			assertTrue("Another exception was thrown", false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.NoSameCharInSequence(p1));
			
			assertTrue(PasswordCheckerUtility.NoSameCharInSequence("dddoesTh1sWork?"));
			assertTrue("No exception thrown", false);
		}
		catch (InvalidSequenceException e){
			assertTrue("Successfully threw a LengthException", true);
		}
		catch (Exception e) {
			assertTrue("Another exception was thrown", false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(p1));
			
			assertTrue(PasswordCheckerUtility.hasDigit("DddoesTh!sWork?"));
			assertTrue("No exception thrown", false);
		}
		catch (NoDigitException e){
			assertTrue("Successfully threw a LengthException", true);
		}
		catch (Exception e) {
			assertTrue("Another exception was thrown", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			for (String a : validPs) 
				assertEquals(true, PasswordCheckerUtility.isValidPassword(a));
		}
		catch (Exception e) {
			assertTrue("An exception was thrown", false);
		}
			
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalid = PasswordCheckerUtility.getInvalidPasswords(ps);
		
		System.out.println(invalid.get(0));
		assertTrue(invalid.get(0).contains("password2"));
		assertTrue(invalid.get(0).contains("uppercase"));
		
		assertTrue(invalid.get(1).contains("doosthiswork3!!"));
		assertTrue(invalid.get(1).contains("uppercase"));
		
		assertTrue(invalid.get(2).contains("aaandThis1?"));
		assertTrue(invalid.get(2).contains("sequence"));
		
		assertTrue(invalid.get(3).contains("goodBurgers32"));
		assertTrue(invalid.get(3).contains("special"));
	}
	
}
