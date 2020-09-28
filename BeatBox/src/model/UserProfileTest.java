package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import model.UserProfile.Problem;

/**Class used to create, save and switch between user profiles 
 * 
 * @author Tobias Mauritzon
 * @since 2020-09-28
 */
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
	
	/**Test the creation of a UserProfile Object, since it is 
	 * very basic no assert condition fits.
	 * 
	 */
	@Test
	public void testCreateUser() {
		UserProfile test = new UserProfile("test");
	}
	
	/**Here we test adding a problem to history and then removing it again
	 * to check on its values.
	 * 
	 */
	@Test
	public void testAddProblemWhitModifiers() {
		userJoachim.addProblemToHistory("2+2", "3", "4", 10, 100, new Operator[] {Operator.ADD});
		Problem p = userJoachim.getHistory().removeFirst();
		String answer =p.getUserAnswer();
		assertTrue(answer.equals("3"));
	}
	
	/**Here we test adding a problem to history and then removing it again
	 * to check on its values. But we also check if our code can handle null 
	 * for a modifiers list.
	 * 
	 */
	@Test
	public void testAddProblemWhitOutModifiers() {
		userJoachim.addProblemToHistory("2*2", "3", "4", 10, 100, null);
		Problem p = userJoachim.getHistory().removeFirst();
		Operator[] modifiers = p.getModifiers();
		assertTrue(modifiers[2] == Operator.MUL);
		assertTrue(modifiers[0] == null);
		assertTrue(modifiers[1] == null);
	}
	
	/**Here we test the save function. 
	 * 
	 */
	@Test
	public void testSave() {
		UserProfile test = new UserProfile("test");
		test.addProblemToHistory("3+3", "6", "6", 10, 4, new Operator[] {Operator.ADD});
		assertDoesNotThrow(() -> {
			SaveManager.saveFile(test, "test.Save");
			SaveManager.deleteFile("test.Save");
		});	
	}
	
	/**Here we test the load function. 
	 * 
	 */
	@Test
	public void testLoad() {
		UserProfile test = new UserProfile("test");
		test.addProblemToHistory("3+3", "6", "6", 10, 4, new Operator[] {Operator.ADD});
		try {
			SaveManager.saveFile(test, "test.Save");
		} catch (IOException e) {}
		
		test = new UserProfile("test");
		test.addProblemToHistory("5-5", "2", "0", 0, 2, new Operator[] {Operator.SUB});
		
		try {
			test = (UserProfile) SaveManager.loadFile("test.Save");
		} catch (ClassNotFoundException | IOException e) {} 
		
		Problem p = test.getHistory().getFirst();
		
		assertTrue(p.getUserAnswer().equals("6"));
		assertTrue(p.getPoints() == 10);
		assertTrue(p.getTimeRequired() == 4);
		
	}
	
	/**Here we test if we can delete a saved UserProfile.
	 * 
	 */
	@Test
	public void testDeleteFileOk() {
		assertDoesNotThrow(() -> {
			SaveManager.saveFile(userTobias, userTobias.getName() + ".Save");
			SaveManager.deleteFile(userTobias.getName() + ".Save");
		});
	}
	/**Here we test if we can delete a UserProfile that does not exist.
	 * 
	 */
	@Test
	public void testDeleteFileFail() {
		assertThrows(IOException.class, () ->
		SaveManager.deleteFile(userTobias.getName() + ".Save")
	);
	}


}
