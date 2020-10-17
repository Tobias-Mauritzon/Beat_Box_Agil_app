package view;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Operator;

import java.util.ArrayList;
import java.util.List;

/***
 * Gui for customization of ProblemParameter instances.
 * 
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class CustomParametersGUI implements GUIHandler {
	private final AnchorPane root;
	private CheckBox addCheckBox;
	private CheckBox subCheckBox;
	private CheckBox mulCheckBox;
	private CheckBox divCheckBox;
	private Slider termSlider;
	private TextField minInput;
	private TextField maxInput;
	private Button playButton;
	private Label rangeError;
	private Label opError;

	/**
	 * Constructor.
	 *
	 * @param AnchorPane	the root of the scene.
	 */
	public CustomParametersGUI(AnchorPane root) {
		this.root = root;
		getGUIObjects();
	}

	/**
	 * Get the root of the scene.
	 *
	 * @return AnchorPane	the root of the scene.
	 */
	public AnchorPane getRoot() {
		return root;
	}

	/**
	 * Get play button.
	 *
	 * @return Button	the play button.
	 */
	public Button getPlayButton() {
		return playButton;
	}

	/**
	 * Get an array of the range text fields.
	 *
	 * @return TextField[]	the text fields.
	 */
	public TextField[] getRangeInputs() {
		return new TextField[]{minInput, maxInput};
	}

	/**
	 * Get an array of the operator check boxes.
	 *
	 * @return CheckBox[] 	the check boxes.
	 */
	public CheckBox[] getOpCheckBoxes() {
		return new CheckBox[]{addCheckBox, subCheckBox, mulCheckBox, divCheckBox};
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
		termSlider = (Slider) root.lookup("#termSlider");
		minInput = (TextField) root.lookup("#minInput");
		maxInput = (TextField) root.lookup("#maxInput");
		playButton = (Button) root.lookup("#playButton");
		opError = (Label) root.lookup("#opError");
		rangeError = (Label) root.lookup("#rangeError");
	}

	/**
	 * Get the data from the operator check boxes in a format that can be understood
	 * by the ProblemParameters class.
	 *
	 * @see Operator class.
	 * @see ProblemParameters class.
	 *
	 * @return List<Operator>	the operator data.
	 */
	public List<Operator> getOperatorsData() {
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
	 * Get the data from the range text fields in a format that can be understood
	 * by the ProblemParameters class.
	 * 
	 * @see ProblemParameters class.
	 *
	 * @return int[]	the range data.
	 */
	public int[] getRangeData() {
		int[] range = new int[2];
		range[0] = Integer.parseInt(minInput.getText());
		range[1] = Integer.parseInt(maxInput.getText());
		return range;
	}

	/**
	 * Get the data from the term amount slider in a format that can be understood
	 * by the ProblemParameters class.
	 *
	 * @see ProblemParameters class.
	 *
	 * @return int	the term amount data.
	 */
	public int getTermAmountData() {
		return (int) termSlider.getValue();
	}

	/**
	 * Displays the operator error or hides it.
	 *
	 * @param display	whether to display the error or not.
	 */
	public void displayOpError(boolean display) {
		opError.setVisible(display);
	}

	/**
	 * Displays the range error or hides it.
	 *
	 * @param display	whether to display the error or not.
	 */
	public void displayRangeError(boolean display) {
		rangeError.setVisible(display);
	}
}
