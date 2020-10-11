package view;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Operator;

public class DifficultyGUI implements GUIHandler{

    private AnchorPane root;
    //Hur ska vi veta vilken Operator det är?
    private Operator operator;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;

    public DifficultyGUI(AnchorPane root, Operator operator){
        this.root = root;
        this.operator = operator;
    }

    @Override
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
