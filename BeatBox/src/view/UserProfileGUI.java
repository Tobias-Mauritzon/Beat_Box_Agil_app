package view;

/**
 * Class that creates a a prototype for the user profile gui
 * @author Joachim Antfolk, Tobias Mauritzon, Philip AxenHamn, Andreas Greppe
 * @since 2020-10-01.
 */
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.UserProfile;
import model.SaveManager;

public class UserProfileGUI implements GUIHandler {

	private Label userName;
	private Button profileNew;
	private Button profileSwitch;
	private Button profileDelete;
	private AnchorPane root;

	/**
	 * the constructor for the GUIHandler.
	 * 
	 * @param root the root of the subscene.
	 */
	public UserProfileGUI(AnchorPane root) {
		this.root = root;
		getGUIObjects();
	}

	/**
	 * Opens an error dialog with an error message
	 * 
	 * @param error message to display
	 */
	public void errorMessage(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/dialog.css").toExternalForm());
		alert.setTitle("Error Detected");
		alert.setHeaderText("");
		alert.setContentText(error);
		alert.showAndWait();
	}

	/**
	 * Opens a dialog that gets user input
	 * 
	 * @param title title for the dialog box window
	 * @return profile name as Optional<String>
	 */
	public Optional<String> getInput(String title) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/dialog.css").toExternalForm());
		dialog.setTitle(title);
		dialog.setGraphic(null);
		dialog.setHeaderText("");
		dialog.setContentText("Profile name:");

		return dialog.showAndWait();
	}

	/**
	 * Initializes the GUI elements so the users can interact with them.
	 */
	@Override
	public void getGUIObjects() {
		userName = (Label) root.lookup("#profileName");
		profileNew = (Button) root.lookup("#profileNew");
		profileSwitch = (Button) root.lookup("#profileSwitch");
		profileDelete = (Button) root.lookup("#profileDelete");
	}

	/**
	 * sets the userName label to inputed string
	 * 
	 * @param name
	 */
	public void setUserNameLabel(String name) {
		userName.setText(name);
	}

	/**
	 * Gets the newprofileButton
	 * 
	 * @return returns the new profile button
	 */
	public Button getPNewButton() {
		return profileNew;
	}

	/**
	 * gets the profileSwitch Button
	 * 
	 * @return returns profile Switch button
	 */
	public Button getPSwitchButton() {
		return profileSwitch;
	}

	/**
	 * gets the Profile Delete Button
	 * 
	 * @return returns profile delete button
	 */
	public Button getPDeleteButton() {
		return profileDelete;
	}

}
