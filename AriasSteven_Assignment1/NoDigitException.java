/**
 * 4.	Password doesn’t contain a numeric character 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class NoDigitException extends Exception{
	public NoDigitException() {
		/**
		 * This constructor passes the error message to the superclass constructor
		 */
		super("The password must contain at least one digit");
	}
}
