package view;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import model.UserProfile2;
import model.History;
import model.Operator;
import model.SaveManager;


/**
 * Class that creates a a prototype for the user profile gui
 * @author Joachim Antfolk, Tobias Mauritzon
 * @since 2020-10-02
 */
public class UserProfileGui2 /* implements GUIHandler */ {

	private AnchorPane root;
	private Label userName;
	private Button profileNew;
	private Button profileSwitch;
	private Button profileDelete;
	private UserProfile2 profile;
	private TableView<History> history;

	public void UserProfileGUI2(UserProfile2 profile) {
		try {
			this.profile = profile;
			this.root = (AnchorPane) FXMLLoader.load(getClass().getResource("/FXML/UserProfile.fxml"));
			Locale.setDefault(Locale.ENGLISH); // Sets button text to english
			getGUIObjects();
			setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the GUI elements so the users can interact with them.
	 */
	@SuppressWarnings("unchecked")
	private void getGUIObjects() {
		userName = (Label) root.lookup("#profileName");
		profileNew = (Button) root.lookup("#profileNew");
		profileSwitch = (Button) root.lookup("#profileSwitch");
		profileDelete = (Button) root.lookup("#profileDelete");
		history = (TableView<History>) root.lookup("#history");

	}

	/**
	 * Gets the root pane for the user profile GUI
	 * 
	 * @return The GUIs root
	 */
	public AnchorPane getRoot() {
		return root;
	}

	/**
	 * Sets the profile to input parameter
	 * 
	 * @param profile new profile
	 */
	public void setProfile(UserProfile2 profile) {
		this.profile = profile;
	}

	/**
	 * Sets the user name label text
	 * 
	 * @param name
	 */
	public void setUserNameLabel(String name) {
		userName.setText(name);
	}

	/**
	 * Sets the list that the table will reflect
	 * 
	 * @param list List that implements ObservableList
	 */
	public void setHistoryList(ObservableList<History> list) {
		history.setItems(list);
	}

	/**
	 * Gets the new profile button
	 * 
	 * @return new profile button
	 */
	public Button getPNewButton() {
		return profileNew;
	}

	/**
	 * Gets the switch profile button
	 * 
	 * @return switch profile button
	 */
	public Button getPSwitchButton() {
		return profileSwitch;
	}

	/**
	 * Gets the delete profile button
	 * 
	 * @return delete profile button
	 */
	public Button getPDeleteButton() {
		return profileDelete;
	}

	/**
	 * Creates a user profile, updates user name text and programs buttons
	 */
	@SuppressWarnings("unchecked")
	private void setup() {
		userName.setText(profile.getName());
		/*
		 * for(int i = 0;i<1000;i++) { profile.addProblemToHistory("1 + "+i, ""+i,
		 * ""+(1+i), 4, 3, new Operator[] {Operator.ADD});
		 * profile.addProblemToHistory("2 + "+i, ""+2*i, ""+(2+i), 4, 3, new Operator[]
		 * {Operator.ADD}); profile.addProblemToHistory("3 + "+i, ""+3*i, ""+(3+i), 4,
		 * 3, new Operator[] {Operator.ADD}); profile.addProblemToHistory("4 - "+i,
		 * ""+4*i, ""+(4+i), 4, 3, new Operator[] {Operator.ADD});
		 * profile.addProblemToHistory("5 * "+i, ""+5*i, ""+(5*1), 4, 3, new Operator[]
		 * {Operator.ADD}); }
		 */

		TableColumn<History, String> dateCol = new TableColumn<History, String>("Date");
		TableColumn<History, String> problemCol = new TableColumn<History, String>("Problem");
		TableColumn<History, Double> userSolutionCol = new TableColumn<History, Double>("User Solution");
		TableColumn<History, Double> rightSolutionCol = new TableColumn<History, Double>("Correct Solution");
		history.getColumns().setAll(dateCol, problemCol, userSolutionCol, rightSolutionCol);
		history.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

		dateCol.setCellValueFactory(new PropertyValueFactory<History, String>("date"));
		problemCol.setCellValueFactory(new PropertyValueFactory<History, String>("problem"));
		userSolutionCol.setCellValueFactory(new PropertyValueFactory<History, Double>("userAnswer"));

		rightSolutionCol.setCellValueFactory(new PropertyValueFactory<History, Double>("correctAnswer"));

		history.setItems(profile.getHistory());

		profileNew.setOnAction((event) -> {
			try {
				Optional<String> name = getInput("New Profile", "Profile name:");

				if (name.isPresent() && name.get().length() > 0) {
					profile.toArrayList();

					SaveManager.saveFile(profile, profile.getName() + "Profile.Save");

					profile = new UserProfile2(name.get());
					userName.setText(name.get());
					history.setItems(FXCollections.observableArrayList(profile.getHistory()));

				}
			} catch (IOException e) {
				errorMessage("Could not save profile: " + profile.getName() + "!");
			}
		});

		profileSwitch.setOnAction((event) -> {
			try {
				Optional<String> name = getInput("Switch Profile", "Profile name:");

				if (name.isPresent() && name.get().length() > 0) {
					profile.toArrayList();
					SaveManager.saveFile(profile, profile.getName() + "Profile.Save");

					profile = (UserProfile2) SaveManager.loadFile(name.get() + "Profile.Save");
					profile.toObservableList();

					userName.setText(profile.getName());
					history.setItems(FXCollections.observableArrayList(profile.getHistory()));
				}
			} catch (IOException | ClassNotFoundException e) {
				errorMessage("Could not switch profile!");
			}
		});

		profileDelete.setOnAction((event) -> {
			try {
				Optional<String> name = getInput("Delete Profile", "Profile name:");

				if (name.isPresent() && name.get().length() > 0) {
					SaveManager.deleteFile(name.get() + "Profile.Save");
				}
			} catch (IOException e) {
				errorMessage("Could not delete profile: " + profile.getName() + "!");
			}
		});
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
	public Optional<String> getInput(String title, String inputText) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/dialog.css").toExternalForm());
		dialog.setTitle(title);
		dialog.setGraphic(null);
		dialog.setHeaderText("");
		dialog.setContentText(inputText);

		return dialog.showAndWait();
	}
}
