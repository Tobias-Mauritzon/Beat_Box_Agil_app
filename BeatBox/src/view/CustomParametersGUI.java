package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/***
 * Gui for customization of ProblemParameter instances.
 * @author Andreas Palmqvist
 * @author Seif Bourogaa
 */
public class CustomParametersGUI {
    private AnchorPane root;
    private CheckBox addCheckBox;
    private CheckBox subCheckBox;
    private CheckBox mulCheckBox;
    private CheckBox divCheckBox;
    private CheckBox timedCheckBox;
    private Slider termSlider;
    private Spinner minSpinner;
    private Spinner maxSpinner;
    private Button playButton;


    public CustomParametersGUI() {
        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/CustomParametersGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getGUIObjects();
    }

    public AnchorPane getRoot() {
        return root;
    }

    private void getGUIObjects() {
        addCheckBox = (CheckBox) root.lookup("#addCheckBox");
        subCheckBox = (CheckBox) root.lookup("#subCheckBox");
        mulCheckBox = (CheckBox) root.lookup("#mulCheckBox");
        divCheckBox = (CheckBox) root.lookup("#divCheckBox");
        timedCheckBox = (CheckBox) root.lookup("#timedCheckBox");
        termSlider = (Slider) root.lookup("#termSlider");
        minSpinner = (Spinner) root.lookup("#minSpinner");
        maxSpinner = (Spinner) root.lookup("#maxSpinner");
        playButton = (Button) root.lookup("#playButton");
    }
}
