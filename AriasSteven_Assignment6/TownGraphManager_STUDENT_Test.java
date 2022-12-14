


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test TownGraphManager
 * @author Steven
 *
 */

public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		// Create graph manager
		  graph = new TownGraphManager();
		  town = new String[5];
		  
		  // Add towns
		  for (int i = 1; i < 5; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  // Add roads
		  graph.addRoad(town[1], town[2], 2, "Road_1");
		  graph.addRoad(town[2], town[4], 4, "Road_2");
		  graph.addRoad(town[2], town[3], 6, "Road_3");
		  graph.addRoad(town[3], town[4], 1, "Road_4");
		  graph.addRoad(town[4], town[1], 2, "Road_5");		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		Collections.sort(roads);
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		
		// Add road
		graph.addRoad(town[1], town[3], 1,"Road_6");
		roads = graph.allRoads();
		Collections.sort(roads);
		
		// Assert
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		assertEquals("Road_5", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		// Assert road
		assertEquals("Road_1", graph.getRoad(town[1], town[2]));
		assertEquals("Road_2", graph.getRoad(town[2], town[4]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_55"));
		
		// Add town and check
		graph.addTown("Town_55");
		assertEquals(true, graph.containsTown("Town_55"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_6"));
		graph.addTown("Town_6");
		ArrayList<String> path = graph.getPath(town[1],"Town_6");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		// Check if town exists
		assertEquals(true, graph.containsTown("Town_4"));
		assertEquals(false, graph.containsTown("Town_20"));
	}

	@Test
	public void testContainsRoadConnection() {
		// Check if edge exists
		assertEquals(true, graph.containsRoadConnection(town[2], town[4]));
		assertEquals(false, graph.containsRoadConnection(town[1], town[3]));
	}

	@Test
	public void testAllRoads() {
		// Get array and sort
		ArrayList<String> roads = graph.allRoads();
		Collections.sort(roads);
		
		// Check roads/edges
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		assertEquals("Road_5", roads.get(4));
	}

	@Test
	public void testDeleteRoadConnection() {		
		assertEquals(true, graph.containsRoadConnection(town[2], town[4]));
		
		// Delete edge and assert
		graph.deleteRoadConnection(town[2], town[4], "Road_2");
		assertEquals(false, graph.containsRoadConnection(town[2], town[4]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		
		// Delete vertex and assert
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		// Create array list of roads and sort
		ArrayList<String> roads = graph.allTowns();
		Collections.sort(roads);
		
		// Assert
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_2", roads.get(1));
		assertEquals("Town_3", roads.get(2));
		assertEquals("Town_4", roads.get(3));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[11]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_1 to Town_2 2 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_12 to Town_11 6 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[1],town[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
		  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[1],town[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
		  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
		  assertEquals("Town_10 via Road_8 to Town_9 4 mi",path.get(3).trim());
		  assertEquals("Town_9 via Road_7 to Town_6 3 mi",path.get(4).trim());

	}

}
