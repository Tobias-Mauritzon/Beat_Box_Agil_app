package view;

import javafx.scene.Scene;
/**
 * ThemeHandler sets the theme on the application.
 * @author Philip
 * @version 1.0
 * @since 2020-10-16
 */
public class ThemeHandler {

    private final Scene scene;
    private String currentTheme;
    /**
     * The constructor of the ThemeHandler class, gets the scene of the main application.
     * @param root the root of the JavaFX application
     */
    public ThemeHandler(Scene scene){
        this.scene = scene;
        currentTheme = "/CSS/defaultTheme.css";
    }
    /**
     * ThemeHandler sets the theme on the application.
     * @param themeName the name of the fml-file, without .fxl in the end.
     */
    public void setTheme(String themeName){
        String newTheme = "/CSS/"+themeName+".css";
        scene.getRoot().getStylesheets().remove(currentTheme);
        scene.getRoot().getStylesheets().add(newTheme);
        scene.getRoot().applyCss();
        currentTheme = newTheme;
    }
}
