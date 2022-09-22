import java.util.ArrayList;


/**
 * Linked implementation of a stack
 * @author Steven
 * @param <T> Object
 */

public class MyStack<T> implements StackInterface<T>{
	private Node top; // Top of stack
	private int size; // Size of stack
	private int MAX_SIZE; // Max capacity
	
	/**
	 * Set top to null and size to 0 
	 */
	public MyStack() {
		top = null;
		size = 0;
		MAX_SIZE = 10000;
	}
	
	/**
	 * Set default top and size.. set max size
	 * @param size Max size
	 */
	public MyStack(int size) {
		top = null;
		this.size = 0;
		MAX_SIZE = size;
	}
	
	
	@Override
	public T pop() throws StackUnderflowException{
		// If empty throw exception
		if (top == null) throw new StackUnderflowException();
		
		// Get top
		T item = top();
		
		// Set top node to the second node and decrement size
		top = top.getNextNode();
		size--;
		
		return item;
	}

	@Override
	public T top() throws StackUnderflowException {
		// If the top is null throw exception
		if (top == null) throw new StackUnderflowException();
		
		return top.data;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		// If stack is full throw exception
		if (isFull()) throw new StackOverflowException();
		
		// Create node with entry e and set it as top node
		Node node = new Node(e, top);
		top = node;
		size++;
		
		return true;
	}

	@Override
	public boolean isEmpty() {
		// Return if empty (top is null)
		return (top == null);
	}

	@Override
	public boolean isFull() {
		// Return if full (size meets capacity)
		return (size >= MAX_SIZE);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString(String delimiter) {
		// Create empty string and temp node (head)
		String str = "";
		Node temp = top;
		T[] arr = (T[]) new Object[size];
		
		for (int i = size - 1; i >= 0; i--) {
			arr[i] = temp.data;
			
			temp = temp.next;
		}
		
		// Enter in reverse order
		for (int i = 0; i <arr.length; i++) {
			str += arr[i];
			
			try {
				if (arr[i+1] != null)
					str += delimiter;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return str;
	}

	public String toString() {
		// Create empty string and temp node (head)
		String str = "";
		Node temp = top;
		
		// Create array
		T[] arr = (T[]) new Object[size];
		
		// Enter in reverse order
		for (int i = size - 1; i >= 0; i--) {
			arr[i] = temp.data;
			
			temp = temp.next;
		}
		
		// Display
		for (T t: arr) {
			str += t;
		}
		return str;
	}

	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		// Clear any other entries
		clear();
				
		// Create new array list 
		ArrayList<T> ls = new ArrayList<>(list.size());	
		
		for (int i = 0; i < list.size(); i++) {
			ls.add(list.get(i));
					
			push(ls.get(i));
		}
	}
	
	/**
	 * Clears the stack
	 */
	public void clear() {
		top = null;
		size = 0;
	}
	
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
