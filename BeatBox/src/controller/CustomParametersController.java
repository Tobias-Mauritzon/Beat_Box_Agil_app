package controller;

import model.Operator;
import view.CustomParametersGUI;
import model.CustomParametersModel;

import java.util.List;

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
        this.cpModel.updateModel(cpGUI.getOperators(), cpGUI.getRange(), cpGUI.getTermAmount(), cpGUI.getTimed());
        setDelegates();
    }

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