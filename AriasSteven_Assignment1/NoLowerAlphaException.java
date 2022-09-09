/**
 * 3.	Password doesn’t contain a lowercase alpha character 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class NoLowerAlphaException extends Exception {
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase"
				+ " alphabetic character");
	}
}
