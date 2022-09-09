/**
 * 1.	Length of password is less than 6 characters 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class LengthException extends Exception{
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public LengthException() {
		super("The password must be at least 6 characters long");
	}
}
