/**
 * 8.	For GUI – check if Password and re-typed Password are identical 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class UnmatchedException extends Exception {
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public UnmatchedException() {
		super("Passwords do not match");
	}
}
