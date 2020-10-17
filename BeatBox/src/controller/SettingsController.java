package controller;

import javafx.stage.Stage;
import model.SceneHandler;
import view.SettingsGUI;
import view.ThemeHandler;
/**
 * The controller class for the settings controller.
 * @author Philip
 * @version 1.0
 * @since 2020-10-17
 *
 */
public class SettingsController implements ControllerInterface{

    private final SettingsGUI settingsGUI;
    private final ThemeHandler themeHandler;
    private final SceneHandler sceneHandler;
    private final Stage stage;

    /**
     * Constructor for the DialogMenuController.
     *
     * @param settingsGUI the view class of the settings menu.
     * @param themeHandler the theme handler class.
     * @param sceneHandler the scene handler class.
     * @param stage the main stage of the application.
     */
    public SettingsController(SettingsGUI settingsGUI, ThemeHandler themeHandler, SceneHandler sceneHandler, Stage stage){
        this.settingsGUI = settingsGUI;
        this.themeHandler = themeHandler;
        this.sceneHandler = sceneHandler;
        this.stage = stage;
        setActions();
    }

    @Override
    public void setActions() {
        settingsGUI.getDefaultThemeButton().setOnAction(e->{
            themeHandler.setTheme("defaultTheme");
        });

        settingsGUI.getLightThemeButton().setOnAction(e->{
            themeHandler.setTheme("lightTheme");
        });

        settingsGUI.getForestThemeButton().setOnAction(e->{
            themeHandler.setTheme("forestTheme");
        });

        settingsGUI.getSandThemeButton().setOnAction(e->{
            themeHandler.setTheme("sandTheme");
        });

        settingsGUI.getBackButton().setOnAction(e->{
            sceneHandler.closeScene(200);
        });

        settingsGUI.getToggleButton().setOnAction(e->{
            if(settingsGUI.getToggleButton().isSelected()){
                settingsGUI.getToggleButton().setStyle("-fx-background-color: green;");
                stage.setFullScreen(true);
            }else{
                settingsGUI.getToggleButton().setStyle("-fx-background-color: red;");
                stage.setFullScreen(false);
            }
        });
    }
}
