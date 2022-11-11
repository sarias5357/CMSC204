import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student JUnit test
 * @author Steven
 *
 */
public class MorseCodeConverter_TEST {
	private MorseCodeTree tree; // Test tree
	
	/**
	 * Creates MorseCodeTree object before each test
	 * @throws Exception Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Build tree
		tree = new MorseCodeTree();
	}
	
	/**
	 * Destroys MorseCodeTree object after each test
	 * @throws Exception Exception
	 */
	@After
	public void tearDown() throws Exception {
		// Set tree to null
		tree = null;
	}
	
	/**
	 * Test tree's fetch and fetchNode methods
	 */
	@Test
	public void testFetch() {
		/*
		 * Assert all values are equal to their correct value
		 */
		assertEquals(tree.fetch("-"), "t");
		assertEquals(tree.fetch("-."), "n");
		assertEquals(tree.fetch("..-"), "u");
		assertEquals(tree.fetch("."), "e");
		assertEquals(tree.fetch("..."), "s");
		assertEquals(tree.fetch("---"), "o");
		assertEquals(tree.fetch("-.-"), "k");
	}
	
	/**
	 * Test tree's insert method
	 */
	@Test
	public void testInsert() {
		// Test insert method with string 
		tree.insert(".....", "Test String");
		assertEquals("Test String", tree.fetch("....."));
	}
	
	/**
	 * Test tree's toArrayList and LNR traversal methods
	 */
	@Test
	public void testToArrayList() {
		// Create array list
		ArrayList<String> ls = tree.toArrayList();
		
		// Assert traversal added letters to ArrayList correctly
		assertEquals(ls.get(0), "h");
		assertEquals(ls.get(1), "s");
		assertEquals(ls.get(2), "v");
		assertEquals(ls.get(3), "i");
		assertEquals(ls.get(10), "p");
	}
	
	/**
	 * Test MorseCodeConverter's static convertToEnglish(String) method
	 */
	@Test
	public void testConvertToEnglishString() {
		// Test with morse code
		String test = MorseCodeConverter.convertToEnglish("-.. --- . ... "
				+ "/ - .... .. ... / .--. .- ... ... / - .... . / - . ... -");
		assertEquals("does this pass the test", test);
	}
	
	/**
	 * Test MorseCodeConverter's static convertToEnglish(File) method
	 */
	@Test
	public void testConvertToEnglishFile() {
		// Use given file
		File file = new File("src/testFile.txt");
		
		// Assert if no exception is thrown, catch if exception is thrown (fail)
		try {
			assertEquals("i love binary trees", MorseCodeConverter.convertToEnglish(file));
		}
		catch (FileNotFoundException e) {
			assertTrue("This was not supposed to be thrown", false);
		}
	}
}
