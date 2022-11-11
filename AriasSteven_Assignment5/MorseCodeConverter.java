import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Convert English to Morse Code
 * @author Steven
 */
public class MorseCodeConverter {	
	
	/**
	 * Print current tree
	 * @return String representation of tree
	 */
	public static String printTree() {
		// Build tree and store array list of tree
		MorseCodeTree tree = new MorseCodeTree();
		ArrayList<String> ar = tree.toArrayList();
		
		// Store resulting string and index of loop
		String res = "";
		int index = 0;
		
		// Loop through letters in tree
		for (String letter : ar) {
			// Add letter to resulting string
			res += letter;
			
			// If last letter, get out of loop. Else add a space
			if (index == ar.size() - 1) break;
			else res += " ";
			
			// Increment index
			index++;
		}
		
		// Return result
		return res;
	}
	
	/**
	 * Convert a given morse code into English
	 * @param code Morse code
	 * @return English conversion
	 */
	public static String convertToEnglish(String code) {
		// Create tree
		MorseCodeTree tree = new MorseCodeTree();

		// Split code by words and create empty resulting string
		String[] arr = code.split("/");	
		String res = "";
		
		// Iterate through array of words
		for (int i = 0; i < arr.length; i++) {
			// Split the word at arr[i] into characters
			String[] str = arr[i].trim().split("\\s+");
			
			// For each character, add the corresponding letter to the resulting string
			for (String x : str) {
				res += tree.fetch(x);
			}
			// If not last node, print space after
			if (i != arr.length - 1)
				res += " ";
		}
		
		// Return resulting string
		return res;
	}
	
	/**
	 * Get a file of morse code text and convert to English
	 * @param codeFile File of morse code
	 * @return English translation
	 * @throws FileNotFoundException File not found
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		// Pass file into scanner and get line of code
		Scanner scan = new Scanner(codeFile);
		String res = scan.nextLine();
		
		// Close scanner and call String version of method
		scan.close();		
		return convertToEnglish(res);		
	}
}
