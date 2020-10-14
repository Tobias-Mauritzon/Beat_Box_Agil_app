package view;

import javafx.scene.Scene;

public class ThemeHandler {

    private final Scene scene;
    private String currentTheme;

    public ThemeHandler(Scene scene){
        this.scene = scene;
        currentTheme = "/CSS/defaultTheme.css";
    }

    public void setTheme(String themeName){
        String newTheme = "/CSS/"+themeName+".css";
        scene.getRoot().getStylesheets().remove(currentTheme);
        scene.getRoot().getStylesheets().add(newTheme);
        scene.getRoot().applyCss();
        currentTheme = newTheme;
    }
}
