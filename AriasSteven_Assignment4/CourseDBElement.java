/**
 * Provides information on a course
 * @author Steven
 *
 */
public class CourseDBElement implements Comparable{
	// Store data
	private String courseID;
	private int CRN;
	private int credits;
	private String roomNumber;
	private String instructorName;
	
	/**
	 * Initialize
	 * @param ID Course ID
	 * @param crn CRN
	 * @param credits Credits
	 * @param room Room number
	 * @param inst Instructor name
	 */
	public CourseDBElement(String ID, int crn, int credits, String room, String inst) {
		courseID = ID;
		this.CRN = crn;
		this.credits = credits;
		roomNumber = room;
		instructorName = inst;
	}
	
	/**
	 * Empty
	 */
	public CourseDBElement() {
		
	}
	
	/**
	 * Set course ID
	 * @param ID course ID
	 */
	public void setID(String ID) {
		courseID = ID;
	}
	
	/**
	 * Set crn
	 * @param crn Crn
	 */
	public void setCRN(int crn) {
		this.CRN = crn;
	}
	
	/**
	 * Set credits
	 * @param cred credits
	 */
	public void setCredits(int cred) {
		credits = cred;
	}
	
	/**
	 * Set room number
	 * @param room room number
	 */
	public void setRoomNumber(String room) {
		roomNumber = room;
	}
	
	/**
	 * Set instructor
	 * @param ID Instructor
	 */
	public void setInstructor(String ID) {
		instructorName = ID;
	}
	
	/**
	 * Get course ID
	 * @return course ID
	 */
	public String getID() {
		return courseID;
	}
	
	/**
	 * Get room number
	 * @return room number
	 */
	public String getRoomNum() {
		return roomNumber;
	}
	
	/**
	 * Get instructor
	 * @return instructor
	 */
	public String getInstructor() {
		return instructorName;
	}
	
	/**
	 * Get crn
	 * @return crn
	 */
	public int getCRN() {
		return CRN;
	}
	
	/**
	 * Get credits
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}

	@Override
	public int compareTo(Object o) {
		CourseDBElement second = (CourseDBElement) o;
		if (this.CRN < second.CRN) return -1;
		else if (this.CRN > second.CRN) return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		 return "\nCourse:" + getID() + " CRN:" + getCRN()
	     + " Credits:" + getCredits()
	     + " Instructor:" + getInstructor() + " Room:" +
	     getRoomNum();
	}
}
