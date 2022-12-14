
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Graph implementation using two sets
 * @author Steven
 */
public class Graph implements GraphInterface<Town, Road> {
    private LinkedHashSet<Town> towns; // Set of towns (vertices)
    private LinkedHashSet<Road> roads; // Set of roads (edges)
    private ArrayList<String> shortest; // Shortest path
    
    /**
     * Initialize sets
     */
    public Graph() {
        towns = new LinkedHashSet<>();
        roads = new LinkedHashSet<>();
    }
    
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
    	Road r = new Road(sourceVertex, destinationVertex, 0, "ex");
    	
    	// Find edge and return if found
    	for (Road i : roads) {
    		if (i.equals(r)) return i;
    	}
    	return null;
    }

    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
    	// Set adjacent vertices
        sourceVertex.setAdjacent(destinationVertex, weight);
        destinationVertex.setAdjacent(sourceVertex, weight);
        
        // If not already added to vertex set, add it
    	if (!towns.contains(sourceVertex))
        	addVertex(sourceVertex);
        if (!towns.contains(destinationVertex))
        	addVertex(destinationVertex);
        
        
        // Add road
    	Road road = new Road(sourceVertex, destinationVertex, weight, description);
    	Road road2 = new Road(destinationVertex, sourceVertex, weight, description);
    	
        roads.add(road);
        roads.add(road2);
        return road;
    }

    @Override
    public boolean addVertex(Town v) {
    	return towns.add(v);
    }
    
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
    	Road r = new Road(sourceVertex, destinationVertex, 0, "ex");
    	
    	// Iterate through roads and find whether or not the given road is found
    	for (Road i : roads) {
    		if (i.equals(r)) return true;
    	}
    	return false;
    }

    @Override
    public boolean containsVertex(Town v) {
    	return towns.contains(v);
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
    	// Set for edges
    	Set<Road> set = new HashSet<>();
    	boolean included; // Whether or not this road is already in the set
    	
    	// Iterate through adjacent towns
    	for (Town i : vertex.getAdjacentTowns().keySet()) {
    		included = false;
    		
    		// Find road
    		Road road = new Road(vertex, i, 0, "Dex");
    		Road road2 = new Road(i, vertex, 0, "ex");
    		
    		for (Road r : roads) {    			
    			if (r.equals(road) || r.equals(road2)) {
    				for (Road s : set) {
    					// If road included, do not add 
    					if (s.equals(road) || s.equals(road2))
    						included = true;
    				}
    				if (!included) set.add(r);
    			}
    		}
    	}
    	return set;
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
    	Road edge1 = new Road(sourceVertex, destinationVertex, weight, description);
    	Road edge2 = new Road(destinationVertex, sourceVertex, weight, description);
    	
    	// Remove road
    	for (Road i : roads) {
    		if (i.equals(edge1)) {
    			roads.remove(i);
    			break;
    		}
    	}
    	for (Road i : roads) {
    		if (i.equals(edge2)) {
    			roads.remove(i);
    			break;
    		}
    	}
    	
    	// Return removed edge
    	return edge1;
    }
    
    @Override
    public boolean removeVertex(Town v) {
    	for (Town i : towns) {
    		if (i.getAdjacentTowns().containsKey(v))
    			i.removeAdjacent(v);
    	}
    	return towns.remove(v);
    }

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
    /** // Initialize shortest path list with destination vertex
        shortest = new ArrayList<>();
        shortest.add(destinationVertex.getName());
        dijkstraShortestPath(sourceVertex);    	
    	
        // Reverse list and return
    	Collections.reverse(shortest);
    	for (String i : shortest) {
    		System.out.println(i);
    	} */
        return shortest; 
    } 

    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
    /** HashMap<String, Integer> cheapest = new HashMap<>();
        HashMap<String, String> cheapest_previous = new HashMap<>();
        HashMap<String, Integer> visited = new HashMap<>();
        ArrayList<Town> unvisited = new ArrayList<>();

        cheapest.put(sourceVertex.getName(), 0);
        Town current = sourceVertex;
        
        while (current != null) {
        	visited.put(current.getName(), 0);
        	unvisited.remove(current);
        	
        	for (Town i : current.getAdjacentTowns().keySet()) {
        		Town adjacent = new Town(i);
        		int distance = current.getAdjacentTowns().get(i);
        		
        		if (!visited.containsKey(adjacent.getName())) {
        			unvisited.add(adjacent);
        		}
        		
        		int currDist = cheapest.get(current.getName()) + distance;
        		if (!cheapest.containsKey(adjacent.getName()) ||
        				currDist < cheapest.get(adjacent.getName())) {
        			cheapest.put(adjacent.getName(), currDist);
        			cheapest_previous.put(adjacent.getName(), current.getName());
        		}
        	}
        	
        	if (unvisited.size() >= 1)
        		current = unvisited.get(unvisited.size() - 1);
        }
        
        String cur = "";
        for (String i : shortest) {
        	cur = i;
        	break;
        }
        
        while (!cur.equals(sourceVertex.getName())) {
        	shortest.add(cur);
        	cur = cheapest_previous.get(cur);
        } */
    } 

    @Override
    public Set<Road> edgeSet() {
    	// Create set
        Set<Road> set = new HashSet<>();
        boolean included;
        
        // Iterate through edges
    	for (Road r : roads) {
    		included = false;
    		
    		// Add road
    		for (Road s : set) {
    			if (s.equals(r) || r.equals(s)) {
    				included = true;
    				break;
    			}
    		}
    		
    		// Do not add if road is included
    		if (included) continue;
    		else set.add(r);
    	}
    	// Return set
    	return set;
    } 

    @Override
    public Set<Town> vertexSet() {
    	// Create set
    	Set<Town> set = new HashSet<>();
        
    	// Add vertices
    	for (Town t : towns) {
    		set.add(t);
    	}
    	return set;
    }
}
