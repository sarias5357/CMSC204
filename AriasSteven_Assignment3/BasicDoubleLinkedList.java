import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Doubly linked list implementation
 * @author Steven
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> {
	// Store head, tail, and size
	protected Node head;
	protected Node tail;
	protected int size;
	
	/**
	 * Initialize doubly linked list with null head and tail and 0 size
	 */
	BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Return size of linked list
	 * @return Size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Append node to list
	 * @param data Data of node
	 */
	public void addToEnd(T data) {
		// If empty, create the new node and make it the head/tail
		if (head == null) {
			head = new Node(data);
			tail = head;
			size++;
		}
		else {
			// Create node and set as tail
			Node node = new Node(data);
			Node temp = tail;
			tail = node;
			temp.next = tail;
			tail.prev = temp;
			
			// Increment size
			size++;
		}
	}
	
	/**
	 * Append node to front of list
	 * @param data Data of node
	 */
	public void addToFront(T data) {
		// If empty, create the new node and make it the head/tail
		if (head == null) {
			head = new Node(data);
			tail = head;
			size++;
		}
		else {
			// Create node and set as head
			Node node = new Node(data);
			Node temp = head;
			head = node;
			head.next = temp;
			temp.prev = head;
			
			// Increment size
			size++;
		}
	}
	
	/**
	 * Return head
	 * @return First node
	 */
	public T getFirst() {
		return head.data;
	}
	
	/**
	 * Return tail
	 * @return Last node
	 */
	public T getLast() {
		if (tail == null) return null;
		return tail.data;
	}
	
	/**
	 * Return iterator 
	 * @return Iterator
	 */
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * Remove a node with a specific data
	 * @param targetData Data of node
	 * @param comparator Comparator object
	 * @return Removed node
	 */
	public Node remove(T targetData, Comparator<T> comparator) {
		if (size == 0) return null; // If no node, return null
		
		// Set 2 temp nodes to head ref
		Node temp = head;
		
		// Iterate through list
		for (int i = 0; i < size; i++) {
			// If node with specified data is found
			if (comparator.compare(targetData, temp.data) == 0) {
				// If it's the head node
				if (i == 0) {
					if (head == null) return null;
					// Remove head by setting it to the next node
					head = temp.next;
					head.prev = null;
					
					// Decrement size and return temp node
					size--;
					return temp;
				}
				
				// If it's the tail node
				else if (i == (size - 1)) {
					if (tail == null) return null;
					// Remove node by setting it to the previous node
					temp = tail;
					tail = temp.prev;
					tail.next = null;
					
					// Decrement size and return temp node
					size--;
					return temp;
				}
				// If any other node
				else {
					// Get out of loop (temp is still saved as that node)
					break;
				}
			}
			temp = temp.next;
		}
		// Set previous node to point to node after the to-be-removed node
		Node prev1 = temp.prev;
		Node nex = temp.next;
		prev1.next = nex;
		
		// Set next node's previous node to point to the to-be-removed node's previous node
		nex.prev = prev1;
		
		// Decrement size and trash temp
		size--;

		return temp;
	}
	
	/**
	 * Return and remove head
	 * @return Head
	 */
	public T retrieveFirstElement() {
		// If size is 0 return null
		if (size == 0) return null;
		
		// Set temp node to head
		Node temp = head;
		
		// If size consists only of the head, make it null (and tail)
		if (size == 1) { 
			head = null;
			tail = null;
		}
		// Else remove head
		else {
			head = head.next;
		}
		size--;
		return temp.data;
	}
	
	/**
	 * Return and remove tail
	 * @return Tail
	 */
	public T retrieveLastElement() {
		// If size is 0 return null
		if (size == 0) return null;
		
		// Set temp node to tail
		Node temp = tail;
		
		// If size consists only of the head, make it null (and tail)
		if (size == 1) { 
			head = null;
			tail = null;
		}
		else {
			// Else remove tail
			tail.prev.next = null;
			tail = tail.prev;
		}
		size--;
		return temp.data;
	}
	
	/**
	 * List to ArrayList
	 * @return ArrayList representation of this List
	 */
	public ArrayList<T> toArrayList() {
		// Create new array list and node referencing the head node
		ArrayList<T> ls = new ArrayList<T>();
		Node temp = head;
		
		// Traverse through list
		for (int i = 0; i < size; i++) {
			// Add each element's data and go to next
			ls.add(temp.data);
			temp = temp.next;
		}
		return ls;
	}
	
	/**
	 * Represents a node in the list
	 * @author Steven
	 *
	 */
	public class Node {
		// Store data and previous/next nodes
		T data;
		Node prev;
		Node next;
		
		/**
		 * Initialize node with data
		 * @param data data
		 */
		Node (T data) {
			this.data = data;
			prev = null;
			next = null;
		}
	}
	
	/**
	 * Iterator for list
	 * @author Steven
	 *
	 */
	public class DoubleLinkedListIterator implements ListIterator<T> {
		// Store current node and index
		private Node current;
		private int index;
		
		/**
		 * Initialize current node at start and index at 0
		 */
		DoubleLinkedListIterator() {
			current = head;
			index = 0;
		}
		
		public boolean hasNext() {
			// Return if index has not reached tail
			return index < size;
		}
		public T next() {
			// If no next, throw exception
			if (!hasNext()) throw new NoSuchElementException();
			else {
				// Set temp to current's data
				T temp = current.data;
				
				// Set current to next node and increase index
				current = current.next;
				index++;
				return temp;
			}
		}
		
		public boolean hasPrevious() {
			// Return whether there are nodes
			return index > 0;
		}
		public T previous() {
			// If no previous, throw exception
			if (!hasPrevious()) throw new NoSuchElementException();
			
			// If current is null, set it to the tail and return the tail's data
			if (current == null) {
				current = tail;
				return current.data;
			}
			
			// If current is the head, throw exception (no tail)
			if (current == head) throw new NoSuchElementException();
			
			// Set current to it's previous node and return the datat
			current = current.prev;
			index--;
			return current.data;
			
		}
		
		/**
		 * Unused
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * Unused
		 */
		public void add(T arg0) {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * Unused
		 */
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * Unused
		 */
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * Unused
		 */
		public void set(T arg0) {
			throw new UnsupportedOperationException();
		}
	}
}
