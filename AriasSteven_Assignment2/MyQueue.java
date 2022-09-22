import java.util.ArrayList;

/**
 * Linked implementation of queue
 * @author Steven
 * @param <T> Object
 */

public final class MyQueue<T> implements QueueInterface<T>{
	// Head and tail nodes
	private Node first;
	private Node last;
	
	// Number of entries and max number of entries
	private int size;
	private int MAX_SIZE;
	
	/**
	 * Set first and last nodes to null
	 */
	public MyQueue() {
		first = null;
		last = null;
		size = 0;
		MAX_SIZE = 10000;
	}
	
	/**
	 * Set nodes to null and set a size
	 * @param size Size of queue
	 */
	public MyQueue(int size) {
		first = null;
		last = null;
		this.size = 0;
		MAX_SIZE = size;
	}
	
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		// Create a null with the entry
		Node node = new Node(e, null);
		
		// If the queue is full throw an exception
		if (isFull())
			throw new QueueOverflowException();
		else {
			// If the queue is empty set the first node as the new node
			if (isEmpty()) 
				first = node;
			else 
				// If not, set last node to reference the new node
				last.setNextNode(node);
			
			// Set the last node as the new node and increment entries
			last = node;
			size++;
			return true;
		}
	}

	@Override
	public boolean isEmpty() {
		// Return whether the first and last nodes are null
		return (first== null) && (last==null);
	}

	@Override
	public boolean isFull() {
		// Return whether the numberOfEntries is over at max capacity
		return (size >= MAX_SIZE);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		// Create object to reference front node data
		T front = null;
		
		// If not empty
		if (!isEmpty()) {
			// Set front to the first node data
			front = first.data;
			// Set the first node to the next node
			first = first.next;
				
			// If empty, make last null too
			if (first == null)
				last = null;
			size--;
		}
		else throw new QueueUnderflowException();
		// Return removed object (if any)
		return front;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString(String delimiter) {
		// Create empty string and temp node (head)
		String str = "";
		Node temp = first;
		
		// Traverse through queue
		while (temp != null) {
			// Add data and delimiter to string
			str += temp.getData();
			if (temp.next != null)
				str += delimiter;
			
			// Go to next node
			temp = temp.next;
		}
		return str;
	}
	
	public String toString() {
		// Create empty string and temp node (head)
		String str = "";
		Node temp = first;
		
		// Traverse through queue
		while (temp != null) {
			// Add data to string
			str += temp.getData();
			// Go to next node
			temp = temp.next;
		}
		return str;
	}

	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		// Clear any other entries
		clear();
		
		// Create new array list 
		ArrayList<T> ls = new ArrayList<>(list.size());	
		
		for (int i = 0; i < list.size(); i++) {
			ls.add(list.get(i));
			
			enqueue(ls.get(i));
		}
	}
	
	/**
	 * Clear queue
	 */
	public void clear() {
		first = null;
		last = null;
	}
	
	/**
	 * Represents a node in the queue
	 * @author Steven
	 *
	 */
	
	private class Node {
		// Store data and reference to next node
		private T data;
		private Node next;
		
		/**
		 * Set data
		 * @param data Object
		 */
		private Node(T data) {
			this.data = data;
			next = null;
		}
		
		/**
		 * Set data and reference to next node
		 * @param data Object
		 * @param next Reference to next node
		 */
		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Return data
		 * @return Object data
		 */
		private T getData() {
			return data;
		}
		
		/**
		 * Set data
		 * @param data Object data
		 */
		private void setData(T data) {
			this.data = data;
		}
		
		/**
		 * Get reference to next node
		 * @return Next node
		 */
		private Node getNextNode() {
			return next;
		}
		
		/**
		 * Set reference to next node 
		 * @param node Next node
		 */
		private void setNextNode(Node node) {
			next = node;
		}
	}
}
