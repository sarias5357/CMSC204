/**
 * 6.	Password contains more than 2 of the same character in sequence 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class InvalidSequenceException extends Exception{
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public InvalidSequenceException() {
		super("The password cannot contain more than two of the same characters"
				+ " in sequence");
	}
}
