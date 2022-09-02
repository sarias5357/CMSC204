import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit Test
 * @author Steven
 *
 */

class GradeBookTester {
	// GradeBook objects
	private GradeBook g1;
	private GradeBook g2;
	
	/**
	 * setUp method
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Instantiate GradeBook objects
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		
		// Use addScore method to add scores
		g1.addScore(50);
		g1.addScore(94);
		g2.addScore(88);
		g2.addScore(76);
	}
	
	/**
	 * tearDown method
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		// Set objects to null
		g1 = null;
		g2 = null;
	}

	/**
	 * Compare the contents of the "scores" array to what is expected to be in it
	 * Compare the value of scoresSize to what is expected to be the value
	 */
	@Test
	void addScoreTest() {
		assertTrue(g1.toString().equals("50.0 94.0 "));
		assertTrue(g2.toString().equals("88.0 76.0 "));
		assertEquals(2, g1.getScoresSize(), .0001);
		assertEquals(2, g2.getScoresSize(), .0001);
	}
	
	/**
	 * Compare what is returned by sum() to the expected sum of the scores entered
	 */
	@Test
	void testSum() {
		assertEquals(144, g1.sum(), .0001);
		assertEquals(164, g2.sum(), .0001);
	}
	
	/**
	 * Compare what is returned by minimum() to the expected minimum of the scores entered
	 */
	@Test
	void testMinimum() {
		assertEquals(50, g1.minimum(), .001);
		assertEquals(76, g2.minimum(), .001);
	}
	
	/**
	 * Compare what is returned by finalScore() to the expected finalScore of the scores entered
	 */
	@Test
	void testFinalScore() {
		assertEquals(94, g1.finalScore(), .0001);
		assertEquals(88, g2.finalScore(), .0001);
	}

}
