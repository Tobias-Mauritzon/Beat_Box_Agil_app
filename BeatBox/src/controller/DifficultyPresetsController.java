package controller;

import model.DifficultyPresets;
import model.Operator;
import view.DifficultyGUI;

public class DifficultyPresetsController implements ControllerInterface{

    DifficultyPresets difficultyPresets;
    DifficultyGUI difficultyGUI;
    Operator operator;

    public DifficultyPresetsController(DifficultyPresets difficultyPresets, DifficultyGUI difficultyGUI){

        this.difficultyPresets = difficultyPresets;
        this.difficultyGUI = difficultyGUI;

        setActions();
    }

    public void setActions(){

        difficultyGUI.getEasyButton().setOnAction(e -> difficultyPresets.easyProblem(operator));

        difficultyGUI.getMediumButton().setOnAction(e -> difficultyPresets.mediumProblem(operator));

        difficultyGUI.getHardButton().setOnAction(e -> difficultyPresets.hardProblem(operator));

    }

}
