package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Operator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * Gui for customization of ProblemParameter instances.
 * 
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class CustomParametersGUI implements GUIHandler {
	private AnchorPane root;
	private CheckBox addCheckBox;
	private CheckBox subCheckBox;
	private CheckBox mulCheckBox;
	private CheckBox divCheckBox;
	private CheckBox timedCheckBox;
	private Slider termSlider;
	private Spinner minSpinner;
	private Spinner maxSpinner;
	private Button playButton;
	private Label rangeError;
	private Label opError;

	public CustomParametersGUI(AnchorPane root) {
		this.root = root;
		getGUIObjects();
	}

	public void displayOpError() {
		opError.setVisible(true);
	}

	public void displayRangeError() {
		rangeError.setVisible(true);
	}

	public AnchorPane getRoot() {
		return root;
	}

	/**
	 * Get all GUI objects: Buttons, checkboxes, spinners etc. on the scene.
	 */
	@Override
	public void getGUIObjects() {
		addCheckBox = (CheckBox) root.lookup("#addCheckBox");
		subCheckBox = (CheckBox) root.lookup("#subCheckBox");
		mulCheckBox = (CheckBox) root.lookup("#mulCheckBox");
		divCheckBox = (CheckBox) root.lookup("#divCheckBox");
		timedCheckBox = (CheckBox) root.lookup("#timedCheckBox");
		termSlider = (Slider) root.lookup("#termSlider");
		minSpinner = (Spinner) root.lookup("#minSpinner");
		maxSpinner = (Spinner) root.lookup("#maxSpinner");
		playButton = (Button) root.lookup("#playButton");
		opError = (Label) root.lookup("#opError");
		rangeError = (Label) root.lookup("#rangeError");
	}

	/**
	 * Get all chosen Operators.
	 * 
	 * @see Operator class.
	 * @see ProblemParameters class.
	 */
	public List<Operator> getOperators() {
		List<Operator> operators = new ArrayList<>();
		if (addCheckBox.isSelected()) {
			operators.add(Operator.ADD);
		}
		if (subCheckBox.isSelected()) {
			operators.add(Operator.SUB);
		}
		if (mulCheckBox.isSelected()) {
			operators.add(Operator.MUL);
		}
		if (divCheckBox.isSelected()) {
			operators.add(Operator.DIV);
		}
		return operators;
	}

	/**
	 * Method to get the range of numbers to generate terms from.
	 * 
	 * @see ProblemParameters class.
	 */
	public int[] getRange() {
		int range[] = new int[2];
		range[0] = (Integer) minSpinner.getValue();
		range[1] = (Integer) maxSpinner.getValue();
		return range;
	}

	/**
	 * Method to get the number of terms for a problem.
	 * 
	 * @see ProblemParameters class.
	 */
	public int getTermAmount() {
		return (int) termSlider.getValue();
	}

	/**
	 * Method to see if a session is timed or not.
	 * 
	 * @see ProblemParameters class.
	 */
	public boolean getTimed() {
		return timedCheckBox.isSelected();
	}

	public Button getPlayButton() {
		return playButton;
	}
}