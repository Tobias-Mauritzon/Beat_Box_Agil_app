package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
 
public class Main extends Application {
    private VBox root;
	
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
    	primaryStage.show();
    }
}