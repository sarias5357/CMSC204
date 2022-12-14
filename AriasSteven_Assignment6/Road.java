/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Represents a road in a network of towns and roads
 * @author Steven
 */
public class Road implements Comparable<Road> {
    private String name; // Road name
    private Town source, destination; // Source town and destination town
    private int weight; // Weight/distance of road
    
    /**
     * Set values
     * @param source Source town
     * @param destination Destination town
     * @param weight Weight of road
     * @param name Road name
     */
    public Road(Town source, Town destination, int weight, String name) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }
    
    /**
     * Set values (default weight = 1)
     * @param source Source town
     * @param destination Destination town
     * @param name Road name
     */
    public Road(Town source, Town destination, String name) {
        this.source = source;
        this.destination = destination;
        this.weight = 1;
        this.name = name;
    }
    
    /**
     * Copy constructor
     * @param o Road object
     */
    public Road(Road o) {
        this.source = o.source;
        this.destination = o.destination;
        this.weight = o.weight;
        this.name = o.name;
    }
    /**
     * Set road name
     * @param name road name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Set road weight
     * @param w Road weight
     */
    public void setWeight(int w) {
        weight = w;
    }
    
    /**
     * Get road name
     * @return road name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get road weight
     * @return road weight
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Get destination town
     * @return destination town 
     */
    public Town getDestination() {
        return new Town(destination);
    }
    
    /**
     * Get source town
     * @return source town
     */
    public Town getSource() {
        return new Town(source);
    }
    
    @Override
    public String toString() {
        return name;
    }
    @Override
    public int compareTo(Road o) {
        // If names are equal return 0, else return positive or negative int
        if (this.name.equals(o.getName())) return 0;
        else return this.name.compareTo(o.getName());
    }
    public boolean contains(Town t) {
        // Return whether or not one of the vertices equal a given Town t
        return (source.equals(t) || destination.equals(t));
    }
    @Override
    public boolean equals(Object r) { 
    	if (r == this) return true;
    	
        if (this.destination.equals(((Road)r).getDestination()) || this.destination.equals(((Road)r).getSource())) 
        	if (this.source.equals(((Road)r).getSource()) || this.source.equals(((Road)r).getDestination())) return true;        
        return false;
    }
}
