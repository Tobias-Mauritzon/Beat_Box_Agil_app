package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Label categoryLabel;

    public DifficultyGUI(AnchorPane root){
        this.root = root;
        getGUIObjects();
    }

    /**
     * Method to get all objects on the current GUI .
     */
    public void getGUIObjects() {
        easyButton = (Button) root.lookup("#easyButton");
        mediumButton = (Button) root.lookup("#mediumButton");
        hardButton = (Button) root.lookup("#hardButton");
        categoryLabel = (Label) root.lookup("#categoryLabel");
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
     * Get category label.
     *
     * @return Button	the Hard button.
     */
    public Label getCategoryLabel() {
        return categoryLabel;
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
