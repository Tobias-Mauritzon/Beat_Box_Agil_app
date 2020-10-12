package view;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Operator;

/***
 * A simple placeholder GUI for Difficulty for every Operator.
 *
 * @version 1.0
 * @since 2020-10-11
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class DifficultyGUI implements GUIHandler{

    private AnchorPane root;
    //How do we know what Operator we are setting the difficulty for? Placeholder.
    private Operator operator;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;

    public DifficultyGUI(AnchorPane root, Operator operator){
        this.root = root;
        this.operator = operator;
    }

    /**
     * Method to get all objects on the current GUI.
     */
    public void getGUIObjects() {

        easyButton = (Button) root.lookup("#easyButton");
        mediumButton = (Button) root.lookup("#mediumButton");
        hardButton = (Button) root.lookup("#hardButton");

    }

    /**
     * Get Easy button.
     *
     * @return Button	the Easy button.
     */
    public Button getEasyButton() {
        return easyButton;
    }


    /**
     * Get Medium button.
     *
     * @return Button	the Medium button.
     */
    public Button getMediumButton() {
        return mediumButton;
    }


    /**
     * Get Hard button.
     *
     * @return Button	the Hard button.
     */
    public Button getHardButton() {
        return hardButton;
    }


    /**
     * Get the root of the scene.
     *
     * @return AnchorPane	the root of the scene.
     */
    public AnchorPane getRoot() {
        return root;
    }

}
