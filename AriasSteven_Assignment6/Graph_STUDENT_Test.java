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
 * Test graph
 * @author Steven 
 *
 */
public class Graph_STUDENT_Test {
	// Graph and array of Towns
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {	
		// Create graph
		 graph = new Graph();
		  town = new Town[5];
		  
		  // Add vertices
		  for (int i = 1; i < 5; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addVertex(town[i]);
		  }
		  
		  // Add edges
		  graph.addEdge(town[1], town[2], 2, "Road_1");
		  graph.addEdge(town[2], town[3], 4, "Road_2");
		  graph.addEdge(town[3], town[4], 6, "Road_3");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		// Compare edges
		assertEquals(new Road(town[1], town[2],2, "Road_1"), graph.getEdge(town[1], town[2]));
		assertEquals(new Road(town[2], town[3],1, "Road_2"), graph.getEdge(town[2], town[3]));
	}

	@Test
	public void testAddEdge() {
		// Add edge and compare
		assertEquals(false, graph.containsEdge(town[1], town[4]));
		graph.addEdge(town[1], town[4], 1, "Road_4");
		assertEquals(true, graph.containsEdge(town[1], town[4]));
	}

	@Test
	public void testAddVertex() {
		// Add vertex and compare
		Town newTown = new Town("Town_5");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		// Check if contain edge
		assertEquals(true, graph.containsEdge(town[2], town[3]));
		assertEquals(false, graph.containsEdge(town[1], town[4]));
	}

	@Test
	public void testContainsVertex() {
		// Check if contain vertex
		assertEquals(true, graph.containsVertex(new Town("Town_2")));
		assertEquals(false, graph.containsVertex(new Town("Town_12")));
	}

	@Test
	public void testEdgeSet() {
		// Get and sort array list of edge set
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads) {
			roadArrayList.add(road.getName());
		}
		Collections.sort(roadArrayList);

		// Assert
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_3", roadArrayList.get(2));
	}

	@Test
	public void testEdgesOf() {
		// Get and sort array list of edges of town[1]
		Set<Road> roads = graph.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		
		// Assert
		assertEquals("Road_1", roadArrayList.get(0));
	}
	
	@Test
	public void testRemoveEdge() {
		// Remove and compare
		assertEquals(true, graph.containsEdge(town[2], town[3]));
		graph.removeEdge(town[2], town[3], 4, "Road_2");
		assertEquals(false, graph.containsEdge(town[2], town[3]));
	}
	
	@Test
	public void testRemoveVertex() {
		// Remove and compare
		assertEquals(true, graph.containsVertex(town[1]));
		graph.removeVertex(town[1]);
		assertEquals(false, graph.containsVertex(town[1]));
	}

	@Test
	public void testVertexSet() {
		// Iterate through set and check if good
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[1]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[3]));
		assertEquals(true, roads.contains(town[3]));
		assertEquals(true, roads.contains(town[4]));
	}

	 @Test
	  public void testTown_1ToTown_11() {
		  String beginTown = "Town_1", endTown = "Town_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_1 to Town_2 2 mi",path.get(0).trim());
			  assertEquals("Town_2 via Road_12 to Town_11 6 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testTown1ToTown_10() {
		  String beginTown = "Town_1", endTown = "Town_10";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
			  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
			  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void testTown_4ToTown_11() {
		  String beginTown = "Town_4", endTown = "Town_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_4 via Road_6 to Town_8 3 mi",path.get(0).trim());
			  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(1).trim());
			  assertEquals("Town_10 via Road_11 to Town_11 3 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}
