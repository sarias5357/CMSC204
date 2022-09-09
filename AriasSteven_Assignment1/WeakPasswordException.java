/**
 * 7.	Password contains 6 to 9 characters which are otherwise valid 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class WeakPasswordException extends Exception{
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public WeakPasswordException() {
		super("The password is OK but weak - it contains fewer than 10 characters");
	}
}
