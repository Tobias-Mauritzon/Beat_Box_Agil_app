package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.UserProfile.Problem;

public class UserProfileTest {
	private UserProfile userJoachim;
	private UserProfile userTobias;
	
	/**
	 * 	Before each test reinitialize generator object
	 */
	@Before
	public void setup() {
		userJoachim = new UserProfile("Joachim");
		userTobias = new UserProfile("Tobias");
	}
	
	@Test
	public void testCreateUser() {
		UserProfile test = new UserProfile("test");
	}
	
	@Test
	public void testAddProblemWhitModifiers() {
		userJoachim.addProblemToHistory("2+2", "3", "4", 10, 100, new Operator[] {Operator.ADD});
		Problem p = userJoachim.getHistory().removeFirst();
		String answer =p.getUserAnswer();
		assertTrue(answer.equals("3"));
	}
	
	@Test
	public void testAddProblemWhitOutModifiers() {
		userJoachim.addProblemToHistory("2*2", "3", "4", 10, 100, null);
		Problem p = userJoachim.getHistory().removeFirst();
		Operator[] modifiers = p.getModifiers();
		assertTrue(modifiers[2] == Operator.MUL);
		assertTrue(modifiers[0] == null);
		assertTrue(modifiers[1] == null);
	}
	
	@Test
	public void testSave() {
		UserProfile test = new UserProfile("test");
		test.addProblemToHistory("3+3", "6", "6", 10, 4, new Operator[] {Operator.ADD});
		assertTrue(test.saveProfile(test));
		
	}
	
	/**May fail if it's the first time you run the test 
	 * since test methods are run in a random order
	 * 
	 */
	@Test
	public void testLoad() {
		UserProfile test = new UserProfile("test");
		test.addProblemToHistory("5-5", "2", "0", 0, 2, new Operator[] {Operator.SUB});
		test = test.loadProfile(test.getName(), test);
		Problem p = test.getHistory().getFirst();
		assertTrue(p.getUserAnswer().equals("6"));
		assertTrue(p.getPoints() == 10);
		assertTrue(p.getTimeRequierd() == 4);
		
	}

}
