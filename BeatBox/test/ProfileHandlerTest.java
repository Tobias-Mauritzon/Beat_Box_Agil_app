

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ProfileHandler;

/***
 * Testing class for the profile handler
 * @author Andreas Greppe
 * * @since 2020-10-08.
 */
class ProfileHandlerTest {

	ProfileHandler ph;

	@Test
	void test() {

	}

	/**
	 * Before each test reinitialize generator object
	 */
	@BeforeEach
	public void setup() {
		this.ph = new ProfileHandler("TestUser1");
	}
	
	//Tests so that we can't remove our current profile.
	@Test
	public void DeleteCurrentProfileTest() {

		String Pname = ph.getCurrentProfile().getName();
		Optional<String> optional = Optional.of(Pname);
		assertFalse(ph.deleteProfile(optional));
	}

	//Adds 10 profiles and checks the size of the profiles list and then removed the 10 added profiles and tests the size again-
	@Test
	public void addNdelete() {
		for (int i = 0; i < 9; i++) {
			String Pname = "profile" + i;
			Optional<String> optional = Optional.of(Pname);
			ph.addProfile(optional);
		}
		assertTrue(ph.profileAmount() == 10 );

		Optional<String> test = Optional.of("TestUser1");
		ph.switchProfile(test);
		
		for (int i = 0; i < 9; i++) {
			String Pname = "profile" + i;
			Optional<String> optional = Optional.of(Pname);
			ph.deleteProfile(optional);
		}
		assertTrue(ph.profileAmount() == 1 );
	}
	
	// Creates two profiles and tests swaps between them back and forth.
	@Test
	public void SwitchProfile() 
	{
		String Pname1 = "profile0";
		Optional<String> optional1 = Optional.of(Pname1);
		String Pname2 = "profile1";
		Optional<String> optional2 = Optional.of(Pname2);
		
		for (int i = 0; i < 2; i++) {
			String Pname = "profile" + i;
			Optional<String> optional = Optional.of(Pname);
			ph.addProfile(optional);
		}
		
		ph.switchProfile(optional1);
		assertEquals(ph.getCurrentProfile().getName(), ph.getProfile(optional1.get()).getName());
		ph.switchProfile(optional2);
		assertEquals(ph.getCurrentProfile().getName(), ph.getProfile(optional2.get()).getName());
	}
	
	// Tests so switchProfile retunrs false if we try to switch to an invalid profile.
	@Test
	public void InvalidSwitch() 
	{
		String Pname = "Invalid Profile";
		Optional<String> optional = Optional.of(Pname);
		assertFalse(ph.switchProfile(optional));
	}
	

}
