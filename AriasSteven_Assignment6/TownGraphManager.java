import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds and performs operations on Graph object
 * @author Steven
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{
	private Graph graph;
	
	/**
	 * Initialize graph
	 */
	public TownGraphManager() {
		graph = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		// If null return false
		if (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) == null) return false;
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		// Iterate through Vertex set and get town
		for (Town i : graph.vertexSet()) {
			if (i.getName().equals(name)) return i;
		}
		
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		// Create ArrayList
		ArrayList<String> list = new ArrayList<>();
		
		// Iterate through edge set and add to list
		for (Road i : graph.edgeSet()) {
			list.add(i.getName());
		}
		
		// Sort and return
		Collections.sort(list);
		return list;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		// // If null return false
		if (graph.removeEdge(new Town(town1), new Town(town2), 0, road) == null) return false;
		return true;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		// Create ArrayList
		ArrayList<String> list = new ArrayList<>();
		
		// Iterate through edge set and add to list
		for (Town i : graph.vertexSet()) {
			list.add(i.getName());
		}
		// Sort and return
		Collections.sort(list);
		return list;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
		return null;
	}

}
