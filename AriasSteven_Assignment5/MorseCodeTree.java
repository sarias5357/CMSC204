import java.util.ArrayList;

/**
 * Implementation of a binary tree to hold morse code data
 * @author Steven 
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	// Root node
	private TreeNode<String> mainRoot;
	
	/**
	 * Build morse code tree
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	public TreeNode<String> getRoot() {
		return mainRoot;
	}

	public void setRoot(TreeNode<String> newNode) {
		mainRoot = new TreeNode<String>(newNode); // Set root
	}

	public void insert(String code, String result) {
		addNode(mainRoot, code, result); // Add node
	}

	public void addNode(TreeNode<String> root, String code, String letter) {
		// Store current root
		TreeNode<String> current = root;
		
		// If there is only one character left
		if (code.length() == 1) {
			// If period, set left child of current root to the tree node
			if (code.charAt(0) == '.') current.left = new TreeNode<String>(letter);
			// If dash, set right child of current root to the tree node
			else if (code.charAt(0) == '-') current.right = new TreeNode<String>(letter);
			// Otherwise exit (not valid)
			else return;
		}
		// If there is more than one character left
		else if (code.length() > 1) {
			// If period, set current to the next node on the left
			if (code.charAt(0) == '.') current = current.left;
			// If dash, set current to the next node on the right
			else if (code.charAt(0) == '-') current = current.right;
			
			// Decrease code by 1 and call method again with new code
			code = code.substring(1);
			addNode(current, code, letter);			
		}
	}

	public String fetch(String code) {
		return fetchNode(mainRoot, code); // Get node
	}
	
	public String fetchNode(TreeNode<String> root, String code) {
		// Store current root
		TreeNode<String> current = root;		
		// String used to store node data
		String h = "";
		
		// If only one character left
		if (code.length() == 1) {
			// Return left or right node's data based on character (period and dash, respectively)
			if (code.charAt(0) == '.') return current.left.data;
			else if (code.charAt(0) == '-') return current.right.data;
		}
		// If more than one character left
		else if (code.length() > 1) {
			// Call method again and store into h
			if (code.charAt(0) == '.') h = fetchNode(current.left, code.substring(1));
			else if (code.charAt(0) == '-') h= fetchNode(current.right, code.substring(1));
		}		
		// Return result
		return h;
	}

	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void buildTree() {
		// Set main root to blank
		mainRoot = new TreeNode<String>("");
		
		// Build rest of the tree
		levelTwoAndThreeInsert();
		levelFourInsert();
		levelFiveInsert();
	}

	public ArrayList<String> toArrayList() {
		ArrayList<String> al = new ArrayList<>(); // Create array list
		
		// Traverse through tree with array list and return after
		LNRoutputTraversal(mainRoot, al);
		return al;
	}

	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		// If root is null, exit
		if (root == null) return;
		
		// Traverse left
		LNRoutputTraversal(root.left, list);
		// Get data
		list.add(root.data);
		// Traverse right
		LNRoutputTraversal(root.right, list);
	}
	
	/**
	 * Build 2-3 levels
	 */
	public void levelTwoAndThreeInsert() {
		// Insert letters at corresponding locations
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
	}
	
	/**
	 * Build 4th level
	 */
	public void levelFourInsert() {
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
	}
	
	/**
	 * Build 5th level
	 */
	public void levelFiveInsert() {
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
}
