package controller;

import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import view.CustomParametersGUI;
import model.CustomParametersModel;

import java.math.BigInteger;

/***
 * A simple controller for the custom parameters gui. WILL BE FUSED WITH
 * CONTROLLER NEXT SPRINT!
 * 

 * @version 1.0
 * @since 2020-10-01
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 * @author Greppe
 * @author Philip
 */
public class CustomParametersController implements ControllerInterface {
	private final CustomParametersGUI cpGUI;
	private final CustomParametersModel cpModel;
	private boolean errorNoOpSelected;
	private boolean errorInvalidRange;

	/**
	 * Constructor for CustomParameter controller.
	 *
	 * @param cpgui   GUI for custom parameter.
	 * @param cpmodel model for CustomParameter
	 *
	 * @see CustomParameterGUI
	 * @see CustomParameterModel
	 * @see CustomParameters
	 *
	 */
	public CustomParametersController(CustomParametersGUI cpGUI, CustomParametersModel cpModel) {
		this.cpGUI = cpGUI;
		this.cpModel = cpModel;

		errorNoOpSelected = false;
		errorInvalidRange = false;

		setActions();
	}

	/**
	 * Method to define what happens when the gui is interacted with.
	 */
	@Override
	public void setActions() {
		cpGUI.getPlayButton().setOnAction(e -> {
			cpModel.updateModel(cpGUI.getOperatorsData(), cpGUI.getRangeData(),
					cpGUI.getTermAmountData(), cpGUI.getTimedData());
			cpModel.generateProblemParameters();
		});

		for (CheckBox cb : cpGUI.getOpCheckBoxes()) {
			cb.setOnAction(e -> {
				errorNoOpSelected = !operatorsIsValid();
				handleErrors();
			});
		}

		for (TextField tf : cpGUI.getRangeInputs()) {
			// Set text formatter so only numbers can be inputted
			tf.setTextFormatter(new TextFormatter<>(change -> {
				if (change.getText().matches("[0-9]*")) {
					return change;
				}
				return null;
			}));

			// fitStrValueToInt() is used in a recursive way here since every time a different value than
			// newValue is returned the change function is called again because of setText().
			tf.textProperty().addListener((observable, oldValue, newValue) -> {
				// Only call this method again if there still is changes that needs to be done before
				// updating the model.
				String editedValue = fitStrValueToInt(newValue);

				if (!editedValue.equals(newValue)) {
					// Calls this change action function again if needed. Need to use Platform.runLater() because
					// the text can not be modified directly in this function.
					Platform.runLater(() -> {
						tf.setText(editedValue);
					});
					return;
				}

				errorInvalidRange = !rangeIsValid();
				handleErrors();
			});
		}
	}

	/***
	 * Parses non empty strings (assumed to contain only numbers) and fits it into an int and converts
	 * it back to a string. This is to make sure the number in the string cant be larger than the
	 * maximum size of an int.
	 *
	 * @param str	the string to parse and fit into an int.
	 * @return String	the value fitted into an int as a String, is empty if the input was.
	 */
	private String fitStrValueToInt(String str) {
		BigInteger val;

		// If the there is no content in the new string, replace it with a 0.
		if (str.equals("")) {
			str = "0";
		}

		// Used to make sure any expression does not start with 0. Fixes an exception
		// that gets thrown if a string starts with too many zeroes when converting to
		// int.
		if (str.charAt(0) == '0' && str.length() > 1) {
			str = str.substring(1);
		}

		// Makes sure it is impossible to enter a value larger than what fits in an int.
		val = new BigInteger(str);
		if (val.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0) {
			str = String.valueOf(Integer.MAX_VALUE);
		}

		return str;
	}

	/***
	 * Checks whether at least one operator is selected or not.
	 *
	 * @return boolean	whether the validation succeeded or not.
	 */
	private boolean operatorsIsValid() {
		return cpGUI.getOperatorsData().size() > 0;
	}

	/***
	 * Checks whether minRange is smaller than maxRange or not.
	 *
	 * @return boolean	whether the validation succeeded or not.
	 */
	private boolean rangeIsValid() {
		int[] range = cpGUI.getRangeData();
		return range[0] < range[1];
	}

	/***
	 * Checks if there are errors in the input. If there is it makes sure to display the
	 * corresponding error and deactivates the play button.
	 */
	private void handleErrors() {
		boolean errorsPresent = false;

		if (errorNoOpSelected) {
			errorsPresent = true;
			cpGUI.displayOpError(true);
		} else {
			cpGUI.displayOpError(false);
		}

		if (errorInvalidRange) {
			errorsPresent = true;
			cpGUI.displayRangeError(true);
		} else {
			cpGUI.displayRangeError(false);
		}

		if (errorsPresent) {
			cpGUI.getPlayButton().setDisable(true);
		} else {
			cpGUI.getPlayButton().setDisable(false);
		}
	}
}