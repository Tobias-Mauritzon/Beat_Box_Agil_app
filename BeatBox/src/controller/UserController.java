package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import model.SaveManager;
import model.UserProfile;
import view.UserProfileGUI;

/***
 * A controller for the userProfiler so the userGUI can interact with the
 * UserprofileModell.
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-10-01
 */
public class UserController implements ControllerInterface {

	private UserProfileGUI userProfileGUI;
	private UserProfile userProfile;

	/***
	 * Constructor for the UserController.
	 * 
	 * @param userProfileGUI the UserProfileGUI instance
	 * @param userProfile    the UserProfile model instance.
	 */
	public UserController(UserProfileGUI userProfileGUI, UserProfile userProfile) {
		this.userProfileGUI = userProfileGUI;
		this.userProfile = userProfile;
		setActions();
	}

	@Override
	/***
	 * Sets the actions for the userController GUI.
	 */
	public void setActions() {
//		System.out.println("test: " + userProfileGUI.getInput("Switch Profile"));
		System.out.println("test2: " + userProfileGUI.getPSwitchButton());

		userProfileGUI.getPSwitchButton().setOnAction((event) -> {
			try {
				Optional<String> name = userProfileGUI.getInput("Switch Profile");

				if (name.isPresent() && name.get().length() > 0) {
					SaveManager.saveFile(userProfile, userProfile.getName() + "Profile.Save");
					userProfile = (UserProfile) SaveManager.loadFile(name.get() + "Profile.Save");

					userProfileGUI.setUserNameLabel(userProfile.getName());
				}
			} catch (IOException | ClassNotFoundException e) {
				userProfileGUI.errorMessage("Could not switch profile!");
			}
		});

		userProfileGUI.getPDeleteButton().setOnAction((event) -> {
			try {
				Optional<String> name = userProfileGUI.getInput("Delete Profile");

				if (name.isPresent() && name.get().length() > 0) {
					SaveManager.deleteFile(name.get() + "Profile.Save");
				}
			} catch (IOException e) {
				userProfileGUI.errorMessage("Could not delete profile: " + userProfile.getName() + "!");
			}
		});

	}

}
