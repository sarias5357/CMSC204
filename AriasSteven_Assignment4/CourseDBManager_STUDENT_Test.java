import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for CDM
 * @author Steven
 *
 */
public class CourseDBManager_STUDENT_Test {
	// 2 CDM objects
	CourseDBManager manager;
	CourseDBManager manager2;
	
	/**
	 * Creates 2 instances of CDM
	 * @throws Exception Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Initialize
		manager = new CourseDBManager();
		manager2 = new CourseDBManager();
	}
	
	/**
	 * Set both references to null
	 */
	@After
	public void tearDown() {
		// Set to null
		manager = null;
		manager2= null;
	}
	
	/**
	 * Test add method
	 */
	@Test
	public void testAdd() {
		try {
			// Add 2 courses
			manager.add("CMSC204", 911, 4, "401", "Professor Jelly");
			manager2.add("CMSC204", 912, 4, "402", "Professor Popsicle");
		}
		catch(Exception e) {
			// Should not throw exception (successful additions)
			fail("Should not have thrown an exception");
		}
	}
	
	/**
	 * Test get method
	 */
	@Test
	public void testGet() {
		try {
			// Add 2 courses
			manager.add("CMSC204", 911, 4, "401", "Professor Jelly");
			manager2.add("CMSC204", 912, 4, "402", "Professor Popsicle");
		}
		catch(Exception e) {
			// Should not throw exceptinos
			fail("Should not have thrown an exception");
		}
		
		// Get and assert ID's match
		assertEquals("CMSC204", manager.get(911).getID());
		assertEquals("CMSC204", manager2.get(912).getID());
		
		// Add another and assert
		manager.add("INT2020", 3349, 3, "511", "Professor Cheesecake");
		assertEquals("INT2020", manager.get(3349).getID());
	}
	
	/**
	 * Test read file method
	 */
	@Test
	public void testRead() {
		try {
			// Create input file
			File input = new File("file.txt");
			PrintWriter inFile = new PrintWriter(input);
			
			// Add courses
			inFile.println("ART2010 777 4 450W Professor Donut");
			inFile.print("INT2040 734 4 470E Professor Muffin");
			
			// Close file and read 
			inFile.close();
			manager.readFile(input);
			manager2.readFile(input);
			
			// Assert ID's match
			assertEquals("ART2010", manager.get(777).getID());
			assertEquals("INT2040", manager2.get(734).getID());
		}
		catch (Exception e) {
			// Should not throw exception
			fail("Should not have thrown an exception");
		}
	}
}
