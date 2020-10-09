package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/***
 * The view class that creates the GUI for the Problem solving scene of the
 * application. Handles Input through the SampleController.
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 * 
 * @author Joachim Antfolk
 * @author Tobias Mauritzon
 * @version 2.0
 * @since 2020-10-09
 */
public class ProblemGUI implements GUIHandler {

	// Vilket Delegate ?

	/***
	 * A Delegate used to communicate with the main class without and direct
	 * contact. has two methods grade that take a string and compares it to the
	 * answer of the problem and getProblem which generates a new problem and
	 * returns it.
	 * 
	 * @author Greppe
	 * @author Philip
	 * @version 1.0
	 * @since 2020-09-17
	 */

	// Input objects
	private TextField answerText;
	private Button answerButton;

	// Output objects
	private VBox responseBox;
	private VBox imageBox;
	private ImageView problemImageView;
	private Text responseText;
	private AnchorPane root;

	private LinkedList<Node> inputObjects;

	/***
	 * The constructor of the ProblemGUI class, initializes the GUI elements with
	 * initGUI and sets the root of the main application to the problem gui:s root.
	 * 
	 * @param root the root of the JavaFX application
	 * 
	 */
	public ProblemGUI(AnchorPane root) {
		this.root = root;
		inputObjects = new LinkedList<Node>();
		getGUIObjects();
		addTextInputListener();
		clearAnswerText();

		imageBox.setMinHeight(0);
		imageBox.setMinWidth(0);
		problemImageView.fitHeightProperty().bind(imageBox.heightProperty());
		problemImageView.fitWidthProperty().bind(imageBox.widthProperty());
		problemImageView.setPreserveRatio(true);

	}

	private void addTextInputListener() {
		// Check if userinput is a number.
		answerText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("^[-+]?[0-9]*[.,]?[0-9]+$")) {
					answerText.setText(newValue.replaceAll("[^[-+]?[0-9]*[.,]?[0-9]+$]", ""));
				}
			}
		});

	}

	/**
	 * Converts the given LaTex string to a FXImge and sets the image in
	 * problemImageView to the converted LaTex FXImage
	 * 
	 * @param problem the new string for ProblemText
	 */
	public void setProblemText(String problem) {
		TeXFormula formula = new TeXFormula(problem);

		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 300);// Here you can change text size
		icon.setForeground(java.awt.Color.WHITE); // White text

		BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(new java.awt.Color(0, 0, 0, 1)); // Transparent background
		g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
		JLabel jl = new JLabel();
		jl.setForeground(new java.awt.Color(0, 0, 0));
		icon.paintIcon(jl, g2, 0, 0);

		problemImageView.setImage(SwingFXUtils.toFXImage(image, null));
	}

	/**
	 * Clears the current text in the answer field.
	 */
	public void clearAnswerText() {
		answerText.clear();
	}

	/***
	 * shows a temporary message depending on the in parameter, if it's true it show
	 * the correct message otherwise it will show wrong.
	 * 
	 * @param correct a boolean indicated if the user answered correctly or not
	 */
	public void showResponse(boolean correct) {
		if (correct) {
			responseText.setText("CORRECT!");
			responseText.setFill(Color.LIMEGREEN);
		} else {
			responseText.setText("WRONG!");
			responseText.setFill(Color.RED);
		}
		responseBox.setVisible(true);
		FadeTransition ft = new FadeTransition(Duration.millis(1500), responseBox);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
	}

	/**
	 * Initializes the GUI elements so the users can interact with them.
	 */
	@Override
	public void getGUIObjects() {
		// input objects
		answerText = (TextField) root.lookup("#answerText");
		answerButton = (Button) root.lookup("#answerButton");
		inputObjects.add(answerText);
		inputObjects.add(answerButton);

		// output objects
		responseBox = (VBox) root.lookup("#responseBox");
		imageBox = (VBox) root.lookup("#imageBox");
		problemImageView = (ImageView) root.lookup("#problemImage");
		responseText = (Text) root.lookup("#responseText");

	}

	/**
	 * gets the answerText field
	 * 
	 * @return returns the answertext field.
	 */
	public TextField getAnswerText() {
		return answerText;
	}

	/**
	 * gets the answer button
	 * 
	 * @return retunrs the answer button
	 */
	public Button getAnswerButton() {
		return answerButton;
	}

}
