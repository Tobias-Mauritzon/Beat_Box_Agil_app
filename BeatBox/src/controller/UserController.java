package controller;

import javafx.collections.FXCollections;
import model.ProfileHandler;
import view.UserProfileGUI;

import java.util.Optional;

/**
 * A controller for the userProfiler so the userGUI can interact with the
 * UserprofileModel.
 *
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-10-09
 */
public class UserController implements ControllerInterface {

	private UserProfileGUI userProfileGUI;
	private ProfileHandler profileHandler;
	private DialogMenuController dialogMenuController;

	/**
	 * Constructor for the UserController.
	 *
	 * @param userProfileGUI the UserProfileGUI instance
	 * @param profileHandler    the UserProfile model instance.
	 */
	public UserController(UserProfileGUI userProfileGUI, ProfileHandler profileHandler, DialogMenuController dialogMenuController) {
		this.userProfileGUI = userProfileGUI;
		this.profileHandler = profileHandler;
		this.dialogMenuController = dialogMenuController;
		// Updates the UserProfileGUi with the name of the testprofile, this will most
		// likely be replaced with the start window / loggin windows profile
		userProfileGUI.setUserNameLabel(profileHandler.getCurrentProfile().getName());
		// Updates the history tabell with the pregenerated sample history for the
		// testprofile.
		userProfileGUI.getHistory().setItems(profileHandler.getCurrentProfile().getHistory());
		setActions();
		setDelegate();
	}

	@Override
	/**
	 * Sets the actions for the userController GUI.
	 */
	public void setActions() {
		// Sets the action for the new profile button.
		userProfileGUI.getPNewButton().setOnAction((event) -> {
			dialogMenuController.open("New");
		});

		// Sets the actions for the profile switch button
		userProfileGUI.getPSwitchButton().setOnAction((event) -> {
			dialogMenuController.open("Switch");
		});

		// Sets the actions for the profile delete Button.
		userProfileGUI.getPDeleteButton().setOnAction((event) -> {
			dialogMenuController.open("Delete");
		});
	}

	/**
	 * Sets delegate for dialogMenuController
	 */
	private void setDelegate(){
		dialogMenuController.setDelegate(new DialogMenuController.Delegate() {
			@Override
			public boolean newProfile(Optional<String> name) {
				if (profileHandler.addProfile(name)) {
					userProfileGUI.setUserNameLabel(profileHandler.getCurrentProfile().getName());
					userProfileGUI.getHistory().setItems(FXCollections.observableArrayList(profileHandler.getCurrentProfile().getHistory()));
					return true;
				}
				return false;
			}

			@Override
			public boolean switchProfile(Optional<String> name) {
				if (profileHandler.switchProfile(name)) {
					userProfileGUI.setUserNameLabel(profileHandler.getCurrentProfile().getName());
					userProfileGUI.getHistory().setItems(FXCollections.observableArrayList(profileHandler.getCurrentProfile().getHistory()));
					return true;
				}
				return false;
			}

			@Override
			public boolean deleteProfile(Optional<String> name) { return (profileHandler.deleteProfile(name));}});
	}
}
