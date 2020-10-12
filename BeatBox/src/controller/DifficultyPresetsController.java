package controller;

import model.DifficultyPresets;
import model.Operator;
import view.DifficultyGUI;

/***
 * A simple controller for the DifficultyGUI.
 *
 * @version 1.0
 * @since 2020-10-11
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class DifficultyPresetsController implements ControllerInterface{

    /**
     * @see #difficultyPresets  model part of the controller.
     * @see #difficultyGUI  view part of the controller.
     *
     * @see #operator Temporary. How will we know what Operator the User is viewing?
     */
    private DifficultyPresets difficultyPresets;
    private DifficultyGUI difficultyGUI;
    private Operator operator;

    /**
     * Constructor for DifficultyPresetsController.
     *
     * @param difficultyGUI   GUI for difficulty on every operator.
     * @param difficultyPresets model for difficulty.
     *
     * @see DifficultyGUI
     * @see DifficultyPresets
     * @see #setActions()
     */
    public DifficultyPresetsController(DifficultyPresets difficultyPresets, DifficultyGUI difficultyGUI){

        this.difficultyPresets = difficultyPresets;
        this.difficultyGUI = difficultyGUI;

        setActions();
    }

    /**
     * Method that defines what happens when the GUI is interacted with.
     */
    public void setActions(){

        difficultyGUI.getEasyButton().setOnAction(e -> difficultyPresets.easyProblem(operator));

        difficultyGUI.getMediumButton().setOnAction(e -> difficultyPresets.mediumProblem(operator));

        difficultyGUI.getHardButton().setOnAction(e -> difficultyPresets.hardProblem(operator));

    }

}
