package controller;

import model.DifficultyPresets;
import view.DifficultyGUI;

public class DifficultyPresetsController implements ControllerInterface{

    DifficultyPresets difficultyPresets;
    DifficultyGUI difficultyGUI;

    public DifficultyPresetsController(DifficultyPresets difficultyPresets, DifficultyGUI difficultyGUI){

        this.difficultyPresets = difficultyPresets;
        this.difficultyGUI = difficultyGUI;

        setActions();
    }

    public void setActions(){}

}
