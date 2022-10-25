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
		// If no nodes in list, make node head and tail
		if (size == 0) {
			head = new Node(data);
			tail = head;
			size++;
			return;
		}
		else {
			// If value is less than the head node's value
			if (comp.compare(data, head.data) < 0) {
				// Create new node with data
				Node node = new Node(data);
				
				// Set to head
				node.prev = null;
				node.next = head;
				head.prev = node;
				head = node;
				
				// Increment size and exit
				size++;
				return;
			}
			else {
				// Traverse through list
				Node curr = head.next;
				while (curr != null) {
					// If found spot insert before current
					if (comp.compare(data, curr.data) <= 0) {
						// Create new node
						Node node = new Node(data);
						
						// Insert before current node
						node.next = curr;
						node.prev = curr.prev;
						curr.prev.next = node;
						curr.prev = node;
						
						// Increment size and exit
						size++;
						return;
					}
					// Go to next element
					curr = curr.next;
				}
			}
		}
		
		// If none, insert at tail
		Node node = new Node(data);
		node.prev = tail;
		tail.next = node;
		tail = node;
		size++;
		return;
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