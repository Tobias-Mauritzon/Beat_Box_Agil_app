package model;

import java.io.File;
import java.io.IOException;
import model.Operator;

import java.net.URISyntaxException;
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
	public ProfileHandler(String profile) {
		profiles = new ArrayList<UserProfile>();
		if(!loadSaveFiles()) {

			Optional<String> profileName = Optional.of(profile);
			addProfile(profileName);
			System.out.println("no file found, created a new one");
		}
		else {
			currentProfile = profiles.get(0);
			currentProfile.toObservableList();
		}

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
				SaveManager.saveFile(currentProfile, currentProfile.getName() + ".Save");
				success = true;
			}
		} catch (IOException e) {
			success = false;
			System.out.println(e.getMessage());
		}

		if (success && (ProfileToAdd != null)) {

			profiles.add(currentProfile);
			currentProfile.toObservableList();
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
						SaveManager.saveFile(currentProfile, currentProfile.getName() + ".Save");
						SwitchToThis = (UserProfile) SaveManager.loadFile(name.get() + ".Save");
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

						SaveManager.deleteFile(name.get() + ".Save");
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
	 * A method that loads in all serialized savefiles on startup etc and adds them
	 * to the arrayList.
	 */
	public boolean loadSaveFiles()  {
		// to do, loads all savefiles in a set location and adds them to the list.

		File[] listOfFiles = new File(".").listFiles();

		for (File file: listOfFiles) {

			if(file.isFile())
			{
				if(file.getName().contains("."))
				{
					String[] extension = file.getName().split("\\.(?=[^.]*$)");
					if(extension[1].equals("Save"))
					{
						loadProfile(extension[0]);
					}
				}
			}

		}
		return (profileAmount() > 0);
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
	 * 
	 * @return the size of the profiles list
	 */
	public int profileAmount() {
		return profiles.size();
	}

	/***
	 * Loads a profile and adds it to the arraylist
	 * @param name the name of the profile
	 * @return returns true on success false on failure.
	 */
	public void loadProfile(String name)
	{
		UserProfile loadprofile;

		try {
			loadprofile = (UserProfile) SaveManager.loadFile(name + ".Save");
			profiles.add(loadprofile);
			System.out.println("loaded :" + name);
		}
		catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
