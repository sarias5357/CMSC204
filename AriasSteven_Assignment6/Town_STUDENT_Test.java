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
 * Test Town
 * @author Steven
 *
 */
public class Town_STUDENT_Test {
	private Town town;
	private Town town2;
	
	@Before
	public void setUp() throws Exception{
		town = new Town("Annapolis");		
		town2 = new Town("Laurel");
	}
	@After
	public void tearDown() throws Exception {
		town = null;
		town2 = null;
	}

	@Test
	public void testName() {
		// Assert names
		assertEquals("Annapolis", town.getName());
		assertEquals("Laurel", town2.getName());
	}
	
	@Test
	public void testAdjacent() {
		// Check if adjacent works
		town.setAdjacent(town2, 3);
		town2.setAdjacent(town, 0);
		assertEquals((Integer)3, town.getAdjacentTowns().get(town2));
		assertEquals("Annapolis", town2.getAdjacentTowns().keySet().iterator().next().getName());
	}
}
