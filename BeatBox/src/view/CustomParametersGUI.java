package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import model.Operator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private Delegate delegate;

    public CustomParametersGUI() {
        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/CustomParametersGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getGUIObjects();

        playButton.setOnAction(e -> delegate.play());
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
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

    public List<Operator> getOperators() {
        List<Operator> operators = new ArrayList<>();
        if (addCheckBox.isSelected()) {
            operators.add(Operator.ADD);
        }
        if (subCheckBox.isSelected()) {
            operators.add(Operator.SUB);
        }
        if (mulCheckBox.isSelected()) {
            operators.add(Operator.MUL);
        }
        if (divCheckBox.isSelected()) {
            operators.add(Operator.DIV);
        }
        return operators;
    }

    public int[] getRange() {
        int range[] = new int[2];
        range[0] = (Integer) minSpinner.getValue();
        range[1] = (Integer) maxSpinner.getValue();
        return range;
    }

    public int getTermAmount() {
        return (int) termSlider.getValue();
    }

    public boolean getTimed() {
        return timedCheckBox.isSelected();
    }

    public interface Delegate {
        void play();
    }
}
