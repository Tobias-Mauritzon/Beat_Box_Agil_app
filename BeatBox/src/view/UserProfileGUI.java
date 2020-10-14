package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.History;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Optional;

/**
 * Class that creates a a prototype for the user profile gui
 *
 * @author Joachim Antfolk, Tobias Mauritzon, Philip AxenHamn, Andreas Greppe
 * @since 2020-10-09.
 */

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
	@SuppressWarnings("unchecked")
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
	 * 
	 * @return the History TabelView.
	 */
	public TableView<History> getHistory() {
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
	 * Initiates the history table with the correct titles, and categories.
	 */
	@SuppressWarnings("unchecked")
	public void init() {
		TableColumn<History, String> dateCol = new TableColumn<History, String>("Date");
		TableColumn<History, ImageView> problemCol = new TableColumn<History, ImageView>("Problem");
		TableColumn<History, Double> userSolutionCol = new TableColumn<History, Double>("User Solution");
		TableColumn<History, Double> rightSolutionCol = new TableColumn<History, Double>("Correct Solution");

		history.getColumns().setAll(problemCol, userSolutionCol, rightSolutionCol, dateCol);
		history.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

		dateCol.setCellValueFactory(new PropertyValueFactory<History, String>("date"));
		userSolutionCol.setCellValueFactory(new PropertyValueFactory<History, Double>("userAnswer"));
		rightSolutionCol.setCellValueFactory(new PropertyValueFactory<History, Double>("correctAnswer"));

		// Under here we convert the History object Problem string into a LaTex formated
		// image
		problemCol.setCellValueFactory(c -> {
			TeXFormula formula = new TeXFormula(c.getValue().getProblem());
			TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18);
			icon.setForeground(Color.white); // White text

			BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = image.createGraphics();
			g2.setColor(new Color(0, 0, 0, 1)); // Transparent background
			g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
			JLabel jl = new JLabel();
			jl.setForeground(new Color(0, 0, 0));
			icon.paintIcon(jl, g2, 0, 0);

			return new SimpleObjectProperty<ImageView>(new ImageView(SwingFXUtils.toFXImage(image, null)));
		});
	}
	

}
