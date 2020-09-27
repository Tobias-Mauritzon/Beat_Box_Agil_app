package model;

import controller.CustomParametersController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.CustomParametersGUI;

public class TempMain extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    private AnchorPane root;
    @Override
    public void start(Stage primaryStage) {
        CustomParametersGUI customParametersGUI = new CustomParametersGUI();
        root = customParametersGUI.getRoot();
        Scene scene = new Scene(root,root.getWidth(),root.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.show();

        new CustomParametersController(customParametersGUI);
    }
}