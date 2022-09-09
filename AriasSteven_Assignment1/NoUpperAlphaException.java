/**
 * 2.	Password doesn’t contain an uppercase alpha character 
 * @author Steven
 *
 */
@SuppressWarnings("serial")
public class NoUpperAlphaException extends Exception{
	/**
	 * This constructor passes the error message to the superclass constructor
	 */
	public NoUpperAlphaException() {
		super("The password must contain at least one uppercase"
				+ " alphabetic character");
	}
}
