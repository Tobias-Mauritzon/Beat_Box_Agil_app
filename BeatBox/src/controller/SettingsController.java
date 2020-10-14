package controller;

import javafx.stage.Stage;
import model.SceneHandler;
import view.SettingsGUI;
import view.ThemeHandler;

public class SettingsController implements ControllerInterface{

    private final SettingsGUI settingsGUI;
    private final ThemeHandler themeHandler;
    private final SceneHandler sceneHandler;
    private final Stage stage;
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
            sceneHandler.closeScene();
        });
        settingsGUI.getToggleButton().setOnAction(e->{
            if(settingsGUI.getToggleButton().isSelected()){
                stage.setFullScreen(true);
            }else{
                stage.setFullScreen(false);
            }

        });

    }
}
