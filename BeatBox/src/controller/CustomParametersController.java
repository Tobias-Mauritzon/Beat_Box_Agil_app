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

    public CustomParametersController(CustomParametersGUI cpGUI, CustomParametersModel cpModel) {
        this.cpGUI = cpGUI;
        this.cpModel = cpModel;
        this.cpModel.initModel(cpGUI.getOperators(), cpGUI.getRange(), cpGUI.getTermAmount(), cpGUI.getTimed());
        setActions();
    }

    private void setActions() {

    }
}