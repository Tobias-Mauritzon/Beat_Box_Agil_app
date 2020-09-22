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
		String answer =p.getAwnser();
		assertTrue(answer.equals("3"));
	}
	
	@Test
	public void testAddProblemWhitOutModifiers() {
		userJoachim.addProblemToHistory("2*2", "3", "4", 10, 100, null);
		Problem p = userJoachim.getHistory().removeFirst();
		Operator[] modifiers = p.getModifiers();
		assertTrue(modifiers[2] == Operator.MUL);
	}

}
