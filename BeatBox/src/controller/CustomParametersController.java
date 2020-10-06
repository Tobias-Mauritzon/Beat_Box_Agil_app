package controller;

import javafx.scene.control.TextFormatter;
import view.CustomParametersGUI;
import model.CustomParametersModel;

import java.util.function.UnaryOperator;

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
	CustomParametersGUI cpGUI;
	CustomParametersModel cpModel;

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
		setActions();
	}

	/**
	 * Method to define the delegate for the GUI.
	 */

	@Override
	public void setActions() {
		cpGUI.getPlayButton().setOnAction(e -> {
			boolean error = false;
			cpModel.updateModel(cpGUI.getOperators(), cpGUI.getRange(), cpGUI.getTermAmount(), cpGUI.getTimed());

			if (!cpModel.operatorsIsValid()) {
				cpGUI.displayOpError();
				error = true;
			}
			if (!cpModel.rangeIsValid()) {
				cpGUI.displayRangeError();
				error = true;
			}
			if (error) {
				return;
			}
			cpModel.generateProblemParameters();
		});

		UnaryOperator<TextFormatter.Change> filter = change -> {
			if (change.getText().matches("[0-9]*")) {
				return change;
			}
			return null;
		};

		cpGUI.getMinSpinner().getEditor().setTextFormatter(new TextFormatter<>(filter));
		cpGUI.getMinSpinner().getEditor().setTextFormatter(new TextFormatter<>(filter));
	}
}