/**
 * 5.	Password doesn’t contain a special character 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class NoSpecialCharacterException extends Exception{
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}
}
