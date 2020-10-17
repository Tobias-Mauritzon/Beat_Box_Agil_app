package view;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
/**
 * The view class that gets all objects from the settings menu.
 * Contains getters for each object.
 * @author Philip
 * @version 1.0
 * @since 2020-10-16
 */
public class SettingsGUI implements GUIHandler{

    private final AnchorPane root;

    private Button defaultThemeButton;
    private Button lightThemeButton;
    private Button forestThemeButton;
    private Button sandThemeButton;
    private Button backButton;
    private ToggleButton toggleButton;

    /**
     * The constructor of the SettingsGUI class, gets the root of the SettingsGUI fxml-file.
     * @param root the root of the JavaFX application
     */
    public SettingsGUI(AnchorPane root){
        this.root = root;
        getGUIObjects();
        initToggleButton();
    }

    @Override
    public void getGUIObjects() {
        defaultThemeButton = (Button) root.lookup("#defaultThemeButton");
        lightThemeButton = (Button) root.lookup("#lightThemeButton");
        forestThemeButton = (Button) root.lookup("#forestThemeButton");
        sandThemeButton = (Button) root.lookup("#sandThemeButton");
        backButton = (Button) root.lookup("#backButton");
        toggleButton = (ToggleButton) root.lookup("#toggleButton");
    }
    /**
     * Sets default color on the toggle button to red.
     */
    private void initToggleButton(){
        toggleButton.setStyle("-fx-background-color: red;");
    }

    public Button getDefaultThemeButton(){
        return defaultThemeButton;
    }

    public Button getLightThemeButton() {
        return lightThemeButton;
    }

    public Button getForestThemeButton() {
        return forestThemeButton;
    }

    public Button getSandThemeButton() {
        return sandThemeButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public ToggleButton getToggleButton() { return toggleButton; }
}
