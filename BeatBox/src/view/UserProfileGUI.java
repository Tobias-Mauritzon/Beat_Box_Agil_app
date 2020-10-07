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
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.UserProfile;
import model.UserProfile;
import model.History;
import model.SaveManager;

public class UserProfileGUI implements GUIHandler {
	
	private AnchorPane root;
	private Label userName;
	private Button profileNew;
	private Button profileSwitch;
	private Button profileDelete;
	private TableView<History> history;


	/**
	 * the constructor for the GUIHandler.
	 * 
	 * @param root the root of the subscene.
	 */
	public UserProfileGUI(AnchorPane root) {
		this.root = root;
		Locale.setDefault(Locale.ENGLISH); // Sets button text to english
		getGUIObjects();
		init();
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
		history = (TableView<History>) root.lookup("#history");
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
	 * Gets the History TabelView
	 * @return the History TabelView.
	 */
	public TableView<History> getHistory()
	{
		return history;
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
	
	/***
	 * Inits the history table with the correct titles, and catagories.
	 */
	public void init() 
	{
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
	}

}
