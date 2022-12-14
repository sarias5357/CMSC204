import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Road
 * @author Steven
 *
 */
public class Road_STUDENT_Test {
	private Road road;
	private Road road2;
	
	@Before
	public void setUp() throws Exception{
		road = new Road(new Town("town1"), new Town("town2"), "Road_1");
		road2 = new Road(new Town("town3"), new Town("town4"), 3, "Road_2");		
	}
	@After
	public void tearDown() throws Exception {
		road = null;
		road2 = null;
	}

	@Test
	public void testContains() {
		// Check if contains road
		assertEquals(true, road.contains(new Town("town1")));
		assertEquals(false, road2.contains(new Town("town5")));
	}
	
	@Test
	public void testDestination() {
		// Check destinations are correct
		assertEquals(new Town("town2"), road.getDestination());
		assertEquals(new Town("town4"), road2.getDestination());
	}
	
	@Test
	public void testName() {
		// Test names are correct
		assertEquals("Road_1", road.getName());
		assertEquals("Road_2", road2.getName());
	}
	
	@Test
	public void testSource() {
		// Check sources are correct
		assertEquals(new Town("town1"), road.getSource());
		assertEquals(new Town("town3"), road2.getSource());
	}
	
	@Test
	public void testWeight() {
		// Set and check weight
		road.setWeight(10);
		assertEquals(10, road.getWeight());
		assertEquals(3, road2.getWeight());
	}
}
