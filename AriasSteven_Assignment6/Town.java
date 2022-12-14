
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Represents a Town in a network of towns of roads
 * @author Steven
 */
public class Town implements Comparable<Town> {
    private String name; // Town name
    private HashMap<Town, Integer> adjacent; // Adjacent town dictionary
    
    /**
     * Sets name and default hashMap
     * @param name Town name
     */
    public Town(String name) {
        this.name = name;
        adjacent = new HashMap<>();
    }
    
    /**
     * Copy constructor
     * @param copy Another town object
     */
    public Town(Town copy) {
        this.name = copy.getName();
        adjacent = copy.getAdjacentTowns();
    }
    
    /**
     * Return map of adjacent towns
     * @return Map of adjacent towns
     */
    public HashMap<Town, Integer> getAdjacentTowns() {
        // Create new HashMap(deep copy)
        HashMap<Town, Integer> newDict = new HashMap<>();
        
        // Copy all values
        for (Town i : adjacent.keySet()) {
            newDict.put(i, adjacent.get(i));
        }
        
        // Return new map
        return newDict;
    }
    
    public void setAdjacent(Town i, int weight) {
    	adjacent.put(i, weight);
    }
    public void removeAdjacent(Town i) {
    	adjacent.remove(i);
    }
    
    /**
     * Return town name
     * @return town name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set town name
     * @param name town name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    @Override
    public int compareTo(Town o) {
        // If objects are equal: return 0, else return positive or negative int
        if (this.name.equals(o.getName())) return 0;
        else return this.name.compareTo(o.getName());
    }
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        Town other = (Town) obj; // Cast object
        
        // Compare
        return (this.name.equals(other.getName()));
    }
}
