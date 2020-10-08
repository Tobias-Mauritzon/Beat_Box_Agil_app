package model;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Optional;

/***
 * A class that list and handles all Profile instances, can create new profiles, Switch profiles and DeleteProfiles.
 * Also serializes the profiles using the saveManger.
 * @author Andreas Greppe
 *
 */
public class ProfileHandler {
	
	private ArrayList<UserProfile> profiles;
	private UserProfile currentProfile;
	
	/***
	 * Constructor for the profileHandler, takes in a starting profile that is the current profile.
	 * @param profile
	 */
	public ProfileHandler(UserProfile profile) 
	{
		profiles = new ArrayList<UserProfile>();
		profiles.add(profile);
		currentProfile = profile;
		HistoryTest();
	}
	
	
	/***
	 * Adds a new profile if the string name is valid and a profile with the same name does not exists.
	 * Sets the current profile to the new profile, adds it to the profiles list and creates a savefile.
	 * if there already is a profile with the same name nothing happens.
	 * @param name the name of the new profile.
	 * @throws IOException the exception if it's not possible to create a savefile.
	 */
	public Boolean addProfile(Optional<String> name) throws IOException {
		
		if (name.isPresent() && name.get().length() > 0) {
			
			for(UserProfile profile : profiles) {
				
				if(profile.getName().equals(name.get())) {
					System.out.println("Inputed Name: " + name.get() + "  Duplicate Name :" + profile.getName());
					return false;
				}
			}
			System.out.println("Added :" + name.get());
			currentProfile = new UserProfile(name.get());
			profiles.add(currentProfile);
			currentProfile.toArrayList();
			SaveManager.saveFile(currentProfile, currentProfile.getName() + "Profile.Save");
			currentProfile.toObservableList();
			return true;
		}
		return false;
	}
	
	
	/***
	 * Switches the current profile to the specified profile if it exists and the input is valid.
	 * @param name the name of the profile to change to.
	 * @throws IOException the exception if it's not possible to create a savefile.
	 * @throws ClassNotFoundException the exception if it's not possible to load the savefile.
	 */
	public Boolean switchProfile(Optional<String> name) throws IOException, ClassNotFoundException {
		if (name.isPresent() && name.get().length() > 0) {
			
			for(UserProfile profile : profiles) {
				
				if(profile.getName().equals(name.get())) {
					
					System.out.println("Switched To :" + name.get());
					currentProfile.toArrayList();
					SaveManager.saveFile(currentProfile, currentProfile.getName() + "Profile.Save");
					currentProfile = (UserProfile) SaveManager.loadFile(name.get() + "Profile.Save");
					currentProfile.toObservableList();
					
					return true;
				}
			}
		}
		return false;
	}
	
	/***
	 * Deletes the profile with the specified name if it exits.
	 * @param name the name of the profile to delete.
	 * @throws NoSuchFileException exception if the save file can't be found
	 * @throws DirectoryNotEmptyException exception if the savefile can't be deleted.
	 * @throws IOException other general exceptions.
	 */
	public Boolean deleteProfile(Optional<String> name) throws NoSuchFileException, DirectoryNotEmptyException, IOException {
		
		if (name.isPresent() && name.get().length() > 0) {
			
			for(UserProfile profile : profiles) {
				
				if(profile.getName().equals(name.get()) && (currentProfile != profile)) {
					profiles.remove(profile);
					SaveManager.deleteFile(name.get() + "Profile.Save");
					System.out.println("Deleted To :" + name.get());
					return true;
	
				}
			}
		}
		return false;
	}
	
	public void HistoryTest() {
		
		for (int i = 0; i < 10; i++) {
			
			currentProfile.addProblemToHistory("1 + " + i, "" + i, "" + (1 + i), 4, 3, new model.Operator[] { model.Operator.ADD, model.Operator.SUB });
			currentProfile.addProblemToHistory("2 + " + i, "" + 2 * i, "" + (2 + i), 4, 3, new model.Operator[] { model.Operator.ADD });
			currentProfile.addProblemToHistory("3 + " + i, "" + 3 * i, "" + (3 + i), 4, 3, new model.Operator[] { model.Operator.ADD });
			currentProfile.addProblemToHistory("4 - " + i, "" + 4 * i, "" + (4 + i), 4, 3, new model.Operator[] { model.Operator.ADD });
			currentProfile.addProblemToHistory("5 * " + i, "" + 5 * i, "" + (5 * 1), 4, 3, new model.Operator[] { model.Operator.ADD });
		
		}
		
	}
	
	/***
	 * A method that loads in all serialized savefiles on startup etc and adds them to the arrayList.
	 */
	public void loadSaveFiles() 
	{
		//to do, loads all savefiles in a set location and adds them to the list.
		
	}
	
	/***
	 * Returns the profile with the specified name if it exists.
	 */
	public UserProfile getProfile(String name) 
	{
		for(UserProfile profile : profiles) {
			
			if(profile.getName().equals(name)) {
				
				return profile;
			}
		}
		return null;
	}
	
	/***
	 * Returns the  currentprofile
	 */
	public UserProfile getCurrentProfile() 
	{
		return currentProfile;
	}
}
