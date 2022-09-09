import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to check passwords if they are valid
 * @author Steven
 *
 */

public class PasswordCheckerUtility {
	
	/**
	 * Compares equality of two passwords
	 * @param password password string to be checked for
	 * @param passwordConfirm passwordConfirm string to be checked against password for
	 * @throws UnmatchedException thrown if not same (case sensitive)
	 */
	
	public static void comparePasswords(String password, 
			String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}
	}
	
	/**
	 * Compare equality of two passwords
	 * @param password password string to be checked for
	 * @param passwordConfirm passwordConfirm string to be checked against password for
	 * @return true if both same (case sensitive), false otherwise
	 */
	
	public static boolean comparePasswordsWithReturn(String password,
			String passwordConfirm) {
		
		// If the comparePasswords method (no return) throws an exception, then
		// that means the passwords do not match. Return false
		try {
			comparePasswords(password, passwordConfirm);
		}
		catch (UnmatchedException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks the password length requirement - The password must be at least 6 characters long
	 * @param password password string to be checked for length
	 * @return true if meets minimum length requirement
	 * @throws LengthException thrown if does not meet minimum length requirement
	 */
	
	public static boolean isValidLength(String password) throws LengthException {
		if (password.length() >= 6) return true;
			
		else throw new LengthException();
	}
	
	/**
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException thrown if does not meet alpha character requirement
	 */
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		// Flag
		boolean f = false;
		
		// Loop through password
		for (int i = 0; i < password.length(); i++) {
			// If an uppercase letter is found
			if (Character.isUpperCase(password.charAt(i))) {
				f = true; // Flag switch
				break; // Get out of loop
			}
		}
		
		// Return true if true, otherwise throw exception
		if (f) return f;
		else throw new NoUpperAlphaException();
	}
	
	/**
	 * Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
	 * @param password password string to be checked for lowercase requirement
	 * @return true if meets lowercase requirement
	 * @throws NoLowerAlphaException thrown if does not meet lowercase requirement
	 */
	
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		boolean f = false;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				f = true;
				break;
			}
		}
		
		if (f) return f;
		else throw new NoLowerAlphaException();
	}
	
	/**
	 * Checks the password Digit requirement - Password must contain a numeric character
	 * @param password password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException thrown if does not meet Digit requirement
	 */
	
	public static boolean hasDigit(String password) throws NoDigitException {
		boolean f = false;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				f = true;
				break;
			}
		}
		
		if (f) return f;
		else throw new NoDigitException();
	}
	
	/**
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password password string to be checked for SpecialCharacter requirement
	 * @return true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 */
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		// Check for special character using regex
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		
		if (!matcher.matches()) return true;
		else throw new NoSpecialCharacterException();
	}
	
	/**
	 * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException thrown if meets Sequence requirement
	 */
	
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		int count = 0; // Keep count
		
		// Iterate through password
		for (int i = 0; i < password.length() - 2; i++) {
			// Check two characters ahead
			for (int j = i; j <= (i+2); j++) {
				// If characters match initial character, increment count
				if (password.charAt(i) == password.charAt(j))
					count++;
			}
			// If 3 are found in a row, throw exception, else reset counter
			if (count == 3) throw new InvalidSequenceException();
			else count = 0;
		}
		
		return true;
	}
	
	/**
	 * Return true if valid password (follows all the following rules), returns false if an invalid password 1. At least 6 characters long - 2. 
	 * At least 1 numeric character- 3. At least 1 uppercase alphabetic character - 4. At least 1 lowercase alphabetic character - 5. At least 1 special character - 6. 
	 * No more than 2 of the same character in a sequence - Hello@123 – OK AAAbb@123 – not OK Aaabb@123 – OK
	 * @param password string to be checked for validity
	 * @return true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws InvalidSequenceException thrown if no uppercase alphabetic
	 * @throws NoUpperAlphaException thrown if no lowercase alphabetic
	 * @throws NoLowerAlphaException thrown if no digit
	 * @throws NoDigitException thrown if does not meet SpecialCharacter requirement
	 * @throws NoSpecialCharacterException thrown if more than 2 of same character.
	 */
	
	public static boolean isValidPassword(String password) throws LengthException, InvalidSequenceException, 
							NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException {
		
		// Call all methods. 
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		NoSameCharInSequence(password);

		//If no exception is thrown, true will be returned
		return true;
	}
	
	/**
	 * checks if the password contains 6 to 9 characters
	 * @param password password string to be checked for
	 * @return true if password contains 6 to 9 characters, false otherwise
	 */
	
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() >= 6 && password.length() <= 9) return true;
		else return false;
	}
	
	/**
	 * Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param password string to be checked if weak password
	 * @return false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
	 * @throws WeakPasswordException if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID.
	 */
	
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		if (!hasBetweenSixAndNineChars(password)) return false;
		else throw new WeakPasswordException();
	}
	
	/**
	 * This method will accept an ArrayList of passwords as the parameter and return an ArrayList with the status of any invalid passwords 
	 * (weak passwords are not considered invalid). The ArrayList of invalid passwords will be of the following format: 
	 * password BLANK message of the exception thrown
	 * @param passwords list of passwords
	 * @return ArrayList of invalid passwords in the correct format
	 */
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		// Create arraylist
		ArrayList<String> invalidPasswords = new ArrayList<>();
		
		// Go through given arraylist
		for (String pass: passwords) {
			// Check if password is valid
			try {
				isValidPassword(pass);
			}
			// If not, add it to the invalid passwords arraylist
			catch (Exception e) {
				invalidPasswords.add(pass + " -> " +e.getMessage());
			}
		}
		
		return invalidPasswords;
	}
}
