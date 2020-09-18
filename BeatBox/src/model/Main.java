package model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import view.MenuGUI;
import controller.MenuController;
 
public class Main extends Application {
    private VBox root;
    private MenuGUI menuGUI;
    private MenuController menuCtrl;
	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	try {
    		root = (VBox) FXMLLoader.load(getClass().getResource("/view/MenuGUI.fxml"));
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	Scene scene = new Scene(root, root.getWidth(), root.getHeight());
    	primaryStage.setScene(scene);
    	primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
    	primaryStage.show();
    	
    	menuGUI = new MenuGUI(root);
    	menuCtrl = new MenuController(menuGUI);
    }
}