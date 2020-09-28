package controller;

import view.CustomParametersGUI;
import model.CustomParametersModel;

/***
 * A simple controller for the custom parameters gui. WILL BE FUSED WITH CONTROLLER NEXT SPRINT!
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class CustomParametersController  {
    CustomParametersGUI cpGUI;
    CustomParametersModel cpModel;


    /**
    * Constructor for CustomParameter controller. 
    *
    * @param cpgui GUI for custom parameter. 
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
        setDelegates();
    }

    /**
    * Method to define the delegate for the GUI.
    */ 
    private void setDelegates() {
        cpGUI.setDelegate(new CustomParametersGUI.Delegate() {
            @Override
            public void play() {
                cpModel.updateModel(cpGUI.getOperators(), cpGUI.getRange(), cpGUI.getTermAmount(), cpGUI.getTimed());
                if (!cpModel.operatorsIsValid()) {
                    cpGUI.displayOpError();
                }
                if (!cpModel.rangeIsValid()) {
                    cpGUI.displayRangeError();
                }
                cpModel.generateProblemParameters();
            }
        });
    }
}