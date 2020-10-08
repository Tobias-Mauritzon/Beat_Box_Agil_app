package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class ProfileHandlerTest {

	ProfileHandler ph;
	
	@Test
	void test() {

	}
	
	/**
	 * 	Before each test reinitialize generator object
	 */
	@Before
	public void setup() {
		this.ph = new ProfileHandler(new UserProfile("TestUser1"));
	}
	
	@Test
	public void DeleteCurrentProfileTest() {
		ProfileHandler ph = new ProfileHandler(new UserProfile("TestUser1"));
		String Pname = ph.getCurrentProfile().getName();
		Optional<String> optional = Optional.of(Pname);
		assertFalse(ph.deleteProfile(optional));
	}

}
