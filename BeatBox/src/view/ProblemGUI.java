package view;

import java.io.IOException;
import java.util.LinkedList;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.testGenerator;

/***
 * The view class that creates the GUI for the Problem solving scene of the
 * application. Handles Input through the SampleController.
 * 
 * @author Greppe
 * @author Philip
 * @version 1.0
 * @since 2020-09-17
 */
public class ProblemGUI implements GUIHandler {

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
	private Text problemText;
	private Text responseText;
	private String text;
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
		problemText.setFocusTraversable(false);
	}

	/**
	 * Sets the problem text to the inputed string
	 * 
	 * @param problem the new string for ProblemText
	 */
	public void setProblemText(String problem) {
		problemText.setText(problem);
	}

	/**
	 * Clears the current text in the answer field.
	 */
	public void clearAnswerText() {
		answerText.clear();
	}

	/***
	 * if the answer is correct a new problem is generated and the text field is
	 * cleared, otherwise it stays on the same problem.
	 */
//	public void answer() {
//	
//			if (delegate.grade(answerText.getText())) {
//				text = delegate.getProblem();
//				problemText.setText(text);
//				showResponse(true);
//			} else {
//				showResponse(false);
//
//				ShakeTransition anim = new ShakeTransition(answerText, t -> System.out.println("test"));
//				anim.playFromStart();
//			}
//			answerText.clear();
//			answerText.requestFocus();
//		}
//	}

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
		problemText = (Text) root.lookup("#problemText");
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
