package model;

import java.io.IOException;
import model.Operator;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Optional;

/***
 * A class that list and handles all Profile instances, can create new profiles,
 * Switch profiles and DeleteProfiles. Also serializes the profiles using the
 * saveManger.
 * 
 * @author Andreas Greppe
 *
 */
public class ProfileHandler {

	private ArrayList<UserProfile> profiles;
	private UserProfile currentProfile;

	/***
	 * Constructor for the profileHandler, takes in a starting profile that is the
	 * current profile.
	 * 
	 * @param profile
	 */
	public ProfileHandler(UserProfile profile) {
		profiles = new ArrayList<UserProfile>();
		profiles.add(profile);
		currentProfile = profile;
		HistoryTest();
	}

	/***
	 * Adds a new profile if the string name is valid and a profile with the same
	 * name does not exists. Sets the current profile to the new profile, adds it to
	 * the profiles list and creates a savefile. if there already is a profile with
	 * the same name nothing happens.
	 * 
	 * @param name the name of the new profile.
	 * @throws IOException the exception if it's not possible to create a savefile.
	 */
	public Boolean addProfile(Optional<String> name) {

		Boolean success = false;
		UserProfile ProfileToAdd = null;
		try {
			if (name.isPresent() && name.get().length() > 0) {

				for (UserProfile profile : profiles) {

					if (profile.getName().equals(name.get())) {
						System.out.println("Inputed Name: " + name.get() + "  Duplicate Name :" + profile.getName());
						return false;
					}
				}
				System.out.println("Added :" + name.get());
				currentProfile = new UserProfile(name.get());
				ProfileToAdd = currentProfile;
				currentProfile.toArrayList();
				SaveManager.saveFile(currentProfile, currentProfile.getName() + "Profile.Save");
				success = true;
			}
		} catch (IOException e) {
			success = false;
			System.out.println(e.getMessage());
		}

		if (success && (ProfileToAdd != null)) {
			
			currentProfile.toObservableList();
			profiles.add(currentProfile);

		}
		return success;
	}

	/***
	 * Switches the current profile to the specified profile if it exists and the
	 * input is valid.
	 * 
	 * @param name the name of the profile to change to.
	 * @throws IOException            the exception if it's not possible to create a
	 *                                savefile.
	 * @throws ClassNotFoundException the exception if it's not possible to load the
	 *                                savefile.
	 */
	public Boolean switchProfile(Optional<String> name) {
		Boolean success = false;
		UserProfile SwitchToThis = null;
		try {
			if (name.isPresent() && name.get().length() > 0) {

				for (UserProfile profile : profiles) {

					if (profile.getName().equals(name.get())) {

						System.out.println("Switched To :" + name.get());
						currentProfile.toArrayList();
						SaveManager.saveFile(currentProfile, currentProfile.getName() + "Profile.Save");
						SwitchToThis = (UserProfile) SaveManager.loadFile(name.get() + "Profile.Save");
						success = true;
					}
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			success = false;
			System.out.println(e.getMessage());
		}

		if (success && (SwitchToThis != null)) {
			System.out.println("Success");
			currentProfile = SwitchToThis;
			currentProfile.toObservableList();
		}
		return success;
	}

	/***
	 * Deletes the profile with the specified name if it exits.
	 * 
	 * @param name the name of the profile to delete.
	 * @throws NoSuchFileException        exception if the save file can't be found
	 * @throws DirectoryNotEmptyException exception if the savefile can't be
	 *                                    deleted.
	 * @throws IOException                other general exceptions.
	 */
	public Boolean deleteProfile(Optional<String> name) {
		Boolean success = false;
		UserProfile deleteThis = null;
		try {
			if (name.isPresent() && name.get().length() > 0) {

				for (UserProfile profile : profiles) {

					if (profile.getName().equals(name.get()) && (currentProfile != profile)) {

						SaveManager.deleteFile(name.get() + "Profile.Save");
						System.out.println("Deleted To :" + name.get());
						deleteThis = profile;
						success = true;
					}
				}
			}
		} catch (IOException e) {
			success = false;
			System.out.println(e.getMessage());
		}

		if (success && (deleteThis != null)) {
			profiles.remove(deleteThis);
		}
		return success;
	}

	/***
	 * Adds some sample problems / answers to the history tabell for testing
	 * purposes.
	 */
	public void HistoryTest() {

		for (int i = 0; i < 10; i++) {

			currentProfile.addProblemToHistory("1 + " + i, "" + i, "" + (1 + i), 4, 3,
					new Operator[] { Operator.ADD, Operator.SUB });
			currentProfile.addProblemToHistory("2 + " + i, "" + 2 * i, "" + (2 + i), 4, 3,
					new Operator[] { Operator.ADD });
			currentProfile.addProblemToHistory("3 + " + i, "" + 3 * i, "" + (3 + i), 4, 3,
					new Operator[] { Operator.ADD });
			currentProfile.addProblemToHistory("4 - " + i, "" + 4 * i, "" + (4 + i), 4, 3,
					new Operator[] { Operator.ADD });
			currentProfile.addProblemToHistory("5 * " + i, "" + 5 * i, "" + (5 * 1), 4, 3,
					new Operator[] { Operator.ADD });

		}

	}

	/***
	 * A method that loads in all serialized savefiles on startup etc and adds them
	 * to the arrayList.
	 */
	public void loadSaveFiles() {
		// to do, loads all savefiles in a set location and adds them to the list.

	}

	/***
	 * Returns the profile with the specified name if it exists.
	 */
	public UserProfile getProfile(String name) {
		for (UserProfile profile : profiles) {

			if (profile.getName().equals(name)) {

				return profile;
			}
		}
		return null;
	}

	/***
	 * Returns the currentprofile
	 */
	public UserProfile getCurrentProfile() {
		return currentProfile;
	}
	
	/***
	 * Get the profiles list size
	 * @return the size of the profiles list
	 */
	public int profileAmount() 
	{
		return profiles.size();
	}
}
