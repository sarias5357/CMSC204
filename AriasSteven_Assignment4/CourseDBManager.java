import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Data structure client
 * @author Steven
 *
 */

public class CourseDBManager implements CourseDBManagerInterface {
	// Data structure and default capacity
	private CourseDBStructure structure;
	private final static int DEFAULT_CAPACITY = 11;
	
	/*
	 * Initialize with given capacity
	 */
	public CourseDBManager(int cap) {
		structure = new CourseDBStructure(cap);
	}
	
	/**
	 * Initalize with default capacity
	 */
	public CourseDBManager() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	@Override
	public CourseDBElement get(int crn){
		try {
			return structure.get(crn);
		}
		catch (IOException e) {
			return null;
		}
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			String[] parts = str.split(" ");
			
			CourseDBElement course = new CourseDBElement(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3], parts[4]);
			structure.add(course);
		}
		scanner.close();
	}

	@Override
	public ArrayList<String> showAll() {
		return structure.showAll();
	}

}
