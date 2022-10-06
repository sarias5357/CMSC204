import java.util.Comparator;
import java.util.ListIterator;

/**
 * Sorted doubly linked list
 * @author Steven
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	// Comparator object
	private Comparator<T> comp;
	
	/**
	 * Initialize list with comparator
	 * @param compareableObject Comparator
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		super();
		comp = compareableObject;
	}
	
	/**
	 * Add a node
	 * @param data Data of node
	 */
	public void add(T data) {
		// Create new node
		Node node = new Node(data);
		
		// If no nodes, set head and tail to new node, increment size
		if (size == 0) {
			head = node;
			tail = node;
			size++;
		}
		else {
			// Set temp variable to iterate list
			Node temp = head;
			
			// Iterate through list while there are still nodes
			while (temp != null) {
				// If data on first half
				if(temp == head && comp.compare(data, temp.data) <= 0){
					// Create new node with data
					head = new Node(temp.data); 
					
					// Add new node between previous nodes
					Node old = head;
					head.next = old; 
					old.prev = head; 
					size++;
					break;
				}
				// If data in second half
				else if (temp != head && comp.compare(data, temp.data) <= 0){
					// Create new node with data
					Node node2 = new Node(temp.data);
					
					// Add new node in between previous nodes
					temp.prev.next = node2; 
					node2.prev = temp.prev; 
					node2.next = temp;
					temp.prev = node2; 
					size++;
					break;
				}
				// If tail
				if(temp.next == null){
					
					// Set new tail 
					Node newTail = new Node(temp.data);
					Node oldTail = tail;
					tail = newTail;
					oldTail.next = tail;
					tail.prev = oldTail;
					
					// Increment size and break out of loop
					size++;
					break;
				}
			}
		}
	}
	
	@Override
	public Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
	
	/**
	 * Unused
	 */
	public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Unused
	 */
	public void addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Unused
	 */
	public ListIterator<T> iterator() {
		return super.iterator();
	}
}