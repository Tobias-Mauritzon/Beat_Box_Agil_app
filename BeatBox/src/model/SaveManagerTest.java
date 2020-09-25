package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class SaveManagerTest {

	/**
	 * Here we test the save function
	 * Expected Success 
	 */
	@Test
	@Order(1)
	public void testSaveSuccess() {
		UserProfile test = new UserProfile("testSaveSuccess");
		assertDoesNotThrow(() ->
			SaveManager.saveFile(test, "testSaveSuccess.Save")
		);
	}
	
	/**
	 * Here we test the save function
	 * Expected Failure 
	 */
	@Test
	@Order(2)
	public void testSaveFailure () {
		UserProfile test = new UserProfile("testSaveFailure");
		assertThrows(IOException.class, () ->
			SaveManager.saveFile(test, "/fdss/testSaveFailure.Save")
		);
	}
	
	/**
	 * Here we test the load function
	 * Expected Success
	 */
	@Test
	@Order(3)
	public void testLoadSuccess() {
		UserProfile test = new UserProfile("testLoadSuccess");
		assertDoesNotThrow(() -> {
			SaveManager.saveFile(test, "testLoadSuccess.Save");
			SaveManager.loadFile("testLoadSuccess.Save");
		});
	}
	
	/**
	 * Here we test the load function 
	 * Expected Failure
	 */
	@Test
	@Order(4)
	public void testLoadFailure() {
		assertThrows(IOException.class, () ->
			SaveManager.loadFile("testLoadFailure"));
	}
	
	/**
	 * Here we test the delete function
	 * Expected Success
	 */
	@Test
	@Order(5)
	public void testDeleteSuccess() {
		UserProfile test = new UserProfile("testDeleteSuccess");
		assertDoesNotThrow(() -> {
			SaveManager.saveFile(test, "testDeleteSuccess.Save");
			SaveManager.deleteFile("testDeleteSuccess.Save");
		});
	}
	
	/**
	 * Here we test the delete function
	 * Expected Failure
	 */
	@Test
	@Order(6)
	public void testDeleteFailure() {
		UserProfile test = new UserProfile("testDeleteFailure");
		assertThrows(IOException.class, () ->
			SaveManager.deleteFile("testDeleteFailure")
		);
	}
}
