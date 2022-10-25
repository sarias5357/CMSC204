import java.io.IOException;
import java.util.ArrayList;

/**
 * Dictionary implementation (HashMap)
 * @author Steven
 *
 */

public class CourseDBStructure implements CourseDBStructureInterface {
	private HashMapNode[] list; // Array of linked lists (hash table)
	private int size; // Entries
	private int capacity; // Array length
	private static final double LOAD_FACTOR = 1.5; // Max load factor
	
	/**
	 * Represents a node in a linked list
	 * @author Steven
	 *
	 */
	class HashMapNode {
		private int key; // key (an integer, crn)
		private CourseDBElement value; // value, a CDE object
		private HashMapNode next; // next reference
		
		/**
		 * Initialize node
		 * @param o CDE object
		 */
		public HashMapNode(CourseDBElement o) {
			value = new CourseDBElement(o.getID(), o.getCRN(), o.getCredits(),o.getRoomNum(), o.getInstructor());
			
			key = value.getCRN();
			next = null;
		}
		
		/**
		 * Determine whether a given object is equal to this node
		 * @param e Node object
		 * @return Whether a given object is equal to this node
		 */
		public boolean equals(HashMapNode e) {
			if (this.value.getCRN() == e.value.getCRN() && this.value.getCredits() == e.value.getCredits()
					&& this.value.getID().equals(e.value.getID()) && this.value.getInstructor().equals(e.value.getInstructor())
							&& this.value.getRoomNum().equals(e.value.getRoomNum())) {
								return true;
							}
			return false;
		}
	}
	
	/**
	 * Initialize capacity and hashtable
	 * @param n Approximate number of classes
	 */
	public CourseDBStructure(int n) {
		// Calculate new N using prime numbers
		n /= LOAD_FACTOR;
		int k = (n - 3) / 4;
		int newN = (4 * k) + 3;
		
		do {
			k++;
			newN = (4 * k) + 3;
		} while (!isPrime(newN));
		
		// Store new capacity and create hashtable
		capacity = newN;
		list = new HashMapNode[capacity];
	}
	
	/**
	 * Testing
	 * @param str String
	 * @param size Size
	 */
	public CourseDBStructure(String str, int size) {
		this.capacity = size;
		list = new HashMapNode[capacity];
	}
	
	/**
	 * Determine whether or not a number is prime 
	 * @param n Number
	 * @return Prime or not
	 */
	public boolean isPrime(int n) {
		// If 1 or less, not prime
		if (n <= 1) return false;
		
		// Evaluate numbers from 2 to n - 1 and make sure n % i is prime 
		for (int i = 2; i < n; i++) 
			if (n % i == 0) return false;
		return true;
	}
	
	@Override
	public void add(CourseDBElement element) {
		// Create hashmap node and hash
		HashMapNode node = new HashMapNode(element);
		int index = hash(node.key);
		
		// Temporary node at head
		HashMapNode temp = list[index];
		// If object exists, exit
		while (temp != null) {
			if (temp.equals(node)) return;
			temp = temp.next;
		}
		
		// Add object to list
		size++;
		if (list[index] == null) list[index] = node; // If no list at index, create one (have head node at index)
		else {
			// Go through list and check if a course needs to be updated (same CRN given)
			temp = list[index];
			while (temp != null) {
				if (temp.value.getCRN() == node.value.getCRN()) {
					temp.value = node.value;
					return;
				}
				temp = temp.next;
			}
			
			// Append object to nodes in list
			temp = list[index];
			while (temp.next != null) {
				if (temp.value.equals(element)) return;
				temp = temp.next;
			}
			temp.next = node;	
		}
		
		// If hashtable is too big, rehash
		if ((size / capacity) >= LOAD_FACTOR) {
			rehash();
		}
	}
	
	/**
	 * Rehash table
	 */
	private void rehash() {
		// Get new list (double capacity) and store old one
		HashMapNode[] temp = list;
		list = new HashMapNode[2 * temp.length];
		size = 0;

		// Go through new table and add old table's elements (go through hashing again)
		for (HashMapNode e : temp) {
			while (e != null) {
				add(e.value);
			}
		}
	}
	
	/**
	 * Hash a key
	 * @param i Key
	 * @return Hashcode
	 */
	private int hash(int i) {
		// Hash
		String str = Integer.toString(i);
		int index = str.hashCode();
		index = Math.abs(index % capacity);
		return index;
	}
	@Override
	public CourseDBElement get(int crn) throws IOException {
		// Store index and get the head node at that index
		int index = hash(crn);
		HashMapNode temp = list[index];
		
		// Go through list and find element
		while (temp != null) {
			if (temp.key == crn) return temp.value; // If found return
			temp = temp.next;
		}
		throw new IOException(); // If not found throw exception
	}

	@Override
	public ArrayList<String> showAll() {
		// Create arraylist of strings
		ArrayList<String> al = new ArrayList<String>();

		// Iterate through hash table
		for (HashMapNode e : list) {
			// If null, continue
			if (e == null) continue;
			
			// Add course
			al.add("\nCourse:" + e.value.getID() + " CRN:" + e.value.getCRN()
			     + " Credits:" + e.value.getCredits()
			     + " Instructor:" + e.value.getInstructor() + " Room:" +
			     e.value.getRoomNum());
			continue;
		}
		// Return array list
		return al;
	}

	@Override
	public int getTableSize() {
		return capacity;
	}
}
