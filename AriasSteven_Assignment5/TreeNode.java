/**
 * Represents a node in a Tree
 * @author Steven
 *
 * @param <T> Generic data type
 */
public class TreeNode<T> {
	// Store data and children
	protected T data;
	protected TreeNode<T> left;
	protected TreeNode<T> right;
	
	/**
	 * Initialize data
	 * @param data Given data
	 */
	public TreeNode(T data) {
		this.data = data;
	}
	
	/**
	 * Copy constructor
	 * @param node Node to be copied from
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}
	
	/**
	 * Return data
	 * @return Data
	 */
	public T getData() {
		return data;
	}
}
