package view;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class SettingsGUI implements GUIHandler{

    private final AnchorPane root;

    private Button defaultThemeButton;
    private Button lightThemeButton;
    private Button forestThemeButton;
    private Button sandThemeButton;
    private Button backButton;
    private ToggleButton toggleButton;

    public SettingsGUI(AnchorPane root){
        this.root = root;
        getGUIObjects();
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

    public ToggleButton getToggleButton() {
        return toggleButton;
    }
}
